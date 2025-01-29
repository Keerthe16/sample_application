package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ProductService;
import com.example.demo.dto.*;
import com.example.demo.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user/admin")
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	/*
	 * @GetMapping("/pendingUsers") public List<UserDTO> approveCustomers() {
	 * List<UserDTO> users = userService.getPendingUsers(); return users; }
	 */

	@GetMapping("/pendingUsers")
	public Flux<UserDTO> approveCustomers() {
		return userService.getPendingUsers();
	}

	/*
	 * @PutMapping("/approve/{id}") public UserDTO approveUser(@PathVariable String
	 * id) { return userService.approveUser(id); }
	 */

	@PutMapping("/approve/{id}")
	public Mono<UserDTO> approveUser(@PathVariable String id) {
		return userService.approveUser(id);
	}

	/*
	 * @PutMapping("/reject/{id}") public UserDTO rejectUser(@PathVariable String
	 * id) { return userService.rejectUser(id); }
	 */

	@PutMapping("/reject/{id}")
	public Mono<UserDTO> rejectUser(@PathVariable String id) {
		return userService.rejectUser(id);
	}

	/*
	 * @GetMapping("/products") public List<ProductDTO> getAllProducts() { return
	 * productService.getAllProducts(); }
	 */

	@GetMapping("/products")
	public ResponseEntity<Flux<ProductDTO>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	/*
	 * @GetMapping("/products/{id}") public ProductDTO getProductById(@PathVariable
	 * Long id) { return productService.getProductById(id); }
	 */

	@GetMapping("/products/{id}")
	public Mono<ResponseEntity<ProductDTO>> getProductById(@PathVariable String id) {
		return productService.getProductById(id).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	/*
	 * @PostMapping("/products/create-new") public ResponseEntity<Map<String,
	 * String>> createProduct(@Validated @RequestBody ProductDTO product) { String
	 * message = productService.createProduct(product); if
	 * (message.equals("Product Alreay Exists!!")) { Map<String, String> response =
	 * new HashMap<>(); response.put("message", "Product Alreay Exists!!"); return
	 * ResponseEntity.badRequest().body(response); }
	 * productService.createProduct(product); { Map<String, String> response = new
	 * HashMap<>(); response.put("message", "Product Added Successfull"); return
	 * ResponseEntity.ok(response); }
	 * 
	 * }
	 */

	@PostMapping("/products/create-new")
	public Mono<ResponseEntity<Map<String, String>>> createProduct(@Validated @RequestBody ProductDTO product) {
		return productService.createProduct(product).flatMap(message -> {
			Map<String, String> response = new HashMap<>();
			response.put("message", (String) message);
			return Mono.just(message.equals("Product Already Exists!!") ? ResponseEntity.badRequest().body(response)
					: ResponseEntity.ok(response));
		});
	}

	/*
	 * @PutMapping("/products/update/{id}") public ProductDTO
	 * updateProduct(@PathVariable Long id, @RequestBody ProductDTO product) {
	 * return productService.updateProduct(id, product); }
	 */

	@PutMapping("/products/update/{id}")
	public Mono<ResponseEntity<ProductDTO>> updateProduct(@PathVariable String id, @RequestBody ProductDTO product) {
		return productService.updateProduct(id, product).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	/*
	 * @DeleteMapping("/products/delete/{id}") public void
	 * deleteProduct(@PathVariable Long id) { productService.deleteProduct(id); }
	 */

	@DeleteMapping("/products/delete/{id}")
	public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String id) {
		return productService.deleteProduct(id).then(Mono.just(ResponseEntity.noContent().build()));
	}

}
