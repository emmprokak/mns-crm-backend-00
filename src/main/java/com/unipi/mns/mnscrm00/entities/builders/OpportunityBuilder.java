package com.unipi.mns.mnscrm00.entities.builders;

import com.unipi.mns.mnscrm00.entities.data.Opportunity;

public class OpportunityBuilder implements EntityBuilder<Opportunity>{
    private Opportunity opportunity;

    @Override
    public Opportunity build() {
        return opportunity;
    }

    public OpportunityBuilder(){
        this.opportunity = new Opportunity();
    }

    public OpportunityBuilder(Opportunity opp){
        this.opportunity = opp;
    }

    public OpportunityBuilder setTitle(String title) {
        opportunity.setTitle(title);
        return this;
    }

    public OpportunityBuilder setStatus(String status) {
        opportunity.setStatus(status);
        return this;
    }

    public OpportunityBuilder setType(String type) {
        opportunity.setType(type);
        return this;
    }

    public OpportunityBuilder setComments(String comments) {
        opportunity.setComments(comments);
        return this;
    }

    public OpportunityBuilder setDescription(String description) {
        opportunity.setDescription(description);
        return this;
    }

    public OpportunityBuilder setExpectedRevenue(double expectedRevenue) {
        opportunity.setExpectedRevenue(expectedRevenue);
        return this;
    }


}
