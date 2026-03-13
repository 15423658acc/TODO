package com.example.todo.entity;

import java.time.LocalDateTime;

public class Todo {
    private Integer id;
    private String title;
    private Boolean completed;
    private LocalDateTime createdAt;

    // getters and setters（或者使用 Lombok 的 @Data）
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}