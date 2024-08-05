package com.unipi.mns.mnscrm00.dto.minimals;

import com.unipi.mns.mnscrm00.dto.abstracts.VoiceCallDTO;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Case;

import java.time.LocalDateTime;
import java.util.Date;

public class VoiceCallDTOMinimal extends VoiceCallDTO {

    public VoiceCallDTOMinimal(String agentName, Date callDate, String customerPhone, int duration, String id, Account account, Case caseRecord, String title, LocalDateTime created, LocalDateTime modified) {
        super(agentName, callDate, customerPhone, duration, id, account, caseRecord, title, created, modified);
    }

    public VoiceCallDTOMinimal() {}
}
