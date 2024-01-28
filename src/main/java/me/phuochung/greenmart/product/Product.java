package me.phuochung.greenmart.product;

import jakarta.persistence.*;
import lombok.Data;
import me.phuochung.greenmart.media.Media;

import java.util.ArrayList;
import java.util.List;
import me.phuochung.greenmart.option.Option;
import me.phuochung.greenmart.productoptionvariant.ProductOptionVariant;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean isDraft;

    @OneToMany(mappedBy = "product")
    private List<Media> media = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Option> options = new ArrayList<>();

    private String title;

    private String description;

    private String SKU;

    private String barcode;

    private double price;

    private Double compareAtPrice;

    private double costPerItem;

    private long unavailable;

    private long available;

    private long committed;

}
