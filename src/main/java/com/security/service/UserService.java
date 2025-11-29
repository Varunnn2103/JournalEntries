package com.security.service;

import com.security.entity.User;
import com.security.Repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    private static final PasswordEncoder password = new BCryptPasswordEncoder();

    public void createJournalEntry(User user){
        userRepo.save(user);
    }

    public boolean SaveUser(User user){
        try {
            user.setPassword(password.encode(user.getPassword()));
            user.setRoles(Arrays.asList("User"));
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            logger.error("Error Occurred for the username {} :",user.getUserName());
            return false;
        }
    }

    public void saveAdmin(User user){
        user.setPassword(Objects.requireNonNull(password.encode(user.getPassword())));
        user.setRoles(Arrays.asList("User","ADMIN"));
        userRepo.save(user);
    }


    public List<User> getAll(){
        return userRepo.findAll();
    }


    public void DeleteEntry(String id){
        userRepo.deleteById(id);
    }

    public Optional<User> getEntryById(String id){
        return userRepo.findById(id);
    }

    public Optional<User> update(String id){
        return userRepo.findById(id);
    }

    public User getByUserName(String username){
        return userRepo.findByUserName(username);
    }

}
