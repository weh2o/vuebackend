package com.joe.vuebackend.service.impl;

import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailService implements UserDetailsService {

    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByAccount(username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("帳號不存在");
        }
        return optional.get();
    }
}
