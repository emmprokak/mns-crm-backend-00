package com.unipi.mns.mnscrm00.mapping;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.*;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private LeadRepository leadRepository;


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
            String parentAccountId = conToBeUpdated.getAccountId();

            conToBeUpdated.setAccountId(null);
            conToBeUpdated.setAccount(null);

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
            String parentAccountId = opptyToBeUpdated.getRelatedAccountId();

            opptyToBeUpdated.setRelatedAccountId(null);
            opptyToBeUpdated.setRelatedAccount(null);

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


            parentAccountOptional.get().getOpportunities().remove(opptyToBeUpdated);
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

    public Task handleTaskParentLead(Task reqTask, Task taskToBeUpdated, Boolean isInsert) {
        if ((reqTask.getRelatedLeadId() == null || reqTask.getRelatedLeadId().isBlank()) && reqTask.getRelatedLeadId() != null) {
            String parentLeadId = taskToBeUpdated.getRelatedLeadId();

            taskToBeUpdated.setRelatedLeadId(null);
            taskToBeUpdated.setRelatedLead(null);

            Optional<Lead> parentLeadOptional = leadRepository.findById(parentLeadId);

            if (!parentLeadOptional.isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.LEAD,
                                Constants.Specifier.ID
                        )
                );
            }


            parentLeadOptional.get().getTasks().remove(taskToBeUpdated);
            leadRepository.save(parentLeadOptional.get());

            return taskToBeUpdated;
        }

        Optional<Lead> parentLeadOptional = leadRepository.findById(reqTask.getRelatedLeadId());

        if (!parentLeadOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.LEAD,
                            Constants.Specifier.ID
                    )
            );
        }

        Lead foundParentLead = parentLeadOptional.get();
        taskToBeUpdated.setRelatedLeadId(foundParentLead.getId());
        taskToBeUpdated.setRelatedLead(foundParentLead);

        if(isInsert){
            taskRepository.save(taskToBeUpdated);
        }

        foundParentLead.getTasks().add(taskToBeUpdated);
        leadRepository.save(foundParentLead);
        return taskToBeUpdated;
    }

    public Task handleTaskParentOpportunity(Task reqTask, Task taskToBeUpdated, Boolean isInsert) {
        if ((reqTask.getRelatedOpportunityId() == null || reqTask.getRelatedOpportunityId().isBlank()) && reqTask.getRelatedOpportunityId() != null) {
            String parentOpportunityId = taskToBeUpdated.getRelatedOpportunityId();

            taskToBeUpdated.setRelatedOpportunityId(null);
            taskToBeUpdated.setRelatedOpportunity(null);

            Optional<Opportunity> parentOpportunityOptional = opportunityRepository.findById(parentOpportunityId);

            if (!parentOpportunityOptional.isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.OPPORTUNITY,
                                Constants.Specifier.ID
                        )
                );
            }


            parentOpportunityOptional.get().getTasks().remove(taskToBeUpdated);
            opportunityRepository.save(parentOpportunityOptional.get());

            return taskToBeUpdated;
        }

        Optional<Opportunity> parentOpportunityOptional = opportunityRepository.findById(reqTask.getRelatedOpportunityId());

        if (!parentOpportunityOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.OPPORTUNITY,
                            Constants.Specifier.ID
                    )
            );
        }

        Opportunity foundParentOpportunity = parentOpportunityOptional.get();
        taskToBeUpdated.setRelatedOpportunityId(foundParentOpportunity.getId());
        taskToBeUpdated.setRelatedOpportunity(foundParentOpportunity);

        if(isInsert){
            taskRepository.save(taskToBeUpdated);
        }

        foundParentOpportunity.getTasks().add(taskToBeUpdated);
        opportunityRepository.save(foundParentOpportunity);
        return taskToBeUpdated;
    }

    public List<DataEntity> handleChildrenParentLead(Account acc, Contact con, Opportunity opp, Lead lead){
        acc.setRelatedLead(lead);
        return Arrays.asList(acc, con, opp);
    }

}