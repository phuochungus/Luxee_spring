package me.phuochung.luxee.product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.variant.Variant;
import me.phuochung.luxee.variant.VariantRepository;
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
    private final VariantRepository variantRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product createProduct(Product product) throws ResponseStatusException {
        if (!pricingValidator.isValid(product.getOptions(), product.getPrice())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Invalid price and options, either price is null or options must be" +
                            " empty");
        }
        List<Option> options =
                product.getOptions()
                        .stream()
                        .peek((option) -> option.setProduct(product))
                        .toList();
        product.setOptions(options);
        return productRepository.save(product);
    }

    @Transactional
    public void updateMedia(Long id, List<Media> media) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found"));
        product.setMedia(media);
        media.forEach((m) -> m.setProduct(product));
        productRepository.save(product);
    }

    @Transactional
    public void updateVariants(Long productId, List<Variant> variants) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found"));

        product.setVariants(variants);
        variants.forEach((variant) -> variant.setProduct(product));

        final List<Option> options = product.getOptions();
        for (Variant variant : variants) {
            for (int i = 0; i < options.size(); i++) {
                variant.getSelectedOptionsValue().get(i).setOption(options.get(i));
            }
        }
        variantRepository.saveAll(variants);
    }
}