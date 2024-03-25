package com.codility.scaleablecapital.entity;

import com.codility.scaleablecapital.constants.TaskStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private TaskStatus taskStatus;

    public Task(String title) {
        this.title = title;
        this.taskStatus = TaskStatus.CREATED;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskDto toDto(){
        TaskDto dto = new TaskDto();
        dto.setId(String.valueOf(id));
        dto.setTitle(title);
        dto.setDescription(description);
        dto.setTaskStatus(String.valueOf(taskStatus));
        return dto;
    }
}
