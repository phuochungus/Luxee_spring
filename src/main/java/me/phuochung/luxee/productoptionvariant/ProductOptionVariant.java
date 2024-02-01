package me.phuochung.luxee.productoptionvariant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import me.phuochung.luxee.product.Product;
import me.phuochung.luxee.variant.Variant;
import me.phuochung.luxee.option.Option;

@Data
@Entity
public class ProductOptionVariant {
    @Id
    @ManyToOne
    @JoinColumn
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn
    private Option option;

    @Id
    @ManyToOne
    @JoinColumn
    private Variant variant;

    private String value;
}
