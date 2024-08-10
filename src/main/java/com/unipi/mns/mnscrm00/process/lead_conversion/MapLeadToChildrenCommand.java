package com.unipi.mns.mnscrm00.process.lead_conversion;

import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.mapping.RelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public class MapLeadToChildrenCommand implements Command{
    private Lead lead;
    private Contact contact;
    private Opportunity opportunity;
    private Account account;

    private AccountRepository accountRepository;
    private RelationshipMapper relationshipMapper;

    public MapLeadToChildrenCommand(Lead lead, Account acc, Contact con, Opportunity opp, RelationshipMapper relationshipMapper, AccountRepository accountRepository){
        this.lead = lead;
        this.account = acc;
        this.contact = con;
        this.opportunity = opp;
        this.accountRepository = accountRepository;
        this.relationshipMapper = relationshipMapper;
    }

    @Override
    public void execute() {
        relationshipMapper.mapLeadToChildren(account, contact, opportunity, lead);
        accountRepository.save(account);

    }
}
