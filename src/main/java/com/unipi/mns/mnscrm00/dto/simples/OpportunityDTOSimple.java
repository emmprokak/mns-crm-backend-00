package com.unipi.mns.mnscrm00.dto.simples;

import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.dto.minimals.OpportunityDTOMinimal;
import com.unipi.mns.mnscrm00.entities.data.Account;

import java.time.LocalDateTime;

public class OpportunityDTOSimple extends OpportunityDTOMinimal {
    private AccountDTO relatedAccount;

    public OpportunityDTOSimple(String comments, String description, double expectedRevenue, String id, String relatedAccountId, String status, String title, String type, Account account, LocalDateTime created, LocalDateTime modified) {
        super(comments, description, expectedRevenue, id, relatedAccountId, status, title, type, account, created, modified);
        if(account != null) {
            this.relatedAccount = account.toDTOMinimal();
        }
    }

    public OpportunityDTOSimple() {}

    public AccountDTO getRelatedAccount() {
        return relatedAccount;
    }

    public void setRelatedAccount(AccountDTO relatedAccount) {
        this.relatedAccount = relatedAccount;
    }
}
