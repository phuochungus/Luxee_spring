package me.phuochung.luxee.variant;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "variant")
    @JsonManagedReference("variant-media")
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
