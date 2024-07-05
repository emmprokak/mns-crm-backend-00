package com.unipi.mns.mnscrm00.utilities;

import com.unipi.mns.mnscrm00.constants.dto.ConstantDTOs;
import com.unipi.mns.mnscrm00.dto.abstracts.AccountDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.ContactDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.EntityDTO;
import com.unipi.mns.mnscrm00.entities.abstracts.Sendable;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.exceptions.ListConversionException;

import java.util.ArrayList;
import java.util.List;

public class ListConverter {

    public static List<AccountDTO> convertAccountsToDTOList(List<Account> accounts, int conversionType) {
        List<AccountDTO> dtos = new ArrayList<>();

        switch(conversionType){
            case ConstantDTOs.CONVERT_TO_DTO_MINIMAL:
                accounts.forEach(acc -> dtos.add(acc.toDTOMinimal()));
                break;
            case ConstantDTOs.CONVERT_TO_DTO_SIMPLE:
                accounts.forEach(acc -> dtos.add(acc.toDTOSimple()));
                break;
            case ConstantDTOs.CONVERT_TO_DTO_COMPLETE:
                accounts.forEach(acc -> dtos.add(acc.toDTOComplete()));
                break;
            default:
                //throw new ListConversionException("Invalid Mapping for Converting to List to DTO List");
        }

        return dtos;
    }

    public static List<ContactDTO> convertContactsToDTOList(List<Contact> contacts, int conversionType){
        List<ContactDTO> dtos = new ArrayList<>();

        switch(conversionType){
            case ConstantDTOs.CONVERT_TO_DTO_MINIMAL:
                contacts.forEach(acc -> dtos.add(acc.toDTOMinimal()));
                break;
            case ConstantDTOs.CONVERT_TO_DTO_SIMPLE:
                contacts.forEach(acc -> dtos.add(acc.toDTOSimple()));
                break;
            case ConstantDTOs.CONVERT_TO_DTO_COMPLETE:
                contacts.forEach(acc -> dtos.add(acc.toDTOComplete()));
                break;
            default:
                //throw new ListConversionException("Invalid Mapping for Converting to List to DTO List");
        }

        return dtos;
    }

}
