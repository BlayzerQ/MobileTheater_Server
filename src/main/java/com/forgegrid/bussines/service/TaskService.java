package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.TaskEntity;

import java.util.List;

public interface TaskService {

    TaskEntity getByID(Long id);
    TaskEntity addTask(TaskEntity news);
    TaskEntity editTask(TaskEntity news);
    void deleteTask(Long id);
    List<TaskEntity> getAll();
}
