package com.unipi.mns.mnscrm00.process.lead_conversion;

import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;

public class CreateOpportunityCommand implements Command{
    private Lead lead;
    private Opportunity opportunity;

    public CreateOpportunityCommand(Lead lead, Opportunity opportunity){
        this.lead = lead;
        this.opportunity = opportunity;
    }

    @Override
    public void execute() {
        opportunity = ObjectMapper.mapLeadToOpportunity(lead, opportunity);
    }
}
