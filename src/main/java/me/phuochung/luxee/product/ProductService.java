package me.phuochung.luxee.product;

import jakarta.transaction.Transactional;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.media.StorageService;
import me.phuochung.luxee.option.OptionRepository;
import me.phuochung.luxee.option.value.ValueRepository;
import me.phuochung.luxee.product.dto.UpdateProductDTO;
import me.phuochung.luxee.variant.Variant;
import me.phuochung.luxee.variant.VariantRepository;
import me.phuochung.luxee.variant.VariantValidator;
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

    @Autowired
    private VariantValidator variantValidator;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private ValueRepository valueRepository;

    @Autowired
    private StorageService storageService;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product createProduct(Product product) throws
                                                  ResponseStatusException {
        if (!pricingValidator.isValid(product.getOptions(),
                                      product.getPrice())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "Invalid price and options, " +
                                                      "either price is null " +
                                                      "or " + "options must " +
                                                      "be empty and the other" +
                                                      " must not be " +
                                                      "empty.");
        }
        product.getVariants().forEach((v) -> v.setProduct(product));

        return productRepository.save(product);
    }


    @Transactional
    public void updateMedia(Long id, List<Media> media) {
        Product product = productRepository.findById(id)
                                           .orElseThrow(
                                                   () -> new ResponseStatusException(
                                                           HttpStatus.NOT_FOUND,
                                                           "Product not " +
                                                                   "found"));
        if (!product.getMedia().isEmpty()) {
            product.getMedia().forEach(m -> {
                if (m.getPublicId()!=null)
                    storageService.deleteAsset(m.getPublicId());

            });
            product.getMedia().clear();
        }
        product.getMedia().addAll(media);
        productRepository.save(product);
    }


    @Transactional
    public void updateVariants(Long productId, List<Variant> variants) {
        Product product = productRepository.findById(productId)
                                           .orElseThrow(
                                                   () -> new ResponseStatusException(
                                                           HttpStatus.NOT_FOUND,
                                                           "Product not " +
                                                                   "found"));

        for (Variant variant : variants) {
            if (!variantValidator.isValid(variant, product)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                  "Invalid variant option " + "values");
            }
        }

        if (!product.getVariants().isEmpty()) product.getVariants().clear();
        product.getVariants().addAll(variants);
        variants.forEach((v) -> v.setProduct(product));
        variants.forEach(v -> {
            v.getVariantOptionValues().forEach((vov) -> {
                vov.setVariant(v);
                vov.setOption(
                        optionRepository.getReferenceById(vov.getOptionId()));
                vov.setValue(
                        valueRepository.getReferenceById(vov.getValueId()));
            });
        });
        variantRepository.saveAll(variants);
    }

    public void updateProduct(Long id, UpdateProductDTO updateProductDto) {
        Product product = productRepository.findById(id)
                                           .orElseThrow(
                                                   () -> new ResponseStatusException(
                                                           HttpStatus.NOT_FOUND,
                                                           "Product not " +
                                                                   "found"));
        if (!pricingValidator.isValid(updateProductDto.getOptions(),
                                      updateProductDto.getPrice())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "Invalid price and options, " +
                                                      "either price is null " +
                                                      "or " + "options must " +
                                                      "be empty and the other" +
                                                      " must not be " +
                                                      "empty.");
        }
        product.setTitle(updateProductDto.getTitle());
        product.setPrice(updateProductDto.getPrice());
        product.setCompareAtPrice(updateProductDto.getCompareAtPrice());
        product.setCost(updateProductDto.getCost());
        product.setSKU(updateProductDto.getSKU());
        product.setBarcode(updateProductDto.getBarcode());
        product.setDescription(updateProductDto.getDescription());
        product.setUnavailable(updateProductDto.getUnavailable());
        product.setAvailable(updateProductDto.getAvailable());
        product.setCommitted(updateProductDto.getCommitted());
        product.setIsDraft(updateProductDto.getIsDraft());
        productRepository.save(product);

    }
}