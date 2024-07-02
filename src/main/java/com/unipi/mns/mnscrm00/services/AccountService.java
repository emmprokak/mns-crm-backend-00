package com.unipi.mns.mnscrm00.services;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.constants.error.ConstantErrors;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountById(String id){
        Optional<Account> accountOptional = accountRepository.findById(id);

        if(!accountOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtility.getEntityNotFoundBySpecifier(Constants.Entity.getDescription(Constants.Entity.ACCOUNT), "id"));
        }

        return accountOptional.orElse(null);
    }

    public Account insertAccount(Account account){
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts(){
        List<Account> doctorList = accountRepository.findAll();

        if(doctorList.size() <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.CONSTANT_ERRORS.ENTITY_NOT_FOUND);
        }

        return doctorList;
    }

    public Account updateAccount(String id, Account account){
        Optional<Account> accountOptional = accountRepository.findById(id);

        if(!accountOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        Account accToUpdate = account;

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


}
