package com.unipi.mns.mnscrm00.dto.completes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dto.abstracts.LeadDTO;
import com.unipi.mns.mnscrm00.dto.abstracts.TaskDTO;
import com.unipi.mns.mnscrm00.dto.simples.LeadDTOSimple;
import com.unipi.mns.mnscrm00.entities.data.Note;
import com.unipi.mns.mnscrm00.entities.data.Task;
import com.unipi.mns.mnscrm00.utilities.ListConverter;

import java.time.LocalDateTime;
import java.util.List;

public class LeadDTOComplete extends LeadDTOSimple {

    private List<TaskDTO> tasks;
    private List<Note> notes;

    // TODO: convert to DTO

    public LeadDTOComplete(String companyAddress, String companyIndustry, String companyName, String contactEmail, String contactMobile, String contactPerson, String contactPhone, String contactPrefix, String contactRole, String id, String status, List<Note> notes, List<Task> tasks, LocalDateTime created, LocalDateTime modified) {
        super(companyAddress, companyIndustry, companyName, contactEmail, contactMobile, contactPerson, contactPhone, contactPrefix, contactRole, id, status, created, modified);
        this.notes = notes;
        this.tasks = ListConverter.convertTasksToDTOList(tasks, Constants.DTO.CONVERT_TO_DTO_MINIMAL);
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

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
