package com.unipi.mns.mnscrm00.mapping;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
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

    public Account mapAccountParents(Account reqAcc, Account accToBeUpdated){
        Account result = accToBeUpdated;

        if(!StringUtil.stringsAreEqual(reqAcc.getParentId(), accToBeUpdated.getParentId())){
            result = relHandlerHelper.handleAccountParentAccount(reqAcc, accToBeUpdated);
        }

        if(!StringUtil.stringsAreEqual(reqAcc.getRelatedLeadId(), accToBeUpdated.getRelatedLeadId())){
            result = relHandlerHelper.handleAccountParentLead(reqAcc, accToBeUpdated);
        }

        return result;
    }

    public Contact mapContactParents(Contact reqContact, Contact conToBeUpdated, Boolean isInsert){
        if(!StringUtil.stringsAreEqual(reqContact.getAccountId(), conToBeUpdated.getAccountId())){
            conToBeUpdated = relHandlerHelper.handleContactParentAccount(reqContact, conToBeUpdated, isInsert);
        }

        return conToBeUpdated;
    }

    public Opportunity mapOpportunityParents(Opportunity reqOppty, Opportunity opptyToBeUpdated, Boolean isInsert){
        if(!StringUtil.stringsAreEqual(reqOppty.getRelatedAccountId(), opptyToBeUpdated.getRelatedAccountId())){
            opptyToBeUpdated = relHandlerHelper.handleOpportunityParentAccount(reqOppty, opptyToBeUpdated, isInsert);
        }

        return opptyToBeUpdated;
    }

    public Task mapTaskParents(Task reqTask, Task taskToBeUpdated, Boolean isInsert){
        if(!StringUtil.stringsAreEqual(reqTask.getRelatedLeadId(), taskToBeUpdated.getRelatedLeadId())){
            taskToBeUpdated = relHandlerHelper.handleTaskParentLead(reqTask, taskToBeUpdated, isInsert);
        }

        if(!StringUtil.stringsAreEqual(reqTask.getRelatedOpportunityId(), taskToBeUpdated.getRelatedOpportunityId())){
            taskToBeUpdated = relHandlerHelper.handleTaskParentOpportunity(reqTask, taskToBeUpdated, isInsert);
        }

        return taskToBeUpdated;
    }

    public Case mapCaseParents(Case reqCase, Case caseToBeUpdated, Boolean isInsert){
        if(!StringUtil.stringsAreEqual(reqCase.getRelatedAccountId(), caseToBeUpdated.getRelatedAccountId())){
            caseToBeUpdated = relHandlerHelper.handleCaseParentAccount(reqCase, caseToBeUpdated, isInsert);
        }

        if(!StringUtil.stringsAreEqual(reqCase.getRelatedContactId(), caseToBeUpdated.getRelatedContactId())){
            caseToBeUpdated = relHandlerHelper.handleCaseParentContact(reqCase, caseToBeUpdated, isInsert);
        }

        return caseToBeUpdated;
    }

    public VoiceCall mapVoiceCallParents(VoiceCall reqVoiceCall, VoiceCall voiceCallToBeUpdated, Boolean isInsert){
        if(!StringUtil.stringsAreEqual(reqVoiceCall.getRelatedAccountId(), voiceCallToBeUpdated.getRelatedAccountId())){
            voiceCallToBeUpdated = relHandlerHelper.handleVoiceCallParentAccount(reqVoiceCall, voiceCallToBeUpdated, isInsert);
        }

        if(!StringUtil.stringsAreEqual(reqVoiceCall.getRelatedCaseId(), voiceCallToBeUpdated.getRelatedCaseId())){
            voiceCallToBeUpdated = relHandlerHelper.handleVoiceCallParentCase(reqVoiceCall, voiceCallToBeUpdated, isInsert);
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
