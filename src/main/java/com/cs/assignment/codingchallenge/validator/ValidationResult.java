package com.cs.assignment.codingchallenge.validator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationResult {

    private List<String> messages;

    private boolean isValid = true;

    public ValidationResult()
    {
        this.messages = new ArrayList<>();
        this.isValid = true;
    }

    public ValidationResult(String message)
    {
        this.messages = new ArrayList<>();
        this.messages.add(message);
        this.isValid = false;
    }

    public ValidationResult(List<String> messages)
    {
        this.messages = messages;
        if(!CollectionUtils.isEmpty(messages))
            this.isValid = false;
        else
            this.isValid = true;

    }

    public ValidationResult addMessage(String message)
    {
        this.messages.add(message);
        if(message != null && !message.isEmpty())
            this.isValid = false;
        return this;
    }

    public ValidationResult addAllMessages(List<String> messages)
    {
        this.messages.addAll(messages);
        if(!CollectionUtils.isEmpty(messages))
            this.isValid = false;
        return this;
    }




}
