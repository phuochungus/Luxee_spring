package me.phuochung.luxee.product;

import me.phuochung.luxee.option.Option;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PricingValidator {

    public boolean isValid(@NotNull List<Option> options, Double price) {
        return options.isEmpty() ^ price == null;
    }
}
