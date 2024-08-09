package com.unipi.mns.mnscrm00.mapping;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.*;
import com.unipi.mns.mnscrm00.entities.abstracts.ChildEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.ParentEntity;
import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class RelationshipHandlerHelper {

    public List<DataEntity> handleChildrenParentLead(Account acc, Contact con, Opportunity opp, Lead lead){
        acc.setRelatedLead(lead);
        acc.setRelatedLeadId(lead.getId());
        return Arrays.asList(acc, con, opp);
    }

    public List<DataEntity> handleLeadConversionChildrenRelationships(Account acc, Contact con, Opportunity opp){
        acc.getContacts().add(con);
        acc.getOpportunities().add(opp);

        con.setAccount(acc);
        con.setAccountId(acc.getId());

        opp.setRelatedAccount(acc);
        opp.setRelatedAccountId(acc.getId());

        return Arrays.asList(acc, con, opp);
    }

    @Transactional
    public <P extends ParentEntity, C extends ChildEntity> C handleParentChildRelationship(
            C reqChild,
            C childToBeUpdated,
            JpaRepository<P, String> parentRepository,
            JpaRepository<C, String> childRepository,
            Class<P> parentType,
            Class<C> childType,
            Boolean isInsert
    ) {
        String newParentId = reqChild.getParentId(parentType);
        String oldParentId = childToBeUpdated.getParentId(parentType);

        if (oldParentId != null && !StringUtil.stringsAreEqual(oldParentId, newParentId)) {
            Optional<P> oldParentOptional = parentRepository.findById(oldParentId);
            if (oldParentOptional.isPresent()) {
                P oldParent = oldParentOptional.get();
                oldParent.removeChild(childType, childToBeUpdated);
                parentRepository.save(oldParent);
            }
        }

        if (newParentId == null) {
            childToBeUpdated.setParent(parentType, null);
//            childToBeUpdated.setParentId(parentType, null);

            return childRepository.save(childToBeUpdated);
        }

        Optional<P> newParentOptional = parentRepository.findById(newParentId);
        if (!newParentOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Parent entity not found"
            );
        }

        P newParent = newParentOptional.get();
        childToBeUpdated.setParent(parentType, newParent);

        if (!newParent.getChildrenEntities(parentType).contains(childToBeUpdated)) {
            newParent.addChild(childType, childToBeUpdated);
        }

        if (isInsert) {
            childRepository.save(childToBeUpdated);
        }

        parentRepository.save(newParent);
        return childRepository.save(childToBeUpdated);
    }
}
