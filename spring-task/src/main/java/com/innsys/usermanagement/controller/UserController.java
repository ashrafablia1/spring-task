package com.innsys.usermanagement.controller;


import com.innsys.usermanagement.model.User;
import com.innsys.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all/users")
    public ResponseEntity<?> getAllUsers(){
       List<User> usersList = userService.getAllUsers();
       return ResponseEntity.ok().body(usersList);
    }

    @PostMapping("/create/user")
    public ResponseEntity<?> createUser(@RequestBody User user){
       User savedUser = userService.saveUser(user);

       if (savedUser != null) {
           URI location = ServletUriComponentsBuilder
                   .fromCurrentContextPath()
                   .path("/user/{id}")
                   .buildAndExpand(savedUser.getId())
                   .toUri();
           return ResponseEntity.created(location).body(savedUser);
       }
     return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
    }

    @PutMapping("/update/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user){
        Optional<User> updatedUser = userService.updateUser(id, user);
        if (updatedUser.isPresent()) {
            return ResponseEntity.ok().body(updatedUser);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Username"+user.getUsername()+" already exists or user ID"+user.getId()+" not exists");
    }

    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        if(userService.deleteUser(id)){
         return ResponseEntity.ok("User with ID " + id + " has been deleted");
        }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
       Optional<User> retrievedUser = userService.findUserById(id);
       if (retrievedUser.isPresent()){
           return ResponseEntity.ok().body(retrievedUser);
       }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found");
    }


    @GetMapping("/usernameAvailable/{username}")
    public ResponseEntity<Boolean> checkUsernameAvailable(@PathVariable String username) {
        boolean available = userService.checkUsernameAvailable(username);
        return ResponseEntity.status(HttpStatus.OK).body(available);
    }


}
