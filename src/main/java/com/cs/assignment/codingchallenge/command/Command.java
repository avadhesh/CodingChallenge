package com.cs.assignment.codingchallenge.command;

import com.cs.assignment.codingchallenge.exception.ExitException;
import com.cs.assignment.codingchallenge.exception.InputValidationException;

import java.util.Optional;


public abstract class Command<T> {

    public abstract T validate(Optional<String> input) throws InputValidationException;

    public abstract String invoke(T arg) throws ExitException;

    public String execute(Optional<String> input) throws InputValidationException, ExitException
    {
        T validated = validate(input);
        return invoke(validated);
    }
}
