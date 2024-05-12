package com.innsys.usermanagement.service;

import com.innsys.usermanagement.model.User;
import com.innsys.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    public boolean deleteUser(Long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User saveUser(User user) {
        User savedUser = null;
        if (userRepository.countByUsername(user.getUsername()) == 0){
           savedUser = userRepository.save(user);
            return savedUser;
        }
      return null;
    }

    public Optional<User> updateUser( Long id,User user) {
        Optional<User> oldUser = userRepository.findById(id);
        user.setId(id);
        if (oldUser.isPresent() && oldUser.get().getUsername().equals(user.getUsername())
                || userRepository.countByUsername(user.getUsername()) == 0) {
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }


    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public boolean checkUsernameAvailable(String username){
       return userRepository.countByUsername(username) == 0;
    }
}
