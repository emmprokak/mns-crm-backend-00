package com.unipi.mns.mnscrm00.validations.concretes;

import com.unipi.mns.mnscrm00.entities.data.Case;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import com.unipi.mns.mnscrm00.validations.abstracts.ValidationTemplate;
import org.springframework.stereotype.Component;

@Component
public class CaseValidationProcessor extends ValidationTemplate<Case> {
    @Override
    protected void validate(Case entry) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(entry.getTitle()) || StringUtil.stringIsEmptyOrNull(entry.getReason()) || StringUtil.stringIsEmptyOrNull(entry.getStatus()) || StringUtil.stringIsEmptyOrNull(entry.getCategory())){
            throw new DataValidationException("Please provide Title, Reason, Category and Status for Case");
        }
    }
}
