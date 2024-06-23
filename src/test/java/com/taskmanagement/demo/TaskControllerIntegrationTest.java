package com.taskmanagement.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.taskmanagement.demo.model.Task;
import com.taskmanagement.demo.repository.TaskRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        taskRepository.deleteAll();
    }

    @Test
    public void shouldCreateTask() throws Exception {
        String taskJson = "{\"title\": \"Task 1\", \"description\": \"Description 1\", \"status\": \"pending\", \"dueDate\": \"2024-06-30\"}";

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Task 1"))
                .andExpect(jsonPath("$.description").value("Description 1"))
                .andExpect(jsonPath("$.status").value("pending"))
                .andExpect(jsonPath("$.dueDate").value("2024-06-30"));
    }

    @Test
    public void shouldGetAllTasks() throws Exception {
        Task task1 = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        Task task2 = new Task("Task 2", "Description 2", "completed", LocalDate.of(2024, 7, 15));
        taskRepository.save(task1);
        taskRepository.save(task2);

        mockMvc.perform(get("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[1].title").value("Task 2"));
    }

    @Test
    public void shouldGetTaskById() throws Exception {
        Task task = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        taskRepository.save(task);

        mockMvc.perform(get("/api/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Task 1"))
                .andExpect(jsonPath("$.description").value("Description 1"));
    }

    @Test
    public void shouldReturnNotFoundForNonExistentTask() throws Exception {
        mockMvc.perform(get("/api/tasks/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        Task task = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        taskRepository.save(task);

        String updatedTaskJson = "{\"title\": \"Updated Task\", \"description\": \"Updated Description\", \"status\": \"completed\", \"dueDate\": \"2024-07-01\"}";

        mockMvc.perform(put("/api/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedTaskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Task"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.status").value("completed"))
                .andExpect(jsonPath("$.dueDate").value("2024-07-01"));
    }

    @Test
    public void shouldReturnNotFoundWhenUpdatingNonExistentTask() throws Exception {
        String updatedTaskJson = "{\"title\": \"Updated Task\", \"description\": \"Updated Description\", \"status\": \"completed\", \"dueDate\": \"2024-07-01\"}";

        mockMvc.perform(put("/api/tasks/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedTaskJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        Task task = new Task("Task 1", "Description 1", "pending", LocalDate.of(2024, 6, 30));
        taskRepository.save(task);

        mockMvc.perform(delete("/api/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}





