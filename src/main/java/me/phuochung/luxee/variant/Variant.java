package me.phuochung.luxee.variant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.product.Product;
import me.phuochung.luxee.variantoption.VariantOption;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    @JsonIgnore
    private Product product;

    @Column(name = "product_id", updatable = false, insertable = false)
    private Long productId;

    @OneToMany(mappedBy = "variant")
    private List<Media> media = new ArrayList<>();

    @OneToMany(mappedBy = "variant")
    private List<VariantOption> selectedOptionsValue = new ArrayList<>();

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
