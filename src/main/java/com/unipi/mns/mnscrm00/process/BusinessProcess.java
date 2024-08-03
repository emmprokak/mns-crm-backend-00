package com.unipi.mns.mnscrm00.process;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.dal.ContactRepository;
import com.unipi.mns.mnscrm00.dal.LeadRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.EntityDTO;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;
import com.unipi.mns.mnscrm00.mapping.RelationshipMapper;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class BusinessProcess {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    RelationshipMapper relationshipMapper;

    public List<EntityDTO> leadConversion(String leadId) {

        Optional<Lead> leadOptional = leadRepository.findById(leadId);

        if(!leadOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.LEAD,
                            Constants.Specifier.ID
                    )
            );
        }

        Lead inputLead = leadOptional.get();

        Account acc = new Account();
        acc = ObjectMapper.mapLeadToAccount(inputLead, acc);

        Contact con = new Contact();
        con = ObjectMapper.mapLeadToContact(inputLead, con);

        Opportunity opp = new Opportunity();
        opp = ObjectMapper.mapLeadToOpportunity(inputLead, opp);

        relationshipMapper.mapLeadToChildren(acc, con, opp, inputLead);

        return Arrays.asList(acc.toDTOSimple(), con.toDTOSimple(), opp.toDTOSimple());
    }

}
