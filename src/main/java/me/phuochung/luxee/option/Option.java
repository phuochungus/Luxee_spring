package me.phuochung.luxee.option;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import me.phuochung.luxee.product.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne()
    @JoinColumn()
    @JsonBackReference
    private Product product;

    private String name;
    private List<String> values = new ArrayList<>();
}
