package com.unipi.mns.mnscrm00.mapping;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.dal.ContactRepository;
import com.unipi.mns.mnscrm00.dal.OpportunityRepository;
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

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class RelationshipHandlerHelper {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private OpportunityRepository opportunityRepository;


    public Account handleAccountParentLead(Account reqAccount, Account accToBeUpdated) {
        return accToBeUpdated;
    }

    public Account handleAccountParentAccount(Account reqAccount, Account accToBeUpdated) {
        // remove relationship
        if (reqAccount.getParentId() == null && accToBeUpdated.getParentId() != null) {
            accToBeUpdated.setParentId(null);
            accToBeUpdated.setParent(null);
            return accToBeUpdated;
        }

        // add new relationship
//        if(reqAccount.getParentId() != null && accToBeUpdated.getParentId() == null){
        Optional<Account> parentAccountOptional = accountRepository.findById(reqAccount.getParentId());

        if (!parentAccountOptional.isPresent()) {
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

    public Contact handleContactParentAccount(Contact reqContact, Contact conToBeUpdated, Boolean isInsert) {
        System.out.println("received = " + reqContact.getAccountId() + " and found = " + conToBeUpdated.getAccountId());
        if ((reqContact.getAccountId() == null || reqContact.getAccountId().isBlank()) && conToBeUpdated.getAccountId() != null) {
            conToBeUpdated.setAccountId(null);
            conToBeUpdated.setAccount(null);

            String parentAccountId = conToBeUpdated.getAccountId();

            Optional<Account> parentAccountOptional = accountRepository.findById(parentAccountId);

            if (!parentAccountOptional.isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.ACCOUNT,
                                Constants.Specifier.ID
                        )
                );
            }


            parentAccountOptional.get().getContacts().remove(conToBeUpdated);
            accountRepository.save(parentAccountOptional.get());


            return conToBeUpdated;
        }

        Optional<Account> parentAccountOptional = accountRepository.findById(reqContact.getAccountId());

        if (!parentAccountOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Account foundParentAccount = parentAccountOptional.get();
        conToBeUpdated.setAccountId(foundParentAccount.getId());
        conToBeUpdated.setAccount(foundParentAccount);

        if(isInsert){
            contactRepository.save(conToBeUpdated);
        }

        foundParentAccount.getContacts().add(conToBeUpdated);
        accountRepository.save(foundParentAccount);
        return conToBeUpdated;
    }

    public Opportunity handleOpportunityParentAccount(Opportunity reqOpportunity, Opportunity opptyToBeUpdated, Boolean isInsert) {
        if ((reqOpportunity.getRelatedAccountId() == null || reqOpportunity.getRelatedAccountId().isBlank()) && opptyToBeUpdated.getRelatedAccountId() != null) {
            opptyToBeUpdated.setRelatedAccountId(null);
            opptyToBeUpdated.setRelatedAccountId(null);

            String parentAccountId = opptyToBeUpdated.getRelatedAccountId();

            Optional<Account> parentAccountOptional = accountRepository.findById(parentAccountId);

            if (!parentAccountOptional.isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.ACCOUNT,
                                Constants.Specifier.ID
                        )
                );
            }


            parentAccountOptional.get().getContacts().remove(opptyToBeUpdated);
            accountRepository.save(parentAccountOptional.get());


            return opptyToBeUpdated;
        }

        Optional<Account> parentAccountOptional = accountRepository.findById(reqOpportunity.getRelatedAccountId());

        if (!parentAccountOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Account foundParentAccount = parentAccountOptional.get();
        opptyToBeUpdated.setRelatedAccountId(foundParentAccount.getId());
        opptyToBeUpdated.setRelatedAccount(foundParentAccount);

        if(isInsert){
            opportunityRepository.save(opptyToBeUpdated);
        }

        foundParentAccount.getOpportunities().add(opptyToBeUpdated);
        accountRepository.save(foundParentAccount);
        return opptyToBeUpdated;
    }

    public List<DataEntity> handleChildrenParentLead(Account acc, Contact con, Opportunity opp, Lead lead){
        acc.setRelatedLead(lead);
        return Arrays.asList(acc, con, opp);
    }

}