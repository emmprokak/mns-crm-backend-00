package com.unipi.mns.mnscrm00.validations;

import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.validations.abstracts.ValidationTemplate;
import com.unipi.mns.mnscrm00.validations.concretes.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValidationHandler {

    private final Map<Class<?>, ValidationTemplate<?>> validators = new HashMap<>();

    public ValidationHandler() {
        validators.put(Account.class, new AccountValidationProcessor());
        validators.put(Contact.class, new ContactValidationProcessor());
        validators.put(Lead.class, new LeadValidationProcessor());
        validators.put(Task.class, new TaskValidationProcessor());
        validators.put(Opportunity.class, new OpportunityValidationProcessor());
        validators.put(VoiceCall.class, new VoiceCallValidationProcessor());
        validators.put(Case.class, new CaseValidationProcessor());
    }

    public <T> void validate(T entity) throws DataValidationException {
        ValidationTemplate<T> validator = (ValidationTemplate<T>) validators.get(entity.getClass());

        if (validator != null) {
            validator.beforeSaveProcessing(entity);
        }else{
            throw new IllegalArgumentException("No validator found for " + entity.getClass().getSimpleName());
        }
    }
}
