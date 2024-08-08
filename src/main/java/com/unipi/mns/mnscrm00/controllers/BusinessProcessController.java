package com.unipi.mns.mnscrm00.controllers;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.LeadRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.EntityDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.OpportunityDTO;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.process.BusinessProcess;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/process")
public class BusinessProcessController {

    @Autowired
    private BusinessProcess businessProcess;

    @PostMapping ("/lead-conversion/{leadId}")
    public List<EntityDTO> convertLead(@PathVariable String leadId){
        return businessProcess.leadConversion(leadId);
    }
}
