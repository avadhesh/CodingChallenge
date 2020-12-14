package com.cs.assignment.codingchallenge.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NumberFormatValidatorTest {

    @Spy
    NumberFormatValidator validator;

    @Test
    void validate() {

        ValidationResult result = validator.validate("1");
        Assertions.assertTrue(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 0);

        result = validator.validate("a");
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 1);
        Assertions.assertEquals(result.getMessages().get(0), "a not a valid number");

        result = validator.validate("a1");
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 1);
        Assertions.assertEquals(result.getMessages().get(0), "a1 not a valid number");

        result = validator.validate("*");
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 1);
        Assertions.assertEquals(result.getMessages().get(0), "* not a valid number");

        result = validator.validate("");
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 1);
        Assertions.assertEquals(result.getMessages().get(0), "Empty or null parameter as number");
    }
}