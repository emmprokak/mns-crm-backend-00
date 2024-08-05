package com.unipi.mns.mnscrm00.triggers.delete.entity_deletion;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.*;
import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.entities.data.VoiceCall;
import com.unipi.mns.mnscrm00.triggers.delete.DeletionHandler;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class VoiceCallDeletionHandler implements DeletionHandler<VoiceCall> {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private CaseRepository caseRepository;

    @Override
    public VoiceCall delete(VoiceCall voiceCall) {
        voiceCall = handleParentReferences(voiceCall);
        voiceCall = handleChildReferences(voiceCall);
        return voiceCall;
    }

    @Override
    public VoiceCall handleParentReferences(VoiceCall voiceCall){
        if(!StringUtil.stringIsEmptyOrNull(voiceCall.getRelatedAccountId())){
            Optional<Account> accountOptional = accountRepository.findById(voiceCall.getRelatedAccountId());

            if(!accountOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.ACCOUNT,
                                Constants.Specifier.ID
                        )
                );
            }

            accountOptional.get().getCalls().remove(voiceCall);
            accountRepository.save(accountOptional.get());
        }

        if(!StringUtil.stringIsEmptyOrNull(voiceCall.getRelatedCaseId())){
            Optional<Case> caseOptional = caseRepository.findById(voiceCall.getRelatedCaseId());

            if(!caseOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.CASE,
                                Constants.Specifier.ID
                        )
                );
            }

            caseOptional.get().getCalls().remove(voiceCall);
            caseRepository.save(caseOptional.get());
        }

        return voiceCall;
    }

    @Override
    public VoiceCall handleChildReferences(VoiceCall voiceCall){
        return voiceCall;
    }
}
