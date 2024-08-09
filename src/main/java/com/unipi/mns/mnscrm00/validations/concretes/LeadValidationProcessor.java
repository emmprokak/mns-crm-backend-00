package com.unipi.mns.mnscrm00.validations.concretes;

import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import com.unipi.mns.mnscrm00.validations.abstracts.ValidationTemplate;
import org.springframework.stereotype.Component;

@Component
public class LeadValidationProcessor extends ValidationTemplate<Lead> {
    @Override
    protected void validate(Lead entry) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(entry.getCompanyName())){
            throw new DataValidationException("Please provide Company Name for Lead");
        }
    }
}
