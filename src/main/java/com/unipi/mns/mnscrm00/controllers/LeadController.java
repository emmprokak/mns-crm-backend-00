package com.unipi.mns.mnscrm00.controllers;

import com.unipi.mns.mnscrm00.dto.abstracts.ContactDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.LeadDTO;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.services.concretes.ContactService;
import com.unipi.mns.mnscrm00.services.concretes.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lead")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @GetMapping("/{id}")
    public LeadDTO getLead(@PathVariable String id){
        return leadService.getLeadByIdSimple(id);
    }

    @GetMapping("/{id}/complete")
    public LeadDTO getLeadWithRels(@PathVariable String id){
        return leadService.getLeadByIdComplete(id);
    }

    @PostMapping("/new")
    public LeadDTO createLead(@RequestBody Lead lead){
        return leadService.insertLead(lead);
    }

    @GetMapping("/all")
    public List<LeadDTO> getLeads(){
        return leadService.getAllLeads();
    }

    @PutMapping("/{id}")
    public LeadDTO updateLead(@PathVariable String id, @RequestBody Lead lead){
        return leadService.updateLead(id, lead);
    }

    @DeleteMapping("/{id}")
    public boolean deleteLead(@PathVariable String id){
        return leadService.deleteLeadById(id);
    }
}
