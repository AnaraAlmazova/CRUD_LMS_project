package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Student;
import peaksoft.entities.User;
import peaksoft.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/users";
    }
    @GetMapping("/addUser")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "user/saveUser";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user")User user){
        System.out.println(user.getRoleName());

        userService.addUser(user, user.getRoleName());
        return "redirect:/users";
    }
    @GetMapping("/profile")
    public String profile(HttpServletRequest request, Model model){
        Principal principal = request.getUserPrincipal();
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user/profile";
    }


}
