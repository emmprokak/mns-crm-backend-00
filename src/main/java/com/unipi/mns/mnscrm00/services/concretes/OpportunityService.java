package com.unipi.mns.mnscrm00.services.concretes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.dal.OpportunityRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.OpportunityDTO;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.mapping.RelationshipMapper;
import com.unipi.mns.mnscrm00.services.abstracts.EntityService;
import com.unipi.mns.mnscrm00.triggers.delete.DeleteTrigger;
import com.unipi.mns.mnscrm00.triggers.insert_update.InsertUpdateTrigger;
import com.unipi.mns.mnscrm00.utilities.ListConverter;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OpportunityService implements EntityService {

    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private InsertUpdateTrigger insertUpdateTrigger;
    @Autowired
    private DeleteTrigger deleteTrigger;

    public OpportunityDTO getOpportunityById(String id, boolean getChildrenRelationships){
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

        if(getChildrenRelationships){
            return opptyOptional.get().toDTOComplete();
        }

        return opptyOptional.get().toDTOSimple();
    }

    public OpportunityDTO insertOpportunity(Opportunity opportunity) throws DataValidationException {
        Opportunity opptyToInsert = new Opportunity();
        opptyToInsert = insertUpdateTrigger.handleOpportunityEntry(opportunity, opptyToInsert, true);

        return opportunityRepository.save(opptyToInsert).toDTOSimple();
    }

    public List<OpportunityDTO> getAllOpportunities(){
        List<Opportunity> opptyList = opportunityRepository.findAll();

        if(opptyList.size() <= 0){
            return new ArrayList<>();
        }

        return ListConverter.convertEntitiesToDTOList(opptyList, Constants.DTO.CONVERT_TO_DTO_SIMPLE);
    }

    public OpportunityDTO updateOpportunity(String id, Opportunity opportunity) throws DataValidationException {
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
        opptyToUpdate = insertUpdateTrigger.handleOpportunityEntry(opportunity, opptyToUpdate, false);

        return opportunityRepository.save(opptyToUpdate).toDTOComplete();
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

        deleteTrigger.handleReferenceDeletion(opptyOptional.get());
        opportunityRepository.delete(opptyOptional.get());

        return true;
    }
}
