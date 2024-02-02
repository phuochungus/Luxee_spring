package me.phuochung.luxee.media;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import me.phuochung.luxee.product.Product;
import me.phuochung.luxee.variant.Variant;

enum MediaType {
    IMAGE, VIDEO
}

@Data
@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated
    private MediaType mediaType = MediaType.IMAGE;

    @ManyToOne
    @JoinColumn()
    @JsonBackReference("product-media")
    private Product product;

    @ManyToOne
    @JoinColumn()
    @JsonBackReference("variant-media")
    private Variant variant;

    @Column(nullable = false)
    @NotBlank(message = "\"url\" is required")
    private String url;
}
