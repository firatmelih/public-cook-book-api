package com.project.questapp.services;

import com.project.questapp.entities.User;
import com.project.questapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE
    public User createUser(User user) {
        return userRepository.save(user);
    }
    // CREATE END

    // READ
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    // READ END

    // UPDATE
    public User updateOneUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            userRepository.save(existingUser);
            return existingUser;
        } else {
            return null;
        }
    }
    // UPDATE END

    // DELETE
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    // DELETE END


}
