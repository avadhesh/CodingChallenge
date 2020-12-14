package com.cs.assignment.codingchallenge.validator;

import com.cs.assignment.codingchallenge.receiver.Canvas;
import com.cs.assignment.codingchallenge.util.ColoredCoordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CoordinateWithColorValidatorTest {

    @Mock
    ColorValidator colorValidator;

    @Mock
    SingleCoordinateValidator singleCoordinateValidator;

    @InjectMocks
    CoordinateWithColorValidator coordinateWithColorValidator;


    @Test
    void validateValidResultCoordinate() {
        ValidationResult validResult = new ValidationResult();
        ColoredCoordinate coordinate = new ColoredCoordinate(1,2, 'o');
        Canvas c = new Canvas(5, 5);

        Mockito.when(colorValidator.validate(coordinate.getColor())).thenReturn(validResult);
        Mockito.when(singleCoordinateValidator.validate(c, coordinate)).thenReturn(validResult);

        ValidationResult result = coordinateWithColorValidator.validate(c, coordinate);

        Assertions.assertTrue(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 0);
    }

    @Test
    void validateInvalidResultCoordinate() {

        ValidationResult invalidColorResult = new ValidationResult("Color x is not permitted");
        ValidationResult invalidRangeResult = new ValidationResult("Coordinates (10 , 2) invalid for canvas ");
        ColoredCoordinate invalidCoordinate = new ColoredCoordinate(10,2, 'x');
        Canvas c = new Canvas(5, 5);

        Mockito.when(colorValidator.validate(invalidCoordinate.getColor())).thenReturn(invalidColorResult);
        Mockito.when(singleCoordinateValidator.validate(c, invalidCoordinate)).thenReturn(invalidRangeResult);

        ValidationResult result = coordinateWithColorValidator.validate(c, invalidCoordinate);

        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 2);
        Assertions.assertEquals(result.getMessages().get(0), "Color x is not permitted");
        Assertions.assertEquals(result.getMessages().get(1), "Coordinates (10 , 2) invalid for canvas ");
    }
}