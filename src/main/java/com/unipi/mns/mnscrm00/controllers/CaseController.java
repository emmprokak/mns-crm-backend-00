package com.unipi.mns.mnscrm00.controllers;

import com.unipi.mns.mnscrm00.dto.abstracts.CaseDTO;
import com.unipi.mns.mnscrm00.entities.data.Case;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.services.concretes.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/case")
public class CaseController {
    @Autowired
    private CaseService caseService;


    @GetMapping("/{id}")
    public CaseDTO getCaseById(@PathVariable String id){
        return caseService.getCaseById(id, false);
    }

    @GetMapping("/{id}/complete")
    public CaseDTO getCaseWithRels(@PathVariable String id){
        return caseService.getCaseById(id, true);
    }

    @PostMapping("/new")
    public CaseDTO createCase(@RequestBody Case caseEntry) throws DataValidationException {
        return caseService.insertCase(caseEntry);
    }

    @GetMapping("/all")
    public List<CaseDTO> getCases(){
        return caseService.getAllCases();
    }

    @PutMapping("/{id}")
    public CaseDTO updateCase(@PathVariable String id, @RequestBody Case caseEntry) throws DataValidationException {
        return caseService.updateCase(id, caseEntry);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCase(@PathVariable String id){
        return caseService.deleteCaseById(id);
    }

}
