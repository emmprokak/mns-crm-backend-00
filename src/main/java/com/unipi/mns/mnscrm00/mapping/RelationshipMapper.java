package com.unipi.mns.mnscrm00.mapping;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.*;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
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
public class RelationshipMapper {

    @Autowired
    private RelationshipHandlerHelper relHandlerHelper;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private VoiceCallRepository voiceCallRepository;

    public Account mapAccountParents(Account reqAcc, Account accToBeUpdated){
        if(!StringUtil.stringsAreEqual(reqAcc.getParentId(), accToBeUpdated.getParentId())){
            accToBeUpdated = relHandlerHelper.handleParentChildRelationship(
                    reqAcc,
                    accToBeUpdated,
                    accountRepository,
                    accountRepository,
                    Account.class,
                    Account.class,
                    true
            );
        }
        return accToBeUpdated;
    }

    public Contact mapContactParents(Contact reqContact, Contact conToBeUpdated, Boolean isInsert){
        if(!StringUtil.stringsAreEqual(reqContact.getAccountId(), conToBeUpdated.getAccountId())){
            conToBeUpdated = relHandlerHelper.handleParentChildRelationship(
                    reqContact,
                    conToBeUpdated,
                    accountRepository,
                    contactRepository,
                    Account.class,
                    Contact.class,
                    isInsert
            );
        }

        return conToBeUpdated;
    }

    public Opportunity mapOpportunityParents(Opportunity reqOppty, Opportunity opptyToBeUpdated, Boolean isInsert){
        if(!StringUtil.stringsAreEqual(reqOppty.getRelatedAccountId(), opptyToBeUpdated.getRelatedAccountId())){
            opptyToBeUpdated = relHandlerHelper.handleParentChildRelationship(
                    reqOppty,
                    opptyToBeUpdated,
                    accountRepository,
                    opportunityRepository,
                    Account.class,
                    Opportunity.class,
                    isInsert
            );
        }

        return opptyToBeUpdated;
    }

    public Task mapTaskParents(Task reqTask, Task taskToBeUpdated, Boolean isInsert){
        if(!StringUtil.stringsAreEqual(reqTask.getRelatedLeadId(), taskToBeUpdated.getRelatedLeadId())){
            taskToBeUpdated = relHandlerHelper.handleParentChildRelationship(
                    reqTask,
                    taskToBeUpdated,
                    leadRepository,
                    taskRepository,
                    Lead.class,
                    Task.class,
                    isInsert
            );
        }

        if(!StringUtil.stringsAreEqual(reqTask.getRelatedOpportunityId(), taskToBeUpdated.getRelatedOpportunityId())){
            taskToBeUpdated = relHandlerHelper.handleParentChildRelationship(
                    reqTask,
                    taskToBeUpdated,
                    opportunityRepository,
                    taskRepository,
                    Opportunity.class,
                    Task.class,
                    isInsert
            );
        }

        return taskToBeUpdated;
    }

    public Case mapCaseParents(Case reqCase, Case caseToBeUpdated, Boolean isInsert){
        if(!StringUtil.stringsAreEqual(reqCase.getRelatedAccountId(), caseToBeUpdated.getRelatedAccountId())) {
            caseToBeUpdated = relHandlerHelper.handleParentChildRelationship(
                    reqCase,
                    caseToBeUpdated,
                    accountRepository,
                    caseRepository,
                    Account.class,
                    Case.class,
                    isInsert
            );
        }

        if(!StringUtil.stringsAreEqual(reqCase.getRelatedContactId(), caseToBeUpdated.getRelatedContactId())) {
            caseToBeUpdated = relHandlerHelper.handleParentChildRelationship(
                    reqCase,
                    caseToBeUpdated,
                    contactRepository,
                    caseRepository,
                    Contact.class,
                    Case.class,
                    isInsert
            );
        }
        return caseToBeUpdated;
    }

    public VoiceCall mapVoiceCallParents(VoiceCall reqVoiceCall, VoiceCall voiceCallToBeUpdated, Boolean isInsert){
        if(!StringUtil.stringsAreEqual(reqVoiceCall.getRelatedAccountId(), voiceCallToBeUpdated.getRelatedAccountId())){
            voiceCallToBeUpdated = relHandlerHelper.handleParentChildRelationship(
                    reqVoiceCall,
                    voiceCallToBeUpdated,
                    accountRepository,
                    voiceCallRepository,
                    Account.class,
                    VoiceCall.class,
                    isInsert
            );
        }

        if(!StringUtil.stringsAreEqual(reqVoiceCall.getRelatedCaseId(), voiceCallToBeUpdated.getRelatedCaseId())){
            voiceCallToBeUpdated = relHandlerHelper.handleParentChildRelationship(
                    reqVoiceCall,
                    voiceCallToBeUpdated,
                    caseRepository,
                    voiceCallRepository,
                    Case.class,
                    VoiceCall.class,
                    isInsert
            );
        }

        return voiceCallToBeUpdated;
    }

    public List<DataEntity> mapLeadToChildren(Account acc, Contact con, Opportunity opp, Lead lead){
            return relHandlerHelper.handleChildrenParentLead(acc, con, opp, lead);
    }

    public List<DataEntity> mapLeadConversionChildrenRelationships(Account acc, Contact con, Opportunity opp){
        return relHandlerHelper.handleLeadConversionChildrenRelationships(acc, con, opp);
    }

}
