package me.phuochung.luxee.product;

import jakarta.transaction.Transactional;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.variant.Variant;
import me.phuochung.luxee.variant.VariantRepository;
import me.phuochung.luxee.variantoption.VariantOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PricingValidator pricingValidator;

    @Autowired
    private VariantRepository variantRepository;

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
                            " empty and the other must not be empty.");
        }
        List<Option> options =
                product.getOptions()
                        .stream()
                        .peek((option) -> option.setProduct(product))
                        .toList();
        product.setOptions(options);

        List<Media> media =
                product.getMedia()
                        .stream()
                        .peek((m) -> m.setProduct(product))
                        .toList();
        product.setMedia(media);

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

    public void updateVariants(Long productId, List<Variant> variants) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found"));

        product.setVariants(variants);
        variants.forEach((variant) -> variant.setProduct(product));

        List<Option> productOptions = product.getOptions();
        productOptions.forEach((option) -> {
            System.out.println("product's option[0] hash: " + option.hashCode());
        });

        for (Variant variant : variants) {
            for (int j = 0; j < productOptions.size(); j++) {
                VariantOption variantOption = variant.getVariantOptions().get(j);
                variantOption.setOption(productOptions.get(j));
                productOptions.get(j).getVariantOptions().add(variantOption);
                variantOption.setVariant(variant);
            }
        }
        variantRepository.saveAll(variants);
    }
}