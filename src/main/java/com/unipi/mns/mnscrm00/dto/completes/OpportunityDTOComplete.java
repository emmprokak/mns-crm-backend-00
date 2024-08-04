package com.unipi.mns.mnscrm00.dto.completes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dto.abstracts.OpportunityDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.TaskDTO;
import com.unipi.mns.mnscrm00.dto.simples.OpportunityDTOSimple;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Note;
import com.unipi.mns.mnscrm00.entities.data.Task;
import com.unipi.mns.mnscrm00.utilities.ListConverter;

import java.util.List;

public class OpportunityDTOComplete extends OpportunityDTOSimple {
    private List<TaskDTO> tasks;
    private List<Note> notes;

    public OpportunityDTOComplete(String comments, String description, double expectedRevenue, String id, String relatedAccountId, String status, String title, String type, Account account, List<Task> tasks, List<Note> notes) {
        super(comments, description, expectedRevenue, id, relatedAccountId, status, title, type, account);
        this.tasks = ListConverter.convertTasksToDTOList(tasks, Constants.DTO.CONVERT_TO_DTO_MINIMAL);
        this.notes = notes;
    }

    public OpportunityDTOComplete(){

    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
