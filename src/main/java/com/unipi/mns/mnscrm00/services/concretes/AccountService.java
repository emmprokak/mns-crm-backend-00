package com.unipi.mns.mnscrm00.services.concretes;

import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.mapping.RelationshipMapper;
import com.unipi.mns.mnscrm00.services.abstracts.EntityService;
import com.unipi.mns.mnscrm00.triggers.delete.DeleteTrigger;
import com.unipi.mns.mnscrm00.triggers.insert_update.InsertUpdateTrigger;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements EntityService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private InsertUpdateTrigger insertUpdateTrigger;

    @Autowired
    private DeleteTrigger deleteTrigger;

    public AccountDTO getAccountById(String id, boolean getChildrenRelationships){
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

        if(getChildrenRelationships){
            return accountOptional.get().toDTOComplete();
        }

        return accountOptional.get().toDTOSimple();
    }

    public AccountDTO insertAccount(Account account) throws DataValidationException {
        Account acc = new Account();
        acc = insertUpdateTrigger.handleAccountEntry(account, acc);

        return accountRepository.save(acc).toDTOSimple();
    }

    public List<AccountDTO> getAllAccounts(){
        List<Account> accountList = accountRepository.findAll();

        if(accountList.size() <= 0){
            return new ArrayList<>();
        }

        return ListConverter.convertEntitiesToDTOList(accountList, Constants.DTO.CONVERT_TO_DTO_SIMPLE);
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


    public AccountDTO updateAccount(String id, Account account) throws DataValidationException {
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
        accToUpdate = insertUpdateTrigger.handleAccountEntry(account, accToUpdate);

        return accountRepository.save(accToUpdate).toDTOComplete();
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
        deleteTrigger.handleReferenceDeletion(accToDelete);
        accountRepository.delete(accToDelete);

        return true;
    }
}
