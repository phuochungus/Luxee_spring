package me.phuochung.luxee.variantoptionvalue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.option.value.Value;
import me.phuochung.luxee.variant.Variant;

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
    @JoinColumn(name = "option_id")
    private Option option;

    @Id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "variant_id")
    private Variant variant;

    @Id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "value_id")
    private Value value;

    @Column(name = "value_id", insertable = false, updatable = false)
    private Long valueId;

    @Column(name = "option_id", updatable = false, insertable = false)
    private Long optionId;

    @Column(name = "variant_id", updatable = false, insertable = false)
    private Long variantId;
}
