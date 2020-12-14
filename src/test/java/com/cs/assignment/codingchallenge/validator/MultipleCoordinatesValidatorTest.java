package com.cs.assignment.codingchallenge.validator;

import com.cs.assignment.codingchallenge.receiver.Canvas;
import com.cs.assignment.codingchallenge.util.ColoredCoordinate;
import com.cs.assignment.codingchallenge.util.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MultipleCoordinatesValidatorTest {

    @Mock
    SingleCoordinateValidator singleCoordinateValidator;

    @InjectMocks
    MultipleCoordinatesValidator validator;

    @Test
    void validateValidCase() {
        ValidationResult validResult = new ValidationResult();
        Coordinate coordinate1 = new Coordinate(1,1);
        Coordinate coordinate2 = new Coordinate(5,5);
        Canvas c = new Canvas(5, 5);

        Mockito.when(singleCoordinateValidator.validate(c, coordinate1)).thenReturn(validResult);
        Mockito.when(singleCoordinateValidator.validate(c, coordinate2)).thenReturn(validResult);

        ValidationResult result = validator.validate(c, new Coordinate[]{coordinate1, coordinate2});

        Assertions.assertTrue(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 0);
    }

    @Test
    void validateInValidCase() {
        ValidationResult invalidResult1 = new ValidationResult("Coordinates (0 , 0) invalid for canvas ");
        ValidationResult invalidResult2 = new ValidationResult("Coordinates (6 , 6) invalid for canvas ");
        Coordinate coordinate1 = new Coordinate(0,0);
        Coordinate coordinate2 = new Coordinate(6,6);
        Canvas c = new Canvas(5, 5);

        Mockito.when(singleCoordinateValidator.validate(c, coordinate1)).thenReturn(invalidResult1);
        Mockito.when(singleCoordinateValidator.validate(c, coordinate2)).thenReturn(invalidResult2);

        ValidationResult result = validator.validate(c, new Coordinate[]{coordinate1, coordinate2});

        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 2);
        Assertions.assertEquals(result.getMessages().get(0), "Coordinates (0 , 0) invalid for canvas ");
        Assertions.assertEquals(result.getMessages().get(1), "Coordinates (6 , 6) invalid for canvas ");
    }
}