package com.unipi.mns.mnscrm00.mapping;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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
}
