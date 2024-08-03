package com.unipi.mns.mnscrm00.dto.minimals;

import com.unipi.mns.mnscrm00.dto.abstracts.OpportunityDTO;
import com.unipi.mns.mnscrm00.entities.data.Account;

public class OpportunityDTOMinimal extends OpportunityDTO {

    public OpportunityDTOMinimal(String comments, String description, double expectedRevenue, String id, String relatedAccountId, String status, String title, String type, Account account) {
       super(comments, description, expectedRevenue, id, relatedAccountId, status, title, type, account);
    }

    public OpportunityDTOMinimal(){
        super();
    }
}
