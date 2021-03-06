package com.cs.assignment.codingchallenge.validator;

import com.cs.assignment.codingchallenge.receiver.Canvas;
import com.cs.assignment.codingchallenge.receiver.helper.CanvasHelper;
import com.cs.assignment.codingchallenge.util.Coordinate;
import org.springframework.stereotype.Component;

@Component
public class SingleCoordinateValidator implements Validator<Coordinate>{


    @Override
    public ValidationResult validate(Canvas canvas, Coordinate c) {

        if(canvas.getWidth() <= 0 || canvas.getHeight() <= 0)
            return new ValidationResult("Canvas height and width should be greater than 0, please create a new valid canvas");
        if(!CanvasHelper.isCoordinateSafe(canvas, c))
            return new ValidationResult("Coordinates ("+ c.getX() +" , "+ c.getY() +") invalid for canvas ");

        return new ValidationResult();
    }
}
