package com.unipi.mns.mnscrm00.validations.abstracts;

import com.unipi.mns.mnscrm00.exceptions.DataValidationException;

public abstract class ValidationTemplate<E> {

    public final void beforeSaveProcessing(E entry) throws DataValidationException {
        validate(entry);
        beforeSave(entry);
    }

    protected abstract void validate(E entry) throws DataValidationException;

    protected void beforeSave(E entry){
        return;
    }
}
