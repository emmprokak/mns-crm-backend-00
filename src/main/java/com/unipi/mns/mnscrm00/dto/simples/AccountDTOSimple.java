package com.unipi.mns.mnscrm00.dto.simples;

import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Lead;

public class AccountDTOSimple extends AccountDTO {

    private AccountDTO parent;

    public AccountDTOSimple(){
        super();
    }

    public AccountDTOSimple(String billingAddress, int clientRating, String companyName, String description, String id, String industry, boolean isActive, Account parent, Lead relatedLead, double revenue, String type, String vat, String website) {
        super(billingAddress, clientRating, companyName, description, id, industry, isActive, parent, relatedLead, revenue, type, vat, website);

        if(parent != null){
            this.parent = parent.toDTOSimple();
        }
    }
}
