package com.unipi.mns.mnscrm00.triggers.insert_update;

import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;
import com.unipi.mns.mnscrm00.mapping.RelationshipMapper;
import com.unipi.mns.mnscrm00.validations.ValidationHandler;
import com.unipi.mns.mnscrm00.validations.concretes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertUpdateTrigger {

    @Autowired
    private RelationshipMapper relationshipMapper;
    @Autowired
    private ValidationHandler validationHandler;

    public Account handleAccountEntry(Account source, Account target) throws DataValidationException {
        validationHandler.validate(source);
        target = ObjectMapper.mapAccountFields(source, target);
        target = relationshipMapper.mapAccountParents(source, target);

        return target;
    }

    public Contact handleContactEntry(Contact source, Contact target, boolean isInsert) throws DataValidationException {
        validationHandler.validate(source);
        target = ObjectMapper.mapContactFields(source, target);
        target = relationshipMapper.mapContactParents(source, target, isInsert);

        return target;
    }

    public Lead handleLeadEntry(Lead source, Lead target) throws DataValidationException {
        validationHandler.validate(source);
        target = ObjectMapper.mapLeadFields(source, target);
        // lead has no parents
        return target;
    }

    public Opportunity handleOpportunityEntry(Opportunity source, Opportunity target, boolean isInsert) throws DataValidationException {
        validationHandler.validate(source);
        target = ObjectMapper.mapOpportunityFields(source, target);
        target = relationshipMapper.mapOpportunityParents(source, target, isInsert);

        return target;
    }

    public Task handleTaskEntry(Task source, Task target, boolean isInsert) throws DataValidationException {
        validationHandler.validate(source);
        target = ObjectMapper.mapTaskFields(source, target);
        target = relationshipMapper.mapTaskParents(source, target, isInsert);

        return target;
    }

    public Case handleCaseEntry(Case source, Case target, boolean isInsert) throws DataValidationException {
        validationHandler.validate(source);
        target = ObjectMapper.mapCaseFields(source, target, isInsert);
        target = relationshipMapper.mapCaseParents(source, target, isInsert);

        return target;
    }

    public VoiceCall handleVoiceCallEntry(VoiceCall source, VoiceCall target, boolean isInsert) throws DataValidationException {
        validationHandler.validate(source);
        target = ObjectMapper.mapVoiceCallFields(source, target);
        target = relationshipMapper.mapVoiceCallParents(source, target, isInsert);

        return target;
    }
}
