package me.phuochung.luxee.variantoption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.variant.Variant;

@Data
@Entity
public class VariantOption {
    @Id
    @Column(name = "option_id")
    private Long optionId;

    @Id
    @Column(name = "variant_id")
    private Long variantId;

    @JoinColumn(name = "option_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id")
    @JsonIgnore
    private Variant variant;

    private Integer valueIndex;
}
