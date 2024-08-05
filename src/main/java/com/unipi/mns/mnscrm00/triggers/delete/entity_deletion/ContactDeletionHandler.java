package com.unipi.mns.mnscrm00.triggers.delete.entity_deletion;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.services.concretes.AccountService;
import com.unipi.mns.mnscrm00.triggers.delete.DeletionHandler;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class ContactDeletionHandler implements DeletionHandler<Contact> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Contact delete(Contact contact) {
        contact = handleParentReferences(contact);
        contact = handleChildReferences(contact);
        return contact;
    }

    @Override
    public Contact handleParentReferences(Contact contact){
        if(!StringUtil.stringIsEmptyOrNull(contact.getAccountId())){
            Optional<Account> accountOptional = accountRepository.findById(contact.getAccountId());

            if(!accountOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.ACCOUNT,
                                Constants.Specifier.ID
                        )
                );
            }

            accountOptional.get().getContacts().remove(contact);
            accountRepository.save(accountOptional.get());
        }

        return contact;
    }

    @Override
    public Contact handleChildReferences(Contact contact){
        return contact;
    }
}
