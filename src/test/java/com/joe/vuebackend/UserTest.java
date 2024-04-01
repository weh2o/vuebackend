package com.joe.vuebackend;

import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.repository.UserRepository;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserTest {

    @Setter(onMethod_ = @Autowired)
    private UserRepository repository;

    @Test
    void searchUser(){
        Optional<User> optional = repository.findByName("新之助");
        System.out.println();
    }
}
