package com.unipi.mns.mnscrm00.dto.completes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dto.abstracts.TaskDTO;
import com.unipi.mns.mnscrm00.dto.simples.CaseDTOSimple;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.entities.data.Task;
import com.unipi.mns.mnscrm00.utilities.ListConverter;
import org.apache.tomcat.util.bcel.Const;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class CaseDTOComplete extends CaseDTOSimple {

    private List<TaskDTO> tasks;

    public CaseDTOComplete(String category, LocalDateTime created, String id, LocalDateTime modified, String reason, Account account, Contact contact, String severity, String source, String status, String title, List<Task> tasks, Date createdDate, Date closedDate) {
        super(category, created, id, modified, reason, account, contact, severity, source, status, title, createdDate, closedDate);
        this.tasks = ListConverter.convertTasksToDTOList(tasks, Constants.DTO.CONVERT_TO_DTO_MINIMAL);
    }

    public CaseDTOComplete() {}

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
