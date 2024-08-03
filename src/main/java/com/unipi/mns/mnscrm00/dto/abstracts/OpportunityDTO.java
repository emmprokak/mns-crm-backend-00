package com.unipi.mns.mnscrm00.dto.abstracts;

import com.unipi.mns.mnscrm00.entities.data.Account;

public abstract class OpportunityDTO implements EntityDTO{

    private String id;
    private String relatedAccountId;
    private String title;
    private String status;
    private String type;
    private String description;
    private String comments;
    private double expectedRevenue;

    public OpportunityDTO(String comments, String description, double expectedRevenue, String id, String relatedAccountId, String status, String title, String type, Account account) {
        this.comments = comments;
        this.description = description;
        this.expectedRevenue = expectedRevenue;
        this.id = id;
        this.relatedAccountId = relatedAccountId;
        this.status = status;
        this.title = title;
        this.type = type;

        if(account != null){
            this.relatedAccountId = account.getId();
        }
    }

    public OpportunityDTO() {}

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getRelatedAccountId() {
        return relatedAccountId;
    }

    public void setRelatedAccountId(String relatedAccountId) {
        this.relatedAccountId = relatedAccountId;
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
