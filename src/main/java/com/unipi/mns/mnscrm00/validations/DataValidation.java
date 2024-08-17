package com.unipi.mns.mnscrm00.validations;

import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class DataValidation {

    public void validateAccountData(Account account) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(account.getCompanyName()) || StringUtil.stringIsEmptyOrNull(account.getVat())){
            throw new DataValidationException("Please provide Company Name and VAT");
        }
    }

    public void validateContactData(Contact contact) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(contact.getFirstName()) || StringUtil.stringIsEmptyOrNull(contact.getLastName())){
            throw new DataValidationException("Please provide first name and last name for Contact");
        }
    }

    public void validateLeadData(Lead lead) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(lead.getCompanyName())){
            throw new DataValidationException("Please provide Company Name for Lead");
        }
    }

    public void validateCaseData(Case caseEntry) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(caseEntry.getTitle()) || StringUtil.stringIsEmptyOrNull(caseEntry.getReason()) || StringUtil.stringIsEmptyOrNull(caseEntry.getStatus()) || StringUtil.stringIsEmptyOrNull(caseEntry.getCategory())){
            throw new DataValidationException("Please provide Title, Reason, Category and Status for Case");
        }
    }

    public void validateOpportunityData(Opportunity opportunity) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(opportunity.getTitle()) || StringUtil.stringIsEmptyOrNull(opportunity.getType()) || StringUtil.stringIsEmptyOrNull(opportunity.getStatus())){
            throw new DataValidationException("Please provide Title, Type and Status for Opportunity");
        }
    }

    public void validateTaskData(Task task) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(task.getName()) || StringUtil.stringIsEmptyOrNull(task.getReason()) || StringUtil.stringIsEmptyOrNull(task.getStatus())){
            throw new DataValidationException("Please provide Name, Reason and Status for Task");
        }
    }

    public void validateVoiceCallData(VoiceCall voiceCall) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(voiceCall.getTitle()) || StringUtil.stringIsEmptyOrNull(voiceCall.getCustomerPhone()) || StringUtil.stringIsEmptyOrNull(voiceCall.getAgentName())){
            throw new DataValidationException("Please provide Title, Customer Phone and Agent Name for VoiceCall");
        }
    }


}
