package me.phuochung.luxee.option.value;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        indexes = {
                @Index(name = "value_unique_index",
                        columnList = "value, option_id",
                        unique = true)
        }
)
public class Value {
    @Id
    @GeneratedValue
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "\"value\" is required")
    private String value;

    public Value(String value) {
        this.value = value;
    }
}
