package com.todo.dave.TodoApp.controllers;

import com.todo.dave.TodoApp.dto.UserDTO;
import com.todo.dave.TodoApp.models.TodoItem;
import com.todo.dave.TodoApp.models.User;
import com.todo.dave.TodoApp.services.TodoService;
import com.todo.dave.TodoApp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.Valid;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TodoService todoService;


//    public String allTask(HttpSession httpSession) {
//        UserDTO userDTO = (UserDTO) httpSession.getAttribute("userDTO");
//        List<TaskDTO> tasks =  taskService.getAllTask(userDTO.getUserId());
//        httpSession.setAttribute("task", tasks);
//        return "redirect:/listTasks";
//    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView , HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("id")==null){
             modelAndView.setViewName("redirect:/login");
        }else {
            Long id = (Long)session.getAttribute("id");
            modelAndView.setViewName("index");
            List<TodoItem> todoItemList = todoService.getAllUu(id);
            System.out.println(todoItemList);
            modelAndView.addObject("todoItems", todoItemList);
        }
         return modelAndView;
    }

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem, Model model,HttpSession session){
        model.addAttribute("todoItem", new TodoItem());
        Long id = (Long) session.getAttribute("id");
        model.addAttribute("sessionId", id);

         return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model, User user,
                                 HttpSession session, HttpServletRequest request){
        TodoItem item = new TodoItem();
        Long id = (Long)session.getAttribute("id");

        item.setDescription(todoItem.getDescription());
        item.setIsComplete(todoItem.getIsComplete());
        System.out.println(id+" is good");
        item.setUserid(id);
        //Long id = todoService//.showId(iSessiond);



        todoService.save(item);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model){
        TodoItem  todoItem = todoService.getById(id).orElseThrow(() -> new IllegalArgumentException("Todo item with id : "+
                id +" not found"));
        todoService.delete(todoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String updateItem(@PathVariable("id") Long id, Model model){
        TodoItem  todoItem = todoService.getById(id).orElseThrow(() -> new IllegalArgumentException("Todo item with id : "+
                id +" not found"));
        model.addAttribute("todo",todoItem);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid TodoItem todoItem,
                                 BindingResult result, Model model){
        TodoItem  item = todoService.getById(id).orElseThrow(() -> new IllegalArgumentException("Todo item with id : "+
                id +" not found"));
        item.setIsComplete(todoItem.getIsComplete());
        item.setDescription(todoItem.getDescription());
        todoService.save(item);
        return "redirect:/";
    }
}
