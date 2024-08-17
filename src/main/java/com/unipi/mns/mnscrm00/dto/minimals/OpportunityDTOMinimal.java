package com.unipi.mns.mnscrm00.dto.minimals;

import com.unipi.mns.mnscrm00.dto.abstracts.OpportunityDTO;
import com.unipi.mns.mnscrm00.entities.data.Account;

import java.time.LocalDateTime;

public class OpportunityDTOMinimal extends OpportunityDTO {

    public OpportunityDTOMinimal(String comments, String description, double expectedRevenue, String id, String relatedAccountId, String status, String title, String type, Account account, LocalDateTime created, LocalDateTime modified) {
       super(comments, description, expectedRevenue, id, relatedAccountId, status, title, type, account, created, modified);
    }

    public OpportunityDTOMinimal(){
        super();
    }
}
