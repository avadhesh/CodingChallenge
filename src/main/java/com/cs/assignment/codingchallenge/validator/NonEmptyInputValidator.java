package com.cs.assignment.codingchallenge.validator;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NonEmptyInputValidator implements GenericValidator<Optional<String>> {
    @Override
    public ValidationResult validate(Optional<String> arg){
        ValidationResult result = new ValidationResult();
        if(arg.isEmpty())
            result.addMessage("Input details not expected to be empty") ;
        return result;
    }
}
