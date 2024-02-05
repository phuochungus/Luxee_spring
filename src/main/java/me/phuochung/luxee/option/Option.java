package me.phuochung.luxee.option;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import me.phuochung.luxee.product.Product;
import me.phuochung.luxee.variantoption.VariantOption;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    @JsonIgnore
    private Product product;

    @OneToMany(mappedBy = "option")
    @JsonIgnore
    private List<VariantOption> selectedOptionValues = new ArrayList<>();

    private List<String> values = new ArrayList<>();
    private String name;
}
