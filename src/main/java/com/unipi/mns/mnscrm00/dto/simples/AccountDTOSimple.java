package com.unipi.mns.mnscrm00.dto.simples;

import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.LeadDTO;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class AccountDTOSimple extends AccountDTO {

    private AccountDTO parent;

    private LeadDTO parentLead;

    public AccountDTOSimple(){
        super();
    }

    public AccountDTOSimple(String billingAddress, int clientRating, String companyName, String description, String id, String industry, boolean isActive, Account parent, Lead relatedLead, double revenue, String type, String vat, String website, LocalDateTime created, LocalDateTime modified) {
        super(billingAddress, clientRating, companyName, description, id, industry, isActive, parent, relatedLead, revenue, type, vat, website, created, modified);

        if(parent != null){
            this.parent = parent.toDTOSimple();
        }

        if(relatedLead != null){
            this.parentLead = relatedLead.toDTOSimple();
        }
    }

    public AccountDTO getParent() {
        return parent;
    }

    public void setParent(AccountDTO parent) {
        this.parent = parent;
    }

    public LeadDTO getParentLead() {
        return parentLead;
    }

    public void setParentLead(LeadDTO parentLead) {
        this.parentLead = parentLead;
    }
}
