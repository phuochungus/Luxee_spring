package me.phuochung.luxee.product.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.phuochung.luxee.option.Option;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDTO {
    @NotBlank(message = "\"title\" is required")
    private String title;

    @Valid
    private List<Option> options = new ArrayList<>();

    private Double price;
    private Double compareAtPrice;
    private Double cost;
    private Boolean isDraft = false;
    private String SKU;
    private String barcode;
    private String description;
    private Long unavailable;
    private Long available;
    private Long committed;
}
