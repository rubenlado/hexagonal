package com.ruben.hexagonal.application.services;

import com.ruben.hexagonal.application.ports.in.TaskServicePort;
import com.ruben.hexagonal.application.ports.out.TaskRepositoryPort;
import com.ruben.hexagonal.domain.models.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements TaskServicePort {

    private final TaskRepositoryPort repositoryPort;

    public TaskService(TaskRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<Task> findAll() {
        return repositoryPort.findAll();
    }

}
