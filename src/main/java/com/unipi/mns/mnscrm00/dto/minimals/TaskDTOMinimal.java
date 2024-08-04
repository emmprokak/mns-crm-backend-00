package com.unipi.mns.mnscrm00.dto.minimals;

import com.unipi.mns.mnscrm00.dto.abstracts.TaskDTO;
import com.unipi.mns.mnscrm00.entities.data.Lead;
import com.unipi.mns.mnscrm00.entities.data.Opportunity;

import java.time.LocalDateTime;
import java.util.Date;

public class TaskDTOMinimal extends TaskDTO {

    public TaskDTOMinimal(Date dueDate, String id, String name, String reason, String status, String type, Lead lead, Opportunity opportunity, LocalDateTime created, LocalDateTime modified) {
       super(dueDate, id, name, reason, status, type, lead, opportunity, created, modified);
    }

    public TaskDTOMinimal() {}

}
