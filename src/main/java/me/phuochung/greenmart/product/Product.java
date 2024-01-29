package me.phuochung.greenmart.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.phuochung.greenmart.media.Media;

import java.util.ArrayList;
import java.util.List;
import me.phuochung.greenmart.option.Option;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean isDraft = false;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Media> media = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Option> options = new ArrayList<>();

    @NotBlank(message = "title is mandatory")
    @Column(nullable = false)
    private String title;

    private String description;

    private String SKU;

    private String barcode;

    @Column(nullable = false)
    @NotNull(message = "price is mandatory")
    @Min(0)
    private Double price;

    private Double compareAtPrice;

    private Double costPerItem;

    private Long unavailable = 0L;

    private Long available = 0L;

    private Long committed = 0L;
}
