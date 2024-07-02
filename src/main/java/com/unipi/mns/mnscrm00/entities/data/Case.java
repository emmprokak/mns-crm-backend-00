package com.unipi.mns.mnscrm00.entities.data;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Case")
public class Case {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account relatedAccount;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact relatedContact;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User caseResponsible;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Note> notes;

    @Column(name="title")
    private String title;

    @Column(name="reason")
    private String reason;

    @Column(name="source")
    private String source;

    @Column(name="category")
    private String category;

    @Column(name="severity")
    private String severity;

    @Column(name="status")
    private String status;

    @Column(name="created_date")
    private Date creationDate;

    @Column(name="closed_date")
    private Date closedDate;

    public Case(User caseResponsible, String category, Date closedDate, Date creationDate, String id, String reason, Account relatedAccount, Contact relatedContact, String severity, String source, String status, String title) {
        this.caseResponsible = caseResponsible;
        this.category = category;
        this.closedDate = closedDate;
        this.creationDate = creationDate;
        this.id = id;
        this.reason = reason;
        this.relatedAccount = relatedAccount;
        this.relatedContact = relatedContact;
        this.severity = severity;
        this.source = source;
        this.status = status;
        this.title = title;
    }

    public Case() {}

    public List<Note> getNotes() {
        return notes;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public User getCaseResponsible() {
        return caseResponsible;
    }

    public void setCaseResponsible(User caseResponsible) {
        this.caseResponsible = caseResponsible;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Account getRelatedAccount() {
        return relatedAccount;
    }

    public void setRelatedAccount(Account relatedAccount) {
        this.relatedAccount = relatedAccount;
    }

    public Contact getRelatedContact() {
        return relatedContact;
    }

    public void setRelatedContact(Contact relatedContact) {
        this.relatedContact = relatedContact;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
}
