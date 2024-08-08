package com.unipi.mns.mnscrm00.entities.data;

import com.unipi.mns.mnscrm00.dto.abstracts.CaseDTO;
import com.unipi.mns.mnscrm00.dto.completes.CaseDTOComplete;
import com.unipi.mns.mnscrm00.dto.minimals.CaseDTOMinimal;
import com.unipi.mns.mnscrm00.dto.simples.CaseDTOSimple;
import com.unipi.mns.mnscrm00.entities.abstracts.ChildEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.ParentEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.Sendable;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Case_ent")
public class Case implements Sendable<CaseDTO>, DataEntity, ChildEntity, ParentEntity {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account relatedAccount;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact relatedContact;

    @Column(name = "account_id_txt")
    private String relatedAccountId;

    @Column(name = "contact_id_txt")
    private String relatedContactId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User caseResponsible;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToMany(cascade = CascadeType.ALL)
    private List<VoiceCall> calls;

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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;

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

    @Override
    public CaseDTO toDTOSimple() {
        return new CaseDTOSimple(category, created, id, modified, reason, relatedAccount, relatedContact, severity, source, status, title, creationDate, closedDate);
    }

    @Override
    public CaseDTO toDTOComplete() {
        return new CaseDTOComplete(category, created, id, modified, reason, relatedAccount, relatedContact, severity, source, status, title, tasks, creationDate, closedDate, calls);
    }

    @Override
    public CaseDTO toDTOMinimal() {
        return new CaseDTOMinimal(category, created, id, modified, reason, relatedAccount, relatedContact, severity, source, status, title, creationDate, closedDate);
    }

    @Override
    public <P> String getParentId(Class<P> entityType) {
        if(entityType == Account.class){
            return relatedAccountId;
        }

        if(entityType == Contact.class){
            return relatedContactId;
        }

        return null;
    }

    @Override
    public <P> void setParentId(Class<P> entityType, P parent) {
        return;
    }

    @Override
    public <P> P getParent(Class<P> entityType) {
        if(entityType == Account.class){
            return (P) relatedAccount;
        }

        if(entityType == Contact.class){
            return (P) relatedContact;
        }

        return null;
    }

    @Override
    public <P> void setParent(Class<P> entityType, P parent) {
        if(entityType == Account.class){
            this.relatedAccount = (Account) parent;
            if(parent != null){
                this.relatedAccountId =  ((Account) parent).getId();
            }
        }

        if(entityType == Contact.class){
            this.relatedContact = (Contact) parent;
            if(parent != null) {
                this.relatedContactId = ((Contact) parent).getId();
            }
        }
    }

    @Override
    public <C> List<C> getChildrenEntities(Class<C> childType) {
        if (childType == Task.class) {
            return (List<C>) tasks;
        }

        return new ArrayList<>();
    }

    @Override
    public <C> void addChild(Class<C> childType, C child) {
        if (childType == Task.class) {
            tasks.add((Task) child);
        }
    }

    @Override
    public <C> void removeChild(Class<C> childType, C child) {
        if (childType == Task.class) {
            tasks.add((Task) child);
        }
    }

    public List<VoiceCall> getCalls() {
        return calls;
    }

    public void setCalls(List<VoiceCall> calls) {
        this.calls = calls;
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
