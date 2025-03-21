package com.example.todolistapplication.controller;


import com.example.todolistapplication.dto.TaskDto;
import com.example.todolistapplication.entity.Task;
import com.example.todolistapplication.repository.TaskRepository;
import com.example.todolistapplication.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;
    //add
    @PostMapping
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto todoDto){
        TaskDto savedTodo = taskService.addTask(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }
    //get all task
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> todos = taskService.getAllTasks();
        return ResponseEntity.ok(todos);
    }
    //get a task
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") Long todoId){
        TaskDto todoDto = taskService.getTask(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // Read/list all tasks with filtering and pagination
    //  Lọc theo tiêu đề + phân trang
    @GetMapping("/filterByTitle")
    public ResponseEntity<Page<Task>> filterTasksByTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Task> tasks = taskRepository.findByTitleContainingIgnoreCase(title, pageable);

        return ResponseEntity.ok(tasks);
    }

    // Lọc theo ngày + phân trang
    @GetMapping("/filterByDueDate")
    public ResponseEntity<Page<Task>> filterTasksByDueDate(
            @RequestParam String dueDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Task> tasks = taskRepository.findByDueDate(dueDate, pageable);

        return ResponseEntity.ok(tasks);
    }

    //lọc theo status
    @GetMapping("/filter")
    public ResponseEntity<Page<Task>> filterTasks(
            @RequestParam(required = false) Boolean status,
            @RequestParam String dueDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dueDate, formatter);

        String dueDatePrefix = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        Pageable pageable = PageRequest.of(page, size);
        Page<Task> tasks = taskRepository.findByStatusAndDueDate(status, dueDatePrefix, pageable);

        return ResponseEntity.ok(tasks);
    }

    //update task
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto){//@Validate
            TaskDto updateTask = taskService.updateTask(id, taskDto);
            return ResponseEntity.ok(updateTask);
    }
    //update status
    @PatchMapping("/{taskId}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long taskId, @RequestParam boolean status) {
        try {
            TaskDto updatedTask = taskService.updateTaskStatus(taskId, status);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok("Task deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    // Thêm dependency vào Task
    @PostMapping("/{taskId}/dependencies/{dependencyId}")
    public ResponseEntity<Task> addDependency(@PathVariable Long taskId, @PathVariable Long dependencyId) {
        return ResponseEntity.ok(taskService.addDependency(taskId, dependencyId));
    }

    // Xóa dependency khỏi Task
    @DeleteMapping("/{taskId}/dependencies/{dependencyId}")
    public ResponseEntity<Task> removeDependency(@PathVariable Long taskId, @PathVariable Long dependencyId) {
        return ResponseEntity.ok(taskService.removeDependency(taskId, dependencyId));
    }
    //Xem dependency
    @GetMapping("/{taskId}/dependencies")
    public ResponseEntity<Set<Long>> getAllTaskDependencies(@PathVariable Long taskId) {
        Set<Long> dependencies = taskService.getAllDependencies(taskId);
        return ResponseEntity.ok(dependencies);
    }
}
