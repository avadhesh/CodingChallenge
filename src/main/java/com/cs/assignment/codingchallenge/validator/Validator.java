package com.cs.assignment.codingchallenge.validator;

import com.cs.assignment.codingchallenge.receiver.Canvas;

public interface Validator<K> {

    ValidationResult validate(Canvas canvas, K args);
}
