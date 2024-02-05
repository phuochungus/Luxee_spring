package me.phuochung.luxee.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.variant.Variant;

import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "product-graph",
        attributeNodes = {
                @NamedAttributeNode("media"),
                @NamedAttributeNode("options"),
                @NamedAttributeNode(value = "variants", subgraph = "variant-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "variant-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("media"),
                                @NamedAttributeNode(value = "selectedOptionsValue",
                                        subgraph = "selected-option-value-subgraph")
                        }

                ),
                @NamedSubgraph(
                        name = "selected-option-value-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("option"),
                                @NamedAttributeNode("valueIndex")
                        }
                )
        }
)
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private List<Media> media = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<Option> options = new ArrayList<>();

    @OneToMany(mappedBy = "product",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Variant> variants = new ArrayList<>();

    @NotBlank(message = "\"title\" is required")
    @Column(nullable = false)
    private String title;

    private Double price;
    private Double compareAtPrice;
    private Double cost;

    @Column(nullable = false)
    private Boolean isDraft = false;

    private String SKU;
    private String barcode;
    private Long unavailable;
    private Long available;
    private Long committed;
    private String description;
}
