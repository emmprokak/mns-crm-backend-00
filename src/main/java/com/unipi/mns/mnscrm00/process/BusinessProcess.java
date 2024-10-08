package com.unipi.mns.mnscrm00.process;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.dal.ContactRepository;
import com.unipi.mns.mnscrm00.dal.LeadRepository;
import com.unipi.mns.mnscrm00.dal.OpportunityRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.EntityDTO;
import com.unipi.mns.mnscrm00.dto.processes.ProcessOutputDTO;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;
import com.unipi.mns.mnscrm00.mapping.RelationshipMapper;
import com.unipi.mns.mnscrm00.process.discounts.DiscountContext;
import com.unipi.mns.mnscrm00.process.discounts.DiscountStrategy;
import com.unipi.mns.mnscrm00.process.discounts.DiscountStrategyFactory;
import com.unipi.mns.mnscrm00.process.discounts.strategies.IndustryDiscount;
import com.unipi.mns.mnscrm00.process.discounts.strategies.LoyaltyDiscount;
import com.unipi.mns.mnscrm00.process.discounts.strategies.RevenueDiscount;
import com.unipi.mns.mnscrm00.process.lead_conversion.*;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class BusinessProcess {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private RelationshipMapper relationshipMapper;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private DiscountStrategyFactory discountStrategyFactory;

    @Transactional
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
        Contact con = new Contact();
        Opportunity opp = new Opportunity();

        LeadConversionInvoker invoker = new LeadConversionInvoker();

        invoker.addCommand(new CreateAccountCommand(inputLead, acc));
        invoker.addCommand(new CreateContactCommand(inputLead, con));
        invoker.addCommand(new CreateOpportunityCommand(inputLead, opp));
        invoker.addCommand(new MapLeadToChildrenCommand(inputLead, acc, con, opp, relationshipMapper, accountRepository));
        invoker.addCommand(new MapLeadConversionChildrenRelationships(acc,
                con, opp,
                relationshipMapper,
                contactRepository,
                opportunityRepository)
        );

        invoker.executeCommands();

        return Arrays.asList(acc.toDTOSimple(), con.toDTOSimple(), opp.toDTOSimple());
    }

    public ProcessOutputDTO clientDiscount(String accountId, double amount){
        Optional<Account> accountOptional = accountRepository.findById(accountId);

        if(!accountOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Account account = accountOptional.get();
        DiscountContext discountContext = new DiscountContext();
        DiscountStrategy selectedDiscountStrategy = discountStrategyFactory.getStrategy(account);

        discountContext.setStrategy(selectedDiscountStrategy);
        double discountPercentage = discountContext.executeStrategy(accountOptional.get());

        return new ProcessOutputDTO(
                "totalAmount",
                "discountProcess",
                String.valueOf((1 - discountPercentage) * amount)
        );
    }

}
