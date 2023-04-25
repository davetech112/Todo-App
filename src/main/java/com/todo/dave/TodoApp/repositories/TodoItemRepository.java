package com.todo.dave.TodoApp.repositories;

import com.todo.dave.TodoApp.dto.UserDTO;
import com.todo.dave.TodoApp.models.TodoItem;
import com.todo.dave.TodoApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

    @Query(value = "select * from todo_items  where userid = ?1",
            nativeQuery = true)
    List<TodoItem> findQueryTask(Long userId);
}
