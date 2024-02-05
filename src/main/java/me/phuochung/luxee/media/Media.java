package me.phuochung.luxee.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.phuochung.luxee.product.Product;
import me.phuochung.luxee.variant.Variant;

@Data
@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Variant variant;

    @Column(nullable = false)
    @NotBlank(message = "\"url\" is required")
    private String url;

    private String publicId;
    private MediaType mediaType = MediaType.IMAGE;

    enum MediaType {
        IMAGE, VIDEO
    }
}
