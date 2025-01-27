package com.ruben.hexagonal.application.ports.out;
import com.ruben.hexagonal.domain.models.Task;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.request.TaskRequest;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.request.TaskSearchCriteria;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.response.PagedResponse;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryPort {
    Mono<Task> save(Task task);
    Mono<Task> findById(Long id);
    Mono<List<Task>> findTask(Optional<String> search);
    Mono<PagedResponse<Task>> findTaskPaged(TaskSearchCriteria criteria);
    Mono<Task> update(Long id, TaskRequest task);
    Mono<Boolean> deleteById(Long id);
    Flux<Task> findCompleted();
}
