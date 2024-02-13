package me.phuochung.luxee.option;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "option_id", nullable = false)
    @OrderColumn
    @Valid
    private List<Value> values = new ArrayList<>();

    @NotBlank(message = "\"name\" is required")
    private String name;

    public Option(List<Value> values, String name) {
        this.values = values;
        this.name = name;
    }
}
