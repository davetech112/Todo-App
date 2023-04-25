package com.todo.dave.TodoApp.controllers;


import com.todo.dave.TodoApp.customexception.CustomException;
import com.todo.dave.TodoApp.dto.UserDTO;
import com.todo.dave.TodoApp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final HttpServletRequest req;





    @GetMapping("/login")
    public String displayIndex(Model model, CustomException e) {
        HttpSession session=req.getSession();
        if (session.getAttribute("id")!=null){
            return "redirect:/";
        }else {
            model.addAttribute("user", new UserDTO());
            model.addAttribute("errorMessage", e.getMessage());

            return "login-signup";
        }
    }


    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("userDto") @Valid UserDTO userDto, jakarta.servlet.http.HttpSession session, Model model, CustomException ex) {
        ModelAndView modelAndView =new ModelAndView();
        int user = userService.loginAuth(userDto);
        if(user == 1){
            session.setAttribute("userDTO",userDto);
            modelAndView.setViewName("redirect:/");
        }
        else if (user==0){
            modelAndView.addObject("errorMessage", ex.getMessage());
            modelAndView.setViewName("redirect:error");

        }
        return modelAndView;
    }


    @GetMapping("/logout")
    public String logout(jakarta.servlet.http.HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }


    @GetMapping("/error")
    public String error() {
        return "error";
    }


    @GetMapping("/signup")
    public String displaySignup(Model model) {
        HttpSession session=req.getSession();
        if (session.getAttribute("id")!=null){
            return "redirect:/";
        }else {

            model.addAttribute("user", new UserDTO());
            return "login-signup";
        }
    }
    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult,
                               Model model, CustomException ex) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "login-signup";
        }
        try{
            model.addAttribute("user", userDTO);
            userService.save(userDTO);
        } catch (CustomException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("signup", true);
            return "login-signup";
        }

        return "redirect:/login";
    }
}
