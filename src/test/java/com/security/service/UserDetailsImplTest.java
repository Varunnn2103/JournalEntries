package com.security.service;

import com.security.Repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class UserDetailsImplTest {


    @InjectMocks
    private UserDetailsImpl userDetails;

    @MockitoBean
    private UserRepo userRepo;

    @Test
    void loadUserByUsernameTest(){
        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn((com.security.entity.User) User.builder().username("Varun").password("xvz").roles(String.valueOf(new ArrayList<>())).build());
        UserDetails userDetails1 = userDetails.loadUserByUsername("Varun");
        Assertions.assertNotNull(userDetails1);
    }
}
