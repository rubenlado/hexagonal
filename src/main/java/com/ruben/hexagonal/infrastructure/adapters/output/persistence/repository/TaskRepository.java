package com.ruben.hexagonal.infrastructure.adapters.output.persistence.repository;
import com.ruben.hexagonal.infrastructure.adapters.output.persistence.entity.TaskEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<TaskEntity, Long> {

    @Query("SELECT * FROM task t WHERE (:search IS NULL OR UPPER(t.title) LIKE CONCAT('%', UPPER(:search), '%'))")
    Flux<TaskEntity> findByTitle(Optional<String> search);

    @Query("SELECT * FROM task t WHERE (:search IS NULL OR UPPER(t.title) LIKE CONCAT('%', UPPER(:search), '%')) " +
            "LIMIT :#{#pageable.pageSize} OFFSET :#{#pageable.offset}")
    Flux<TaskEntity> findByCriteria(Optional<String> search, Pageable pageable);

    @Query("SELECT COUNT(*) FROM task t WHERE (:search IS NULL OR UPPER(t.title) LIKE CONCAT('%', UPPER(:search), '%'))")
    Mono<Long> countByCriteria(@Param("search") Optional<String> search);

    @Query("SELECT * FROM task WHERE completed = true")
    Flux<TaskEntity> findCompleted();
}