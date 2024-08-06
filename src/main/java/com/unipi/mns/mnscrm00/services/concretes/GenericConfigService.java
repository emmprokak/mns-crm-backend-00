package com.unipi.mns.mnscrm00.services.concretes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.GenericConfigRepository;
import com.unipi.mns.mnscrm00.entities.config.GenericConfigEntity;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenericConfigService {
    @Autowired
    private GenericConfigRepository genericConfigRepository;

    public GenericConfigEntity getGenericConfigEntityById(String id){
        Optional<GenericConfigEntity> configOptional = genericConfigRepository.findById(id);

        if(!configOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.CONFIG,
                            Constants.Specifier.ID
                    )
            );
        }

        return configOptional.get();
    }
    
    public GenericConfigEntity insertGenericConfigEntity(GenericConfigEntity gce){
        return genericConfigRepository.save(gce);
    }

    public List<GenericConfigEntity> getAllGenericConfigEntitys(){
        List<GenericConfigEntity> configEntityList = genericConfigRepository.findAll();

        if(configEntityList.size() <= 0){
            return new ArrayList<>();
        }

        return configEntityList;
    }

    public List<GenericConfigEntity> getAllGenericConfigEntitiesByType(String type){
        List<GenericConfigEntity> configEntityList = genericConfigRepository.findByType(type);

        if(configEntityList.size() <= 0){
            return new ArrayList<>();
        }

        return configEntityList;
    }
    

    public boolean deleteGenericConfigEntityById(String id){
        Optional<GenericConfigEntity> configOptional = genericConfigRepository.findById(id);

        if(!configOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.CONFIG,
                            Constants.Specifier.ID
                    )
            );
        }

        genericConfigRepository.delete(configOptional.get());

        return true;
    }
}
