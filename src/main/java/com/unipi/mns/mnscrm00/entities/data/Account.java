package com.unipi.mns.mnscrm00.entities.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.dto.completes.AccountDTOComplete;
import com.unipi.mns.mnscrm00.dto.minimals.AccountDTOMinimal;
import com.unipi.mns.mnscrm00.dto.simples.AccountDTOSimple;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.Sendable;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Account_ent")
public class Account implements Sendable<AccountDTO>, DataEntity {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Account parent;

    @OneToOne
    @JoinColumn(name = "lead_id")
    private Lead relatedLead;

    @Column(name="parent_id_txt")
    private String parentId;

    @Column(name="lead_id_txt")
    private String relatedLeadId;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Account> children = new ArrayList<>();

    @OneToMany(orphanRemoval = false)
    List<Contact> contacts;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = false)
    List<Case> cases;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = false)
    List<VoiceCall> calls;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = false)
    List<Opportunity> opportunities;

    @Column(name="company_name")
    private String companyName;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name="industry")
    private String industry;

    @Column(name="revenue")
    private double revenue;

    @Column(name="bill_address")
    private String billingAddress;

    @Column(name="description")
    private String description;

    @Column(name="account_type")
    private String type;

    @Column(name="website")
    private String website;

    @Column(name="client_rating")
    private int clientRating;

    @Column(name="vat")
    private String vat;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;


    public Account(String billingAddress, int clientRating, String companyName, String description, String id, String industry, boolean isActive, Account parent, Lead relatedLead, double revenue, String type, String vat, String website) {
        this.billingAddress = billingAddress;
        this.clientRating = clientRating;
        this.companyName = companyName;
        this.description = description;
        this.id = id;
        this.industry = industry;
        this.isActive = isActive;
        this.parent = parent;
        this.relatedLead = relatedLead;
        this.revenue = revenue;
        this.type = type;
        this.vat = vat;
        this.website = website;
    }

    public Account(String billingAddress, int clientRating, String companyName, String description, String id, String industry, boolean isActive, Account parent, Lead relatedLead, double revenue, String type, String vat, String website, String leadId, String parentId){
        this(billingAddress, clientRating, companyName, description, id, industry, isActive, parent, relatedLead, revenue, type, vat, website);
        this.parentId = parentId;
        this.relatedLeadId = leadId;
    }

    public Account() {}

    @Override
    public AccountDTO toDTOSimple() {
        return new AccountDTOSimple(billingAddress, clientRating, companyName, description, id, industry, isActive, parent, relatedLead, revenue, type, vat, website, created, modified);
    }

    @Override
    public AccountDTO toDTOComplete() {
        return new AccountDTOComplete(billingAddress, clientRating, companyName, description, id, industry, isActive, parent, relatedLead, revenue, type, vat, website, children, contacts, cases, calls, created, modified, opportunities);
    }

    @Override
    public AccountDTO toDTOMinimal() {
        return new AccountDTOMinimal(billingAddress, clientRating, companyName, description, id, industry, isActive, parent, relatedLead, revenue, type, vat, website, created, modified);
    }

    public List<Opportunity> getOpportunities() {
        return opportunities;
    }

    public List<VoiceCall> getCalls() {
        return calls;
    }

    public List<Case> getCases() {
        return cases;
    }

    public List<Account> getChildren() {
        return children;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRelatedLeadId() {
        return relatedLeadId;
    }

    public void setRelatedLeadId(String relatedLeadId) {
        this.relatedLeadId = relatedLeadId;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public int getClientRating() {
        return clientRating;
    }

    public void setClientRating(int clientRating) {
        this.clientRating = clientRating;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Account getParent() {
        return parent;
    }

    public void setParent(Account parent) {
        this.parent = parent;
    }

    public Lead getRelatedLead() {
        return relatedLead;
    }

    public void setRelatedLead(Lead relatedLead) {
        this.relatedLead = relatedLead;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
