package com.unipi.mns.mnscrm00.services.concretes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.VoiceCallRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.VoiceCallDTO;
import com.unipi.mns.mnscrm00.entities.data.VoiceCall;
import com.unipi.mns.mnscrm00.mapping.RelationshipMapper;
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
public class VoiceCallService {
    @Autowired
    private VoiceCallRepository voiceCallRepository;
    @Autowired
    private InsertUpdateTrigger insertUpdateTrigger;
    @Autowired
    private DeleteTrigger deleteTrigger;

    public VoiceCallDTO getVoiceCallById(String id, boolean getChildrenRelationships){
        Optional<VoiceCall> voiceCallOptional = voiceCallRepository.findById(id);

        if(!voiceCallOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.VOICE_CALL,
                            Constants.Specifier.ID
                    )
            );
        }

        if(getChildrenRelationships){
            return voiceCallOptional.get().toDTOComplete();
        }

        return voiceCallOptional.get().toDTOSimple();
    }

    public VoiceCallDTO insertVoiceCall(VoiceCall voiceCall){
        VoiceCall voiceCallToInsert = new VoiceCall();
        voiceCallToInsert = insertUpdateTrigger.handleVoiceCallEntry(voiceCall, voiceCallToInsert, true);

        return voiceCallRepository.save(voiceCallToInsert).toDTOSimple();
    }

    public List<VoiceCallDTO> getAllVoiceCalls(){
        List<VoiceCall> taskList = voiceCallRepository.findAll();

        if(taskList.size() <= 0){
            return new ArrayList<>();
        }

        return ListConverter.convertVoiceCallsToDTOList(taskList, Constants.DTO.CONVERT_TO_DTO_SIMPLE);
    }

    public VoiceCallDTO updateVoiceCall(String id, VoiceCall voiceCall){
        Optional<VoiceCall> voiceCallOptional = voiceCallRepository.findById(id);

        if(!voiceCallOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.VOICE_CALL,
                            Constants.Specifier.ID
                    )
            );
        }

        VoiceCall taskToUpdate = voiceCallOptional.get();
        taskToUpdate = insertUpdateTrigger.handleVoiceCallEntry(voiceCall, taskToUpdate, false);

        return voiceCallRepository.save(taskToUpdate).toDTOSimple();
    }

    public boolean deleteVoiceCallById(String id){
        Optional<VoiceCall> voiceCallOptional = voiceCallRepository.findById(id);

        if(!voiceCallOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.VOICE_CALL,
                            Constants.Specifier.ID
                    )
            );
        }

        deleteTrigger.handleReferenceDeletion(voiceCallOptional.get());
        voiceCallRepository.delete(voiceCallOptional.get());

        return true;
    }
}
