package me.phuochung.greenmart.product;

import jakarta.persistence.*;
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
    private long id;

    private boolean isDraft = false;

    @OneToMany(mappedBy = "product")
    private List<Media> media = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Option> options = new ArrayList<>();

    @NotBlank(message = "Title is mandatory")
    @NotEmpty(message = "Title is mandatory")
    @NotNull(message = "Title is mandatory")
    private String title;

    private String description;

    private String SKU;

    private String barcode;

    private double price;

    private Double compareAtPrice;

    private double costPerItem = 0;

    private long unavailable = 0;

    private long available = 0;

    private long committed = 0;
}
