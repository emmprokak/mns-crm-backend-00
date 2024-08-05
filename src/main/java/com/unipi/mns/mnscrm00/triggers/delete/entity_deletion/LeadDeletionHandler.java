package com.unipi.mns.mnscrm00.triggers.delete.entity_deletion;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.AccountRepository;
import com.unipi.mns.mnscrm00.dal.TaskRepository;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.entities.data.Lead;
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
public class LeadDeletionHandler implements DeletionHandler<Lead> {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Lead delete(Lead lead) {
        lead = handleParentReferences(lead);
        lead = handleChildReferences(lead);
        return lead;
    }

    @Override
    public Lead handleParentReferences(Lead lead){
        return lead;
    }

    @Override
    public Lead handleChildReferences(Lead lead){

        List<Task> taskList = taskRepository.findByRelatedLeadId(lead.getId());
        for(Task task : taskList){
            task.setRelatedLead(null);
            task.setRelatedLeadId(null);
            taskRepository.save(task);
        }

        //TODO: add note when time comes

        return lead;
    }
}
