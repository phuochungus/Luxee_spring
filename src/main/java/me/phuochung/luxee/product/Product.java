package me.phuochung.luxee.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.variant.Variant;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> media = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options = new ArrayList<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true)
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
    private String description;
    private Long unavailable;
    private Long available;
    private Long committed;
}
