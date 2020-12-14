package com.cs.assignment.codingchallenge.command;

import com.cs.assignment.codingchallenge.exception.ExitException;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component("quitBean")
public class QuitCommand extends Command<Optional>{

    @Override
    public Optional validate(Optional<String> input) {
        return Optional.empty();
    }

    @Override
    public String invoke(Optional arg) throws ExitException {
        throw new ExitException();
    }

}
