package com.unipi.mns.mnscrm00.entities.builders;

import com.unipi.mns.mnscrm00.entities.data.Lead;

import java.util.Date;

public class LeadBuilder implements EntityBuilder<Lead>{
    private Lead lead;

    @Override
    public Lead build() {
        return lead;
    }

    public LeadBuilder(){
        this.lead = new Lead();
    }

    public LeadBuilder(Lead acc){
        this.lead = acc;
    }


    public LeadBuilder setCompanyName(String companyName) {
        lead.setCompanyName(companyName);
        return this;
    }

    public LeadBuilder setContactPerson(String contactPerson) {
        lead.setContactPerson(contactPerson);
        return this;
    }

    public LeadBuilder setContactPrefix(String contactPrefix) {
        lead.setContactPrefix(contactPrefix);
        return this;
    }

    public LeadBuilder setCompanyAddress(String companyAddress) {
        lead.setCompanyAddress(companyAddress);
        return this;
    }

    public LeadBuilder setContactRole(String contactRole) {
        lead.setContactRole(contactRole);
        return this;
    }

    public LeadBuilder setContactEmail(String contactEmail) {
        lead.setContactEmail(contactEmail);
        return this;
    }

    public LeadBuilder setContactMobile(String contactMobile) {
        lead.setContactMobile(contactMobile);
        return this;
    }

    public LeadBuilder setContactPhone(String contactPhone) {
        lead.setContactPhone(contactPhone);
        return this;
    }

    public LeadBuilder setCompanyIndustry(String companyIndustry) {
        lead.setCompanyIndustry(companyIndustry);
        return this;
    }

    public LeadBuilder setStatus(String status) {
        lead.setStatus(status);
        return this;
    }

}
