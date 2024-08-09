package com.unipi.mns.mnscrm00.validations.concretes;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import com.unipi.mns.mnscrm00.validations.abstracts.ValidationTemplate;
import org.springframework.stereotype.Component;

@Component
public class ContactValidationProcessor extends ValidationTemplate<Contact> {
    @Override
    protected void validate(Contact entry) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(entry.getFirstName()) || StringUtil.stringIsEmptyOrNull(entry.getLastName())){
            throw new DataValidationException("Please provide first name and last name for Contact");
        }
    }
}
