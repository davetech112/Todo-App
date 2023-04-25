package com.todo.dave.TodoApp.services;


import com.todo.dave.TodoApp.customexception.CustomException;
import com.todo.dave.TodoApp.dto.UserDTO;
import com.todo.dave.TodoApp.models.TodoItem;
import com.todo.dave.TodoApp.models.User;

import java.util.List;

public interface UserService {
    User save(UserDTO userDTO) throws CustomException;
    User findUser(UserDTO userDTO);
     User loginUser(UserDTO userDTO) throws CustomException;
     User authenticateUser(String username, String password)throws CustomException;
     int loginAuth(UserDTO userDTO);

}
