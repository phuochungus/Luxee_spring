package me.phuochung.luxee.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.phuochung.luxee.option.Option;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

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
    public Long createProduct(@Valid @RequestBody Product product) {
        List<Option> options =
                product.getOptions()
                        .stream()
                        .peek((option) -> option.setProduct(product))
                        .toList();
        product.setOptions(options);
        return productService.createProduct(product).getId();
    }

}
