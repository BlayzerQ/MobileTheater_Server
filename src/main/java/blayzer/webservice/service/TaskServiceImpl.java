package blayzer.webservice.service;

import blayzer.webservice.entity.Task;
import blayzer.webservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task getByID(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public Task addTask(Task user) {
        return taskRepository.saveAndFlush(user);
    }

    @Override
    public Task editTask(Task user) {
        return taskRepository.saveAndFlush(user);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

}
