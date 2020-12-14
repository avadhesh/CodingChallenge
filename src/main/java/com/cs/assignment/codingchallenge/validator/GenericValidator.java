package com.cs.assignment.codingchallenge.validator;

public interface GenericValidator<T> {
    ValidationResult validate(T arg);
}
