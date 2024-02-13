package me.phuochung.luxee.variantoptionvalue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.option.value.Value;

@Data
@AllArgsConstructor
@Entity
@Table(
        indexes = {
                @Index(name = "variant_option_index",
                        columnList = "option_id, variant_id",
                        unique = true)
        }
)
public class VariantOptionValue {

    @Id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "option_id", nullable = false)
    private Option option;

    @Id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "value_id", nullable = false)
    private Value value;

    @NotNull
    @Column(name = "value_id")
    private Long valueId;

    @NotNull
    @Column(name = "option_id")
    private Long optionId;

    @Id
    @Column(name = "variant_id", nullable = false)
    @JsonIgnore
    private Long variantId;

    public VariantOptionValue(Long valueId, Long optionId, Long variantId) {
        this.valueId = valueId;
        this.optionId = optionId;
        this.variantId = variantId;
    }
}
