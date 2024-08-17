package com.unipi.mns.mnscrm00.triggers.delete.entity_deletion;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.dal.TaskRepository;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.entities.data.Task;
import com.unipi.mns.mnscrm00.triggers.delete.DeletionHandler;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class OpportunityDeletionHandler implements DeletionHandler<Opportunity> {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Opportunity delete(Opportunity opportunity) {
        opportunity = handleParentReferences(opportunity);
        opportunity = handleChildReferences(opportunity);
        return opportunity;
    }

    @Override
    public Opportunity handleParentReferences(Opportunity opportunity){
        if(!StringUtil.stringIsEmptyOrNull(opportunity.getRelatedAccountId())){
            Optional<Account> accountOptional = accountRepository.findById(opportunity.getRelatedAccountId());

            if(!accountOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.ACCOUNT,
                                Constants.Specifier.ID
                        )
                );
            }

            accountOptional.get().getOpportunities().remove(opportunity);
            accountRepository.save(accountOptional.get());
        }

        return opportunity;
    }

    @Override
    public Opportunity handleChildReferences(Opportunity opportunity){

        List<Task> taskList = taskRepository.findByRelatedOpportunityId(opportunity.getId());
        for(Task task : taskList){
            task.setRelatedOpportunity(null);
            task.setRelatedOpportunityId(null);
            taskRepository.save(task);
        }

        return opportunity;
    }
}
