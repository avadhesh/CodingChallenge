package com.cs.assignment.codingchallenge.command;

import com.cs.assignment.codingchallenge.exception.InputValidationException;
import com.cs.assignment.codingchallenge.receiver.Canvas;
import com.cs.assignment.codingchallenge.util.Coordinate;
import com.cs.assignment.codingchallenge.validator.ArrayNumberFormatValidator;
import com.cs.assignment.codingchallenge.validator.GenericValidator;
import com.cs.assignment.codingchallenge.validator.MultipleCoordinatesValidator;
import com.cs.assignment.codingchallenge.validator.ValidationResult;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component("createLineBean")
public class CreateLineCommand extends Command<Pair<Coordinate, Coordinate>>{

    @Autowired
    private Canvas canvas;

    @Autowired
    private ArrayNumberFormatValidator numberFormatValidator;

    @Autowired
    private MultipleCoordinatesValidator coordinatesValidator;

    @Autowired
    private GenericValidator<Optional<String>> nonEmptyInputValidator;

    @Override
    public Pair<Coordinate, Coordinate> validate(Optional<String> input) throws InputValidationException {
        ValidationResult result = nonEmptyInputValidator.validate(input);

        if(!result.isValid())
            throw new InputValidationException(result.getMessages().stream().collect(Collectors.joining(",")));

        String[] details = input.get().split("\\s+");

        if(details.length != 4)
            throw new InputValidationException("Invalid number of arguments, expected " + 4);

        result = numberFormatValidator.validate(details);

        if(!result.isValid())
            throw new InputValidationException(result.getMessages().stream().collect(Collectors.joining(",")));

        Coordinate from = new Coordinate(Integer.valueOf(details[0]), Integer.valueOf(details[1]));
        Coordinate to = new Coordinate(Integer.valueOf(details[2]), Integer.valueOf(details[3]));

        result = coordinatesValidator.validate(canvas, new Coordinate[]{from, to});

        if(!result.isValid())
            throw new InputValidationException(result.getMessages().stream().collect(Collectors.joining(",")));

        return Pair.of(from, to);
    }

    @Override
    public String invoke(Pair<Coordinate, Coordinate> arg){
        return canvas.drawLine(arg.getLeft(), arg.getRight());
    }

}
