package com.todo.dave.TodoApp.services.serviceimpl;


import com.todo.dave.TodoApp.customexception.CustomException;
import com.todo.dave.TodoApp.dto.UserDTO;
import com.todo.dave.TodoApp.models.User;
import com.todo.dave.TodoApp.repositories.UserRepository;
import com.todo.dave.TodoApp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final HttpServletRequest request;
    public User save(UserDTO userDTO) throws CustomException {
        Optional<User> existingUser = userRepository.findByUsername(userDTO.getUsername());
        if (existingUser.isPresent()){
            throw new CustomException("User already Exists");
            //throw new RuntimeException("User already exists with username: "+ userDTO.getUsername());
        }
        User user = mapToUser(userDTO);
        //User user = new User(userDTO.getId(),userDTO.getUsername(), userDTO.getPassword());
        return userRepository.save(user);
    }


    public User findUser(UserDTO userDTO) {
        User user =  userRepository.findByUsername(userDTO.getPassword()).get();
        if (user.getPassword().equals(userDTO.getPassword()) && user.getUsername().equals(userDTO.getUsername())){
            return user;
        }
        return null;
    }

    public int loginAuth(UserDTO userDTO){
    int status=0;
    User user = userRepository.findByUsernameAndPassword(userDTO.getUsername(),userDTO.getPassword()).get();
   if(user != null){
       if (user.getUsername().equals(userDTO.getUsername()) && user.getPassword().equals(userDTO.getPassword())){
           status=1;
           HttpSession session =request.getSession();
           session.setAttribute("id",user.getId());
       }
       else if (userDTO.getUsername().equals("admin") && userDTO.getPassword().equals("admin")){
           status=1;
       }
   }
    else{

        status=0;
    }

    return status;

    }

    public User loginUser(UserDTO userDTO) throws CustomException {
        User user =  userRepository.findByUsername(userDTO.getUsername()).get();
        if (user.getPassword().equals(userDTO.getPassword()) && user.getUsername().equals(userDTO.getUsername())){
            return mapToUser(userDTO);
        }
        throw new CustomException("invalid login credential");
    }

    public User authenticateUser(String username, String password) throws CustomException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new CustomException("Invalid username or password");
        }
        User user = optionalUser.get();
        if (!user.getPassword().equals(password)) {
            throw new CustomException("Invalid username or password");
        }
        return user;
    }


    private UserDTO mapToUserDTO(User user){
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        return userDTO;
    }

    private User mapToUser(UserDTO userDTO){
        User user = User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
        return user;
    }
}
