package com.unipi.mns.mnscrm00.dto.completes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.ContactDTO;
import com.unipi.mns.mnscrm00.dto.simples.AccountDTOSimple;
import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.exceptions.ListConversionException;
import com.unipi.mns.mnscrm00.utilities.ListConverter;

import java.time.LocalDateTime;
import java.util.List;

public class AccountDTOComplete extends AccountDTOSimple {
    private List<AccountDTO> children;
    private List<ContactDTO> contacts;
    private List<Case> cases;
    private List<VoiceCall> calls;

    public AccountDTOComplete(){
        super();
    }

    public AccountDTOComplete(String billingAddress, int clientRating, String companyName, String description, String id, String industry, boolean isActive, Account parent, Lead relatedLead, double revenue, String type, String vat, String website, List<Account> accounts, List<Contact> contacts, List<Case> cases, List<VoiceCall> calls, LocalDateTime created, LocalDateTime modified){
        super(billingAddress, clientRating, companyName, description, id, industry, isActive, parent, relatedLead, revenue, type, vat, website, created, modified);
        this.children = ListConverter.convertAccountsToDTOList(accounts, Constants.DTO.CONVERT_TO_DTO_MINIMAL);
        this.contacts = ListConverter.convertContactsToDTOList(contacts, Constants.DTO.CONVERT_TO_DTO_MINIMAL);
        this.cases = cases;
        this.calls = calls;
    }

    public List<VoiceCall> getCalls() {
        return calls;
    }

    public void setCalls(List<VoiceCall> calls) {
        this.calls = calls;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }


    public List<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }
}
