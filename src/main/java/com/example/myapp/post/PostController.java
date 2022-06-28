package com.example.myapp.post;

import com.example.myapp.Aop.Log;
import com.example.myapp.user.User;
import com.example.myapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.ManyToOne;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    @GetMapping("posts")
    public String listPosts(Model model){
        List<Post> postList = postRepository.findAll();
        model.addAttribute("postList",postList);
        return "posts";
    }

    @GetMapping("posts/{id}")
    public String listPostsSellect(Model model,@PathVariable("id") Integer uid){
        List<Post> postList = postRepository.findAll();
        model.addAttribute("postList",postList);
        model.addAttribute("userId",uid);
        return "postselect";
    }


    @GetMapping("posts/new")
    public String showNewPost(Model model){
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList",userList);
        model.addAttribute("post", new Post());
        model.addAttribute("pageTitle","Add New Post");
        return "post_form";
    }

    @Log
    @PostMapping("/posts/save")
    public String updateOrSave(Post post, RedirectAttributes ra){
        postService.updateOrSavePost(post);
        ra.addFlashAttribute("massage1","The Post Has Been Save Successfully");
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public  String editePosts(@PathVariable("id") Integer id,Model model){
        Post post = postRepository.findById(id).get();
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList",userList);
        model.addAttribute("pageTitle","Edite post ("+id+")");
        model.addAttribute("post",post);
        return "post_form";
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable("id") Integer id,RedirectAttributes ra){
        postRepository.deleteById(id);
        ra.addFlashAttribute("massage","The Post Id("+id+") has been deleted ");
        return "redirect:/posts";
    }
}
