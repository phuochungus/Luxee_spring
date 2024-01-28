package me.phuochung.greenmart.product.DTO;

import jakarta.validation.constraints.NotNull;

public class CreateProductDTO {
    @NotNull(message = "Title is mandatory")
    public String title;

    @Override
    public String toString() {
        return "CreateProductDTO [title=" + title + "]";
    }
}
