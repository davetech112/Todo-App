package com.todo.dave.TodoApp.services;

import com.todo.dave.TodoApp.dto.UserDTO;
import com.todo.dave.TodoApp.models.TodoItem;
import com.todo.dave.TodoApp.models.User;
import com.todo.dave.TodoApp.repositories.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    @Autowired
    Iterable<TodoItem> getAll();
    Optional<TodoItem> getById(Long id);
    TodoItem save(TodoItem todoItem);
    //TodoItem save(TodoItem todoItem, User user);
    void delete(TodoItem todoItem);
    //List<TodoItem> getAllU(Long userId);
    List<TodoItem> getAllUu(Long userId);
    //Optional<TodoItem> getAllTodoItemsByUser(UserDTO userDTO);



}
