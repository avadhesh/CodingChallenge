package com.cs.assignment.codingchallenge.command;

import com.cs.assignment.codingchallenge.exception.ExitException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuitCommandTest {

    @Spy
    QuitCommand command;

    @Test
    @SneakyThrows
    void execute() {
        Assertions.assertThrows(ExitException.class, () -> command.execute(Optional.empty()));
    }
}