package me.phuochung.luxee.variantoption;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class VariantOptionId implements Serializable {
    @Column(name = "option_id")
    private Long optionId;

    @Column(name = "variant_id")
    private Long variantId;
}
