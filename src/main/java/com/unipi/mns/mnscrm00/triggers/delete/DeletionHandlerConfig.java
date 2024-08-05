package com.unipi.mns.mnscrm00.triggers.delete;

import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.triggers.delete.entity_deletion.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeletionHandlerConfig {

    @Bean
    public Map<Class<?>, DeletionHandler<?>> deletionHandlers(AccountDeletionHandler accountHandler,
                                                              ContactDeletionHandler contactHandler,
                                                              OpportunityDeletionHandler opportunityHandler,
                                                              LeadDeletionHandler leadHandler,
                                                              TaskDeletionHandler taskHandler,
                                                              CaseDeletionHandler caseHandler
   ) {
        Map<Class<?>, DeletionHandler<?>> handlers = new HashMap<>();
        handlers.put(Account.class, accountHandler);
        handlers.put(Contact.class, contactHandler);
        handlers.put(Opportunity.class, opportunityHandler);
        handlers.put(Lead.class, leadHandler);
        handlers.put(Task.class, taskHandler);
        handlers.put(Case.class, caseHandler);
        return handlers;
    }
}
