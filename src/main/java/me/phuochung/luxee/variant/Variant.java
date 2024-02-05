package me.phuochung.luxee.variant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.product.Product;
import me.phuochung.luxee.selectedoptionvalue.SelectedOptionValue;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference("product-variant")
    @ToString.Exclude
    private Product product;

    @Column(name = "product_id", updatable = false, insertable = false, nullable = false)
    private Long productId;

    @OneToMany(mappedBy = "variant")
    @JsonManagedReference("variant-media")
    private List<Media> media = new ArrayList<>();

    @OneToMany(mappedBy = "variant", cascade = CascadeType.MERGE)
    @JsonManagedReference("selected-option-value-variant")
    private List<SelectedOptionValue> selectedOptionsValue = new ArrayList<>();

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
