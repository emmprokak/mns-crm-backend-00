package com.unipi.mns.mnscrm00.dto.minimals;

import com.unipi.mns.mnscrm00.dto.abstracts.CaseDTO;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;

import java.time.LocalDateTime;
import java.util.Date;

public class CaseDTOMinimal extends CaseDTO {

    public CaseDTOMinimal(String category, LocalDateTime created, String id, LocalDateTime modified, String reason, Account account, Contact contact, String severity, String source, String status, String title, Date createdDate, Date closedDate) {
        super(category, created, id, modified, reason, account, contact, severity, source, status, title,createdDate, closedDate);
    }

    public CaseDTOMinimal() {}

}
