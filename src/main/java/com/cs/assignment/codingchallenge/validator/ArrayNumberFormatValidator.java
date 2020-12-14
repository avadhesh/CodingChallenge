package com.cs.assignment.codingchallenge.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArrayNumberFormatValidator implements GenericValidator<String[]>{

    @Autowired
    private NumberFormatValidator numberFormatValidator;

    @Override
    public ValidationResult validate(String[] args) {

        ValidationResult result = new ValidationResult();
        for(String arg : args)
        {
            result.addAllMessages(numberFormatValidator.validate(arg).getMessages());
        }
        return result;
    }
}
