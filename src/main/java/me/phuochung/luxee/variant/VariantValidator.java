package me.phuochung.luxee.variant;

import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.option.value.Value;
import me.phuochung.luxee.product.Product;
import me.phuochung.luxee.variantoptionvalue.VariantOptionValue;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VariantValidator {

    public boolean isValid(@NotNull Variant variant, @NotNull Product product) {
        List<Option> productOptions = product.getOptions();
        List<VariantOptionValue> variantOptionValues =
                variant.getVariantOptionValues();

        if (productOptions.size()!=variantOptionValues.size()) return false;

        List<Long> optionIds = productOptions.stream()
                                             .map(Option::getId)
                                             .toList();

        List<Long> valueIds = productOptions.stream()
                                            .map(Option::getValues)
                                            .flatMap(List::stream)
                                            .map(Value::getId)
                                            .toList();

        for (VariantOptionValue variantOptionValue : variantOptionValues) {
            if (!optionIds.contains(variantOptionValue.getOptionId()))
                return false;
            if (!valueIds.contains(variantOptionValue.getValueId()))
                return false;
        }
        
        return true;
    }
}
