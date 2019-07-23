package blayzer.webservice.service;

import blayzer.webservice.entities.Task;

import java.util.List;

public interface TaskService {

    Task getByID(Long id);
    Task addTask(Task news);
    Task editTask(Task news);
    void deleteTask(Long id);
    List<Task> getAll();
}
