package com.unipi.mns.mnscrm00.dto.simples;

import com.unipi.mns.mnscrm00.dto.abstracts.LeadDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.OpportunityDTO;
import com.unipi.mns.mnscrm00.dto.minimals.TaskDTOMinimal;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;

import java.time.LocalDateTime;
import java.util.Date;

public class TaskDTOSimple extends TaskDTOMinimal {

    private LeadDTO relatedLead;
    private OpportunityDTO relatedOpportunity;

    public TaskDTOSimple(Date dueDate, String id, String name, String reason, String status, String type, Lead lead, Opportunity opportunity, LocalDateTime created, LocalDateTime modified) {
        super(dueDate, id, name, reason, status, type, lead, opportunity, created, modified);

        if(lead != null){
            this.relatedLead = lead.toDTOMinimal();
        }
        if(opportunity != null){
            this.relatedOpportunity = opportunity.toDTOMinimal();
        }
    }

    public TaskDTOSimple(){}

    public LeadDTO getRelatedLead() {
        return relatedLead;
    }

    public void setRelatedLead(LeadDTO relatedLead) {
        this.relatedLead = relatedLead;
    }

    public OpportunityDTO getRelatedOpportunity() {
        return relatedOpportunity;
    }

    public void setRelatedOpportunity(OpportunityDTO relatedOpportunity) {
        this.relatedOpportunity = relatedOpportunity;
    }
}
