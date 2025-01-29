package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.ProductService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/user/customer")
@CrossOrigin(origins="*")
public class CustomerController {
	
	@Autowired
	private ProductService productService;
	
	  @GetMapping("/active")
	    public ResponseEntity<Flux<ProductDTO>> getActiveProducts() {
	        return ResponseEntity.ok(productService.getActiveProducts());
	    }

}

