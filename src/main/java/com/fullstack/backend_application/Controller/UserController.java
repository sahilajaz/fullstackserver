package com.fullstack.backend_application.Controller;

import com.fullstack.backend_application.Model.User;
import com.fullstack.backend_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://fullstack-front-three.vercel.app")
//"http://localhost:5173"
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
         userService.createNewUser(user);
    }
   @GetMapping("/get/users")
   @ResponseStatus(HttpStatus.OK)
    public List<User> fetchUsers() {
         return userService.getUsers();
    }
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
         return userService.findUserThroughId(id);
    }


    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.updateUserInfo(id, newUser);
    }
    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable Long id) {
         return userService.deleteTheUser(id);
    }


}
