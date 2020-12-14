package com.cs.assignment.codingchallenge.command;

import com.cs.assignment.codingchallenge.exception.InputValidationException;
import com.cs.assignment.codingchallenge.receiver.Canvas;
import com.cs.assignment.codingchallenge.validator.ArrayNumberFormatValidator;
import com.cs.assignment.codingchallenge.validator.NonEmptyInputValidator;
import com.cs.assignment.codingchallenge.validator.ValidationResult;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateCanvasCommandTest {

    @Mock
    private Canvas canvas;

    @Mock
    private ArrayNumberFormatValidator numberFormatValidator;

    @Mock
    private NonEmptyInputValidator nonEmptyInputValidator;

    @InjectMocks
    private CreateCanvasCommand command;


    @SneakyThrows
    @Test
    void executeValidScenario() {
        ValidationResult validationResult = new ValidationResult();

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("5 5"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"5", "5"})).thenReturn(validationResult);
        String result = "-------\n|     |\n|     |\n|     |\n|     |\n|     |\n-------\n";
        Mockito.when(canvas.createCanvas(5,5)).thenReturn(result);

        Assertions.assertEquals(command.execute(Optional.of("5 5")), result);
    }


    @Test
    void executeNegativeHeightWidthScenario()
    {
        ValidationResult validationResult = new ValidationResult();

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("-5 5"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"-5", "5"})).thenReturn(validationResult);
        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("-5 5")));

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("5 -5"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"5", "-5"})).thenReturn(validationResult);
        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("5 -5")));

    }

    @Test
    void executeNumberFormatValidationExceptionScenario()
    {
        ValidationResult validationResult1 = new ValidationResult("a not a valid number");
        ValidationResult validationResult2 = new ValidationResult("b not a valid number");

        ValidationResult validationResult = new ValidationResult();
        Mockito.when(nonEmptyInputValidator.validate(Optional.of("a 5"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"a", "5"})).thenReturn(validationResult1);
        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("a 5")));

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("5 b"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"5", "b"})).thenReturn(validationResult2);
        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("5 b")));

    }


}