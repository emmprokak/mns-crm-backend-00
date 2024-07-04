package com.unipi.mns.mnscrm00.dto.minimals;

import com.unipi.mns.mnscrm00.dto.abstracts.ContactDTO;
import com.unipi.mns.mnscrm00.entities.data.Account;

import java.util.Date;

public class ContactDTOMinimal extends ContactDTO {

    public ContactDTOMinimal() {
        super();
    }

    public ContactDTOMinimal(Account account, Date birthdate, String department, String email, String firstName, String id, boolean isActive, String lastName, String mobile, String phone, String prefix, String role) {
        super(account, birthdate, department, email, firstName, id, isActive, lastName, mobile, phone, prefix, role);
    }
}
