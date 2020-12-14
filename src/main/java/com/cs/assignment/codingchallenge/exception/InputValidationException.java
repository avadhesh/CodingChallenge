package com.cs.assignment.codingchallenge.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InputValidationException extends Exception{

    public InputValidationException(String errorMsg, Throwable t)
    {
        super(errorMsg, t);
    }

    public InputValidationException(String errorMsg)
    {
        super(errorMsg);
    }

}
