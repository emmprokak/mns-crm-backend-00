package com.unipi.mns.mnscrm00.process.lead_conversion;

import com.unipi.mns.mnscrm00.dal.ContactRepository;
import com.unipi.mns.mnscrm00.dal.OpportunityRepository;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.mapping.RelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class MapLeadConversionChildrenRelationships implements Command{
    private Account account;
    private Contact contact;
    private Opportunity opportunity;

    private RelationshipMapper relationshipMapper;
    private ContactRepository contactRepository;
    private OpportunityRepository opportunityRepository;

    public MapLeadConversionChildrenRelationships(Account account, Contact contact, Opportunity opportunity, RelationshipMapper relationshipMapper, ContactRepository contactRepository, OpportunityRepository opportunityRepository){
        this.account = account;
        this.contact = contact;
        this.opportunity = opportunity;
        this.relationshipMapper = relationshipMapper;
        this.contactRepository = contactRepository;
        this.opportunityRepository = opportunityRepository;

    }

    @Override
    public void execute() {
        relationshipMapper.mapLeadConversionChildrenRelationships(account, contact, opportunity);
        contactRepository.save(contact);
        opportunityRepository.save(opportunity);
    }
}
