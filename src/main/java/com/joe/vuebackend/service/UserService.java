package com.joe.vuebackend.service;

import com.joe.vuebackend.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {

    Optional<User> login(User user);
}
