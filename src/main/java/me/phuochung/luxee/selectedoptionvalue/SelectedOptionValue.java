package me.phuochung.luxee.selectedoptionvalue;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.variant.Variant;

@Data
@Entity
public class SelectedOptionValue {
    @Id
    @JoinColumn(name = "option_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;

    @Column(name = "option_id", updatable = false, insertable = false, nullable = false)
    private Long optionId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id")
    private Variant variant;

    @Column(name = "variant_id", updatable = false, insertable = false, nullable = false)
    private Long variantId;

    private Integer valueIndex;
}
