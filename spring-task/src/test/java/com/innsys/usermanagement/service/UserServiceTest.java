package com.innsys.usermanagement.service;

import com.innsys.usermanagement.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void getAllUsers_shouldReturnAllUsers(){
        User newUser1 = new User("Ashraf","Ablia","ashf","Aa1122");
        User newUser2 = new User("test","Ablia","ashf1","Aa11220");
        User newUser3 = new User("test1","Ablia","ashf2","Aa11221");

        userService.saveUser(newUser1);
        userService.saveUser(newUser2);
        userService.saveUser(newUser3);

      List<User> usersList = userService.getAllUsers();

      assertNotNull(usersList);
      assertEquals(3, usersList.size());

      assertEquals("test",usersList.get(1).getFirstName());
        userService.deleteUser(usersList.get(0).getId());
        userService.deleteUser(usersList.get(1).getId());
        userService.deleteUser(usersList.get(2).getId());

         usersList = userService.getAllUsers();

        assertEquals(0, usersList.size());
    }

    @Test
    public void createUser_shouldSaveUserAndReturnIt(){
        User newUser = new User();
        newUser.setFirstName("Ashraf");
        newUser.setLastName("Ablia");
        newUser.setUsername("ashf");
        newUser.setPassword("Aa1122");

        User savedUser = userService.saveUser(newUser);

        assertNotNull(savedUser);
        assertEquals("Ashraf", savedUser.getFirstName());
        assertEquals("Ablia", savedUser.getLastName());
        assertEquals("ashf", savedUser.getUsername());
        assertEquals("Aa1122", savedUser.getPassword());

        userService.deleteUser(savedUser.getId());
    }

    @Test
    public void updateUser_shouldUpdateExistUser(){
        User newUser = new User("test1","Ablia","ashf2","Aa11221");
        User savedUser =  userService.saveUser(newUser);

        savedUser.setFirstName("newName");
        userService.updateUser(savedUser.getId(),savedUser);

        User updatedUser = userService.findUserById(savedUser.getId()).get();
        assertEquals("newName", updatedUser.getFirstName());
        userService.deleteUser(updatedUser.getId());
    }



}