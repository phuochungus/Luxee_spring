package me.phuochung.luxee.option;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.phuochung.luxee.option.value.Value;

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

    @OneToMany(cascade = CascadeType.ALL)
    @OrderColumn
    private List<Value> values = new ArrayList<>();

    @NotBlank(message = "\"name\" is required")
    private String name;
}
