# Task Manager Application

This is a simple Task Manager application built with Spring Boot. It allows you to create, read, update, and delete tasks, as well as check the code coverage of the application.

## What is Spring Boot?

Spring Boot is an open-source Java-based framework used to create stand-alone, production-grade Spring-based applications. It simplifies the development of new Spring applications and helps you create robust, scalable applications with minimal configuration.

## Prerequisites

- Java 21 or higher
- Maven 3.6.0 or higher
- An IDE such as IntelliJ IDEA or VS Code
- Postman (for testing the API)
- H2 Database (in-memory database used for this application)

## Running the Application

1. **Build the project using Maven:**

   ```sh
   mvn clean install
   ```

2. **Run the application:**

   ```sh
   mvn spring-boot:run
   ```

   The application will start on <http://localhost:8080>

## Using POSTMAN to Interact with the API

Start Postman and create a new request.

1. **Create a new task:**

- Method: POST
- URL: <http://localhost:8080/api/tasks>
- Body: Select raw and JSON format, then provide the task details:

   ```json
   {
      "title": "Task 1",
      "description": "Description 1",
      "status": "pending",
      "dueDate": "2024-06-30"
   }
- Send the request.

2. **Retrieve all tasks:**

- Method: GET
- URL: <http://localhost:8080/api/tasks>
- Send the request to get the list of all tasks.

3. **Retrieve a task by ID:**

- Method: GET
- URL: <http://localhost:8080/api/tasks/{id}> (replace {id} with the actual task ID)
- Send the request to get the task details.

4. **Update a task:**

- Method: PUT
- URL: <http://localhost:8080/api/tasks/{id}> (replace {id} with the actual task ID)
- Body: Select raw and JSON format, then provide the updated task details:

   ```json
   {
      "title": "Updated Task 1",
      "description": "Updated Description 1",
      "status": "completed",
      "dueDate": "2024-07-01"
   }

- Send the request.

5. **Delete a task:**

- Method: DELETE
- URL: http://localhost:8080/api/tasks/{id} (replace {id} with the actual task ID)
- Send the request to delete the task.
