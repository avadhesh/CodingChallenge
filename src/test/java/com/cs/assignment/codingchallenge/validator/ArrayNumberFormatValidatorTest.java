package com.cs.assignment.codingchallenge.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class ArrayNumberFormatValidatorTest {

    @Mock
    NumberFormatValidator numberFormatValidator;

    @InjectMocks
    ArrayNumberFormatValidator arrayNumberFormatValidator;

    @BeforeEach
    void init()
    {
        ValidationResult validResult = new ValidationResult();
        Mockito.when(numberFormatValidator.validate("1")).thenReturn(validResult);
        List<String> messagesList = new ArrayList<>();
        messagesList.add("a not a valid number");
        ValidationResult invalidResult = new ValidationResult(messagesList, false);
        Mockito.when(numberFormatValidator.validate("a")).thenReturn(invalidResult);

    }

    @Test
    void validate() {

        ValidationResult result = arrayNumberFormatValidator.validate(new String[]{"1","a" });

        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 1);
        Assertions.assertEquals(result.getMessages().get(0), "a not a valid number");

    }
}