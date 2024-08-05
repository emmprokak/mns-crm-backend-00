package com.unipi.mns.mnscrm00.triggers.delete.entity_deletion;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.*;
import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.entities.data.Case;
import com.unipi.mns.mnscrm00.triggers.delete.DeletionHandler;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class CaseDeletionHandler implements DeletionHandler<Case> {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Case delete(Case caseEntry) {
        caseEntry = handleParentReferences(caseEntry);
        caseEntry = handleChildReferences(caseEntry);
        return caseEntry;
    }

    @Override
    public Case handleParentReferences(Case caseEntry){
        if(!StringUtil.stringIsEmptyOrNull(caseEntry.getRelatedAccountId())){
            Optional<Account> accountOptional = accountRepository.findById(caseEntry.getRelatedAccountId());

            if(!accountOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.ACCOUNT,
                                Constants.Specifier.ID
                        )
                );
            }

            accountOptional.get().getCases().remove(caseEntry);
            accountRepository.save(accountOptional.get());
        }

        if(!StringUtil.stringIsEmptyOrNull(caseEntry.getRelatedContactId())){
            Optional<Contact> contactOptional = contactRepository.findById(caseEntry.getRelatedContactId());

            if(!contactOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.CONTACT,
                                Constants.Specifier.ID
                        )
                );
            }

            contactOptional.get().getCases().remove(caseEntry);
            contactRepository.save(contactOptional.get());
        }

        return caseEntry;
    }

    @Override
    public Case handleChildReferences(Case Case){
        return Case;
    }
}
