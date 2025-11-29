package com.security.service;


import com.security.Repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTestClass {

    @Autowired
    private UserRepo userRepo;

    @ParameterizedTest
    @CsvSource({
            "Varun",
            "ayan",
            "pratiksha"
    })
    @ValueSource(strings = {   //you can also give the type of value pass by using the ValueSourceAnnotation
            "Varun",
            "ayan",
            "ashish"
    })
    public void testFindByUserName(String userName){
        Assertions.assertNotNull(userRepo.findByUserName(userName));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,3,5",
            "2,7,9",
            "33,36,69"
    })
    public void testMultipleParam(int a,int b,int expected){
        Assertions.assertEquals(expected,a+b);
    }
}
