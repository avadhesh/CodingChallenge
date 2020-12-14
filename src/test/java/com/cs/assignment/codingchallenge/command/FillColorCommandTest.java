package com.cs.assignment.codingchallenge.command;

import com.cs.assignment.codingchallenge.exception.InputValidationException;
import com.cs.assignment.codingchallenge.receiver.Canvas;
import com.cs.assignment.codingchallenge.util.ColoredCoordinate;
import com.cs.assignment.codingchallenge.util.Coordinate;
import com.cs.assignment.codingchallenge.validator.ArrayNumberFormatValidator;
import com.cs.assignment.codingchallenge.validator.CoordinateWithColorValidator;
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
class FillColorCommandTest {

    @Mock
    ArrayNumberFormatValidator numberFormatValidator;

    @Mock
    CoordinateWithColorValidator coordinateWithColorValidator;

    @Mock
    NonEmptyInputValidator nonEmptyInputValidator;

    @Mock
    Canvas canvas;

    @InjectMocks
    FillColorCommand command;

    @SneakyThrows
    @Test
    void executeValidScenario() {

        ColoredCoordinate c1 = new ColoredCoordinate(2, 2, 'o');
        ColoredCoordinate c2 = new ColoredCoordinate(4, 4, 'b');


        ValidationResult validationResult = new ValidationResult();
        String result =     "-------\n" +
                "|xxx  |\n" +
                "|xox  |\n" +
                "|xxx  |\n" +
                "|     |\n" +
                "|     |\n" +
                "-------\n";

        String result2 =    "-------\n" +
                "|xxxxx|\n" +
                "|xoxbx|\n" +
                "|xxxbx|\n" +
                "|xbbbx|\n" +
                "|xxxxx|\n" +
                "-------\n";

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("2 2 o"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"2", "2"})).thenReturn(validationResult);
        Mockito.doReturn(validationResult).when(coordinateWithColorValidator).validate(canvas, c1);
        Mockito.when(canvas.bucketFill(c1)).thenReturn(result);

        Assertions.assertEquals(command.execute(Optional.of("2 2 o")), result);

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("4 4 b"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"4", "4"})).thenReturn(validationResult);
        Mockito.doReturn(validationResult).when(coordinateWithColorValidator).validate(canvas, c2);
        Mockito.when(canvas.bucketFill(c2)).thenReturn(result2);

        Assertions.assertEquals(command.execute(Optional.of("4 4 b")), result2);

    }

    @Test
    void executeInvalidNumOfArgsScenario()
    {
        ValidationResult validationResult = new ValidationResult();
        Mockito.when(nonEmptyInputValidator.validate(Optional.of("5 5 o o"))).thenReturn(validationResult);
        Mockito.when(nonEmptyInputValidator.validate(Optional.of("1 2 1 o"))).thenReturn(validationResult);

        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("5 5 o o")));
        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("1 2 1 o")));
    }

    @Test
    void executeInvalidColorLengthScenario()
    {
        ValidationResult validationResult = new ValidationResult();

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("5 5 oo"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"5", "5"})).thenReturn(validationResult);

        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("5 5 oo")));

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("1 2 test"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"1", "2"})).thenReturn(validationResult);

        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("1 2 test")));
    }

    @Test
    void executeNumberFormatValidationExceptionScenario()
    {
        ValidationResult validationResult1 = new ValidationResult("a not a valid number");
        ValidationResult validationResult2 = new ValidationResult("b not a valid number");
        ValidationResult validationResult = new ValidationResult();

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("a 5 1"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"a", "5"})).thenReturn(validationResult1);

        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("a 5 1")));

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("5 b 1"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"5", "b"})).thenReturn(validationResult2);

        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("5 b 1")));

    }

}