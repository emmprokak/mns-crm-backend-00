package com.unipi.mns.mnscrm00.dto.completes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dto.abstracts.*;
import com.unipi.mns.mnscrm00.dto.simples.AccountDTOSimple;
import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.exceptions.ListConversionException;
import com.unipi.mns.mnscrm00.utilities.ListConverter;

import java.time.LocalDateTime;
import java.util.List;

public class AccountDTOComplete extends AccountDTOSimple {
    private List<AccountDTO> children;
    private List<ContactDTO> contacts;
    private List<CaseDTO> cases;
    private List<VoiceCallDTO> calls;
    private List<OpportunityDTO> opportunities;

    public AccountDTOComplete(){
        super();
    }

    public AccountDTOComplete(String billingAddress, int clientRating, String companyName, String description, String id, String industry, boolean isActive, Account parent, Lead relatedLead, double revenue, String type, String vat, String website, List<Account> accounts, List<Contact> contacts, List<Case> cases, List<VoiceCall> calls, LocalDateTime created, LocalDateTime modified, List<Opportunity> opportunities){
        super(billingAddress, clientRating, companyName, description, id, industry, isActive, parent, relatedLead, revenue, type, vat, website, created, modified);
        this.children = ListConverter.convertEntitiesToDTOList(accounts, Constants.DTO.CONVERT_TO_DTO_MINIMAL);
        this.contacts = ListConverter.convertEntitiesToDTOList(contacts, Constants.DTO.CONVERT_TO_DTO_MINIMAL);
        this.cases = ListConverter.convertEntitiesToDTOList(cases, Constants.DTO.CONVERT_TO_DTO_MINIMAL);
        this.calls = ListConverter.convertEntitiesToDTOList(calls, Constants.DTO.CONVERT_TO_DTO_MINIMAL);
        this.opportunities = ListConverter.convertEntitiesToDTOList(opportunities, Constants.DTO.CONVERT_TO_DTO_MINIMAL);;
    }

    public List<OpportunityDTO> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<OpportunityDTO> opportunities) {
        this.opportunities = opportunities;
    }

    public List<VoiceCallDTO> getCalls() {
        return calls;
    }

    public void setCalls(List<VoiceCallDTO> calls) {
        this.calls = calls;
    }

    public List<CaseDTO> getCases() {
        return cases;
    }

    public void setCases(List<CaseDTO> cases) {
        this.cases = cases;
    }


    public List<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }
}
