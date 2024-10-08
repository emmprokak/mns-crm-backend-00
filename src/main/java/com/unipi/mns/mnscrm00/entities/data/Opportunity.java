package com.unipi.mns.mnscrm00.entities.data;

import com.unipi.mns.mnscrm00.dto.abstracts.OpportunityDTO;
import com.unipi.mns.mnscrm00.dto.completes.OpportunityDTOComplete;
import com.unipi.mns.mnscrm00.dto.minimals.OpportunityDTOMinimal;
import com.unipi.mns.mnscrm00.dto.simples.OpportunityDTOSimple;
import com.unipi.mns.mnscrm00.entities.abstracts.ChildEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.ParentEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.Sendable;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Opportunity_ent")
public class Opportunity implements Sendable<OpportunityDTO>, DataEntity, ChildEntity, ParentEntity {
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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;

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
        return new OpportunityDTOSimple(comments, description, expectedRevenue, id, relatedAccountId, status, title, type, relatedAccount, created, modified);
    }

    @Override
    public OpportunityDTO toDTOComplete() {
        return new OpportunityDTOComplete(comments, description, expectedRevenue, id, relatedAccountId, status, title, type, relatedAccount, created, modified, tasks);
    }

    @Override
    public OpportunityDTO toDTOMinimal() {
        return new OpportunityDTOMinimal(comments, description, expectedRevenue, id, relatedAccountId, status, title, type, relatedAccount, created, modified);
    }

    @Override
    public <P> String getParentId(Class<P> entityType) {
        if(entityType == Account.class){
            return relatedAccountId;
        }

        return null;
    }

    @Override
    public <P> P getParent(Class<P> entityType) {
        if(entityType == Account.class){
            return (P) relatedAccount;
        }

        return null;
    }

    @Override
    public <P> void setParent(Class<P> entityType, P parent) {
        if(entityType == Account.class){
            this.relatedAccount = (Account) parent;
            if(parent != null){
                this.relatedAccountId =  ((Account) parent).getId();
            }else{
                this.relatedAccountId = null;
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
            tasks.remove((Task) child);
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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
