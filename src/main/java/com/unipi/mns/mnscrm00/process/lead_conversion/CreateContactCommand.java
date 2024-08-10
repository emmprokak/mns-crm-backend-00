package com.unipi.mns.mnscrm00.process.lead_conversion;

import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;

public class CreateContactCommand implements Command{
    private Lead lead;
    private Contact contact;

    public CreateContactCommand(Lead lead, Contact contact){
        this.lead = lead;
        this.contact = contact;
    }

    @Override
    public void execute() {
        contact = ObjectMapper.mapLeadToContact(lead, contact);
    }
}
