package com.joe.vuebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VuebackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(VuebackendApplication.class, args);
    }

}
