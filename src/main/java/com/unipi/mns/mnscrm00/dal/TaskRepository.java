package com.unipi.mns.mnscrm00.dal;

import com.unipi.mns.mnscrm00.entities.data.Opportunity;
import com.unipi.mns.mnscrm00.entities.data.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}
