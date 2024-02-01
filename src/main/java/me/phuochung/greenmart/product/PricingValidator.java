package me.phuochung.greenmart.product;

import me.phuochung.greenmart.option.Option;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PricingValidator {

    public boolean isValid(@NotNull List<Option> options, Double price) {
        return options.isEmpty() ^ price == null;
    }
}
