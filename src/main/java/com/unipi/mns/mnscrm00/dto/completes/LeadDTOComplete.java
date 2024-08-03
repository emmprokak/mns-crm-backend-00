package com.unipi.mns.mnscrm00.dto.completes;

import com.unipi.mns.mnscrm00.dto.abstracts.LeadDTO;
import com.unipi.mns.mnscrm00.dto.simples.LeadDTOSimple;
import com.unipi.mns.mnscrm00.entities.data.Note;
import com.unipi.mns.mnscrm00.entities.data.Task;

import java.time.LocalDateTime;
import java.util.List;

public class LeadDTOComplete extends LeadDTOSimple {

    private List<Task> tasks;
    private List<Note> notes;

    // TODO: convert to DTO

    public LeadDTOComplete(String companyAddress, String companyIndustry, String companyName, String contactEmail, String contactMobile, String contactPerson, String contactPhone, String contactPrefix, String contactRole, String id, String status, List<Note> notes, List<Task> tasks, LocalDateTime created, LocalDateTime modified) {
        super(companyAddress, companyIndustry, companyName, contactEmail, contactMobile, contactPerson, contactPhone, contactPrefix, contactRole, id, status, created, modified);
        this.notes = notes;
        this.tasks = tasks;
    }

    public LeadDTOComplete(){
        super();
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
