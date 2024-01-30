package me.phuochung.greenmart.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.phuochung.greenmart.media.Media;
import me.phuochung.greenmart.option.Option;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "product")
    private List<Media> media = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Option> options = new ArrayList<>();

    @NotBlank(message = "title is mandatory")
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @NotNull(message = "price is mandatory")
    @Min(0)
    private Double price;

    private String description;
    private String SKU;
    private String barcode;
    private Boolean isDraft = false;
    private Double compareAtPrice;
    private Double cost;
    private Long unavailable = 0L;
    private Long available = 0L;
    private Long committed = 0L;
}
