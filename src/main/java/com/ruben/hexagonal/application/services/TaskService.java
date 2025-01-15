package com.ruben.hexagonal.application.services;

import com.ruben.hexagonal.application.ports.in.TaskServicePort;
import com.ruben.hexagonal.application.ports.out.TaskRepositoryPort;
import com.ruben.hexagonal.domain.models.Task;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.request.TaskRequest;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.response.TaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements TaskServicePort {

    private final TaskRepositoryPort repositoryPort;

    public TaskService(TaskRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Optional<Task> findTaskDetail(Long id) {
        return repositoryPort.findById(id);
    }

    @Override
    public List<Task> findTask(Optional<String> search) {
        return repositoryPort.findTask(search);
    }

    @Override
    public Task createTask(Task task) {
        return repositoryPort.save(task);
    }

    @Override
    public Optional<Task> updateTask(Long id, TaskRequest task){ return repositoryPort.update(id, task);}

    @Override
    public boolean deleteTask(Long id){ return repositoryPort.deleteById(id);}

}
