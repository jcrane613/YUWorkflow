package com.reljicd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.reljicd.model.Product;
import com.reljicd.model.User;
import com.reljicd.model.Workflow;
import com.reljicd.repository.ProductRepository;
import com.reljicd.repository.UserRepository;

@SpringBootApplication
public class ShoppingCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }
    
    @Bean
	CommandLineRunner runner(ProductRepository productRepository, UserRepository userRepository) {
		return args -> {
			Product p1 = new Product(new Long(613), "form1");
			Workflow w1 = new Workflow();
			List<User> users = userRepository.findAll();
			w1.setFlow((ArrayList<User>) users);
			p1.setWorkflow(w1);
			productRepository.save(p1);
			System.out.println("Current User at initialization: " + w1.getCurrentUser().getEmail());
		};
	}
    
}
 