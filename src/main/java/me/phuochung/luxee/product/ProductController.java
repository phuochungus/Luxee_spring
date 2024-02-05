package me.phuochung.luxee.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.variant.Variant;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Validated
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
        return productService.createProduct(product).getId();
    }

    @PutMapping("/{id}/media")
    public void addMedia(@PathVariable Long id, @RequestBody List<@Valid Media> media) {
        productService.updateMedia(id, media);
    }

    @PutMapping("/{id}/variants")
    public void addVariants(@PathVariable Long id,
                            @RequestBody List<@Valid Variant> variants) {
        productService.updateVariants(id, variants);
    }

}
