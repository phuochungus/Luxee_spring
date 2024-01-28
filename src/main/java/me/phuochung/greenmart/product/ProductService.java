package me.phuochung.greenmart.product;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(@NonNull Product product) {    
        return productRepository.save(product);
    }
}