package com.unipi.mns.mnscrm00.mapping;

import com.unipi.mns.mnscrm00.entities.builders.*;
import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ObjectMapper {

    public static Account mapAccountFields(Account source, Account target){
        return new AccountBuilder(target)
                .setActive(source.isActive())
                .setBillingAddress(source.getBillingAddress())
                .setClientRating(source.getClientRating())
                .setCompanyName(source.getCompanyName())
                .setDescription(source.getDescription())
                .setIndustry(source.getIndustry())
                .setRevenue(source.getRevenue())
                .setLeadId(source.getRelatedLeadId())
                .setVat(source.getVat())
                .setWebsite(source.getWebsite())
                .setType(source.getType())
                .build();
    }

    public static Contact mapContactFields(Contact source, Contact target){
        return new ContactBuilder(target)
            .setBirthdate(source.getBirthdate())
            .setActive(source.isActive())
            .setAccount(source.getAccount())
            .setEmail(source.getEmail())
            .setFirstName(source.getFirstName())
            .setLastName(source.getLastName())
            .setPhone(source.getPhone())
            .setDepartment(source.getDepartment())
            .setPrefix(source.getPrefix())
            .setMobile(source.getMobile())
            .setRole(source.getRole())
            .build();
    }


    public static Lead mapLeadFields(Lead source, Lead target){
        return new LeadBuilder(target)
            .setCompanyAddress(source.getCompanyAddress())
            .setCompanyIndustry(source.getCompanyIndustry())
            .setCompanyName(source.getCompanyName())
            .setContactEmail(source.getContactEmail())
            .setContactMobile(source.getContactMobile())
            .setContactPhone(source.getContactPhone())
            .setContactRole(source.getContactRole())
            .setContactPrefix(source.getContactPrefix())
            .setStatus(source.getStatus())
            .setContactPerson(source.getContactPerson())
            .build();
    }

    public static Opportunity mapOpportunityFields(Opportunity source, Opportunity target){
        return new OpportunityBuilder(target)
            .setStatus(source.getStatus())
            .setTitle(source.getTitle())
            .setExpectedRevenue(source.getExpectedRevenue())
            .setComments(source.getComments())
            .setType(source.getType())
            .setDescription(source.getDescription())
            .build();
    }

    public static Task mapTaskFields(Task source, Task target){
        return new TaskBuilder(target)
            .setStatus(source.getStatus())
            .setDueDate(source.getDueDate())
            .setName(source.getName())
            .setReason(source.getReason())
            .setStatus(source.getStatus())
            .setType(source.getType())
            .build();
    }

    public static Case mapCaseFields(Case source, Case target, Boolean isInsert){
        CaseBuilder caseBuilder = new CaseBuilder(target);

        String previousCaseStatus = target.getStatus();

        caseBuilder.setStatus(source.getStatus())
            .setCategory(source.getCategory())
            .setReason(source.getReason())
            .setSeverity(source.getSeverity())
            .setSource(source.getSource())
            .setTitle(source.getTitle());

        if(isInsert){
            caseBuilder.setCreationDate(Date.valueOf(LocalDate.now().plusDays(1)));
        }

        if(!isInsert &&
                StringUtil.stringsAreEqual(source.getStatus(), "Closed") &&
                !StringUtil.stringsAreEqual(previousCaseStatus, "Closed")){
            caseBuilder.setClosedDate(Date.valueOf(LocalDate.now()));
        }

        return caseBuilder.build();
    }

    public static VoiceCall mapVoiceCallFields (VoiceCall source, VoiceCall target){
        return new VoiceCallBuilder(target)
                .setAgentName(source.getAgentName())
                .setCallDate(source.getCallDate())
                .setDuration(source.getDuration())
                .setCustomerPhone(source.getCustomerPhone())
                .setTitle(source.getTitle())
                .build();
    }

    public static Account mapLeadToAccount(Lead lead, Account account){
        return new AccountBuilder(account)
            .setCompanyName(lead.getCompanyName())
            .setBillingAddress(lead.getCompanyAddress())
            .setIndustry(lead.getCompanyIndustry())
            .setActive(true)
            .build();
    }

    public static Contact mapLeadToContact(Lead lead, Contact contact){
        ContactBuilder contactBuilder = new ContactBuilder(contact);

        List<String> nameComponents = Arrays.asList(lead.getContactPerson().split(" "));
        if(nameComponents.size() > 1){
            contactBuilder.setFirstName(nameComponents.get(0));
            contactBuilder.setLastName(nameComponents.get(1));
        }else if(nameComponents.size() == 1){
            contactBuilder.setFirstName(nameComponents.get(0));
            contactBuilder.setLastName(nameComponents.get(0));
        }else{
            contactBuilder.setFirstName("NONAME");
            contactBuilder.setFirstName("NONAME");
        }

        return contactBuilder
            .setRole(lead.getContactRole())
            .setMobile(lead.getContactMobile())
            .setPhone(lead.getContactPhone())
            .setEmail(lead.getContactEmail())
            .setPrefix(lead.getContactPrefix())
            .setActive(true)
            .build();
    }

    public static Opportunity mapLeadToOpportunity(Lead lead, Opportunity opportunity){
        return new OpportunityBuilder(opportunity)
                .setStatus("New")
                .setTitle(lead.getCompanyName() + " " + "Opportunity")
                .build();
    }
}
