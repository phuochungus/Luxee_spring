package me.phuochung.luxee.collection;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.product.Product;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Collection {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "\"name\" is required")
    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToMany
    private List<Product> products = new ArrayList<>();

    @OneToMany
    private List<Media> media = new ArrayList<>();
}
