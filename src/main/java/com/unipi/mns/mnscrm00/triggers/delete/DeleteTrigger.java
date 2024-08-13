package com.unipi.mns.mnscrm00.triggers.delete;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.dal.ContactRepository;
import com.unipi.mns.mnscrm00.dal.LeadRepository;
import com.unipi.mns.mnscrm00.dal.OpportunityRepository;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class DeleteTrigger {

    private final Map<Class<?>, DeletionHandler<?>> handlers;

    @Autowired
    public DeleteTrigger(Map<Class<?>, DeletionHandler<?>> handlers) {
        this.handlers = handlers;
    }

    @SuppressWarnings("unchecked")
    public <T> void handleReferenceDeletion(T entity) {
        DeletionHandler<T> handler = (DeletionHandler<T>) handlers.get(entity.getClass());
        if (handler != null) {
            handler.delete(entity);
        } else {
            throw new IllegalArgumentException(
                    "No handler found for Entity: " + entity.getClass().getName()
            );
        }
    }
}
