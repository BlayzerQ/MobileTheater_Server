package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Optional<TaskEntity> getByID(Long id);
    TaskEntity addTask(TaskEntity news);
    TaskEntity editTask(TaskEntity news);
    void deleteTask(Long id);
    List<TaskEntity> getAll();
}
