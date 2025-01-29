package com.example.demo.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.bean.ProductEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {

	Mono<ProductEntity> findByName(String name);

	Flux<ProductEntity> findByStatus(String status);

}