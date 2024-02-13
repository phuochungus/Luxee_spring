package me.phuochung.luxee.product;

import jakarta.transaction.Transactional;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.variant.Variant;
import me.phuochung.luxee.variant.VariantRepository;
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
    public Product createProduct(Product product)
            throws ResponseStatusException {
        if (!pricingValidator.isValid(product.getOptions(),
                                      product.getPrice())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid price and options, either price is null or " +
                            "options must be empty and the other must not be " +
                            "empty.");
        }
        product.getVariants().forEach((v) -> v.setProduct(product));

        return productRepository.save(product);
    }


    @Transactional
    public void updateMedia(Long id, List<Media> media) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                  "Product not found"));
        if (!product.getMedia().isEmpty()) product.getMedia().clear();
        product.getMedia().addAll(media);
        productRepository.save(product);
    }

//    private <T> List<List<T>> getCartesianProduct(
//            @NotNull List<List<T>> lists) {
//        List<List<T>> result = List.of(List.of());
//        for (List<T> list : lists) {
//            List<List<T>> newResult = new ArrayList<>(List.of());
//            for (List<T> partialResult : result) {
//                for (T item : list) {
//                    List<T> newPartialResult = new ArrayList<>(partialResult);
//                    newPartialResult.add(item);
//                    newResult.add(newPartialResult);
//                }
//            }
//            result = newResult;
//        }
//        return result;
//    }

    @Transactional
    public void updateVariants(Long productId, List<Variant> variants) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                  "Product not found"));

        if (!product.getVariants().isEmpty()) product.getVariants().clear();

        product.getVariants().addAll(variants);
        variants.forEach((variant) -> variant.setProduct(product));

        List<Option> productOptions = product.getOptions();
        productOptions.forEach((option) -> System.out.println(
                "product's option[0] hash: " + option.hashCode()));

        variantRepository.saveAll(variants);
    }

}