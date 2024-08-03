package com.unipi.mns.mnscrm00.services.concretes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.dal.OpportunityRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.OpportunityDTO;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;
import com.unipi.mns.mnscrm00.mapping.RelationshipMapper;
import com.unipi.mns.mnscrm00.services.abstracts.EntityService;
import com.unipi.mns.mnscrm00.utilities.ListConverter;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OpportunityService implements EntityService {

    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RelationshipMapper relationshipMapper;

    public OpportunityDTO getOpportunityByIdSimple(String id){
        Optional<Opportunity> opptyOptional = opportunityRepository.findById(id);

        if(!opptyOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.OPPORTUNITY,
                            Constants.Specifier.ID
                    )
            );
        }

        return opptyOptional.get().toDTOSimple();
    }

    public OpportunityDTO getOpportunityByIdComplete(String id){
        Optional<Opportunity> contactOptional = opportunityRepository.findById(id);

        if(!contactOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.OPPORTUNITY,
                            Constants.Specifier.ID
                    )
            );
        }

        return contactOptional.get().toDTOComplete();
    }

    public OpportunityDTO insertOpportunity(Opportunity opportunity){
        Opportunity opptyToInsert = new Opportunity();

        opptyToInsert = ObjectMapper.mapOpportunityFields(opportunity, opptyToInsert);
        opptyToInsert = relationshipMapper.mapOpportunityParents(opportunity, opptyToInsert, true);

        return opportunityRepository.save(opptyToInsert).toDTOSimple();
    }

    public List<OpportunityDTO> getAllContacts(){
        List<Opportunity> contactList = opportunityRepository.findAll();

        if(contactList.size() <= 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.OPPORTUNITY,
                            Constants.Specifier.ID
                    )
            );
        }

        return ListConverter.convertOpportunitiesToDTOList(contactList, Constants.DTO.CONVERT_TO_DTO_SIMPLE);
    }

    public OpportunityDTO updateOpportunity(String id, Opportunity opportunity){
        Optional<Opportunity> opptyOptional = opportunityRepository.findById(id);

        if(!opptyOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.OPPORTUNITY,
                            Constants.Specifier.ID
                    )
            );
        }

        Opportunity opptyToUpdate = opptyOptional.get();
        opptyToUpdate = ObjectMapper.mapOpportunityFields(opportunity, opptyToUpdate);
        opptyToUpdate = relationshipMapper.mapOpportunityParents(opportunity, opptyToUpdate, false);

        return opportunityRepository.save(opptyToUpdate).toDTOSimple();
    }

    public boolean deleteOpportunityById(String id){
        Optional<Opportunity> opptyOptional = opportunityRepository.findById(id);

        if(!opptyOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.OPPORTUNITY,
                            Constants.Specifier.ID
                    )
            );
        }

        if(opptyOptional.get().getRelatedAccount() != null){
            Optional<Account> accountOptional = accountRepository.findById(opptyOptional.get().getRelatedAccountId());

            if(!accountOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.ACCOUNT,
                                Constants.Specifier.ID
                        )
                );
            }

            accountOptional.get().getOpportunities().remove(opptyOptional.get());
            accountRepository.save(accountOptional.get());
        }

        opportunityRepository.delete(opptyOptional.get());

        return true;
    }
}
