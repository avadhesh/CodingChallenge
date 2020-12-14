package com.cs.assignment.codingchallenge.driver;

import com.cs.assignment.codingchallenge.exception.ExitException;
import com.cs.assignment.codingchallenge.exception.InputValidationException;
import com.cs.assignment.codingchallenge.invoker.CanvasPainter;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CanvasPaintDriver {

    @Autowired
    private CanvasPainter painter;

    private static Logger LOG = LogManager.getLogger(CanvasPaintDriver.class);

    public void drive()
    {
        while(true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("enter command: ");
                String newLine = sc.nextLine();
                String result = painter.paint(newLine);
                System.out.println(result);
            }catch(InputValidationException e)
            {
                LOG.error(e.getMessage() , e.getCause());
            }catch (ExitException e)
            {
                System.exit(0);
            }
        }
    }
}
