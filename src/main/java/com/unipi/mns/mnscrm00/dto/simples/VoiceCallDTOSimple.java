package com.unipi.mns.mnscrm00.dto.simples;

import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.CaseDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.VoiceCallDTO;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Case;

import java.time.LocalDateTime;
import java.util.Date;

public class VoiceCallDTOSimple extends VoiceCallDTO {

    private AccountDTO relatedAccount;
    private CaseDTO relatedCase;

    public VoiceCallDTOSimple(String agentName, Date callDate, String customerPhone, int duration, String id, Account account, Case caseEntry, String title, LocalDateTime created, LocalDateTime modified) {
        super(agentName, callDate, customerPhone, duration, id, account, caseEntry, title, created, modified);

        if(account != null){
            this.relatedAccount = account.toDTOMinimal();
        }
        if(caseEntry != null){
            this.relatedCase = caseEntry.toDTOMinimal();
        }
    }

    public VoiceCallDTOSimple(){}

    public AccountDTO getRelatedAccount() {
        return relatedAccount;
    }

    public void setRelatedAccount(AccountDTO relatedAccount) {
        this.relatedAccount = relatedAccount;
    }

    public CaseDTO getRelatedCase() {
        return relatedCase;
    }

    public void setRelatedCase(CaseDTO relatedCase) {
        this.relatedCase = relatedCase;
    }
}
