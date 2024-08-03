package com.unipi.mns.mnscrm00.entities.data;

import com.unipi.mns.mnscrm00.dto.abstracts.OpportunityDTO;
import com.unipi.mns.mnscrm00.dto.completes.OpportunityDTOComplete;
import com.unipi.mns.mnscrm00.dto.minimals.OpportunityDTOMinimal;
import com.unipi.mns.mnscrm00.dto.simples.OpportunityDTOSimple;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.Sendable;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Opportunity_ent")
public class Opportunity implements Sendable<OpportunityDTO>, DataEntity {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account relatedAccount;

    @Column(name="account_id_txt")
    private String relatedAccountId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Note> notes;

    @Column(name="title")
    private String title;

    @Column(name="status")
    private String status;

    @Column(name="type")
    private String type;

    @Column(name="comments")
    private String comments;

    @Column(name="description")
    private String description;

    @Column(name="expected_revenue")
    private double expectedRevenue;

    public Opportunity(String comments, double expectedRevenue, String id, Account relatedAccount, String status, String title, String type, String description) {
        this.comments = comments;
        this.expectedRevenue = expectedRevenue;
        this.id = id;
        this.relatedAccount = relatedAccount;
        this.status = status;
        this.title = title;
        this.type = type;
        this.description = description;
    }

    public Opportunity() {}

    @Override
    public OpportunityDTO toDTOSimple() {
        return new OpportunityDTOSimple(comments, description, expectedRevenue, id, relatedAccountId, status, title, type, relatedAccount);
    }

    @Override
    public OpportunityDTO toDTOComplete() {
        return new OpportunityDTOComplete(comments, description, expectedRevenue, id, relatedAccountId, status, title, type, relatedAccount, tasks, notes);
    }

    @Override
    public OpportunityDTO toDTOMinimal() {
        return new OpportunityDTOMinimal(comments, description, expectedRevenue, id, relatedAccountId, status, title, type, relatedAccount);
    }

    public List<Note> getNotes() {
        return notes;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelatedAccountId() {
        return relatedAccountId;
    }

    public void setRelatedAccountId(String relatedAccountId) {
        this.relatedAccountId = relatedAccountId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getExpectedRevenue() {
        return expectedRevenue;
    }

    public void setExpectedRevenue(double expectedRevenue) {
        this.expectedRevenue = expectedRevenue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getRelatedAccount() {
        return relatedAccount;
    }

    public void setRelatedAccount(Account relatedAccount) {
        this.relatedAccount = relatedAccount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
