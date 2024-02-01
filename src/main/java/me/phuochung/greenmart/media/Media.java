package me.phuochung.greenmart.media;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import me.phuochung.greenmart.product.Product;
import me.phuochung.greenmart.variant.Variant;

enum MediaType {
    IMAGE, VIDEO
}

@Entity
@Data
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated
    private MediaType mediaType;

    @ManyToOne
    @JoinColumn()
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn()
    private Variant variant;

    private String url;
}
