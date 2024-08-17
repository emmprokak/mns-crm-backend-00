package com.unipi.mns.mnscrm00.triggers.delete;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.dal.ContactRepository;
import com.unipi.mns.mnscrm00.dal.LeadRepository;
import com.unipi.mns.mnscrm00.dal.OpportunityRepository;
import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class DeleteTrigger {

   @Autowired
   DeleteTriggerHelper deleteTriggerHelper;

   public Account handleAccountDelete(Account account){
      account = deleteTriggerHelper.handleAccountParentReferences(account);
      account = deleteTriggerHelper.handleAccountChildrenReferences(account);
      return account;
   }

   public Case handleCaseDelete(Case caseEntry){
      caseEntry = deleteTriggerHelper.handleCaseParentReferences(caseEntry);
      caseEntry = deleteTriggerHelper.handleCaseChildrenReferences(caseEntry);
      return caseEntry;
   }

   public Contact handleContactDelete(Contact contact){
      contact = deleteTriggerHelper.handleContactParentReferences(contact);
      contact = deleteTriggerHelper.handleContactChildrenReferences(contact);
      return contact;
   }

   public Task handleTaskDelete(Task task){
      task = deleteTriggerHelper.handleTaskParentReferences(task);
      task = deleteTriggerHelper.handleTaskChildrenReferences(task);
      return task;
   }

   public Lead handleLeadDelete(Lead lead){
      lead = deleteTriggerHelper.handleLeadParentReferences(lead);
      lead = deleteTriggerHelper.handleLeadChildrenReferences(lead);
      return lead;
   }

   public Opportunity handleOpportunityDelete(Opportunity opportunity){
      opportunity = deleteTriggerHelper.handleOpportunityParentReferences(opportunity);
      opportunity = deleteTriggerHelper.handleOpportunityChildrenReferences(opportunity);
      return opportunity;
   }

   public VoiceCall handleVoiceCallDelete(VoiceCall voiceCall){
      voiceCall = deleteTriggerHelper.handleVoiceCallParentReferences(voiceCall);
      voiceCall = deleteTriggerHelper.handleVoiceCallChildrenReferences(voiceCall);
      return voiceCall;
   }


}
