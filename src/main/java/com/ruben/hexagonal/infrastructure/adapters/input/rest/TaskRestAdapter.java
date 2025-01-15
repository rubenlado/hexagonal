package com.ruben.hexagonal.infrastructure.adapters.input.rest;

import com.ruben.hexagonal.application.ports.in.TaskServicePort;
import com.ruben.hexagonal.domain.models.Task;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.request.TaskRequest;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.response.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/task")
public class TaskRestAdapter {

    private final TaskServicePort servicePort;

    public TaskRestAdapter(TaskServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findTaskDetail(@PathVariable("id") Long id) {
        Optional<Task> task = servicePort.findTaskDetail(id);

        if (task.isPresent()) {
            TaskResponse response = new TaskResponse(
                    task.get().getId(),
                    task.get().getTitle(),
                    task.get().getDescription(),
                    task.get().getCreationDate(),
                    task.get().isCompleted()
            );
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") Long id, @RequestBody TaskRequest taskRequest) {

        Optional<Task> updatedTask = servicePort.updateTask(id, taskRequest);
        if (updatedTask.isPresent()) {
            Task task = updatedTask.get();
            TaskResponse response = new TaskResponse(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getCreationDate(),
                    task.isCompleted()
            );
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public boolean deleteTask(@PathVariable("id") Long id) {
        return servicePort.deleteTask(id);
    }

    @GetMapping("")
    public List<TaskResponse> findTasks(@RequestParam("search") Optional<String> search) {
        return servicePort.findTask(search).stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCreationDate(),
                        task.isCompleted()))
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public TaskResponse createTask(@RequestBody TaskRequest taskRequest) {
        Task newTask = new Task(
                null,
                taskRequest.getTitle(),
                taskRequest.getDescription(),
                LocalDateTime.now(),
                false
        );
        Task createdTask = servicePort.createTask(newTask);
        return new TaskResponse( createdTask.getId(),
                createdTask.getTitle(),
                createdTask.getDescription(),
                createdTask.getCreationDate(),
                createdTask.isCompleted());
    }


}


