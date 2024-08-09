package com.unipi.mns.mnscrm00.validations.concretes;

import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import com.unipi.mns.mnscrm00.validations.abstracts.ValidationTemplate;
import org.springframework.stereotype.Component;

@Component
public class OpportunityValidationProcessor extends ValidationTemplate<Opportunity> {
    @Override
    protected void validate(Opportunity entry) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(entry.getTitle()) || StringUtil.stringIsEmptyOrNull(entry.getType()) || StringUtil.stringIsEmptyOrNull(entry.getStatus())){
            throw new DataValidationException("Please provide Title, Type and Status for Opportunity");
        }
    }
}
