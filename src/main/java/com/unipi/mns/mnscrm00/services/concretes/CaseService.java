package com.unipi.mns.mnscrm00.services.concretes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.CaseRepository;
import com.unipi.mns.mnscrm00.dal.ContactRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.CaseDTO;
import com.unipi.mns.mnscrm00.entities.data.Case;
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
public class CaseService implements EntityService {

    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private InsertUpdateTrigger insertUpdateTrigger;
    @Autowired
    private DeleteTrigger deleteTrigger;

    public CaseDTO getCaseByIdSimple(String id){
        Optional<Case> caseOptional = caseRepository.findById(id);

        if(!caseOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.CASE,
                            Constants.Specifier.ID
                    )
            );
        }

        return caseOptional.get().toDTOSimple();
    }

    public CaseDTO getCaseByIdComplete(String id){
        Optional<Case> caseOptional = caseRepository.findById(id);

        if(!caseOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.CASE,
                            Constants.Specifier.ID
                    )
            );
        }

        return caseOptional.get().toDTOComplete();
    }

    public CaseDTO insertCase(Case caseEntry){
        Case caseToInsert = new Case();
        caseToInsert = insertUpdateTrigger.handleCaseEntry(caseEntry, caseToInsert, true);

        return caseRepository.save(caseToInsert).toDTOSimple();
    }

    public List<CaseDTO> getAllCases(){
        List<Case> caseList = caseRepository.findAll();

        if(caseList.size() <= 0){
            return new ArrayList<>();
        }

        return ListConverter.convertCasesToDTOList(caseList, Constants.DTO.CONVERT_TO_DTO_SIMPLE);
    }

    public CaseDTO updateCase(String id, Case caseEntry){
        Optional<Case> caseOptional = caseRepository.findById(id);

        if(!caseOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.CASE,
                            Constants.Specifier.ID
                    )
            );
        }

        Case caseToUpdate = caseOptional.get();
        caseToUpdate = insertUpdateTrigger.handleCaseEntry(caseEntry, caseToUpdate, false);
        return caseRepository.save(caseToUpdate).toDTOSimple();
    }

    public boolean deleteCaseById(String id){
        Optional<Case> caseOptional = caseRepository.findById(id);

        if(!caseOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.CASE,
                            Constants.Specifier.ID
                    )
            );
        }
        deleteTrigger.handleReferenceDeletion(caseOptional.get());
        caseRepository.delete(caseOptional.get());

        return true;
    }
}
