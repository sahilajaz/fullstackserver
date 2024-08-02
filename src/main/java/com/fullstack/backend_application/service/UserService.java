package com.fullstack.backend_application.service;

import com.fullstack.backend_application.Model.User;
import com.fullstack.backend_application.Repository.UserRepository;
import com.fullstack.backend_application.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void createNewUser(User user) {
         userRepository.save(user);
    }

    public List<User> getUsers() {
        return  userRepository.findAll();
    }

    public User findUserThroughId(Long id) {
        return  userRepository.findById(id)
                .orElseThrow(() ->new UserNotFoundException(id));
    }


    public User updateUserInfo(Long id, User newUser) {
        return userRepository.findById(id)
                .map(user -> {
                    System.out.println("Found user: " + user);
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    public String deleteTheUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "user with the id " +id+ " has been deleted";
    }
}
