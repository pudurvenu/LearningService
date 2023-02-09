package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.entity.UserRole;
import com.model.User;
import com.repository.UserRepository;
import com.service.impls.AccountServiceImpl;
import com.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import java.util.*;
import com.model.Roles;


@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.feignclient"})
public class LearningServiceApplication {
	
	@Autowired
	public UserRepository repository;
	
	@Autowired
	private AccountServiceImpl accountService;
	
	@PostConstruct
	public void start() {
		Roles role = new Roles();
		role.setUserRole(UserRole.ADMIN);
		role.setDescription("Student role allows to access premium courses");
		UserDTO user = new UserDTO("a","b", "c", "venugopalpudur1@gmail.com", "7720", "12345", Arrays.asList(role));
		accountService.signUp(user);
		//repository.save(new User(" bcrypt.encode("12345"), null, false, false));
		
	}
	public static void main(String[] args) {
		SpringApplication.run(LearningServiceApplication.class, args);
	}

}
