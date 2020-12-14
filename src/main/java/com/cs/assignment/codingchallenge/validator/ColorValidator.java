package com.cs.assignment.codingchallenge.validator;

import org.springframework.stereotype.Component;

import static com.cs.assignment.codingchallenge.common.Constants.*;

@Component
public class ColorValidator implements GenericValidator<Character>{

    @Override
    public ValidationResult validate(Character arg) {
        ValidationResult result = new ValidationResult();

        if(arg == OBSTACLE_CHAR
                || arg == HORIZONTAL_BORDER_CHAR
                || arg == VERTICAL_BORDER_CHAR)
            result.addMessage("Color " + arg + " is not permitted");

        return result;
    }
}
