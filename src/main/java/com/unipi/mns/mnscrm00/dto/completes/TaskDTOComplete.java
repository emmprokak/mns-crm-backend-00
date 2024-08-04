package com.unipi.mns.mnscrm00.dto.completes;

import com.unipi.mns.mnscrm00.dto.simples.TaskDTOSimple;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;

import java.time.LocalDateTime;
import java.util.Date;

public class TaskDTOComplete extends TaskDTOSimple {

    public TaskDTOComplete(Date dueDate, String id, String name, String reason, String status, String type, Lead lead, Opportunity opportunity, LocalDateTime created, LocalDateTime modified) {
        super(dueDate, id, name, reason, status, type, lead, opportunity, created, modified);
    }

    public TaskDTOComplete(){}
}
