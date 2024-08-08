package com.unipi.mns.mnscrm00.entities.data;

import com.unipi.mns.mnscrm00.dto.abstracts.TaskDTO;
import com.unipi.mns.mnscrm00.dto.completes.TaskDTOComplete;
import com.unipi.mns.mnscrm00.dto.minimals.TaskDTOMinimal;
import com.unipi.mns.mnscrm00.dto.simples.TaskDTOSimple;
import com.unipi.mns.mnscrm00.entities.abstracts.ChildEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.Sendable;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="Task_ent")
public class Task implements Sendable<TaskDTO>, DataEntity, ChildEntity {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private Lead relatedLead;

    @Column(name = "lead_id_txt")
    private String relatedLeadId;

    @ManyToOne
    @JoinColumn(name = "opportunity_id")
    private Opportunity relatedOpportunity;

    @Column(name = "opportunity_id_txt")
    private String relatedOpportunityId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User responsible;

    @Column(name = "name")
    private String name;

    @Column(name = "reason")
    private String reason;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;


    public Task(Date dueDate, String id, String name, String reason, Lead relatedLead, Opportunity relatedOpportunity, User responsible, String status, String type) {
        this.dueDate = dueDate;
        this.id = id;
        this.name = name;
        this.reason = reason;
        this.relatedLead = relatedLead;
        this.relatedOpportunity = relatedOpportunity;
        this.responsible = responsible;
        this.status = status;
        this.type = type;
    }

    public Task(Date dueDate, String id, String name, String reason, Lead relatedLead, Opportunity relatedOpportunity, User responsible, String status, String type, String relatedLeadId, String relatedOpportunityId) {
        this(dueDate, id, name, reason, relatedLead, relatedOpportunity, responsible, status, type);
        this.relatedLeadId = relatedLeadId;
        this.relatedOpportunityId = relatedOpportunityId;
    }

    public Task() {
    }

    @Override
    public TaskDTO toDTOSimple() {
        return new TaskDTOSimple(dueDate, id, name, reason, status, type, relatedLead, relatedOpportunity, created, modified);
    }

    @Override
    public TaskDTO toDTOComplete() {
        return new TaskDTOComplete(dueDate, id, name, reason, status, type, relatedLead, relatedOpportunity, created, modified);
    }

    @Override
    public TaskDTO toDTOMinimal() {
        return new TaskDTOMinimal(dueDate, id, name, reason, status, type, relatedLead, relatedOpportunity, created, modified);
    }

    @Override
    public <P> String getParentId(Class<P> entityType) {
        if(entityType == Lead.class){
            return relatedLeadId;
        }

        if(entityType == Opportunity.class){
            return relatedOpportunityId;
        }

        return null;
    }

    @Override
    public <P> void setParentId(Class<P> entityType, P parent) {
        return;
    }

    @Override
    public <P> P getParent(Class<P> entityType) {
        if(entityType == Lead.class){
            return (P) relatedLead;
        }

        if(entityType == Opportunity.class){
            return (P) relatedOpportunity;
        }

        return null;
    }

    @Override
    public <P> void setParent(Class<P> entityType, P parent) {
        if(entityType == Lead.class){
            this.relatedLead = (Lead) parent;
            if(parent != null){
                this.relatedLeadId =  ((Lead) parent).getId();
            }
        }

        if(entityType == Opportunity.class){
            this.relatedOpportunity = (Opportunity) parent;
            if(parent != null){
                this.relatedOpportunityId =  ((Opportunity) parent).getId();
            }
        }
    }

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

    public Lead getRelatedLead() {
        return relatedLead;
    }

    public void setRelatedLead(Lead relatedLead) {
        this.relatedLead = relatedLead;
    }

    public Opportunity getRelatedOpportunity() {
        return relatedOpportunity;
    }

    public void setRelatedOpportunity(Opportunity relatedOpportunity) {
        this.relatedOpportunity = relatedOpportunity;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
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
