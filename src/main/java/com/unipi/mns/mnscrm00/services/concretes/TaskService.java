package com.unipi.mns.mnscrm00.services.concretes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.TaskRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.TaskDTO;
import com.unipi.mns.mnscrm00.entities.data.Task;
import com.unipi.mns.mnscrm00.mapping.RelationshipMapper;
import com.unipi.mns.mnscrm00.triggers.delete.DeleteTrigger;
import com.unipi.mns.mnscrm00.triggers.insert_update.InsertUpdateTrigger;
import com.unipi.mns.mnscrm00.utilities.ListConverter;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private RelationshipMapper relationshipMapper;
    @Autowired
    private InsertUpdateTrigger insertUpdateTrigger;
    @Autowired
    private DeleteTrigger deleteTrigger;

    public TaskDTO getTaskById(String id, boolean getChildrenRelationships){
        Optional<Task> taskOptional = taskRepository.findById(id);

        if(!taskOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.TASK,
                            Constants.Specifier.ID
                    )
            );
        }

        if(getChildrenRelationships){
            return taskOptional.get().toDTOComplete();
        }

        return taskOptional.get().toDTOSimple();
    }

    public TaskDTO insertTask(Task task){
        Task taskToInsert = new Task();
        taskToInsert = insertUpdateTrigger.handleTaskEntry(task, taskToInsert, true);

        return taskRepository.save(taskToInsert).toDTOSimple();
    }

    public List<TaskDTO> getAllTasks(){
        List<Task> taskList = taskRepository.findAll();

        if(taskList.size() <= 0){
            return new ArrayList<>();
        }

        return ListConverter.convertEntitiesToDTOList(taskList, Constants.DTO.CONVERT_TO_DTO_SIMPLE);
    }

    public TaskDTO updateTask(String id, Task task){
        Optional<Task> taskOptional = taskRepository.findById(id);

        if(!taskOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.TASK,
                            Constants.Specifier.ID
                    )
            );
        }

        Task taskToUpdate = taskOptional.get();
        taskToUpdate = insertUpdateTrigger.handleTaskEntry(task, taskToUpdate, false);

        return taskRepository.save(taskToUpdate).toDTOComplete();
    }

    public boolean deleteTaskById(String id){
        Optional<Task> taskOptional = taskRepository.findById(id);

        if(!taskOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.TASK,
                            Constants.Specifier.ID
                    )
            );
        }

        deleteTrigger.handleReferenceDeletion(taskOptional.get());
        taskRepository.delete(taskOptional.get());

        return true;
    }
}
