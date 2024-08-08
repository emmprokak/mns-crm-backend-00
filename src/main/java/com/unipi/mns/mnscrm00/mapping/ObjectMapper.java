package com.unipi.mns.mnscrm00.mapping;

import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ObjectMapper {

    public static Account mapAccountFields(Account source, Account target){
        Account result = target;

        result.setActive(source.isActive());
        result.setBillingAddress(source.getBillingAddress());
        result.setClientRating(source.getClientRating());
        result.setCompanyName(source.getCompanyName());
        result.setDescription(source.getDescription());
        result.setIndustry(source.getIndustry());
        result.setRelatedLead(source.getRelatedLead());
        result.setRevenue(source.getRevenue());
        result.setVat(source.getVat());
        result.setWebsite(source.getWebsite());
        result.setType(source.getType());

        return result;
    }

    public static Contact mapContactFields(Contact source, Contact target){
        Contact result = target;

        if(source.getBirthdate() != null){
            result.setBirthdate(source.getBirthdate());
        }

        result.setActive(source.isActive());
        result.setAccount(source.getAccount());
        result.setEmail(source.getEmail());
        result.setFirstName(source.getFirstName());
        result.setLastName(source.getLastName());
        result.setPhone(source.getPhone());
        result.setDepartment(source.getDepartment());
        result.setPrefix(source.getPrefix());
        result.setMobile(source.getMobile());
        result.setRole(source.getRole());

        return result;
    }


    public static Lead mapLeadFields(Lead source, Lead target){
        Lead result = target;

        result.setCompanyAddress(source.getCompanyAddress());
        result.setCompanyIndustry(source.getCompanyIndustry());
        result.setCompanyName(source.getCompanyName());
        result.setContactEmail(source.getContactEmail());
        result.setContactMobile(source.getContactMobile());
        result.setContactPhone(source.getContactPhone());
        result.setContactRole(source.getContactRole());
        result.setContactPrefix(source.getContactPrefix());
        result.setStatus(source.getStatus());
        result.setContactPerson(source.getContactPerson());

        return result;
    }

    public static Opportunity mapOpportunityFields(Opportunity source, Opportunity target){
        Opportunity result = target;

        result.setStatus(source.getStatus());
        result.setTitle(source.getTitle());
        result.setExpectedRevenue(source.getExpectedRevenue());
        result.setComments(source.getComments());
        result.setType(source.getType());
        result.setDescription(source.getDescription());

        return result;
    }

    public static Task mapTaskFields(Task source, Task target){
        Task result = target;

        result.setStatus(source.getStatus());
        result.setDueDate(source.getDueDate());
        result.setName(source.getName());
        result.setReason(source.getReason());
        result.setStatus(source.getStatus());
        result.setType(source.getType());

        return result;
    }

    public static Case mapCaseFields(Case source, Case target, Boolean isInsert){
        Case result = target;

        result.setCategory(source.getCategory());
        result.setReason(source.getReason());
        result.setStatus(source.getStatus());
        result.setSeverity(source.getSeverity());
        result.setSource(source.getSource());
        result.setTitle(source.getTitle());

        if(isInsert){
            result.setCreationDate(Date.valueOf(LocalDate.now().plusDays(1)));
        }

        if(!isInsert &&
                StringUtil.stringsAreEqual(source.getStatus(), "Closed") &&
                !StringUtil.stringsAreEqual(target.getStatus(), "Closed")){
            result.setClosedDate(Date.valueOf(LocalDate.now().plusDays(1)));
        }

        return result;
    }

    public static Account mapLeadToAccount(Lead lead, Account account){
        Account result = account;

        result.setCompanyName(lead.getCompanyName());
        result.setBillingAddress(lead.getCompanyAddress());
        result.setIndustry(lead.getCompanyIndustry());
        result.setActive(true);

        return result;
    }

    public static Contact mapLeadToContact(Lead lead, Contact contact){
        Contact result = contact;

        List<String> nameComponents = Arrays.asList(lead.getContactPerson().split(" "));
        if(nameComponents.size() > 1){
            result.setFirstName(nameComponents.get(0));
            result.setLastName(nameComponents.get(1));
        }else if(nameComponents.size() == 1){
            result.setFirstName(nameComponents.get(0));
            result.setLastName(nameComponents.get(0));
        }else{
            result.setFirstName("NONAME");
            result.setFirstName("NONAME");
        }

        result.setRole(lead.getContactRole());
        result.setMobile(lead.getContactMobile());
        result.setPhone(lead.getContactPhone());
        result.setEmail(lead.getContactEmail());
        result.setPrefix(lead.getContactPrefix());
        result.setActive(true);

        return result;
    }

    public static Opportunity mapLeadToOpportunity(Lead lead, Opportunity opportunity){
        Opportunity result = opportunity;

        result.setStatus("New");
        result.setTitle(lead.getCompanyName() + " " + "Opportunity");
        return result;
    }

    public static VoiceCall mapVoiceCallFields (VoiceCall source, VoiceCall target){
        VoiceCall result = target;

        target.setAgentName(source.getAgentName());
        target.setCallDate(source.getCallDate());
        target.setDuration(source.getDuration());
        target.setCustomerPhone(source.getCustomerPhone());
        target.setTitle(source.getTitle());

        return result;
    }

}
