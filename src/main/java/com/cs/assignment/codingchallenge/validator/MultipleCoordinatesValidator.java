package com.cs.assignment.codingchallenge.validator;



import com.cs.assignment.codingchallenge.receiver.Canvas;
import com.cs.assignment.codingchallenge.util.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MultipleCoordinatesValidator implements Validator<Coordinate []>{

    @Autowired
    private SingleCoordinateValidator singleCoordinateValidator;

    @Override
    public ValidationResult validate(Canvas canvas, Coordinate[] args) {

        ValidationResult result = new ValidationResult();

        for(Coordinate c : args)
        {
            ValidationResult singleResult = singleCoordinateValidator.validate(canvas, c);
            if(!singleResult.isValid())
                result.addAllMessages(singleResult.getMessages());
        }

        return result;
    }
}
