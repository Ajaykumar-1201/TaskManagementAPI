package com.example.TaskManagementAPI.Controllers;

import com.example.TaskManagementAPI.Models.User;
import com.example.TaskManagementAPI.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User createdUser = userService.createUser(newUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            // Handle user not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser,
                                            User authenticatedUser) {
        // Ensure that the authenticated user is updating their own profile
        if (!authenticatedUser.getId().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // User is not authorized to update this profile
        }

        User updated = userService.updateUser(userId, updatedUser);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            // Handle user not found or other errors during update
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
