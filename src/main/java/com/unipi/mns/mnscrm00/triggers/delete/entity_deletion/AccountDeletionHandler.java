package com.unipi.mns.mnscrm00.triggers.delete.entity_deletion;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.dal.CaseRepository;
import com.unipi.mns.mnscrm00.dal.ContactRepository;
import com.unipi.mns.mnscrm00.dal.OpportunityRepository;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Case;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.triggers.delete.DeletionHandler;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class AccountDeletionHandler implements DeletionHandler<Account> {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CaseRepository caseRepository;

    @Override
    public Account delete(Account account) {
        account = handleParentReferences(account);
        account = handleChildReferences(account);
        return account;
    }

    @Override
    public Account handleParentReferences(Account account){
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

        return account;
    }

    @Override
    public Account handleChildReferences(Account account){
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

        List<Case> caseList = caseRepository.findByRelatedAccountId(account.getId());
        for(Case c : caseList){
            c.setRelatedAccount(null);
            c.setRelatedAccountId(null);
            caseRepository.save(c);
        }

        return account;
    }
}
