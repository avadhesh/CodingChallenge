package com.cs.assignment.codingchallenge.invoker;

import com.cs.assignment.codingchallenge.command.*;
import com.cs.assignment.codingchallenge.exception.ExitException;
import com.cs.assignment.codingchallenge.exception.InputValidationException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CanvasPainterTest {

    @Mock
    private CreateCanvasCommand createCanvasCommand;

    @Mock
    private CreateLineCommand drawLineCommand;

    @Mock
    private CreateRectangleCommand createRectangleCommand;

    @Mock
    private FillColorCommand fillColorCommand;

    @Mock
    private QuitCommand quitCommand;

    @InjectMocks
    private CanvasPainter painter;

    @BeforeEach
    void setUp() {
        painter.init();
    }


    @SneakyThrows
    @Test
    void validPaintScenario() {
         String t = "-------\n" +
                "|     |\n" +
                "|     |\n" +
                "|     |\n" +
                "|     |\n" +
                "|     |\n" +
                "-------\n";
        Mockito.when(createCanvasCommand.execute(Optional.of("5 5"))).thenReturn(t);
        String t1 = "-------\n" +
                "|     |\n" +
                "|xxxx |\n" +
                "|     |\n" +
                "|     |\n" +
                "|     |\n" +
                "-------\n";
        Mockito.when(drawLineCommand.execute(Optional.of("1 2 4 2"))).thenReturn(t1);
        String t2 = "-------\n" +
                "|     |\n" +
                "|xxxx |\n" +
                "|x  x |\n" +
                "|xxxx |\n" +
                "|     |\n" +
                "-------\n";
        Mockito.when(createRectangleCommand.execute(Optional.of("1 2 4 4"))).thenReturn(t2);
        String t3 = "-------\n" +
                "|     |\n" +
                "|xxxx |\n" +
                "|xoox |\n" +
                "|xxxx |\n" +
                "|     |\n" +
                "-------\n";
        Mockito.when(fillColorCommand.execute(Optional.of("3 3 o"))).thenReturn(t3);

        Mockito.when(quitCommand.execute(Optional.empty())).thenThrow(ExitException.class);

        String canvas = painter.paint("C 5 5");
        Assertions.assertEquals(t, canvas);

        canvas = painter.paint("L 1 2 4 2");
        Assertions.assertEquals(t1, canvas);

        canvas = painter.paint("R 1 2 4 4");
        Assertions.assertEquals(t2, canvas);

        canvas = painter.paint("B 3 3 o");
        Assertions.assertEquals(t3, canvas);

        Assertions.assertThrows(ExitException.class, () -> painter.paint("Q"));

    }

    @Test
    void incorrectCommandScenario()
    {
        Assertions.assertThrows(InputValidationException.class, () -> painter.paint(""));
        Assertions.assertThrows(InputValidationException.class, () -> painter.paint("Z 1 2"));
        Assertions.assertThrows(InputValidationException.class, () -> painter.paint("Z"));
        Assertions.assertThrows(InputValidationException.class, () -> painter.paint("1 2"));
    }





}