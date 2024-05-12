package com.innsys.usermanagement.repository;

import com.innsys.usermanagement.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;


    @Test
    public void saveFindDeleteExistsTest(){
        User newUser = new User();
        newUser.setFirstName("Ashraf");
        newUser.setLastName("Ablia");
        newUser.setUsername("ashf");
        newUser.setPassword("Aa1122");

        userRepository.save(newUser);

        boolean exist = userRepository.countByUsername("ashf") == 1;
        assertTrue(exist);

        List<User> userList = userRepository.findAll();

        assertEquals(1, userList.size());

        User retrievedUser = userList.get(0);

        assertEquals("Ashraf", retrievedUser.getFirstName());
        assertEquals("Ablia", retrievedUser.getLastName());
        assertEquals("ashf", retrievedUser.getUsername());

        userRepository.deleteById(newUser.getId());

        userList = userRepository.findAll();

        assertEquals(0, userList.size());
    }

}