package com.taskmanagement.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.taskmanagement.demo.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testSaveTask() {
        Task task = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        Task savedTask = taskRepository.save(task);

        assertNotNull(savedTask);
        assertNotNull(savedTask.getId());
        assertEquals("Task 1", savedTask.getTitle());
    }

    @Test
    void testFindById() {
        Task task = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        taskRepository.save(task);

        Optional<Task> foundTask = taskRepository.findById(task.getId());

        assertTrue(foundTask.isPresent());
        assertEquals("Task 1", foundTask.get().getTitle());
    }

    @Test
    void testFindAll() {
        Task task1 = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        Task task2 = new Task("Task 2", "Description 2", "completed", LocalDate.of(2024, 6, 29));
        taskRepository.save(task1);
        taskRepository.save(task2);

        List<Task> tasks = taskRepository.findAll();

        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());
    }

    @Test
    void testDeleteById() {
        Task task = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        Task savedTask = taskRepository.save(task);
        
        taskRepository.deleteById(savedTask.getId());

        Optional<Task> foundTask = taskRepository.findById(savedTask.getId());
        assertFalse(foundTask.isPresent());
    }
}