package com.unipi.mns.mnscrm00.services.concretes;

import com.unipi.mns.mnscrm00.dal.ContactRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.dto.simples.AccountDTOSimple;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;
import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.mapping.RelationshipMapper;
import com.unipi.mns.mnscrm00.services.abstracts.EntityService;
import com.unipi.mns.mnscrm00.utilities.ListConverter;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AccountService implements EntityService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactService contactService;

    @Autowired
    private RelationshipMapper relationshipMapper;

    public AccountDTO getAccountByIdSimple(String id){
        Optional<Account> accountOptional = accountRepository.findById(id);

        if(!accountOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        return accountOptional.get().toDTOSimple();
    }

    public AccountDTO getAccountByIdComplete(String id){
        Optional<Account> accountOptional = accountRepository.findById(id);

        if(!accountOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        return accountOptional.get().toDTOComplete();
    }

    public AccountDTO insertAccount(Account account){
        Account acc = new Account();
        acc = ObjectMapper.mapAccountFields(account, acc);
        acc = relationshipMapper.mapAccountParents(account, acc);

        return accountRepository.save(acc).toDTOSimple();
    }

    public List<AccountDTO> getAllAccounts(){
        List<Account> accountList = accountRepository.findAll();

        if(accountList.size() <= 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getListEntityNotFound(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        return ListConverter.convertAccountsToDTOList(accountList, Constants.DTO.CONVERT_TO_DTO_SIMPLE);
    }

    public List<AccountDTO> getAllAccountsWithFilters(int limit, String orderByField, String orderType){
        Sort sort = Sort.by(Sort.Direction.fromString(orderType), orderByField);
        Pageable pageable = PageRequest.of(0, limit, sort);
        Page<Account> accountPage = accountRepository.findAll(pageable);

        return accountPage.stream()
                .map(account -> {
                    return account.toDTOSimple();
                })
                .collect(Collectors.toList());
    }


    public AccountDTO updateAccount(String id, Account account){
        Optional<Account> accountOptional = accountRepository.findById(id);

        if(!accountOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Account accToUpdate = accountOptional.get();
        accToUpdate = ObjectMapper.mapAccountFields(account, accToUpdate);
        accToUpdate = relationshipMapper.mapAccountParents(account, accToUpdate);

        return accountRepository.save(accToUpdate).toDTOSimple();
    }

    public boolean deleteAccountById(String id){
        Optional<Account> accountOptional = accountRepository.findById(id);

        if(!accountOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.ACCOUNT,
                            Constants.Specifier.ID
                    )
            );
        }

        Account accToDelete = accountOptional.get();
        List<Account> accountList = accountRepository.findByParentId(accToDelete.getId());
        for(Account acc : accountList){
            acc.setParent(null);
            acc.setParentId(null);
            accountRepository.save(acc);
        }

        accountRepository.delete(accToDelete);

        return true;
    }

    // pending deletion
//
//    public AccountDTO addContactToAccount(String id, Contact contact){
//        Optional<Account> accountOptional = accountRepository.findById(id);
//
//        if(!accountOptional.isPresent()){
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
//                            Constants.Entity.ACCOUNT,
//                            Constants.Specifier.ID
//                    )
//            );
//        }
//
//        Contact contactToInsert = contact;
//        contactToInsert.setAccount(accountOptional.get());
//        Contact insertedContact = contactRepository.save(contactToInsert);
//
//        Account accToUpdate = accountOptional.get();
//        accToUpdate.getContacts().add(insertedContact);
//        accountRepository.save(accToUpdate);
//
//        return accToUpdate.toDTOComplete();
//    }
//
//    public AccountDTO relateContactToAccount(String accountId, String contactId){
//        Optional<Account> accountOptional = accountRepository.findById(accountId);
//
//        if(!accountOptional.isPresent()){
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
//                            Constants.Entity.ACCOUNT,
//                            Constants.Specifier.ID
//                    )
//            );
//
//        }
//
//        Optional<Contact> contactOptional = contactRepository.findById(contactId);
//
//        if(!contactOptional.isPresent()){
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
//                            Constants.Entity.CONTACT,
//                            Constants.Specifier.ID
//                    )
//            );
//        }
//
//        accountOptional.get().getContacts().add(contactOptional.get());
//        contactOptional.get().setAccount(accountOptional.get());
//
//        contactRepository.save(contactOptional.get());
//        return accountRepository.save(accountOptional.get()).toDTOComplete();
//    }
//
//    public AccountDTO removeContactFromAccount(String accountId, String contactId){
//        Optional<Account> accountOptional = accountRepository.findById(accountId);
//
//        if(!accountOptional.isPresent()){
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
//                            Constants.Entity.ACCOUNT,
//                            Constants.Specifier.ID
//                    )
//            );
//
//        }
//
//        Optional<Contact> contactOptional = contactRepository.findById(contactId);
//
//        if(!contactOptional.isPresent()){
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
//                            Constants.Entity.CONTACT,
//                            Constants.Specifier.ID
//                    )
//            );
//        }
//
//        if(!accountOptional.get().getContacts().contains(contactOptional.get())){
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    ErrorMessageUtility.getRelationshipNotFound(
//                            Constants.Entity.ACCOUNT,
//                            Constants.Entity.CONTACT
//                    )
//            );
//        }
//
//        accountOptional.get().getContacts().remove(contactOptional.get());
//        contactOptional.get().setAccount(null);
//        contactRepository.save(contactOptional.get());
//        return accountRepository.save(accountOptional.get()).toDTOComplete();
//    }
}
