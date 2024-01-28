package me.phuochung.greenmart.productoptionvariant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import me.phuochung.greenmart.product.Product;
import me.phuochung.greenmart.variant.Variant;
import me.phuochung.greenmart.option.Option;

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
