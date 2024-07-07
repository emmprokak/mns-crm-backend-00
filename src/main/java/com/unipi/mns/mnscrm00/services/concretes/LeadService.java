package com.unipi.mns.mnscrm00.services.concretes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.LeadRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.ContactDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.LeadDTO;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;
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
public class LeadService implements EntityService {

    @Autowired
    LeadRepository leadRepository;

    public LeadDTO getLeadByIdSimple(String id){
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

        return leadOptional.get().toDTOSimple();
    }

    public LeadDTO getLeadByIdComplete(String id){
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

        return leadOptional.get().toDTOComplete();
    }

    public LeadDTO insertLead(Lead lead){
        Lead leadToInsert = new Lead();

        leadToInsert = ObjectMapper.mapLeadFields(lead, leadToInsert);

        return leadRepository.save(leadToInsert).toDTOSimple();
    }

    public List<LeadDTO> getAllLeads(){
        List<Lead> leadList = leadRepository.findAll();

        if(leadList.size() <= 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.LEAD,
                            Constants.Specifier.ID
                    )
            );
        }

        return ListConverter.convertLeadsToDTOList(leadList, Constants.DTO.CONVERT_TO_DTO_MINIMAL);
    }

    public LeadDTO updateLead(String id, Lead lead){
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
        leadToUpdate = ObjectMapper.mapLeadFields(lead, leadToUpdate);

        return leadRepository.save(leadToUpdate).toDTOSimple();
    }

    public boolean deleteContactById(String id){
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

        leadRepository.delete(leadOptional.get());

        return true;
    }
}
