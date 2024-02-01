package me.phuochung.luxee.variant;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import me.phuochung.luxee.media.Media;

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

    private Double price;

    private Double compareAtPrice;

    private Double costPerItem;

    private Long unavailable;

    private Long available;

    private Long committed;

}
