package com.unipi.mns.mnscrm00.validations.concretes;

import com.unipi.mns.mnscrm00.entities.data.Case;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.entities.data.Task;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import com.unipi.mns.mnscrm00.validations.abstracts.ValidationTemplate;
import org.springframework.stereotype.Component;

@Component
public class TaskValidationProcessor extends ValidationTemplate<Task> {
    @Override
    protected void validate(Task entry) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(entry.getName()) || StringUtil.stringIsEmptyOrNull(entry.getReason()) || StringUtil.stringIsEmptyOrNull(entry.getStatus())){
            throw new DataValidationException("Please provide Name, Reason and Status for Task");
        }
    }
}
