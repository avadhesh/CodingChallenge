package com.cs.assignment.codingchallenge.invoker;

import com.cs.assignment.codingchallenge.command.Command;
import com.cs.assignment.codingchallenge.exception.ExitException;
import com.cs.assignment.codingchallenge.exception.InputValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CanvasPainter {

    private final Map<String, Command> commandMap = new HashMap<>();

    @Autowired
    @Qualifier("createCanvasBean")
    private Command createCanvasCommand;

    @Autowired
    @Qualifier("createLineBean")
    private Command drawLineCommand;

    @Autowired
    @Qualifier("createRectangleBean")
    private Command drawRectangleCommand;

    @Autowired
    @Qualifier("fillColorBean")
    private Command fillColorCommand;

    @Autowired
    @Qualifier("quitBean")
    private Command quitCommand;


    @PostConstruct
    public void init()
    {
        commandMap.put("C", createCanvasCommand);
        commandMap.put("L", drawLineCommand);
        commandMap.put("R", drawRectangleCommand);
        commandMap.put("B", fillColorCommand);
        commandMap.put("Q", quitCommand);
    }

    public String paint(String newCommand) throws InputValidationException, ExitException
    {
        String[] args = parseCommand(newCommand);
        Optional<String> commandDetails;

        try {
            if (!commandMap.containsKey(args[0].trim()))
                throw new InputValidationException("Incorrect command, expected one of " + commandMap.keySet().toString());
        }catch (IndexOutOfBoundsException e) {
            throw new InputValidationException("Number of arguments expected > 1");
        }

        try{
             commandDetails = Optional.of(args[1].trim());
        }catch (IndexOutOfBoundsException e)
        {
            commandDetails = Optional.empty();
        }

        return commandMap.get(args[0]).execute(commandDetails);
    }

    private String[] parseCommand(String command)
    {
        return command.split("\\s+", 2);
    }


}
