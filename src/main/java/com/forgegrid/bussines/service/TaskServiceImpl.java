package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.TaskEntity;
import com.forgegrid.dal.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskEntity getByID(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public TaskEntity addTask(TaskEntity user) {
        return taskRepository.saveAndFlush(user);
    }

    @Override
    public TaskEntity editTask(TaskEntity user) {
        return taskRepository.saveAndFlush(user);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskEntity> getAll() {
        return taskRepository.findAll();
    }

}
