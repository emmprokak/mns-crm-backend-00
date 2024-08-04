package com.unipi.mns.mnscrm00.dto.abstracts;

import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;

import java.time.LocalDateTime;
import java.util.Date;

public class TaskDTO implements EntityDTO{
    private String id;
    private String name;
    private String reason;
    private Date dueDate;
    private String status;
    private String type;
    private String relatedLeadId;
    private String relatedOpportunityId;
    private LocalDateTime created;
    private LocalDateTime modified;

    public TaskDTO(Date dueDate, String id, String name, String reason, String status, String type, Lead lead, Opportunity opportunity, LocalDateTime created, LocalDateTime modified) {
        this.dueDate = dueDate;
        this.id = id;
        this.name = name;
        this.reason = reason;
        this.status = status;
        this.type = type;
        this.created = created;
        this.modified = modified;

        if(lead != null){
            this.relatedLeadId = lead.getId();
        }

        if(opportunity != null){
            this.relatedOpportunityId = opportunity.getId();
        }
    }

    public TaskDTO(){}

    public String getRelatedLeadId() {
        return relatedLeadId;
    }

    public void setRelatedLeadId(String relatedLeadId) {
        this.relatedLeadId = relatedLeadId;
    }

    public String getRelatedOpportunityId() {
        return relatedOpportunityId;
    }

    public void setRelatedOpportunityId(String relatedOpportunityId) {
        this.relatedOpportunityId = relatedOpportunityId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
