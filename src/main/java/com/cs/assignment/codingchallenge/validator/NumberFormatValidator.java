package com.cs.assignment.codingchallenge.validator;

import org.springframework.stereotype.Component;

@Component
public class NumberFormatValidator implements GenericValidator<String>{

    @Override
    public ValidationResult validate(String arg) {
        ValidationResult result = new ValidationResult();

        if(arg == null || arg.isEmpty())
            result.addMessage("Empty or null parameter as number");
        else if(!org.apache.commons.validator.GenericValidator.isInt(arg))
            result.addMessage(arg + " not a valid number");

        return result;
    }
}
