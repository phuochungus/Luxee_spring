package me.phuochung.greenmart.variant;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import me.phuochung.greenmart.media.Media;

@Entity
@Data
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "variant")
    private List<Media> media = new ArrayList<>();

    private String SKU;

    private String barcode;

    private double price;

    private Double compareAtPrice;

    private double costPerItem;

    private long unavailable;

    private long available;

    private long committed;

}
