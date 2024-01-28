package me.phuochung.greenmart.product.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateProductDTO {

    @NotBlank(message = "Title is mandatory")
    @NotEmpty(message = "Title is mandatory")
    @NotNull(message = "Title is mandatory")
    @NotNull
    public String title;

    @Override
    public String toString() {
        return "CreateProductDTO [title=" + title + "]";
    }

}
