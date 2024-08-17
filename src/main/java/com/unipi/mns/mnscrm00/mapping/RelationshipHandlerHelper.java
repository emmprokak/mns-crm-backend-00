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
    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private VoiceCallRepository voiceCallRepository;


    public Account handleAccountParentLead(Account reqAccount, Account accToBeUpdated) {
        return accToBeUpdated;
    }

    @Transactional
    public Account handleAccountParentAccount(Account reqAccount, Account accToBeUpdated) {
        String newParentAccountId = reqAccount.getParentId();
        String oldParentAccountId = accToBeUpdated.getParentId();

        
        if (!StringUtil.stringIsEmptyOrNull(oldParentAccountId) && !StringUtil.stringsAreEqual(oldParentAccountId, newParentAccountId)) {
            Optional<Account> oldParentAccountOptional = accountRepository.findById(oldParentAccountId);
            if (oldParentAccountOptional.isPresent()) {
                Account oldParentAccount = oldParentAccountOptional.get();
                oldParentAccount.getChildren().remove(accToBeUpdated);
                accountRepository.save(oldParentAccount);
            }
        }

        if (StringUtil.stringIsEmptyOrNull(newParentAccountId)) {
            accToBeUpdated.setParent(null);
            accToBeUpdated.setParentId(null);

            return accountRepository.save(accToBeUpdated);
        }

        Optional<Account> newParentAccountOptional = accountRepository.findById(newParentAccountId);
        if (!newParentAccountOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Account parentAccountFound = newParentAccountOptional.get();
        accToBeUpdated.setParentId(parentAccountFound.getId());
        accToBeUpdated.setParent(parentAccountFound);

        if (!parentAccountFound.getChildren().contains(accToBeUpdated)) {
            parentAccountFound.getChildren().add(accToBeUpdated);
        }

        //TODO: check if isInsert is required

        accountRepository.save(parentAccountFound);
        return accountRepository.save(accToBeUpdated);
    }

    @Transactional
    public Contact handleContactParentAccount(Contact reqContact, Contact conToBeUpdated, Boolean isInsert) {
        // we might want this
//        if ((reqContact.getAccountId() == null || reqContact.getAccountId().isBlank()) && conToBeUpdated.getAccountId() != null) {
//            String parentAccountId = conToBeUpdated.getAccountId();
//
//            conToBeUpdated.setAccountId(null);
//            conToBeUpdated.setAccount(null);
//
//            Optional<Account> parentAccountOptional = accountRepository.findById(parentAccountId);
//
//            if (!parentAccountOptional.isPresent()) {
//                throw new ResponseStatusException(
//                        HttpStatus.BAD_REQUEST,
//                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
//                                Constants.Entity.ACCOUNT,
//                                Constants.Specifier.ID
//                        )
//                );
//            }
//
//
//            parentAccountOptional.get().getContacts().remove(conToBeUpdated);
//            accountRepository.save(parentAccountOptional.get());
//            return conToBeUpdated;
//        }

        String newParentAccountId = reqContact.getAccountId();
        String oldParentAccountId = conToBeUpdated.getAccountId();

        
        if (!StringUtil.stringIsEmptyOrNull(oldParentAccountId) && !StringUtil.stringsAreEqual(oldParentAccountId, newParentAccountId)) {
            Optional<Account> oldParentAccountOptional = accountRepository.findById(oldParentAccountId);
            if (oldParentAccountOptional.isPresent()) {
                Account oldParentAccount = oldParentAccountOptional.get();
                oldParentAccount.getContacts().remove(conToBeUpdated);
                accountRepository.save(oldParentAccount);
            }
        }

        if (StringUtil.stringIsEmptyOrNull(newParentAccountId)) {
            conToBeUpdated.setAccountId(null);
            conToBeUpdated.setAccount(null);

            return contactRepository.save(conToBeUpdated);
        }

        Optional<Account> newParentAccountOptional = accountRepository.findById(newParentAccountId);
        if (!newParentAccountOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Account parentAccountFound = newParentAccountOptional.get();
        conToBeUpdated.setAccountId(parentAccountFound.getId());
        conToBeUpdated.setAccount(parentAccountFound);

        if (!parentAccountFound.getContacts().contains(conToBeUpdated)) {
            parentAccountFound.getContacts().add(conToBeUpdated);
        }

        if(isInsert){
            contactRepository.save(conToBeUpdated);
        }

        accountRepository.save(parentAccountFound);
        return contactRepository.save(conToBeUpdated);
    }

    @Transactional
    public Opportunity handleOpportunityParentAccount(Opportunity reqOpportunity, Opportunity opptyToBeUpdated, Boolean isInsert) {
        String newParentAccountId = reqOpportunity.getRelatedAccountId();
        String oldParentAccountId = opptyToBeUpdated.getRelatedAccountId();

        
        if (!StringUtil.stringIsEmptyOrNull(oldParentAccountId) && !StringUtil.stringsAreEqual(oldParentAccountId, newParentAccountId)) {
            Optional<Account> oldParentAccountOptional = accountRepository.findById(oldParentAccountId);
            if (oldParentAccountOptional.isPresent()) {
                Account oldParentAccount = oldParentAccountOptional.get();
                oldParentAccount.getOpportunities().remove(opptyToBeUpdated);
                accountRepository.save(oldParentAccount);
            }
        }

        if (StringUtil.stringIsEmptyOrNull(newParentAccountId)) {
            opptyToBeUpdated.setRelatedAccountId(null);
            opptyToBeUpdated.setRelatedAccount(null);

            return opportunityRepository.save(opptyToBeUpdated);
        }

        Optional<Account> newParentAccountOptional = accountRepository.findById(newParentAccountId);
        if (!newParentAccountOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Account parentAccountFound = newParentAccountOptional.get();
        opptyToBeUpdated.setRelatedAccountId(parentAccountFound.getId());
        opptyToBeUpdated.setRelatedAccount(parentAccountFound);

        if (!parentAccountFound.getOpportunities().contains(opptyToBeUpdated)) {
            parentAccountFound.getOpportunities().add(opptyToBeUpdated);
        }

        if(isInsert){
            opportunityRepository.save(opptyToBeUpdated);
        }

        accountRepository.save(parentAccountFound);
        return opportunityRepository.save(opptyToBeUpdated);
    }

    @Transactional
    public Task handleTaskParentLead(Task reqTask, Task taskToBeUpdated, Boolean isInsert) {
        String newParentLeadId = reqTask.getRelatedLeadId();
        String oldParentLeadId = taskToBeUpdated.getRelatedLeadId();

        
        if (!StringUtil.stringIsEmptyOrNull(oldParentLeadId) && !StringUtil.stringsAreEqual(oldParentLeadId, newParentLeadId)) {
            Optional<Lead> oldParentLeadOptional = leadRepository.findById(oldParentLeadId);
            if (oldParentLeadOptional.isPresent()) {
                Lead oldParentLead = oldParentLeadOptional.get();
                oldParentLead.getTasks().remove(taskToBeUpdated);
                leadRepository.save(oldParentLead);
            }
        }

        if (StringUtil.stringIsEmptyOrNull(newParentLeadId)) {
            taskToBeUpdated.setRelatedLeadId(null);
            taskToBeUpdated.setRelatedLead(null);

            return taskRepository.save(taskToBeUpdated);
        }

        Optional<Lead> newParentLeadOptional = leadRepository.findById(newParentLeadId);
        if (!newParentLeadOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Lead parentLeadFound = newParentLeadOptional.get();
        taskToBeUpdated.setRelatedLeadId(parentLeadFound.getId());
        taskToBeUpdated.setRelatedLead(parentLeadFound);

        if (!parentLeadFound.getTasks().contains(taskToBeUpdated)) {
            parentLeadFound.getTasks().add(taskToBeUpdated);
        }

        if(isInsert){
            taskRepository.save(taskToBeUpdated);
        }

        leadRepository.save(parentLeadFound);
        return taskRepository.save(taskToBeUpdated);
    }

    @Transactional
    public Task handleTaskParentOpportunity(Task reqTask, Task taskToBeUpdated, Boolean isInsert) {
        String newParentOpportunityId = reqTask.getRelatedOpportunityId();
        String oldParentOpportunityId = taskToBeUpdated.getRelatedOpportunityId();

        
        if (!StringUtil.stringIsEmptyOrNull(oldParentOpportunityId) && !StringUtil.stringsAreEqual(oldParentOpportunityId, newParentOpportunityId)) {
            Optional<Opportunity> oldParentOpportunityOptional = opportunityRepository.findById(oldParentOpportunityId);
            if (oldParentOpportunityOptional.isPresent()) {
                Opportunity oldParentOpportunity = oldParentOpportunityOptional.get();
                oldParentOpportunity.getTasks().remove(taskToBeUpdated);
                opportunityRepository.save(oldParentOpportunity);
            }
        }

        if (StringUtil.stringIsEmptyOrNull(newParentOpportunityId)) {
            taskToBeUpdated.setRelatedOpportunityId(null);
            taskToBeUpdated.setRelatedOpportunity(null);

            return taskRepository.save(taskToBeUpdated);
        }

        Optional<Opportunity> newParentOpportunityOptional = opportunityRepository.findById(newParentOpportunityId);
        if (!newParentOpportunityOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Opportunity parentOpportunityFound = newParentOpportunityOptional.get();
        taskToBeUpdated.setRelatedOpportunityId(parentOpportunityFound.getId());
        taskToBeUpdated.setRelatedOpportunity(parentOpportunityFound);

        if (!parentOpportunityFound.getTasks().contains(taskToBeUpdated)) {
            parentOpportunityFound.getTasks().add(taskToBeUpdated);
        }

        if(isInsert){
            taskRepository.save(taskToBeUpdated);
        }

        opportunityRepository.save(parentOpportunityFound);
        return taskRepository.save(taskToBeUpdated);
    }

    @Transactional
    public Case handleCaseParentAccount(Case reqCase, Case caseToBeUpdated, Boolean isInsert) {
        String newParentAccountId = reqCase.getRelatedAccountId();
        String oldParentAccountId = caseToBeUpdated.getRelatedAccountId();

        
        if (!StringUtil.stringIsEmptyOrNull(oldParentAccountId) && !StringUtil.stringsAreEqual(oldParentAccountId, newParentAccountId)) {
            Optional<Account> oldParentAccountOptional = accountRepository.findById(oldParentAccountId);
            if (oldParentAccountOptional.isPresent()) {
                Account oldParentAccount = oldParentAccountOptional.get();
                oldParentAccount.getCases().remove(caseToBeUpdated);
                accountRepository.save(oldParentAccount);
            }
        }

        if (StringUtil.stringIsEmptyOrNull(newParentAccountId)) {
            caseToBeUpdated.setRelatedAccountId(null);
            caseToBeUpdated.setRelatedAccount(null);

            return caseRepository.save(caseToBeUpdated);
        }

        Optional<Account> newParentAccountOptional = accountRepository.findById(newParentAccountId);
        if (!newParentAccountOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Account parentAccountFound = newParentAccountOptional.get();
        caseToBeUpdated.setRelatedAccountId(parentAccountFound.getId());
        caseToBeUpdated.setRelatedAccount(parentAccountFound);

        if (!parentAccountFound.getCases().contains(caseToBeUpdated)) {
            parentAccountFound.getCases().add(caseToBeUpdated);
        }

        if(isInsert){
            caseRepository.save(caseToBeUpdated);
        }

        accountRepository.save(parentAccountFound);
        return caseRepository.save(caseToBeUpdated);
    }

    @Transactional
    public Case handleCaseParentContact(Case reqCase, Case caseToBeUpdated, Boolean isInsert) {
        String newParentContactId = reqCase.getRelatedContactId();
        String oldParentContactId = caseToBeUpdated.getRelatedContactId();

        
        if (!StringUtil.stringIsEmptyOrNull(oldParentContactId) && !StringUtil.stringsAreEqual(oldParentContactId, newParentContactId)) {
            Optional<Contact> oldParentContactOptional = contactRepository.findById(oldParentContactId);
            if (oldParentContactOptional.isPresent()) {
                Contact oldParentContact = oldParentContactOptional.get();
                oldParentContact.getCases().remove(caseToBeUpdated);
                contactRepository.save(oldParentContact);
            }
        }

        if (StringUtil.stringIsEmptyOrNull(newParentContactId)) {
            caseToBeUpdated.setRelatedContact(null);
            caseToBeUpdated.setRelatedContactId(null);

            return caseRepository.save(caseToBeUpdated);
        }

        Optional<Contact> newParentContactOptional = contactRepository.findById(newParentContactId);
        if (!newParentContactOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Contact parentContactFound = newParentContactOptional.get();
        caseToBeUpdated.setRelatedContactId(parentContactFound.getId());
        caseToBeUpdated.setRelatedContact(parentContactFound);

        if (!parentContactFound.getCases().contains(caseToBeUpdated)) {
            parentContactFound.getCases().add(caseToBeUpdated);
        }

        if(isInsert){
            caseRepository.save(caseToBeUpdated);
        }

        contactRepository.save(parentContactFound);
        return caseRepository.save(caseToBeUpdated);
    }

    @Transactional
    public VoiceCall handleVoiceCallParentAccount(VoiceCall reqVoiceCall, VoiceCall voiceCallToBeUpdated, Boolean isInsert) {
        String newParentAccountId = reqVoiceCall.getRelatedAccountId();
        String oldParentAccountId = voiceCallToBeUpdated.getRelatedAccountId();

        
        if (!StringUtil.stringIsEmptyOrNull(oldParentAccountId) && !StringUtil.stringsAreEqual(oldParentAccountId, newParentAccountId)) {
            Optional<Account> oldParentAccountOptional = accountRepository.findById(oldParentAccountId);
            if (oldParentAccountOptional.isPresent()) {
                Account oldParentAccount = oldParentAccountOptional.get();
                oldParentAccount.getCalls().remove(voiceCallToBeUpdated);
                accountRepository.save(oldParentAccount);
            }
        }

        if (StringUtil.stringIsEmptyOrNull(newParentAccountId)) {
            voiceCallToBeUpdated.setRelatedAccountId(null);
            voiceCallToBeUpdated.setRelatedAccount(null);

            return voiceCallRepository.save(voiceCallToBeUpdated);
        }

        Optional<Account> newParentAccountOptional = accountRepository.findById(newParentAccountId);
        if (!newParentAccountOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Account parentAccountFound = newParentAccountOptional.get();
        voiceCallToBeUpdated.setRelatedAccountId(parentAccountFound.getId());
        voiceCallToBeUpdated.setRelatedAccount(parentAccountFound);

        if (!parentAccountFound.getCalls().contains(voiceCallToBeUpdated)) {
            parentAccountFound.getCalls().add(voiceCallToBeUpdated);
        }

        if(isInsert){
            voiceCallRepository.save(voiceCallToBeUpdated);
        }

        accountRepository.save(parentAccountFound);
        return voiceCallRepository.save(voiceCallToBeUpdated);
    }

    @Transactional
    public VoiceCall handleVoiceCallParentCase(VoiceCall reqVoiceCall, VoiceCall voiceCallToBeUpdated, Boolean isInsert) {
        String newParentCaseId = reqVoiceCall.getRelatedCaseId();
        String oldParentCaseId = voiceCallToBeUpdated.getRelatedCaseId();

        if (!StringUtil.stringIsEmptyOrNull(oldParentCaseId) && !StringUtil.stringsAreEqual(oldParentCaseId, newParentCaseId)) {
            Optional<Case> oldParentCaseOptional = caseRepository.findById(oldParentCaseId);
            if (oldParentCaseOptional.isPresent()) {
                Case oldParentCase = oldParentCaseOptional.get();
                oldParentCase.getCalls().remove(voiceCallToBeUpdated);
                caseRepository.save(oldParentCase);
            }
        }

        if (StringUtil.stringIsEmptyOrNull(newParentCaseId)) {
            voiceCallToBeUpdated.setRelatedCaseId(null);
            voiceCallToBeUpdated.setRelatedCase(null);

            return voiceCallRepository.save(voiceCallToBeUpdated);
        }

        Optional<Case> newParentCaseOptional = caseRepository.findById(newParentCaseId);
        if (!newParentCaseOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Case parentCaseFound = newParentCaseOptional.get();
        voiceCallToBeUpdated.setRelatedCaseId(parentCaseFound.getId());
        voiceCallToBeUpdated.setRelatedCase(parentCaseFound);

        if (!parentCaseFound.getCalls().contains(voiceCallToBeUpdated)) {
            parentCaseFound.getCalls().add(voiceCallToBeUpdated);
        }

        if(isInsert){
            voiceCallRepository.save(voiceCallToBeUpdated);
        }

        caseRepository.save(parentCaseFound);
        return voiceCallRepository.save(voiceCallToBeUpdated);
    }

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
}
