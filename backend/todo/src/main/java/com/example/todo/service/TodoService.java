package com.example.todo.service;

import com.example.todo.entity.Todo;
import com.example.todo.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoMapper todoMapper;

    public List<Todo> getAllTodos() {
        return todoMapper.findAll();
    }

    public Todo getTodoById(Integer id) {
        return todoMapper.findById(id);
    }

    public Todo createTodo(Todo todo) {
        todo.setCompleted(false); // 默认未完成
        todoMapper.insert(todo);
        return todo;
    }

    public Todo updateTodo(Integer id, Todo todo) {
        Todo existing = todoMapper.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setTitle(todo.getTitle());
        existing.setCompleted(todo.getCompleted());
        todoMapper.update(existing);
        return existing;
    }

    public boolean deleteTodo(Integer id) {
        return todoMapper.deleteById(id) > 0;
    }
}