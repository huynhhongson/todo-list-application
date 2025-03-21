package com.example.todolistapplication.service;

import com.example.todolistapplication.dto.TaskDto;
import com.example.todolistapplication.entity.Task;
import com.example.todolistapplication.repository.TaskRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class TaskService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TaskRepository taskRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public TaskDto addTask(TaskDto taskDto) {
        Task task = new Task(taskDto);
        task = taskRepository.save(task);
        return new TaskDto(task);
    }


    public TaskDto getTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found!"));
        return modelMapper.map(task, TaskDto.class);

    }

    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map((task) -> modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());
    }

    public TaskDto updateTask(Long id, TaskDto taskDto) {

        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found!"));

        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setDueDate(taskDto.getDueDate());
        existingTask.setPriority(taskDto.isPriority());
        existingTask.setStatus(taskDto.isStatus());
        existingTask.setDependencies(taskDto.getDependencies());

        Task updatedTask = taskRepository.save(existingTask);
        return modelMapper.map(updatedTask, TaskDto.class);
    }
    public TaskDto updateTaskStatus(Long taskId, boolean status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task không tồn tại"));

        // Lấy danh sách các Task mà Task này phụ thuộc vào
        List<Long> dependencies = task.getDependencies();
        for (Long dependentTaskId : dependencies) {
            Task dependentTask = taskRepository.findById(dependentTaskId)
                    .orElseThrow(() -> new RuntimeException("Task phụ thuộc không tồn tại"));

            // Nếu có Task phụ thuộc chưa hoàn thành thì không cho cập nhật
            if (!dependentTask.isStatus()) {
                throw new RuntimeException("Không thể cập nhật Task vì Task phụ thuộc chưa hoàn thành!");
            }
        }

        // Nếu tất cả Task phụ thuộc đã hoàn thành, cho phép cập nhật
        task.setStatus(status);
        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskDto.class);
    }
    // Thêm dependency vào Task
    public Task addDependency(Long taskId, Long dependencyId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        Task dependencyTask = taskRepository.findById(dependencyId)
                .orElseThrow(() -> new RuntimeException("Dependency Task not found"));

        // Kiểm tra tránh vòng lặp phụ thuộc
        if (hasCircularDependency(taskId, dependencyId)) {
            throw new RuntimeException("Circular dependency detected!");
        }

        // Kiểm tra nếu dependency đã tồn tại thì không thêm lại
        if (task.getDependencies().contains(dependencyId)) {
            throw new RuntimeException("Dependency already exists!");
        }

        task.getDependencies().add(dependencyId);
        return taskRepository.save(task);
    }

    // Xóa dependency khỏi Task
    public Task removeDependency(Long taskId, Long dependencyId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Kiểm tra nếu dependency không tồn tại thì báo lỗi
        if (!task.getDependencies().contains(dependencyId)) {
            throw new RuntimeException("Dependency does not exist!");
        }

        task.getDependencies().remove(dependencyId);
        return taskRepository.save(task);
    }

    // Kiểm tra vòng lặp phụ thuộc (Task A không thể phụ thuộc vào Task B nếu B cũng phụ thuộc vào A)
    private boolean hasCircularDependency(Long taskId, Long dependencyId) {
        Task dependencyTask = taskRepository.findById(dependencyId).orElse(null);
        if (dependencyTask == null) return false;

        // Kiểm tra nếu dependencyTask phụ thuộc lại vào taskId
        return dependencyTask.getDependencies().contains(taskId);
    }

    //delete task
    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    public Set<Long> getAllDependencies(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Set<Long> allDependencies = new HashSet<>();
        findDependenciesRecursively(task.getDependencies(), allDependencies);
        return allDependencies;
    }

    private void findDependenciesRecursively(List<Long> dependencies, Set<Long> allDependencies) {
        for (Long depId : dependencies) {
            if (!allDependencies.contains(depId)) {  // Avoid cycles
                allDependencies.add(depId);
                Task depTask = taskRepository.findById(depId)
                        .orElseThrow(() -> new RuntimeException("Dependent task not found"));
                findDependenciesRecursively(depTask.getDependencies(), allDependencies);
            }
        }
    }

}
