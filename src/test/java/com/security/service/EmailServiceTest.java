package com.security.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void emailTest(){
        emailService.sendEmail("kalyadapuvarun21@gmail.com"
                ,"Testing the Applicaton"
                ,"Heyy How are you sending mail from the Spring Application!!");
    }
}
