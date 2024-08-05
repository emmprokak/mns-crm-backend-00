package com.unipi.mns.mnscrm00.dto.completes;

import com.unipi.mns.mnscrm00.dto.simples.VoiceCallDTOSimple;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Case;

import java.time.LocalDateTime;
import java.util.Date;

public class VoiceCallDTOComplete extends VoiceCallDTOSimple {

    public VoiceCallDTOComplete(String agentName, Date callDate, String customerPhone, int duration, String id, Account account, Case caseEntry, String title, LocalDateTime created, LocalDateTime modified) {
        super(agentName, callDate, customerPhone, duration, id, account, caseEntry, title, created, modified);
    }

    public VoiceCallDTOComplete(){}
}
