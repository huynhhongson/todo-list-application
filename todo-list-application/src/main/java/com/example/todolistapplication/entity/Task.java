package com.example.todolistapplication.entity;


import com.example.todolistapplication.dto.TaskDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String dueDate;


    @Column(nullable = false)
    private boolean priority;

    @Column(nullable = false)
    private boolean status;

    // Danh sách các Task mà Task này phụ thuộc vào
    @ElementCollection
    private List<Long> dependencies = new ArrayList<>();

    public Task(){}

    public Task(Long id, String title, String description, String dueDate, boolean priority, boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    public Task(TaskDto todoDto) {
        this.title = todoDto.getTitle();
        this.description = todoDto.getDescription();
        this.dueDate = todoDto.getDueDate();
        this.priority = todoDto.isPriority();
        this.status = todoDto.isStatus();
        this.dependencies = todoDto.getDependencies();
    }

    public Long getId() {
        return id;
    }

    public List<Long> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Long> dependencies) {
        this.dependencies = dependencies;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}
