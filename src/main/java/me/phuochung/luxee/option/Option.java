package me.phuochung.luxee.option;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import me.phuochung.luxee.product.Product;
import me.phuochung.luxee.selectedoptionvalue.SelectedOptionValue;

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
    @JsonBackReference("product-option")
    @ToString.Exclude
    private Product product;

    @Column(name = "product_id", updatable = false, insertable = false, nullable = false)
    private Long productId;


    @OneToMany(mappedBy = "option", cascade = CascadeType.PERSIST)
    @JsonManagedReference("selected-option-value-option")
    private final List<SelectedOptionValue> selectedOptionValues = new ArrayList<>();

    private final List<String> values = new ArrayList<>();
    private String name;
}
