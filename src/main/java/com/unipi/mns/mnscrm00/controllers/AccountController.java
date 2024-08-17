package com.unipi.mns.mnscrm00.controllers;

import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.services.concretes.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/{id}")
    public AccountDTO getAccount(@PathVariable String id){
        return accountService.getAccountById(id, false);
    }

    @GetMapping("/{id}/complete")
    public AccountDTO getAccountWithRels(@PathVariable String id){
        return accountService.getAccountById(id, true);
    }

    @PostMapping("/new")
    public AccountDTO createAccount(@RequestBody Account account) throws DataValidationException {
        return accountService.insertAccount(account);
    }

    @GetMapping("/all")
    public List<AccountDTO> getAccounts(@RequestParam(required = false) Integer limit,
                                        @RequestParam(required = false) String orderByField,
                                        @RequestParam(required = false) String orderType){
        if(limit != null && orderType != null && orderByField != null){
            return accountService.getAllAccountsWithFilters(limit, orderByField, orderType);
        }
        return accountService.getAllAccounts();
    }

    @PutMapping("/{id}")
    public AccountDTO updateAccount(@PathVariable String id, @RequestBody Account account) throws DataValidationException {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAccount(@PathVariable String id){
        return accountService.deleteAccountById(id);
    }
}
