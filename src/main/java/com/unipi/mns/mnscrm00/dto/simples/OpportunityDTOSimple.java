package com.unipi.mns.mnscrm00.dto.simples;

import com.unipi.mns.mnscrm00.dto.minimals.OpportunityDTOMinimal;
import com.unipi.mns.mnscrm00.entities.data.Account;

public class OpportunityDTOSimple extends OpportunityDTOMinimal {
    private Account relatedAccount;

    public OpportunityDTOSimple(String comments, String description, double expectedRevenue, String id, String relatedAccountId, String status, String title, String type, Account account) {
        super(comments, description, expectedRevenue, id, relatedAccountId, status, title, type, account);
        this.relatedAccount = account;
    }

    public OpportunityDTOSimple() {}

    public Account getRelatedAccount() {
        return relatedAccount;
    }

    public void setRelatedAccount(Account relatedAccount) {
        this.relatedAccount = relatedAccount;
    }
}
