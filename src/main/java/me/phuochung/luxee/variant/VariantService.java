package me.phuochung.luxee.variant;

import me.phuochung.luxee.product.Product;
import me.phuochung.luxee.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VariantService {

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private ProductRepository productRepository;

    public Variant createVariant(Product product, Variant variant) {
        product.getVariants().add(variant);
        variant.setProduct(product);

        return variant;
    }
}
