package com.example.myapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public String listUsers(Model model){
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList",userList);
        return "users";
    }

    @GetMapping("users/new")
    public String showNewUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle","Add New User");
        return "user_form";
    }


    @PostMapping("/users/save")
    public String updateOrSave(User user, RedirectAttributes ra){
        userService.updateOrSaveUser(user);
        ra.addFlashAttribute("massage1","The User Has Been saved Successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editeUser(@PathVariable("id") Integer id, Model model){
        User user = userRepository.findById(id).get();
        model.addAttribute("pageTitle","Edite user ("+id+")");
//        user.setId(id);
        model.addAttribute("user",user);
        return "user_form";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id,RedirectAttributes ra){
        userRepository.deleteById(id);
        ra.addFlashAttribute("massage","The User Id("+id+") has been deleted ");
        return "redirect:/users";
    }


}
