package com.joe.vuebackend.service.impl;


import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.repository.UserRepository;
import com.joe.vuebackend.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;

    @Override
    public Optional<User> login(User user) {
        Optional<User> target = Optional.empty();
        Optional<User> dbResult = userRepository.findByName(user.getName());
        if (dbResult.isPresent()) {
            User dbUser = dbResult.get();
            if (dbUser.getPassword().equals(user.getPassword())) {
                target = Optional.of(dbUser);
            }
        }
        return target;

    }
}
