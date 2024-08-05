package com.unipi.mns.mnscrm00.dto.abstracts;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class CaseDTO implements EntityDTO{

    private String id;
    private String title;
    private String reason;
    private String source;
    private String category;
    private String severity;
    private String status;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String relatedContactId;
    private String relatedAccountId;
    private Date createdDate;
    private Date closedDate;

    public CaseDTO(String category, LocalDateTime created, String id, LocalDateTime modified, String reason, Account account, Contact contact, String severity, String source, String status, String title, Date createdDate, Date closedDate) {
        this.category = category;
        this.created = created;
        this.id = id;
        this.modified = modified;
        this.reason = reason;
        this.severity = severity;
        this.source = source;
        this.status = status;
        this.title = title;
        this.createdDate = createdDate;
        this.closedDate = closedDate;

        if(account != null){
            this.relatedAccountId = account.getId();
        }
        if(contact != null){
            this.relatedContactId = contact.getId();
        }
    }

    public CaseDTO(){}

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRelatedAccountId() {
        return relatedAccountId;
    }

    public void setRelatedAccountId(String relatedAccountId) {
        this.relatedAccountId = relatedAccountId;
    }

    public String getRelatedContactId() {
        return relatedContactId;
    }

    public void setRelatedContactId(String relatedContactId) {
        this.relatedContactId = relatedContactId;
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
