package com.unipi.mns.mnscrm00.triggers.delete;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.triggers.delete.entity_deletion.AccountDeletionHandler;
import com.unipi.mns.mnscrm00.triggers.delete.entity_deletion.ContactDeletionHandler;
import com.unipi.mns.mnscrm00.triggers.delete.entity_deletion.OpportunityDeletionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeletionHandlerConfig {

    @Bean
    public Map<Class<?>, DeletionHandler<?>> deletionHandlers(AccountDeletionHandler accountHandler,
                                                              ContactDeletionHandler contactHandler,
                                                              OpportunityDeletionHandler opportunityHandler
   ) {
        Map<Class<?>, DeletionHandler<?>> handlers = new HashMap<>();
        handlers.put(Account.class, accountHandler);
        handlers.put(Contact.class, contactHandler);
        handlers.put(Opportunity.class, opportunityHandler);
        return handlers;
    }
}
