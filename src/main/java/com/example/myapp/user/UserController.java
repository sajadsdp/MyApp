package com.example.myapp.user;

import com.example.myapp.exportion.Export;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.ICsvBeanReader;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Controller
public class UserController {

    @Autowired
    private Export export;

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
    public String updateOrSave(@Valid User user, RedirectAttributes ra, BindingResult bindingResult) throws Exception {
            userService.updateOrSaveUser(user);
            ra.addFlashAttribute("massage1", "The User Has Been saved Successfully");
            return "redirect:/users";
    }


    @GetMapping("/users/edit/{id}")
    public String editeUser(@PathVariable("id") Integer id, Model model){
        User user = userRepository.findById(id).get();
        model.addAttribute("pageTitle","Edite user ("+id+")");
        model.addAttribute("user",user);
        return "user_form";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id,RedirectAttributes ra){
        userRepository.deleteById(id);
        ra.addFlashAttribute("massage","The User Id("+id+") has been deleted ");
        return "redirect:/users";
    }

    @GetMapping("/download/user")
    public void downloadCsv(HttpServletResponse response,RedirectAttributes ra) throws IOException {
        List<User> listUsers = userService.getAllUsers();
        Export.ExCsv task = new Export.ExCsv();
        task.call(listUsers,response);
//        Export.ExThread Exthread = new Export.ExThread();
//        Exthread.start();
//        Exthread.run(listUsers,response);
//        export.exportToVSC(listUsers,response);
    }

}
