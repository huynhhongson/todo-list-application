package com.example.todolistapplication.dto;

import com.example.todolistapplication.entity.Task;
import lombok.*;

import java.util.List;

@Data
public class TaskDto {
    private String title;
    private String description;
    private String dueDate;
    private boolean priority;
    private boolean status;
    private List<Long> dependencies;

    public TaskDto() {
    }
    public TaskDto(Task todo) {
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.dueDate = todo.getDueDate();
        this.priority = todo.isPriority();
        this.status = todo.isStatus();
        this.dependencies = todo.getDependencies();
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public boolean isPriority() { return priority; }
    public void setPriority(boolean priority) { this.priority = priority; }

    public boolean isStatus() {
        return status;
    }

    public void setDependencies(List<Long> dependencies) {
        this.dependencies = dependencies;
    }

    public List<Long> getDependencies() {
        return dependencies;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", dependencies=" + dependencies +
                '}';
    }
}
