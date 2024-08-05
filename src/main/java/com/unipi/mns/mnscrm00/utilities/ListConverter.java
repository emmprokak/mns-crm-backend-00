package com.unipi.mns.mnscrm00.utilities;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dto.abstracts.*;
import com.unipi.mns.mnscrm00.entities.abstracts.Sendable;
import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.exceptions.ListConversionException;

import java.util.ArrayList;
import java.util.List;

public class ListConverter {

    public static List<AccountDTO> convertAccountsToDTOList(List<Account> accounts, int conversionType) {
        List<AccountDTO> dtos = new ArrayList<>();

        switch(conversionType){
            case Constants.DTO.CONVERT_TO_DTO_MINIMAL:
                accounts.forEach(acc -> dtos.add(acc.toDTOMinimal()));
                break;
            case Constants.DTO.CONVERT_TO_DTO_SIMPLE:
                accounts.forEach(acc -> dtos.add(acc.toDTOSimple()));
                break;
            case Constants.DTO.CONVERT_TO_DTO_COMPLETE:
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
            case Constants.DTO.CONVERT_TO_DTO_MINIMAL:
                contacts.forEach(con -> dtos.add(con.toDTOMinimal()));
                break;
            case Constants.DTO.CONVERT_TO_DTO_SIMPLE:
                contacts.forEach(con -> dtos.add(con.toDTOSimple()));
                break;
            case Constants.DTO.CONVERT_TO_DTO_COMPLETE:
                contacts.forEach(con -> dtos.add(con.toDTOComplete()));
                break;
            default:
                //throw new ListConversionException("Invalid Mapping for Converting to List to DTO List");
        }

        return dtos;
    }

    public static List<LeadDTO> convertLeadsToDTOList(List<Lead> leads, int conversionType){
        List<LeadDTO> dtos = new ArrayList<>();

        switch(conversionType){
            case Constants.DTO.CONVERT_TO_DTO_MINIMAL:
                leads.forEach(lead -> dtos.add(lead.toDTOMinimal()));
                break;
            case Constants.DTO.CONVERT_TO_DTO_SIMPLE:
                leads.forEach(lead -> dtos.add(lead.toDTOSimple()));
                break;
            case Constants.DTO.CONVERT_TO_DTO_COMPLETE:
                leads.forEach(lead -> dtos.add(lead.toDTOComplete()));
                break;
            default:
                //throw new ListConversionException("Invalid Mapping for Converting to List to DTO List");
        }

        return dtos;
    }

    public static List<OpportunityDTO> convertOpportunitiesToDTOList(List<Opportunity> opportunities, int conversionType){
        List<OpportunityDTO> dtos = new ArrayList<>();

        switch(conversionType){
            case Constants.DTO.CONVERT_TO_DTO_MINIMAL:
                opportunities.forEach(opp -> dtos.add(opp.toDTOMinimal()));
                break;
            case Constants.DTO.CONVERT_TO_DTO_SIMPLE:
                opportunities.forEach(opp -> dtos.add(opp.toDTOSimple()));
                break;
            case Constants.DTO.CONVERT_TO_DTO_COMPLETE:
                opportunities.forEach(opp -> dtos.add(opp.toDTOComplete()));
                break;
            default:
                //throw new ListConversionException("Invalid Mapping for Converting to List to DTO List");
        }

        return dtos;
    }

    public static List<TaskDTO> convertTasksToDTOList(List<Task> tasks, int conversionType){
        List<TaskDTO> dtos = new ArrayList<>();

        switch(conversionType){
            case Constants.DTO.CONVERT_TO_DTO_MINIMAL:
                tasks.forEach(task -> dtos.add(task.toDTOMinimal()));
                break;
            case Constants.DTO.CONVERT_TO_DTO_SIMPLE:
                tasks.forEach(task -> dtos.add(task.toDTOSimple()));
                break;
            case Constants.DTO.CONVERT_TO_DTO_COMPLETE:
                tasks.forEach(task -> dtos.add(task.toDTOComplete()));
                break;
            default:
                //throw new ListConversionException("Invalid Mapping for Converting to List to DTO List");
        }

        return dtos;
    }

    public static List<CaseDTO> convertCasesToDTOList(List<Case> cases, int conversionType){
        List<CaseDTO> dtos = new ArrayList<>();

        switch(conversionType){
            case Constants.DTO.CONVERT_TO_DTO_MINIMAL:
                cases.forEach(c -> dtos.add(c.toDTOMinimal()));
                break;
            case Constants.DTO.CONVERT_TO_DTO_SIMPLE:
                cases.forEach(c -> dtos.add(c.toDTOSimple()));
                break;
            case Constants.DTO.CONVERT_TO_DTO_COMPLETE:
                cases.forEach(c -> dtos.add(c.toDTOComplete()));
                break;
            default:
                //throw new ListConversionException("Invalid Mapping for Converting to List to DTO List");
        }

        return dtos;
    }

}
