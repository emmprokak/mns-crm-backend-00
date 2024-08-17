package com.unipi.mns.mnscrm00.entities.builders;

import com.unipi.mns.mnscrm00.entities.data.Account;

public class AccountBuilder implements EntityBuilder<Account>{
    private Account account;

    @Override
    public Account build() {
        return account;
    }

    public AccountBuilder(){
        this.account = new Account();
    }

    public AccountBuilder(Account acc){
        this.account = acc;
    }

    public AccountBuilder setCompanyName(String companyName){
        account.setCompanyName(companyName);
        return this;
    }

    public AccountBuilder setParentId(String parentId) {
        account.setParentId(parentId);
        return this;
    }

    public AccountBuilder setLeadId(String leadId) {
        account.setRelatedLeadId(leadId);
        return this;
    }

    public AccountBuilder setActive(boolean isActive) {
        account.setActive(isActive);
        return this;
    }

    public AccountBuilder setIndustry(String industry) {
        account.setIndustry(industry);
        return this;
    }

    public AccountBuilder setRevenue(double revenue) {
        account.setRevenue(revenue);
        return this;
    }

    public AccountBuilder setBillingAddress(String billingAddress) {
        account.setBillingAddress(billingAddress);
        return this;
    }

    public AccountBuilder setDescription(String description) {
        account.setDescription(description);
        return this;
    }

    public AccountBuilder setType(String type) {
        account.setType(type);
        return this;
    }

    public AccountBuilder setWebsite(String website) {
        account.setWebsite(website);
        return this;
    }

    public AccountBuilder setClientRating(int clientRating) {
        account.setClientRating(clientRating);
        return this;
    }

    public AccountBuilder setVat(String vat) {
        account.setVat(vat);
        return this;
    }
}
