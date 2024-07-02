package com.unipi.mns.mnscrm00.entities.data;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Table(name="Opportunity")
public class Opportunity {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account relatedAccount;

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

    @Column(name="expected_revenue")
    private String expectedRevenue;

    public Opportunity(String comments, String expectedRevenue, String id, Account relatedAccount, String status, String title, String type) {
        this.comments = comments;
        this.expectedRevenue = expectedRevenue;
        this.id = id;
        this.relatedAccount = relatedAccount;
        this.status = status;
        this.title = title;
        this.type = type;
    }

    public Opportunity() {}

    public List<Note> getNotes() {
        return notes;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getExpectedRevenue() {
        return expectedRevenue;
    }

    public void setExpectedRevenue(String expectedRevenue) {
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
