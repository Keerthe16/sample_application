package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.ProductEntity;
import com.example.demo.dto.ProductDTO;
import com.example.demo.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
     
	/*
	 * public List<ProductDTO> getAllProducts() { List<ProductEntity> products =
	 * productRepository.findAll(); return
	 * products.stream().map(this::convertToDTO).collect(Collectors.toList()); }
	 */
    
    public Flux<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .map(this::convertToDTO);
    }
    
	/*
	 * public ProductDTO getProductById(Long productId) { ProductEntity product =
	 * productRepository.findById(productId).orElseThrow(); return
	 * convertToDTO(product); }
	 */
    
    public Mono<ProductDTO> getProductById(String productId) {
        return productRepository.findById(productId)
                .map(this::convertToDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));
    }
    
	/*
	 * public String createProduct(ProductDTO productDTO) { if
	 * (productRepository.findByName(productDTO.getName()) != null) { return
	 * "Product Already Exists!!"; } ProductEntity product =
	 * convertToEntity(productDTO); productRepository.save(product); return
	 * "Product Added Successfully"; }
	 */
    
    public Mono<Object> createProduct(ProductDTO productDTO) {
        return productRepository.findByName(productDTO.getName())
                .flatMap(existingProduct -> Mono.error(new RuntimeException("Product Already Exists!!")))
                .switchIfEmpty(Mono.defer(() -> {
                    ProductEntity product = convertToEntity(productDTO);
                    return productRepository.save(product)
                            .thenReturn("Product Added Successfully");
                }));
    }
    
	/*
	 * public ProductDTO updateProduct(long productId, ProductDTO productDTO) {
	 * ProductEntity existingProduct =
	 * productRepository.findById(productId).orElseThrow();
	 * existingProduct.setName(productDTO.getName());
	 * existingProduct.setDescription(productDTO.getDescription());
	 * existingProduct.setPrice(productDTO.getPrice());
	 * existingProduct.setCategory(productDTO.getCategory());
	 * existingProduct.setQuantity(productDTO.getQuantity());
	 * existingProduct.setStatus(productDTO.getStatus());
	 * 
	 * ProductEntity updatedProduct = productRepository.save(existingProduct);
	 * return convertToDTO(updatedProduct); }
	 */
    
    public Mono<ProductDTO> updateProduct(String productId, ProductDTO productDTO) {
        return productRepository.findById(productId)
                .flatMap(existingProduct -> {
                    existingProduct.setName(productDTO.getName());
                    existingProduct.setDescription(productDTO.getDescription());
                    existingProduct.setPrice(productDTO.getPrice());
                    existingProduct.setCategory(productDTO.getCategory());
                    existingProduct.setQuantity(productDTO.getQuantity());
                    existingProduct.setStatus(productDTO.getStatus());
                    return productRepository.save(existingProduct);
                })
                .map(this::convertToDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));
    }
    
	/*
	 * public void deleteProduct(Long productId) {
	 * productRepository.deleteById(productId); }
	 */
    
    public Mono<Void> deleteProduct(String productId) {
        return productRepository.deleteById(productId);
    }

	/*
	 * public List<ProductDTO> getActiveProducts() { List<ProductEntity> products =
	 * productRepository.findByStatus("ACTIVE"); return
	 * products.stream().map(this::convertToDTO).collect(Collectors.toList()); }
	 */
    
    public Flux<ProductDTO> getActiveProducts() {
        return productRepository.findByStatus("ACTIVE").map(this::convertToDTO);
    }

    private ProductDTO convertToDTO(ProductEntity product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setStatus(product.getStatus());
        return productDTO;
    }

    private ProductEntity convertToEntity(ProductDTO productDTO) {
        ProductEntity product = new ProductEntity();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setQuantity(productDTO.getQuantity());
        product.setStatus(productDTO.getStatus());
        return product;
    }
	
}
