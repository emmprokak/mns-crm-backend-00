package com.unipi.mns.mnscrm00.validations.concretes;

import com.unipi.mns.mnscrm00.entities.data.Case;
import com.unipi.mns.mnscrm00.entities.data.VoiceCall;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import com.unipi.mns.mnscrm00.validations.abstracts.ValidationTemplate;
import org.springframework.stereotype.Component;

@Component
public class VoiceCallValidationProcessor extends ValidationTemplate<VoiceCall> {
    @Override
    protected void validate(VoiceCall entry) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(entry.getTitle()) || StringUtil.stringIsEmptyOrNull(entry.getCustomerPhone()) || StringUtil.stringIsEmptyOrNull(entry.getAgentName())){
            throw new DataValidationException("Please provide Title, Customer Phone and Agent Name for VoiceCall");
        }
    }
}
