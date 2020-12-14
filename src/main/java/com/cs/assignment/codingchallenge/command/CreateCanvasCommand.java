package com.cs.assignment.codingchallenge.command;

import com.cs.assignment.codingchallenge.exception.InputValidationException;
import com.cs.assignment.codingchallenge.receiver.Canvas;
import com.cs.assignment.codingchallenge.validator.ArrayNumberFormatValidator;
import com.cs.assignment.codingchallenge.validator.GenericValidator;
import com.cs.assignment.codingchallenge.validator.ValidationResult;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component("createCanvasBean")
public class CreateCanvasCommand extends Command<Pair<Integer, Integer>>{

    @Autowired
    private Canvas canvas;

    @Autowired
    private ArrayNumberFormatValidator numberFormatValidator;

    @Autowired
    private GenericValidator<Optional<String>> nonEmptyInputValidator;

    @Value("${canvas.max.height:50}")
    private Integer maxHeight;

    @Value("${canvas.max.width:50}")
    private Integer maxWidth;

    @Override
    public Pair<Integer, Integer> validate(Optional<String> input) throws InputValidationException {

        ValidationResult result = nonEmptyInputValidator.validate(input);

        if(!result.isValid())
            throw new InputValidationException(result.getMessages().stream().collect(Collectors.joining(",")));

        String[] details = input.get().split("\\s+");

        if(details.length != 2)
            throw new InputValidationException("Invalid number of arguements, expected " + 2);

        result = numberFormatValidator.validate(details);

        if(!result.isValid())
            throw new InputValidationException(result.getMessages().stream().collect(Collectors.joining(",")));

        int y = Integer.valueOf(details[0]);
        int x = Integer.valueOf(details[1]);

        if(x < 1 || y < 1 || x > maxWidth || y > maxHeight)
            throw new InputValidationException(String.format("Invalid canvas height/width" +
                    ", expected range (%d, %d) height and (%d, %d) width", 1, maxHeight, 1, maxWidth ));

        return Pair.of(y, x);
    }

    @Override
    public String invoke(Pair<Integer, Integer> arg) {
        return canvas.createCanvas(arg.getLeft(), arg.getRight());

    }

}
