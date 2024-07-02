package com.unipi.mns.mnscrm00.entities.data;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Account")
public class Account {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Account parent;

    @OneToOne
    @JoinColumn(name = "lead_id")
    private Lead relatedLead;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> children = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    List<Contact> contacts;

    @OneToMany(cascade = CascadeType.ALL)
    List<Case> cases;

    @OneToMany(cascade = CascadeType.ALL)
    List<VoiceCall> calls;

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

    @Column(name="type")
    private String type;

    @Column(name="website")
    private String website;

    @Column(name="client_rating")
    private String clientRating;

    @Column(name="vat")
    private String vat;

    public Account(String billingAddress, String clientRating, String companyName, String description, String id, String industry, boolean isActive, Account parent, Lead relatedLead, double revenue, String type, String vat, String website) {
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

    public Account() {}

    public List<VoiceCall> getCalls() {
        return calls;
    }

    public List<Case> getCases() {
        return cases;
    }

    public Set<Account> getChildren() {
        return children;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getClientRating() {
        return clientRating;
    }

    public void setClientRating(String clientRating) {
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
