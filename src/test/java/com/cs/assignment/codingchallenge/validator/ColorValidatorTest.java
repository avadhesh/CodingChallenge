package com.cs.assignment.codingchallenge.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)

class ColorValidatorTest {

    @Spy
    ColorValidator validator;

    @Test
    void validate() {

        ValidationResult result = validator.validate('-');
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 1);
        Assertions.assertEquals(result.getMessages().get(0), "Color - is not permitted");

        result = validator.validate('|');
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 1);
        Assertions.assertEquals(result.getMessages().get(0), "Color | is not permitted");

        result = validator.validate('x');
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 1);
        Assertions.assertEquals(result.getMessages().get(0), "Color x is not permitted");

        result = validator.validate('o');
        Assertions.assertTrue(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 0);
    }
}