package com.unipi.mns.mnscrm00.dto.abstracts;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Lead;

public abstract class AccountDTO implements EntityDTO{
    private String id;
    private String parentId;
    private String companyName;
    private Lead relatedLead;
    private boolean isActive;
    private String industry;
    private double revenue;
    private String billingAddress;
    private String description;
    private String type;
    private String website;
    private int clientRating;
    private String vat;

    public AccountDTO(String billingAddress, int clientRating, String companyName, String description, String id, String industry, boolean isActive, Account parent, Lead relatedLead, double revenue, String type, String vat, String website) {
        this.billingAddress = billingAddress;
        this.clientRating = clientRating;
        this.companyName = companyName;
        this.description = description;
        this.id = id;
        this.industry = industry;
        this.isActive = isActive;
        this.relatedLead = relatedLead;
        this.revenue = revenue;
        this.type = type;
        this.vat = vat;
        this.website = website;

        if(parent != null){
            this.parentId = parent.getId();
        }

    }

    public AccountDTO() {}

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

    public String getParent() {
        return parentId;
    }

    public void setParent(String parentId) {
        this.parentId = parentId;
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
