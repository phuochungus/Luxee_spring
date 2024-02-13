package me.phuochung.luxee.variant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.product.Product;
import me.phuochung.luxee.variantoptionvalue.VariantOptionValue;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Product product;

    @OneToMany(cascade = CascadeType.ALL)
    @Valid
    private List<Media> media = new ArrayList<>();

    @OneToMany(mappedBy = "variant", cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    @Valid
    private List<VariantOptionValue> variantOptionValues = new ArrayList<>();

    @Column(nullable = false)
    private Double price;

    private String description;
    private String SKU;
    private String barcode;
    private Double compareAtPrice;
    private Double costPerItem;
    private Long unavailable;
    private Long committed;
    private Long available;

    public Variant(List<VariantOptionValue> variantOptionValues, Double price) {
        this.variantOptionValues = variantOptionValues;
        this.price = price;
    }
}
