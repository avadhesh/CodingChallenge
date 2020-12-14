package com.cs.assignment.codingchallenge.command;

import com.cs.assignment.codingchallenge.exception.InputValidationException;
import com.cs.assignment.codingchallenge.receiver.Canvas;
import com.cs.assignment.codingchallenge.util.Coordinate;
import com.cs.assignment.codingchallenge.validator.ArrayNumberFormatValidator;
import com.cs.assignment.codingchallenge.validator.MultipleCoordinatesValidator;
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
class CreateRectangleCommandTest {

    @Mock
    private Canvas canvas;

    @Mock
    private ArrayNumberFormatValidator numberFormatValidator;

    @Mock
    private MultipleCoordinatesValidator coordinatesValidator;

    @Mock
    private NonEmptyInputValidator nonEmptyInputValidator;

    @InjectMocks
    private CreateRectangleCommand command;



    @SneakyThrows
    @Test
    void executeValidScenario() {

        Coordinate[] coordinates1;
        Coordinate[] coordinates2;

        Coordinate c1 = new Coordinate(1,1);
        Coordinate c2 = new Coordinate(3,3);
        Coordinate c3 = new Coordinate(1,1);
        Coordinate c4 = new Coordinate(5, 5);
        coordinates1 = new Coordinate[]{c1, c2};
        coordinates2 = new Coordinate[]{c3, c4};

        ValidationResult validationResult = new ValidationResult();
        String result =     "-------\n" +
                "|xxx  |\n" +
                "|x x  |\n" +
                "|xxx  |\n" +
                "|     |\n" +
                "|     |\n" +
                "-------\n";

        String result2 =    "-------\n" +
                "|xxxxx|\n" +
                "|x x x|\n" +
                "|xxx x|\n" +
                "|x   x|\n" +
                "|xxxxx|\n" +
                "-------\n";

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("1 1 3 3"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"1", "1", "3", "3"})).thenReturn(validationResult);
        Mockito.doReturn(validationResult).when(coordinatesValidator).validate(canvas, coordinates1);
        Mockito.when(canvas.drawRectangle(c1, c2)).thenReturn(result);

        Assertions.assertEquals(command.execute(Optional.of("1 1 3 3")), result);

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("1 1 5 5"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"1", "1", "5", "5"})).thenReturn(validationResult);
        Mockito.doReturn(validationResult).when(coordinatesValidator).validate(canvas, coordinates2);
        Mockito.when(canvas.drawRectangle(c3, c4)).thenReturn(result2);

        Assertions.assertEquals(command.execute(Optional.of("1 1 5 5")), result2);


    }

    @Test
    void executeInvalidNumOfArgsScenario()
    {
        ValidationResult validationResult = new ValidationResult();

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("5 5 5"))).thenReturn(validationResult);
        Mockito.when(nonEmptyInputValidator.validate(Optional.of("1 2 2 2 3"))).thenReturn(validationResult);
        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("5 5 5")));
        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("1 2 2 2 3")));
    }

    @Test
    void executeNumberFormatValidationExceptionScenario()
    {
        ValidationResult validationResult1 = new ValidationResult("a not a valid number");
        ValidationResult validationResult2 = new ValidationResult("b not a valid number");

        ValidationResult validationResult = new ValidationResult();

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("a 5 1 2"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"a", "5", "1", "2"})).thenReturn(validationResult1);
        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("a 5 1 2")));

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("5 b 1 2"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"5", "b", "1", "2"})).thenReturn(validationResult2);
        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("5 b 1 2")));

    }

    @Test
    void executeInvalidCoordiatesScenario()
    {
        Coordinate[] coordinates1;

        ValidationResult validationResult = new ValidationResult();
        ValidationResult invalidResult = new ValidationResult();
        invalidResult.addMessage("Coordinates (6 , 2) invalid for canvas ");
        invalidResult.addMessage("Coordinates (1 , 8) invalid for canvas ");


        Coordinate c1 = new Coordinate(6,2);
        Coordinate c2 = new Coordinate(1,8);

        coordinates1 = new Coordinate[]{c1, c2};

        Mockito.when(nonEmptyInputValidator.validate(Optional.of("6 2 1 8"))).thenReturn(validationResult);
        Mockito.when(numberFormatValidator.validate(new String[]{"6", "2", "1", "8"})).thenReturn(validationResult);
        Mockito.doReturn(invalidResult).when(coordinatesValidator).validate(canvas, coordinates1);

        Assertions.assertThrows(InputValidationException.class, () -> command.execute(Optional.of("6 2 1 8")));

    }
}