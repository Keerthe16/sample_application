package com.example.demo.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {
	@Autowired
	private UserService userService;

	

	 @PostMapping("/register")
	    public Mono<String> register(@RequestBody UserDTO userDTO) {
	        return userService.registerUser(userDTO);
	    }

	    @PostMapping("/login")
	    public Mono<ResponseEntity<String>> loginUser(@RequestBody UserDTO userDTO) {
	        return userService.loginUser(userDTO.getEmail(), userDTO.getPassword())
	                .map(role -> ResponseEntity.ok(role))
	                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(e.getMessage())));
	    }

	    @PostConstruct
	    public void initializeAdmin() {
	        userService.addDefaultAdmin().subscribe();
	    }
}