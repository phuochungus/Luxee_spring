package me.phuochung.luxee.product;

import me.phuochung.luxee.option.Option;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PricingValidatorTest {

    /*
      TestIsValid tests the following cases:
      - options is empty and price is not null -> true
      - options is not empty and price is null -> true
      - options is empty and price is null -> false
      - options is not empty and price is not null -> false
     */
    @Test
    void TestIsValid() {
        PricingValidator pricingValidator = new PricingValidator();
        Option option = new Option();
        assertTrue(pricingValidator.isValid(List.of(), 100.0));
        assertTrue(pricingValidator.isValid(List.of(option), null));
        assertFalse(pricingValidator.isValid(List.of(option), 100.0));
        assertFalse(pricingValidator.isValid(List.of(), null));
    }
}