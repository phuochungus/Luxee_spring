package me.phuochung.greenmart.product;

import jakarta.persistence.*;
import lombok.Data;
import me.phuochung.greenmart.media.Media;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "product")
    private List<Media> media = new ArrayList<>();

}

