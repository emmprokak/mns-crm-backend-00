package com.unipi.mns.mnscrm00.validations.concretes;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import com.unipi.mns.mnscrm00.validations.abstracts.ValidationTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountValidationProcessor extends ValidationTemplate<Account> {
    @Override
    @SuppressWarnings("unchecked")
    protected void validate(Account entry) throws DataValidationException {
        if(StringUtil.stringIsEmptyOrNull(entry.getCompanyName()) || StringUtil.stringIsEmptyOrNull(entry.getVat())){
            throw new DataValidationException("Please provide Company Name and VAT");
        }
    }
}
