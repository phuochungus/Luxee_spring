package me.phuochung.luxee.media;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import me.phuochung.luxee.product.Product;
import me.phuochung.luxee.variant.Variant;

@Data
@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private MediaType mediaType = MediaType.IMAGE;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_id", updatable = false, insertable = false, nullable = false)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private Variant variant;

    @Column(name = "variant_id", updatable = false, insertable = false, nullable = false)
    private Long variantId;

    @Column(nullable = false)
    @NotBlank(message = "\"url\" is required")
    private String url;

    private String publicId;

    enum MediaType {
        IMAGE, VIDEO
    }
}
