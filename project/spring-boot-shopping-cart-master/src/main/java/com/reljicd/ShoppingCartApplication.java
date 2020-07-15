package com.reljicd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.reljicd.model.User;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.impl.UserServiceImp;

@SpringBootApplication
public class ShoppingCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }
    
    @Bean
	CommandLineRunner runner(UserServiceImp userService) {
		return args -> {
			User yair = new User();
			yair.setId(new Long(4));
			yair.setActive(1);
			yair.setEmail("yaircaplan@gmail.com");
			yair.setLastName("Caplan");
			yair.setName("Yair");
			yair.setPassword("password");
			yair.setUsername("yairc");
			userService.saveUser(yair);
		};
	}
    
}
