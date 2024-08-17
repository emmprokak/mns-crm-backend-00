package com.unipi.mns.mnscrm00.triggers.delete;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.*;
import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Component
public class DeleteTriggerHelper {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private LeadRepository leadRepository;

    public Account handleAccountParentReferences(Account account){
        if(!StringUtil.stringIsEmptyOrNull(account.getParentId())){
            Optional<Account> parentAccountOptional = accountRepository.findById(account.getParentId());

            if (!parentAccountOptional.isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.ACCOUNT,
                                Constants.Specifier.ID
                        )
                );
            }

            parentAccountOptional.get().getChildren().remove(account);
            accountRepository.save(parentAccountOptional.get());
        }

        return account;
    }

    public Account handleAccountChildrenReferences(Account account){
        List<Account> accountList = accountRepository.findByParentId(account.getId());
        for(Account acc : accountList){
            acc.setParent(null);
            acc.setParentId(null);
            accountRepository.save(acc);
        }

        List<Contact> contactList = contactRepository.findByAccountId(account.getId());
        for(Contact con : contactList){
            con.setAccount(null);
            con.setAccountId(null);
            contactRepository.save(con);
        }

        List<Opportunity> opportunityList = opportunityRepository.findByRelatedAccountId(account.getId());
        for(Opportunity opp : opportunityList){
            opp.setRelatedAccount(null);
            opp.setRelatedAccountId(null);
            opportunityRepository.save(opp);
        }

        List<Case> caseList = caseRepository.findByRelatedAccountId(account.getId());
        for(Case c : caseList){
            c.setRelatedAccount(null);
            c.setRelatedAccountId(null);
            caseRepository.save(c);
        }

        return account;
    }

    public Case handleCaseParentReferences(Case caseEntry){
        if(!StringUtil.stringIsEmptyOrNull(caseEntry.getRelatedAccountId())){
            Optional<Account> accountOptional = accountRepository.findById(caseEntry.getRelatedAccountId());

            if(!accountOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.ACCOUNT,
                                Constants.Specifier.ID
                        )
                );
            }

            accountOptional.get().getCases().remove(caseEntry);
            accountRepository.save(accountOptional.get());
        }

        if(!StringUtil.stringIsEmptyOrNull(caseEntry.getRelatedContactId())){
            Optional<Contact> contactOptional = contactRepository.findById(caseEntry.getRelatedContactId());

            if(!contactOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.CONTACT,
                                Constants.Specifier.ID
                        )
                );
            }

            contactOptional.get().getCases().remove(caseEntry);
            contactRepository.save(contactOptional.get());
        }

        return caseEntry;
    }

    public Case handleCaseChildrenReferences(Case Case){
        return Case;
    }

    public Contact handleContactParentReferences(Contact contact){
        if(!StringUtil.stringIsEmptyOrNull(contact.getAccountId())){
            Optional<Account> accountOptional = accountRepository.findById(contact.getAccountId());

            if(!accountOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.ACCOUNT,
                                Constants.Specifier.ID
                        )
                );
            }

            accountOptional.get().getContacts().remove(contact);
            accountRepository.save(accountOptional.get());
        }

        return contact;
    }

    public Contact handleContactChildrenReferences(Contact contact){
        return contact;
    }

    public Lead handleLeadParentReferences(Lead lead){
        return lead;
    }

    public Lead handleLeadChildrenReferences(Lead lead){

        List<Task> taskList = taskRepository.findByRelatedLeadId(lead.getId());
        for(Task task : taskList){
            task.setRelatedLead(null);
            task.setRelatedLeadId(null);
            taskRepository.save(task);
        }

        return lead;
    }

    public Task handleTaskParentReferences(Task task){
        if(!StringUtil.stringIsEmptyOrNull(task.getRelatedLeadId())){
            Optional<Lead> leadOptional = leadRepository.findById(task.getRelatedLeadId());

            if(!leadOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.LEAD,
                                Constants.Specifier.ID
                        )
                );
            }

            leadOptional.get().getTasks().remove(task);
            leadRepository.save(leadOptional.get());
        }

        if(!StringUtil.stringIsEmptyOrNull(task.getRelatedOpportunityId())){
            Optional<Opportunity> opportunityOptional = opportunityRepository.findById(task.getRelatedOpportunityId());

            if(!opportunityOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.OPPORTUNITY,
                                Constants.Specifier.ID
                        )
                );
            }

            opportunityOptional.get().getTasks().remove(task);
            opportunityRepository.save(opportunityOptional.get());
        }

        return task;
    }

    public Task handleTaskChildrenReferences(Task task){
        return task;
    }

    public Opportunity handleOpportunityParentReferences(Opportunity opportunity){
        if(!StringUtil.stringIsEmptyOrNull(opportunity.getRelatedAccountId())){
            Optional<Account> accountOptional = accountRepository.findById(opportunity.getRelatedAccountId());

            if(!accountOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.ACCOUNT,
                                Constants.Specifier.ID
                        )
                );
            }

            accountOptional.get().getOpportunities().remove(opportunity);
            accountRepository.save(accountOptional.get());
        }

        return opportunity;
    }

    public Opportunity handleOpportunityChildrenReferences(Opportunity opportunity){

        List<Task> taskList = taskRepository.findByRelatedOpportunityId(opportunity.getId());
        for(Task task : taskList){
            task.setRelatedOpportunity(null);
            task.setRelatedOpportunityId(null);
            taskRepository.save(task);
        }

        return opportunity;
    }

    public VoiceCall handleVoiceCallParentReferences(VoiceCall voiceCall){
        if(!StringUtil.stringIsEmptyOrNull(voiceCall.getRelatedAccountId())){
            Optional<Account> accountOptional = accountRepository.findById(voiceCall.getRelatedAccountId());

            if(!accountOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.ACCOUNT,
                                Constants.Specifier.ID
                        )
                );
            }

            accountOptional.get().getCalls().remove(voiceCall);
            accountRepository.save(accountOptional.get());
        }

        if(!StringUtil.stringIsEmptyOrNull(voiceCall.getRelatedCaseId())){
            Optional<Case> caseOptional = caseRepository.findById(voiceCall.getRelatedCaseId());

            if(!caseOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.CASE,
                                Constants.Specifier.ID
                        )
                );
            }

            caseOptional.get().getCalls().remove(voiceCall);
            caseRepository.save(caseOptional.get());
        }

        return voiceCall;
    }

    public VoiceCall handleVoiceCallChildrenReferences(VoiceCall voiceCall){
        return voiceCall;
    }
}
