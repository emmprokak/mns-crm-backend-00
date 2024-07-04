package com.unipi.mns.mnscrm00.dto.simples;

import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.ContactDTO;
import com.unipi.mns.mnscrm00.dto.minimals.ContactDTOMinimal;
import com.unipi.mns.mnscrm00.entities.data.Account;

import java.util.Date;

public class ContactDTOSimple extends ContactDTOMinimal {
    private Account parent;

    public ContactDTOSimple() {
        super();
    }

    public ContactDTOSimple(Account account, Date birthdate, String department, String email, String firstName, String id, boolean isActive, String lastName, String mobile, String phone, String prefix, String role) {
        super(account, birthdate, department, email, firstName, id, isActive, lastName, mobile, phone, prefix, role);

        if(parent != null){
            this.parent = account;
        }
    }
}
