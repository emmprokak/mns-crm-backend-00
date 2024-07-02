package com.unipi.mns.mnscrm00.entities.data;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Entity
@Table(name="Task")
public class Task {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private Lead relatedLead;

    @ManyToOne
    @JoinColumn(name = "opportunity_id")
    private Opportunity relatedOpportunity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User responsible;

    @Column(name="name")
    private String name;

    @Column(name="reason")
    private String reason;

    @Column(name="due_date")
    private Date dueDate;

    @Column(name="status")
    private String status;

    @Column(name="type")
    private String type;


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

    public Task() {}

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
