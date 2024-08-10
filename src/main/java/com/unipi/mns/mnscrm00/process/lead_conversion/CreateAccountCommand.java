package com.unipi.mns.mnscrm00.process.lead_conversion;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;

public class CreateAccountCommand implements Command{
    private Lead lead;
    private Account account;

    public CreateAccountCommand(Lead lead, Account account){
        this.lead = lead;
        this.account = account;
    }

    @Override
    public void execute() {
        account = ObjectMapper.mapLeadToAccount(lead, account);
    }
}
