package me.phuochung.greenmart.media;

import jakarta.persistence.*;
import me.phuochung.greenmart.product.Product;

enum MediaType {
    IMAGE, VIDEO
}

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String url;

    private MediaType mediaType;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
