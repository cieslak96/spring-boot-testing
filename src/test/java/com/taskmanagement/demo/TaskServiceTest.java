package com.taskmanagement.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.taskmanagement.demo.model.Task;
import com.taskmanagement.demo.repository.TaskRepository;

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

    @Test
    public void shouldGetAllTasks() {
        Task task1 = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        Task task2 = new Task("Task 2", "Description 2", "completed", LocalDate.of(2024, 6, 29));
        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();
        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getTitle());
        assertEquals("Task 2", result.get(1).getTitle());
    }

    @Test
    public void shouldGetTaskById() {
        Task task = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);
        assertNotNull(result);
        assertEquals("Task 1", result.getTitle());
    }

    @Test
    public void shouldUpdateTask() {
        Task existingTask = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        Task updatedDetails = new Task("Updated Task", "Updated Description", "completed", LocalDate.of(2024, 7, 1));

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(existingTask);

        Task result = taskService.updateTask(1L, updatedDetails);
        assertNotNull(result);
        assertEquals("Updated Task", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
        assertEquals("completed", result.getStatus());
        assertEquals(LocalDate.of(2024, 7, 1), result.getDueDate());
    }

    @Test
    public void shouldDeleteTask() {
        doNothing().when(taskRepository).deleteById(1L);

        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    public void shouldGetAllTasksAsync() throws Exception {
        Task task1 = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        Task task2 = new Task("Task 2", "Description 2", "completed", LocalDate.of(2024, 6, 29));
        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(tasks);

        CompletableFuture<List<Task>> resultFuture = taskService.getAllTasksAsync();
        List<Task> result = resultFuture.get();
        
        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getTitle());
        assertEquals("Task 2", result.get(1).getTitle());
    }

    @Test
    public void shouldGetTaskByIdAsync() throws Exception {
        Task task = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        CompletableFuture<Task> resultFuture = taskService.getTaskByIdAsync(1L);
        Task result = resultFuture.get();

        assertNotNull(result);
        assertEquals("Task 1", result.getTitle());
    }
}