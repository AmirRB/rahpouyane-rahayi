package com.rahpouyan.rahayi.demo;

import com.rahpouyan.rahayi.demo.model.entity.User;
import com.rahpouyan.rahayi.demo.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private UserService userService;

    @Bean
    public CommandLineRunner runner() {
        CommandLineRunner runner = new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                userService.save(new User("amir", "7596", "admin"));
                userService.save(new User("orcl", "myjava123", "admin"));
            }
        };

        return runner;
    }

}
