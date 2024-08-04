package com.unipi.mns.mnscrm00.dto.completes;

import com.unipi.mns.mnscrm00.dto.simples.TaskDTOSimple;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;

import java.util.Date;

public class TaskDTOComplete extends TaskDTOSimple {

    public TaskDTOComplete(Date dueDate, String id, String name, String reason, String status, String type, Lead lead, Opportunity opportunity) {
        super(dueDate, id, name, reason, status, type, lead, opportunity);
    }

    public TaskDTOComplete(){}
}
