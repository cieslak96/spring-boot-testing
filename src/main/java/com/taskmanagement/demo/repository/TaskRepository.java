package com.taskmanagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.demo.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
