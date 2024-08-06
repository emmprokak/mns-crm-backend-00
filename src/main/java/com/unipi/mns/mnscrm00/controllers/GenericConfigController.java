package com.unipi.mns.mnscrm00.controllers;

import com.unipi.mns.mnscrm00.entities.config.GenericConfigEntity;
import com.unipi.mns.mnscrm00.services.concretes.GenericConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/config")
public class GenericConfigController {

    @Autowired
    private GenericConfigService genericConfigService;

    @GetMapping("/{id}")
    public GenericConfigEntity getGenericConfigEntity(@PathVariable String id){
        return genericConfigService.getGenericConfigEntityById(id);
    }

    @PostMapping("/new")
    public GenericConfigEntity createGenericConfigEntity(@RequestBody GenericConfigEntity configEntity){
        return genericConfigService.insertGenericConfigEntity(configEntity);
    }

    @GetMapping("/all")
    public List<GenericConfigEntity> getGenericConfigEntities(){
        return genericConfigService.getAllGenericConfigEntitys();
    }

    @GetMapping("/all/{type}")
    public List<GenericConfigEntity> getGenericConfigEntitiesByType(@PathVariable String type){
        return genericConfigService.getAllGenericConfigEntitiesByType(type);
    }

    @DeleteMapping("/{id}")
    public boolean deleteGenericConfigEntity(@PathVariable String id){
        return genericConfigService.deleteGenericConfigEntityById(id);
    }
}
