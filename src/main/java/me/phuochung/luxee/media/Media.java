package me.phuochung.luxee.media;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
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
    @JsonBackReference("product-media")
    @ToString.Exclude
    private Product product;

    @Column(name = "product_id", updatable = false, insertable = false, nullable = false)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    @JsonBackReference("variant-media")
    @ToString.Exclude
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
