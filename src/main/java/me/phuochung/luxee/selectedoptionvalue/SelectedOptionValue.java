package me.phuochung.luxee.selectedoptionvalue;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToOne
    @JsonBackReference("selected-option-value-option")
    private Option option;

    @Column(name = "option_id", updatable = false, insertable = false, nullable = false)
    private Long optionId;

    @Id
    @ManyToOne
    @JoinColumn(name = "variant_id")
    @JsonBackReference("selected-option-value-variant")
    @ToString.Exclude
    private Variant variant;

    @Column(name = "variant_id", updatable = false, insertable = false, nullable = false)
    private Long variantId;

    private Integer valueIndex;
}
