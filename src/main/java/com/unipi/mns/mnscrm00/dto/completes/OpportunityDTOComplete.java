package com.unipi.mns.mnscrm00.dto.completes;

import com.unipi.mns.mnscrm00.dto.abstracts.OpportunityDTO;
import com.unipi.mns.mnscrm00.dto.simples.OpportunityDTOSimple;
import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Note;
import com.unipi.mns.mnscrm00.entities.data.Task;

import java.util.List;

public class OpportunityDTOComplete extends OpportunityDTOSimple {
    private List<Task> tasks;
    private List<Note> notes;

    public OpportunityDTOComplete(String comments, String description, double expectedRevenue, String id, String relatedAccountId, String status, String title, String type, Account account, List<Task> tasks, List<Note> notes) {
        super(comments, description, expectedRevenue, id, relatedAccountId, status, title, type, account);
        this.tasks = tasks;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
