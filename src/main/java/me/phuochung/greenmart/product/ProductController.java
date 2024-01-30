package me.phuochung.greenmart.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.phuochung.greenmart.mapper.ProductDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductDTOMapper productDTOMapper;

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService
                .getProduct(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found"));
    }

    @PostMapping()
    public Long createProduct(@Valid @RequestBody ProductCreationDTO productCreateDTO) {
        Product product = productDTOMapper.toProduct(productCreateDTO);
        return productService.createProduct(product).getId();
    }


}
