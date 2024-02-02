package me.phuochung.luxee.product;

import lombok.RequiredArgsConstructor;
import me.phuochung.luxee.media.Media;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final PricingValidator pricingValidator;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) throws ResponseStatusException {
        if (!pricingValidator.isValid(product.getOptions(), product.getPrice())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Invalid price and options, either price is null or options must be" +
                            " empty");
        }
        return productRepository.save(product);
    }

    public Product updateMedia(Long id, List<Media> media) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found"));
        product.setMedia(media);
        return productRepository.save(product);
    }
}