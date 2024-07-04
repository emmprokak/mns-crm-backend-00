package com.unipi.mns.mnscrm00.services.concretes;

import com.unipi.mns.mnscrm00.dal.ContactRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;
import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.services.abstracts.EntityService;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AccountService implements EntityService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactService contactService;

    public AccountDTO getAccountByIdSimple(String id){
        Optional<Account> accountOptional = accountRepository.findById(id);

        if(!accountOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtility.getEntityNotFoundBySpecifier(Constants.Entity.getDescription(Constants.Entity.ACCOUNT), "id"));
        }

        return accountOptional.get().toDTOSimple();
    }

    public AccountDTO getAccountByIdComplete(String id){
        Optional<Account> accountOptional = accountRepository.findById(id);

        if(!accountOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtility.getEntityNotFoundBySpecifier(Constants.Entity.getDescription(Constants.Entity.ACCOUNT), "id"));
        }

        return accountOptional.get().toDTOComplete();
    }

    public AccountDTO insertAccount(Account account){
        return accountRepository.save(account).toDTOSimple();
    }

    public List<Account> getAllAccounts(){
        List<Account> accountList = accountRepository.findAll();

        if(accountList.size() <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.CONSTANT_ERRORS.ENTITY_NOT_FOUND);
        }

        return accountList;
    }

    public Account updateAccount(String id, Account account){
        Optional<Account> accountOptional = accountRepository.findById(id);

        if(!accountOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        Account accToUpdate = accountOptional.get();
        accToUpdate = ObjectMapper.mapAccountFields(account, accToUpdate);

        return accountRepository.save(accToUpdate);
    }

    public boolean deleteAccountById(String id){
        Optional<Account> accountOptional = accountRepository.findById(id);

        if(!accountOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        accountRepository.delete(accountOptional.get());

        return true;
    }

    public AccountDTO addContactToAccount(String id, Contact contact){
        Optional<Account> accountOptional = accountRepository.findById(id);

        if(!accountOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }
        Contact contactToInsert = contact;
        contactToInsert.setAccount(accountOptional.get());
        Contact insertedContact = contactRepository.save(contactToInsert);

        Account accToUpdate = accountOptional.get();
        accToUpdate.getContacts().add(insertedContact);
        accountRepository.save(accToUpdate);

        return accToUpdate.toDTOComplete();
    }

    public AccountDTO removeContactFromAccount(String accountId, String contactId){
        Optional<Account> accountOptional = accountRepository.findById(accountId);

        if(!accountOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }

        Optional<Contact> contactOptional = contactRepository.findById(contactId);

        if(!contactOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found");
        }

        if(!accountOptional.get().getContacts().contains(contactOptional.get())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not related to Account");
        }

        accountOptional.get().getContacts().remove(contactOptional.get());
        contactOptional.get().setAccount(null);
        contactRepository.save(contactOptional.get());
        return accountRepository.save(accountOptional.get()).toDTOComplete();
    }
}
