package com.unipi.mns.mnscrm00.services.concretes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.LeadRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.LeadDTO;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;
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
public class LeadService implements EntityService {

    @Autowired
    LeadRepository leadRepository;
    @Autowired
    private InsertUpdateTrigger insertUpdateTrigger;
    @Autowired
    private DeleteTrigger deleteTrigger;

    public LeadDTO getLeadById(String id, boolean getChildrenRelationships){
        Optional<Lead> leadOptional = leadRepository.findById(id);

        if(!leadOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.LEAD,
                            Constants.Specifier.ID
                    )
            );
        }

        if(getChildrenRelationships){
            return leadOptional.get().toDTOComplete();
        }

        return leadOptional.get().toDTOSimple();
    }

    public LeadDTO insertLead(Lead lead) throws DataValidationException {
        Lead leadToInsert = new Lead();
        leadToInsert = insertUpdateTrigger.handleLeadEntry(lead, leadToInsert);

        return leadRepository.save(leadToInsert).toDTOSimple();
    }

    public List<LeadDTO> getAllLeads(){
        List<Lead> leadList = leadRepository.findAll();

        if(leadList.size() <= 0){
            return new ArrayList<>();
        }

        return ListConverter.convertLeadsToDTOList(leadList, Constants.DTO.CONVERT_TO_DTO_MINIMAL);
    }

    public LeadDTO updateLead(String id, Lead lead) throws DataValidationException {
        Optional<Lead> leadOptional = leadRepository.findById(id);

        if(!leadOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.LEAD,
                            Constants.Specifier.ID
                    )
            );
        }

        Lead leadToUpdate = leadOptional.get();
        leadToUpdate = insertUpdateTrigger.handleLeadEntry(lead, leadToUpdate);

        return leadRepository.save(leadToUpdate).toDTOComplete();
    }

    public boolean deleteLeadById(String id){
        Optional<Lead> leadOptional = leadRepository.findById(id);

        if(!leadOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.LEAD,
                            Constants.Specifier.ID
                    )
            );
        }


        deleteTrigger.handleLeadDelete(leadOptional.get());
        leadRepository.delete(leadOptional.get());

        return true;
    }
}
