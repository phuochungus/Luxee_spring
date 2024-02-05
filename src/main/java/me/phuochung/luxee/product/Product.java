package me.phuochung.luxee.product;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.variant.Variant;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    @JsonManagedReference("product-media")
    private List<Media> media = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @JsonManagedReference("product-option")
    private List<Option> options = new ArrayList<>();

    @OneToMany(mappedBy = "product",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JsonManagedReference("product-variant")
    private List<Variant> variants = new ArrayList<>();

    @NotBlank(message = "\"title\" is required")
    @Column(nullable = false)
    private String title;

    private Double price;
    private Double compareAtPrice;
    private Double cost;

    @Column(nullable = false)
    private Boolean isDraft = false;

    private String SKU;
    private String barcode;
    private Long unavailable;
    private Long available;
    private Long committed;
    private String description;
}
