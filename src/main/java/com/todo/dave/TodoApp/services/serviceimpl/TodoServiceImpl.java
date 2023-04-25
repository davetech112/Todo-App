package com.todo.dave.TodoApp.services.serviceimpl;

import com.todo.dave.TodoApp.dto.UserDTO;
import com.todo.dave.TodoApp.models.TodoItem;
import com.todo.dave.TodoApp.models.User;
import com.todo.dave.TodoApp.repositories.TodoItemRepository;
import com.todo.dave.TodoApp.repositories.UserRepository;
import com.todo.dave.TodoApp.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    private  TodoItemRepository todoItemRepository;
    private UserRepository userRepository;
@Autowired
    public TodoServiceImpl(TodoItemRepository todoItemRepository, UserRepository userRepository) {
        this.todoItemRepository = todoItemRepository;
        this.userRepository = userRepository;
    }

//    public Iterable<TodoItem> getAll(){
//        return todoItemRepository.findAll();
//    }
//public List<TaskDTO> getAllTask(Long userId) {
//    List<Task> tasks = taskRepository.findAllByUserUserId(userId);
//    System.out.println(tasks.size());
//
//    return tasks.stream().map(this::mappedToDTO).toList();
//}
//public List<TodoItem> getAllU(Long userId){
//    List<TodoItem> todoItems = todoItemRepository.findAllByUserUserId(userId);
//    return todoItems;
//}
    public List<TodoItem> getAllUu(Long userId){
        List<TodoItem> todoItems = todoItemRepository.findQueryTask(userId);
        return todoItems;
    }




//    public Optional<TodoItem> getAllTodoItemsByUser(UserDTO userDTO) {
//        return todoItemRepository.findTodoItemsByUser(userDTO);
//    }



//    public Optional<TodoItem> getAllItemsByUser(UserDTO userDTO) {
//        return todoItemRepository.findTodoItemsByUser(userDTO);
//    }

    @Override
    public Iterable<TodoItem> getAll() {
        return null;
    }

    public Optional<TodoItem> getById(Long id){
        return todoItemRepository.findById(id);
    }

//    @Override
//    public TodoItem save(TodoItem todoItem) {
//        return null;
//    }
    public TodoItem save(TodoItem todoItem){
        if(todoItem.getId() == null){
        todoItem.setCreatedAt(Instant.now());
        }
        todoItem.setUpdatedAt(Instant.now());
        return todoItemRepository.save(todoItem);
    }

//    public TodoItem save(TodoItem todoItem, User user){
//        if(todoItem.getId() == null){
//            todoItem.setCreatedAt(Instant.now());
//        }
//        todoItem.setUpdatedAt(Instant.now());
//
//
//        return todoItemRepository.save(todoItem);
//    }
    public void delete(TodoItem todoItem){
        todoItemRepository.delete(todoItem);
    }

}
