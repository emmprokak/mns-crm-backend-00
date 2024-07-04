package com.unipi.mns.mnscrm00.dto.minimals;

import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.EntityDTO;
import com.unipi.mns.mnscrm00.dto.completes.AccountDTOComplete;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Lead;

public class AccountDTOMinimal extends AccountDTO implements EntityDTO {

    public AccountDTOMinimal(){
        super();
    }

    public AccountDTOMinimal(String billingAddress, int clientRating, String companyName, String description, String id, String industry, boolean isActive, Account parent, Lead relatedLead, double revenue, String type, String vat, String website) {
        super(billingAddress, clientRating, companyName, description, id, industry, isActive, parent, relatedLead, revenue, type, vat, website);
    }
}
