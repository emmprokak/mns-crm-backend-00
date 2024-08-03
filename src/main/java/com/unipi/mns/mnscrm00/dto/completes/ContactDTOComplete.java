package com.unipi.mns.mnscrm00.dto.completes;

import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.ContactDTO;
import com.unipi.mns.mnscrm00.dto.simples.ContactDTOSimple;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Case;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactDTOComplete extends ContactDTOSimple {

    private List<Case> cases;

    public ContactDTOComplete(List<Case> cases, Account account, Date birthdate, String department, String email, String firstName, String id, boolean isActive, String lastName, String mobile, String phone, String prefix, String role, LocalDateTime created, LocalDateTime modified) {
        super(account, birthdate, department, email, firstName, id, isActive, lastName, mobile, phone, prefix, role, created, modified);
        this.cases = cases;
    }

    public ContactDTOComplete(){

    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }
}
