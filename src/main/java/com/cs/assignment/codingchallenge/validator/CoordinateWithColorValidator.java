package com.cs.assignment.codingchallenge.validator;

import com.cs.assignment.codingchallenge.receiver.Canvas;
import com.cs.assignment.codingchallenge.util.ColoredCoordinate;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Setter
public class CoordinateWithColorValidator implements Validator<ColoredCoordinate>{

    @Autowired
    private ColorValidator colorValidator;

    @Autowired
    private SingleCoordinateValidator coordinateValidator;

    @Override
    public ValidationResult validate(Canvas canvas, ColoredCoordinate args) {

        ValidationResult result = new ValidationResult();
        result.addAllMessages(colorValidator.validate(args.getColor()).getMessages())
                .addAllMessages(coordinateValidator.validate(canvas, args).getMessages());

        return result;
    }
}
