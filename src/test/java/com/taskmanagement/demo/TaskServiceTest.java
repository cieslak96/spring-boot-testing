package com.taskmanagement.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.taskmanagement.demo.model.Task;
import com.taskmanagement.demo.repository.TaskRepository;
import com.taskmanagement.demo.service.TaskService;

public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateTask() {
        Task task = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);
        assertEquals("Task 1", result.getTitle());
        verify(taskRepository, times(1)).save(task);
    }
}
