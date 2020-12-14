package com.cs.assignment.codingchallenge.command;

import com.cs.assignment.codingchallenge.exception.InputValidationException;
import com.cs.assignment.codingchallenge.receiver.Canvas;
import com.cs.assignment.codingchallenge.util.ColoredCoordinate;
import com.cs.assignment.codingchallenge.validator.ArrayNumberFormatValidator;
import com.cs.assignment.codingchallenge.validator.CoordinateWithColorValidator;
import com.cs.assignment.codingchallenge.validator.GenericValidator;
import com.cs.assignment.codingchallenge.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("fillColorBean")
public class FillColorCommand extends Command<ColoredCoordinate>{

    @Autowired
    private Canvas canvas;

    @Autowired
    private ArrayNumberFormatValidator numberFormatValidator;

    @Autowired
    private CoordinateWithColorValidator coordinateWithColorValidator;

    @Autowired
    private GenericValidator<Optional<String>> nonEmptyInputValidator;

    @Override
    public ColoredCoordinate validate(Optional<String> input) throws InputValidationException {
        ValidationResult result = nonEmptyInputValidator.validate(input);

        if(!result.isValid())
            throw new InputValidationException(result.getMessages().stream().collect(Collectors.joining(",")));

        String[] details = input.get().split("\\s+");

        if(details.length != 3)
            throw new InputValidationException("Invalid number of arguments, expected " + 3);

        result = numberFormatValidator.validate(Arrays.copyOf(details, 2));

        if(!result.isValid())
            throw new InputValidationException(result.getMessages().stream().collect(Collectors.joining(",")));
        if(details[2].length() > 1)
            throw new InputValidationException("Invalid color argument, expected a character");

        ColoredCoordinate coloredCoordinate = new ColoredCoordinate(Integer.valueOf(details[0]),
                Integer.valueOf(details[1]),
                details[2].charAt(0));

        result = coordinateWithColorValidator.validate(canvas, coloredCoordinate);

        if(!result.isValid())
            throw new InputValidationException(result.getMessages().stream().collect(Collectors.joining(",")));

        return coloredCoordinate;
    }

    @Override
    public String invoke(ColoredCoordinate arg) {
        return canvas.bucketFill(arg);
    }

}
