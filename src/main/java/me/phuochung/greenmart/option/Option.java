package me.phuochung.greenmart.option;

import jakarta.persistence.*;
import lombok.Data;
import me.phuochung.greenmart.product.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Option {
    @Id
    private long id;

    @ManyToOne()
    @JoinColumn()
    @MapsId
    private Product product;

    private String name;
    private List<String> values = new ArrayList<>();
}
