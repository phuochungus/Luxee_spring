package me.phuochung.luxee.variantoption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.variant.Variant;

@Data
@Entity
public class VariantOption {
    @EmbeddedId
    private VariantOptionId id = new VariantOptionId();

    @MapsId("optionId")
    @ManyToOne()
    @JoinColumn(name = "option_id")
    private Option option;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @MapsId("variantId")
    @ManyToOne()
    @JoinColumn(name = "variant_id")
    private Variant variant;

    @Column(nullable = false)
    private Integer valueIndex;

    @Transient
    public String getValue() {
        return option.getValues().get(valueIndex);
    }
}
