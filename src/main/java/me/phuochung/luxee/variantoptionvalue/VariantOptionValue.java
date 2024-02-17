package me.phuochung.luxee.variantoptionvalue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.option.value.Value;
import me.phuochung.luxee.variant.Variant;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @ManyToOne()
    @JoinColumn(name = "value_id", nullable = false)
    private Value value;

    @Id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    @NotNull
    @Column(name = "value_id", insertable = false, updatable = false,
            nullable = false)
    private Long valueId;

    @NotNull
    @Column(name = "option_id", nullable = false, insertable = false,
            updatable = false)
    private Long optionId;

    @Column(name = "variant_id", nullable = false, insertable = false,
            updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long variantId;

    public VariantOptionValue(Long valueId, Long optionId) {
        this.valueId = valueId;
        this.optionId = optionId;
    }
}
