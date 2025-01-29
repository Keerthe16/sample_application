package com.example.demo.service;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Mono<String> registerUser(UserDTO userDTO) {
        
        return userRepository.findByEmail(userDTO.getEmail())
                .flatMap(existingUser -> Mono.just("Email already exists!")) 
                .switchIfEmpty(
                    userRepository.save(new User(null,
                            userDTO.getEmail(),
                            passwordEncoder.encode(userDTO.getPassword()),
                            userDTO.getUsername(),
                            "CUSTOMER",
                            "PENDING"))
                            .thenReturn("Registration successful! Status: pending approval.")
                );
    }
    
   
	/*
	 * public UserDTO registerUser(UserDTO userDTO) { User user =
	 * convertToEntity(userDTO);
	 * user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	 * user.setRole("CUSTOMER"); user.setStatus("PENDING"); return
	 * convertToDto(userRepository.save(user)); }
	 */
    
    public Mono<String> loginUser(String email, String password) {
        return userRepository.findByEmail(email)
                .flatMap(user -> {
                    if ("PENDING".equals(user.getStatus())) {
                        return Mono.error(new Exception("Your account is pending approval"));
                    }
                    if (!passwordEncoder.matches(password, user.getPassword())) {
                        return Mono.error(new Exception("Invalid email or password"));
                    }

                    return Mono.just(user.getRole());
                });
    }

    
	/*
	 * public void addDefaultAdmin() { if
	 * (userRepository.findByEmail("admin@example.com").isEmpty()) { User admin =
	 * new User(); admin.setEmail("admin@example.com");
	 * admin.setPassword(passwordEncoder.encode("admin123"));
	 * admin.setFirstname("Admin"); admin.setLastname("User");
	 * admin.setRole("ADMIN"); admin.setStatus("APPROVED");
	 * userRepository.save(admin); } }
	 */

    
    public Mono<Void> addDefaultAdmin() {
        User defaultAdmin = new User();
        defaultAdmin.setEmail("admin@example.com");
        defaultAdmin.setPassword(passwordEncoder.encode("admin@123"));
        defaultAdmin.setRole("ADMIN");
        defaultAdmin.setStatus("APPROVED");

        return userRepository.save(defaultAdmin).then();
    }
    
    public Flux<UserDTO> getPendingUsers() {
        return Flux.fromIterable(userRepository.findByStatus("PENDING")
                .toStream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    
	/*
	 * public UserDTO approveUser(String id) { User user =
	 * userRepository.findById(id).orElseThrow(() -> new
	 * RuntimeException("User not found")); user.setStatus("APPROVED"); return
	 * convertToDto(userRepository.save(user)); }
	 */
    
    public Mono<UserDTO> approveUser(String id) {
        return userRepository.findById(id)
                .flatMap(user -> {
                    user.setStatus("APPROVED");
                    return userRepository.save(user); 
                })
                .map(this::convertToDto); 
    }
	/*
	 * public UserDTO rejectUser(String userId) { User user =
	 * userRepository.findById(userId).orElseThrow(() -> new
	 * RuntimeException("User not found")); user.setStatus("REJECTED"); return
	 * convertToDto(userRepository.save(user)); }
	 */
    
    public Mono<UserDTO> rejectUser(String userId) {
        return userRepository.findById(userId)
                .flatMap(user -> {
                    user.setStatus("REJECTED");
                    return userRepository.save(user); 
                })
                .map(this::convertToDto); 
    }

   

    
    private UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        
        userDTO.setRole(user.getRole());
        userDTO.setStatus(user.getStatus());
        return userDTO;
    }
}
