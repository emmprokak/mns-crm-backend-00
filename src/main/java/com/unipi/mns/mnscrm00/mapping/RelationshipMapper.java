package com.unipi.mns.mnscrm00.mapping;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Component
public class RelationshipMapper {

    @Autowired
    private RelationshipHandlerHelper relHandlerHelper;

    public Account mapAccountParents(Account reqAcc, Account accToBeUpdated){
        Account result = accToBeUpdated;

        if(reqAcc.getParentId() != accToBeUpdated.getParentId()){
            result = relHandlerHelper.handleAccountParentAccount(reqAcc, accToBeUpdated);
        }

        if(reqAcc.getRelatedLeadId() != accToBeUpdated.getRelatedLeadId()){
            result = relHandlerHelper.handleAccountParentLead(reqAcc, accToBeUpdated);
        }

        return result;
    }

    public Contact mapContactParents(Contact reqContact, Contact conToBeUpdated, Boolean isInsert){
        if(reqContact.getAccountId() != conToBeUpdated.getAccountId()){
            conToBeUpdated = relHandlerHelper.handleContactParentAccount(reqContact, conToBeUpdated, isInsert);
        }

        return conToBeUpdated;
    }

    public Opportunity mapOpportunityParents(Opportunity reqOppty, Opportunity opptyToBeUpdated, Boolean isInsert){
        if(reqOppty.getRelatedAccountId() != opptyToBeUpdated.getRelatedAccountId()){
            opptyToBeUpdated = relHandlerHelper.handleOpportunityParentAccount(reqOppty, opptyToBeUpdated, isInsert);
        }

        return opptyToBeUpdated;
    }

    public List<DataEntity> mapLeadToChildren(Account acc, Contact con, Opportunity opp, Lead lead){
            return relHandlerHelper.handleChildrenParentLead(acc, con, opp, lead);
    }

}
