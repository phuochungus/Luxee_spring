package me.phuochung.luxee.option;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import me.phuochung.luxee.product.Product;
import me.phuochung.luxee.variantoption.VariantOption;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Option {
    @Id
    @GeneratedValue
    private Long id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "option", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<VariantOption> variantOptions = new ArrayList<>();

    private List<String> values = new ArrayList<>();

    @NotBlank(message = "\"name\" is required")
    private String name;
}
