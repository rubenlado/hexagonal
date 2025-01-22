package com.ruben.hexagonal.infrastructure.adapters.input.rest;

import com.ruben.hexagonal.application.ports.in.TaskServicePort;
import com.ruben.hexagonal.domain.models.Task;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.request.TaskRequest;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.response.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskRestAdapter {

    private final TaskServicePort servicePort;

    public TaskRestAdapter(TaskServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<TaskResponse>> findTaskDetail(@PathVariable Long id) {
        return servicePort.findTaskDetail(id)
                .map(task -> ResponseEntity.ok(new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCreationDate(),
                        task.isCompleted()
                )))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<TaskResponse>> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return servicePort.updateTask(id, taskRequest).map(task ->
                ResponseEntity.ok(new TaskResponse(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getCreationDate(),
                    task.isCompleted()
                ))).defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTask(@PathVariable Long id) {
        return servicePort.deleteTask(id)
                .flatMap(deleted -> {
                    if (deleted) {
                        return Mono.just(ResponseEntity.noContent().build());
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
                    }
                });
    }

    @GetMapping
    public Mono<List<TaskResponse>> findTasks(@RequestParam Optional<String> search) {
        return servicePort.findTask(search)
                .map(tasks -> tasks.stream()
                        .map(task -> new TaskResponse(
                                task.getId(),
                                task.getTitle(),
                                task.getDescription(),
                                task.getCreationDate(),
                                task.isCompleted()))
                        .collect(Collectors.toList()));
    }

    @PostMapping
    public Mono<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {

        Task newTask = new Task(
                null,
                taskRequest.getTitle(),
                taskRequest.getDescription(),
                LocalDateTime.now(),
                false
        );

        return servicePort.createTask(newTask).map(task -> new TaskResponse(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreationDate(),
                task.isCompleted()));

    }

    @GetMapping("/completed")
    public Flux<TaskResponse> getCompletedTasks() {
        return servicePort.getCompletedTasks()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCreationDate(),
                        task.isCompleted()
                ));
    }



}