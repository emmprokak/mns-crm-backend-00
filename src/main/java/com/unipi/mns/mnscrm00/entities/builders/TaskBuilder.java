package com.unipi.mns.mnscrm00.entities.builders;

import com.unipi.mns.mnscrm00.entities.data.Task;

import java.util.Date;

public class TaskBuilder implements EntityBuilder<Task>{
    private Task task;

    @Override
    public Task build() {
        return task;
    }

    public TaskBuilder(){
        this.task = new Task();
    }

    public TaskBuilder(Task task){
        this.task = task;
    }

    public TaskBuilder setName(String name) {
        task.setName(name);
        return this;
    }

    public TaskBuilder setReason(String reason) {
        task.setReason(reason);
        return this;
    }

    public TaskBuilder setDueDate(Date dueDate) {
        task.setDueDate(dueDate);
        return this;
    }

    public TaskBuilder setStatus(String status) {
        task.setStatus(status);
        return this;
    }

    public TaskBuilder setType(String type) {
        task.setType(type);
        return this;
    }


}
