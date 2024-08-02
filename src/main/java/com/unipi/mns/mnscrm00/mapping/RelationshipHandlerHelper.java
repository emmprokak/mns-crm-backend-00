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
public class RelationshipHandlerHelper {
    @Autowired
    private AccountRepository accountRepository;


    public Account handleAccountParentLead(Account reqAccount, Account accToBeUpdated){
       return accToBeUpdated;
    }

    public Account handleAccountParentAccount(Account reqAccount, Account accToBeUpdated){
        // remove relationship
        if(reqAccount.getParentId() == null && accToBeUpdated.getParentId() != null){
            accToBeUpdated.setParentId(null);
            return accToBeUpdated;
        }

        // add new relationship
//        if(reqAccount.getParentId() != null && accToBeUpdated.getParentId() == null){
        Optional<Account> parentAccountOptional = accountRepository.findById(reqAccount.getParentId());

        if(!parentAccountOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }
        Account foundParentAccount = parentAccountOptional.get();
        accToBeUpdated.setParentId(foundParentAccount.getId());
        accToBeUpdated.setParent(foundParentAccount);

        return accToBeUpdated;
    }
}
