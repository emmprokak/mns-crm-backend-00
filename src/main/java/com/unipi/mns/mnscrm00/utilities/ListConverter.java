package com.unipi.mns.mnscrm00.utilities;

import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.ContactDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.EntityDTO;
import com.unipi.mns.mnscrm00.entities.abstracts.Sendable;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;

import java.util.ArrayList;
import java.util.List;

public class ListConverter {

    public static List<AccountDTO> convertAccountsToDTOList(List<Account> accounts){
        List<AccountDTO> dtos = new ArrayList<>();
        accounts.forEach(acc -> dtos.add(acc.toDTOSimple()));

        return dtos;
    }

    public static List<ContactDTO> convertContactsToDTOList(List<Contact> contacts){
        List<ContactDTO> dtos = new ArrayList<>();
        contacts.forEach(con -> dtos.add(con.toDTOMinimal()));

        return dtos;
    }

}
