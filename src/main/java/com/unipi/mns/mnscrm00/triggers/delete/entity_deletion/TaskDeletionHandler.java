package com.unipi.mns.mnscrm00.triggers.delete.entity_deletion;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.dal.LeadRepository;
import com.unipi.mns.mnscrm00.dal.OpportunityRepository;
import com.unipi.mns.mnscrm00.dal.TaskRepository;
import com.unipi.mns.mnscrm00.entities.data.*;
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
public class TaskDeletionHandler implements DeletionHandler<Task> {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;

    @Override
    public Task delete(Task task) {
        task = handleParentReferences(task);
        task = handleChildReferences(task);
        return task;
    }

    @Override
    public Task handleParentReferences(Task task){
        if(!StringUtil.stringIsEmptyOrNull(task.getRelatedLeadId())){
            Optional<Lead> leadOptional = leadRepository.findById(task.getRelatedLeadId());

            if(!leadOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.LEAD,
                                Constants.Specifier.ID
                        )
                );
            }

            leadOptional.get().getTasks().remove(task);
            leadRepository.save(leadOptional.get());
        }

        if(!StringUtil.stringIsEmptyOrNull(task.getRelatedOpportunityId())){
            Optional<Opportunity> opportunityOptional = opportunityRepository.findById(task.getRelatedOpportunityId());

            if(!opportunityOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        ErrorMessageUtility.getEntityNotFoundBySpecifier(
                                Constants.Entity.OPPORTUNITY,
                                Constants.Specifier.ID
                        )
                );
            }

            opportunityOptional.get().getTasks().remove(task);
            opportunityRepository.save(opportunityOptional.get());
        }

        return task;
    }

    @Override
    public Task handleChildReferences(Task task){
        return task;
    }
}
