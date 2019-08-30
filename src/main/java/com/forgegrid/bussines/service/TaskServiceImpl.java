package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.TaskEntity;
import com.forgegrid.dal.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Optional<TaskEntity> getByID(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public TaskEntity addTask(TaskEntity user) {
        return taskRepository.save(user);
    }

    @Override
    public TaskEntity editTask(TaskEntity user) {
        return taskRepository.save(user);
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
