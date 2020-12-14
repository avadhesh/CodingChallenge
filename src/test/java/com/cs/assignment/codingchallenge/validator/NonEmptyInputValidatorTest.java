package com.cs.assignment.codingchallenge.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NonEmptyInputValidatorTest {

    @Spy
    private NonEmptyInputValidator validator;

    @Test
    void validate() {

        ValidationResult result = validator.validate(Optional.empty());
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 1);
        Assertions.assertEquals(result.getMessages().get(0), "Input details not expected to be empty");

        result = validator.validate(Optional.of("1 2 3 4"));
        Assertions.assertTrue(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 0);

        result = validator.validate(Optional.of("1 2 o"));
        Assertions.assertTrue(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 0);

        result = validator.validate(Optional.of("5 5"));
        Assertions.assertTrue(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 0);
    }
}