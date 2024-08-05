package com.unipi.mns.mnscrm00.dto.simples;

import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.ContactDTO;
import com.unipi.mns.mnscrm00.dto.minimals.CaseDTOMinimal;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;

import java.time.LocalDateTime;
import java.util.Date;

public class CaseDTOSimple extends CaseDTOMinimal {

    private AccountDTO relatedAccount;
    private ContactDTO relatedContact;

    public CaseDTOSimple(String category, LocalDateTime created, String id, LocalDateTime modified, String reason, Account account, Contact contact, String severity, String source, String status, String title, Date createdDate, Date closedDate) {
        super(category, created, id, modified, reason, account, contact, severity, source, status, title, createdDate, closedDate);
        if(account != null){
            this.relatedAccount = account.toDTOMinimal();
        }
        if(contact != null){
            this.relatedContact = contact.toDTOMinimal();
        }
    }

    public CaseDTOSimple(){}

    public AccountDTO getRelatedAccount() {
        return relatedAccount;
    }

    public void setRelatedAccount(AccountDTO relatedAccount) {
        this.relatedAccount = relatedAccount;
    }

    public ContactDTO getRelatedContact() {
        return relatedContact;
    }

    public void setRelatedContact(ContactDTO relatedContact) {
        this.relatedContact = relatedContact;
    }
}
