package com.example.demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.example.demo.bean.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByEmail(String email);
    Flux<User> findByStatus(String status);
    Mono<User> findById(String userId);
}
