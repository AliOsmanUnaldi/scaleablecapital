package com.codility.scaleablecapital.repository;

import com.codility.scaleablecapital.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
