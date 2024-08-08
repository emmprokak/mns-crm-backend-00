package com.unipi.mns.mnscrm00.controllers;

import com.unipi.mns.mnscrm00.dto.abstracts.OpportunityDTO;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.services.concretes.ContactService;
import com.unipi.mns.mnscrm00.services.concretes.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunity")
public class OpportunityController {

    @Autowired
    private OpportunityService opportunityService;


    @GetMapping("/{id}")
    public OpportunityDTO getOpportunity(@PathVariable String id){
        return opportunityService.getOpportunityById(id, false);
    }

    @GetMapping("/{id}/complete")
    public OpportunityDTO getOpportunityWithRels(@PathVariable String id){
        return opportunityService.getOpportunityById(id, true);
    }

    @PostMapping("/new")
    public OpportunityDTO createOpportunity(@RequestBody Opportunity opportunity){
        return opportunityService.insertOpportunity(opportunity);
    }

    @GetMapping("/all")
    public List<OpportunityDTO> getOpportunities(){
        return opportunityService.getAllOpportunities();
    }

    @PutMapping("/{id}")
    public OpportunityDTO updateOpportunity(@PathVariable String id, @RequestBody Opportunity opportunity){
        return opportunityService.updateOpportunity(id, opportunity);
    }

    @DeleteMapping("/{id}")
    public boolean deleteOpportunity(@PathVariable String id){
        return opportunityService.deleteOpportunityById(id);
    }

}
