package com.unipi.mns.mnscrm00.triggers;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.dal.ContactRepository;
import com.unipi.mns.mnscrm00.dal.LeadRepository;
import com.unipi.mns.mnscrm00.dal.OpportunityRepository;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class DeleteTrigger {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;

    public Account handleAccountDelete(Account account){
        // handle parents
        if(!StringUtil.stringIsEmptyOrNull(account.getParentId())){
            Optional<Account> parentAccountOptional = accountRepository.findById(account.getParentId());

            if (!parentAccountOptional.isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.ACCOUNT,
                                Constants.Specifier.ID
                        )
                );
            }

            parentAccountOptional.get().getChildren().remove(account);
            accountRepository.save(parentAccountOptional.get());
        }


        // handle children

        List<Account> accountList = accountRepository.findByParentId(account.getId());
        for(Account acc : accountList){
            acc.setParent(null);
            acc.setParentId(null);
            accountRepository.save(acc);
        }


        List<Contact> contactList = contactRepository.findByAccountId(account.getId());
        for(Contact con : contactList){
            con.setAccount(null);
            con.setAccountId(null);
            contactRepository.save(con);
        }

        List<Opportunity> opportunityList = opportunityRepository.findByRelatedAccountId(account.getId());
        for(Opportunity opp : opportunityList){
            opp.setRelatedAccount(null);
            opp.setRelatedAccountId(null);
            opportunityRepository.save(opp);
        }


        return account;
    }
}
