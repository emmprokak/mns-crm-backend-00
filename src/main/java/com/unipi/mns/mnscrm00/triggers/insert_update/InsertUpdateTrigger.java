package com.unipi.mns.mnscrm00.triggers.insert_update;

import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;
import com.unipi.mns.mnscrm00.mapping.RelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertUpdateTrigger {

    @Autowired
    private RelationshipMapper relationshipMapper;

    public Account handleAccountEntry(Account source, Account target){
        target = ObjectMapper.mapAccountFields(source, target);
        target = relationshipMapper.mapAccountParents(source, target);

        return target;
    }

    public Contact handleContactEntry(Contact source, Contact target, boolean isInsert){
        target = ObjectMapper.mapContactFields(source, target);
        target = relationshipMapper.mapContactParents(source, target, isInsert);

        return target;
    }

    public Lead handleLeadEntry(Lead source, Lead target){
        target = ObjectMapper.mapLeadFields(source, target);

        return target;
    }

    public Opportunity handleOpportunityEntry(Opportunity source, Opportunity target, boolean isInsert){
        target = ObjectMapper.mapOpportunityFields(source, target);
        target = relationshipMapper.mapOpportunityParents(source, target, isInsert);

        return target;
    }

    public Task handleTaskEntry(Task source, Task target, boolean isInsert){
        target = ObjectMapper.mapTaskFields(source, target);
        target = relationshipMapper.mapTaskParents(source, target, isInsert);

        return target;
    }
}
