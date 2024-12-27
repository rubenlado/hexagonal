package com.ruben.hexagonal.infrastructure.adapters.input.rest;

import com.ruben.hexagonal.application.ports.in.TaskServicePort;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.response.TaskResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskRestAdapter {

    private final TaskServicePort servicePort;

    public TaskRestAdapter(TaskServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @GetMapping("")
    public List<TaskResponse> findAll() {
        return servicePort.findAll().stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCreationDate(),
                        task.isCompleted()))
                .collect(Collectors.toList());
    }
}
