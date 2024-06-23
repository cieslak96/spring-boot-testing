package com.taskmanagement.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testTaskConstructorAndGetters() {
        LocalDate dueDate = LocalDate.of(2024, 6, 30);
        Task task = new Task("Task 1", "Description 1", "pending", dueDate);

        assertEquals("Task 1", task.getTitle());
        assertEquals("Description 1", task.getDescription());
        assertEquals("pending", task.getStatus());
        assertEquals(dueDate, task.getDueDate());
    }

    @Test
    public void testTaskSetters() {
        Task task = new Task();
        LocalDate dueDate = LocalDate.of(2024, 6, 30);
        task.setTitle("Task 1");
        task.setDescription("Description 1");
        task.setStatus("pending");
        task.setDueDate(dueDate);

        assertEquals("Task 1", task.getTitle());
        assertEquals("Description 1", task.getDescription());
        assertEquals("pending", task.getStatus());
        assertEquals(dueDate, task.getDueDate());
    }

    @Test
    public void testTaskIdSetterGetter() {
        Task task = new Task();
        task.setId(1L);

        assertNotNull(task.getId());
        assertEquals(1L, task.getId());
    }
}