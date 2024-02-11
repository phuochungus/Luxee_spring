package me.phuochung.luxee.variant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.product.Product;
import me.phuochung.luxee.variantoptionvalue.VariantOptionValue;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Product product;

    @Column(name = "product_id", updatable = false, insertable = false)
    private Long productId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Media> media = new ArrayList<>();

    @OneToMany(mappedBy = "variant", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST})
    private List<VariantOptionValue> variantOptions = new ArrayList<>();

    private String description;
    private String SKU;
    private String barcode;
    private Double price;
    private Double compareAtPrice;
    private Double costPerItem;
    private Long unavailable;
    private Long committed;
    private Long available;
}
