package me.phuochung.greenmart.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.phuochung.greenmart.media.Media;
import me.phuochung.greenmart.option.Option;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "product")
    private List<Media> media = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<Option> options = new ArrayList<>();

    @NotBlank(message = "\"title\" is required")
    @Column(nullable = false)
    private String title;

    @NotNull(message = "\"price\" is required")
    @Min(0)
    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Boolean isDraft = false;

    private String SKU;
    private String barcode;
    private Long unavailable;
    private Long available;
    private Long committed;
    private Double compareAtPrice;
    private Double cost = 0.0;
    private String description;
}
