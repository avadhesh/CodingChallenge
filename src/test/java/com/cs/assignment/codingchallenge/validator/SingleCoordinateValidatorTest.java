package com.cs.assignment.codingchallenge.validator;

import com.cs.assignment.codingchallenge.receiver.Canvas;
import com.cs.assignment.codingchallenge.util.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SingleCoordinateValidatorTest {

    @Spy
    SingleCoordinateValidator singleCoordinateValidator;

    @Test
    void validateValidCase() {

        Coordinate coordinate1 = new Coordinate(1,1);
        Canvas c = new Canvas(4, 5);

        ValidationResult result = singleCoordinateValidator.validate(c, coordinate1);

        Assertions.assertTrue(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 0);

        Coordinate coordinate2 = new Coordinate(3,5);

        result = singleCoordinateValidator.validate(c, coordinate1);

        Assertions.assertTrue(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 0);
    }

    @Test
    void validateInvalidCase() {

        Coordinate coordinate1 = new Coordinate(0,0);
        Coordinate coordinate2 = new Coordinate(6,6);
        Coordinate coordinate3 = new Coordinate(2,7);
        Coordinate coordinate4 = new Coordinate(7,2);
        Canvas c = new Canvas(4, 5);


        ValidationResult result = singleCoordinateValidator.validate(c, coordinate1);

        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 1);
        Assertions.assertEquals(result.getMessages().get(0), "Coordinates (0 , 0) invalid for canvas ");

        result = singleCoordinateValidator.validate(c, coordinate2);
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 1);
        Assertions.assertEquals(result.getMessages().get(0), "Coordinates (6 , 6) invalid for canvas ");

        result = singleCoordinateValidator.validate(c, coordinate3);
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 1);
        Assertions.assertEquals(result.getMessages().get(0), "Coordinates (2 , 7) invalid for canvas ");

        result = singleCoordinateValidator.validate(c, coordinate4);
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.getMessages().size(), 1);
        Assertions.assertEquals(result.getMessages().get(0), "Coordinates (7 , 2) invalid for canvas ");
    }
}