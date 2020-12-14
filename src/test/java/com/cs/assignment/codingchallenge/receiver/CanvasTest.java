package com.cs.assignment.codingchallenge.receiver;

import com.cs.assignment.codingchallenge.util.ColoredCoordinate;
import com.cs.assignment.codingchallenge.util.Coordinate;
import com.cs.assignment.codingchallenge.validator.ColorValidator;
import com.cs.assignment.codingchallenge.validator.CoordinateWithColorValidator;
import com.cs.assignment.codingchallenge.validator.SingleCoordinateValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CanvasTest {

    @Test
    public void createCanvasScenario()
    {
        Canvas canvas = new Canvas();
        String canvasAsString = canvas.createCanvas(5, 5);
        String expectedResult =
                "-------\n" +
                "|     |\n" +
                "|     |\n" +
                "|     |\n" +
                "|     |\n" +
                "|     |\n" +
                "-------\n";

        Assertions.assertEquals(canvasAsString, expectedResult);
        System.out.println(canvasAsString);

        canvasAsString = canvas.createCanvas(10, 8);
        expectedResult =
                "----------\n" +
                        "|        |\n" +
                        "|        |\n" +
                        "|        |\n" +
                        "|        |\n" +
                        "|        |\n" +
                        "|        |\n" +
                        "|        |\n" +
                        "|        |\n" +
                        "|        |\n" +
                        "|        |\n" +
                        "----------\n";

        Assertions.assertEquals(canvasAsString, expectedResult);
        System.out.println(canvasAsString);
    }

    @Test
    public void drawLineTest()
    {
        Canvas canvas = new Canvas();
        canvas.createCanvas(5, 20);
        Coordinate from = new Coordinate(1,2);
        Coordinate to = new Coordinate(6,2);
        String canvasAsString = canvas.drawLine(from, to);
        String expectedResult =
                "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n";
        Assertions.assertEquals(canvasAsString, expectedResult);
        System.out.println(canvasAsString);
        from = new Coordinate(6,3);
        to = new Coordinate(6,4);
        canvasAsString = canvas.drawLine(from, to);

        expectedResult =
                "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|     x              |\n" +
                        "|     x              |\n" +
                        "|                    |\n" +
                        "----------------------\n";
        Assertions.assertEquals(canvasAsString, expectedResult);
        System.out.println(canvasAsString);

        from = new Coordinate(6,4);
        to = new Coordinate(1,4);
        canvasAsString = canvas.drawLine(from, to);

        expectedResult =
                "----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|     x              |\n" +
                "|xxxxxx              |\n" +
                "|                    |\n" +
                "----------------------\n";
        System.out.println(canvasAsString);
        Assertions.assertEquals(canvasAsString, expectedResult);


    }

    @Test
    public void drawRectangleTest()
    {
        Canvas canvas = new Canvas();
        canvas.createCanvas(5, 20);
        String canvasAsString = canvas.drawRectangle(new Coordinate(14, 1), new Coordinate(18, 3));

        String expectedResult =
                "----------------------\n" +
                "|             xxxxx  |\n" +
                "|             x   x  |\n" +
                "|             xxxxx  |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";
        System.out.println(canvasAsString);
        Assertions.assertEquals(canvasAsString, expectedResult);

        canvasAsString = canvas.drawRectangle(new Coordinate(1,1), new Coordinate(18, 3));
        expectedResult =
                "----------------------\n" +
                "|xxxxxxxxxxxxxxxxxx  |\n" +
                "|x            x   x  |\n" +
                "|xxxxxxxxxxxxxxxxxx  |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        System.out.println(expectedResult);
        Assertions.assertEquals(canvasAsString, expectedResult);

        canvasAsString = canvas.drawRectangle(new Coordinate(1,1), new Coordinate(20, 5));
        expectedResult =
                "----------------------\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "|x            x   x x|\n" +
                "|xxxxxxxxxxxxxxxxxx x|\n" +
                "|x                  x|\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "----------------------\n";
        System.out.println(canvasAsString);
        Assertions.assertEquals(canvasAsString, expectedResult);
    }

    @Test
    public void bucketFillTest()
    {
        Canvas canvas = new Canvas();
        initCanvas(canvas);

        canvas.createCanvas(5, 20);
        String canvasAsString = canvas.drawRectangle(new Coordinate(14, 1), new Coordinate(18, 3));
        System.out.println(canvasAsString);


        canvasAsString = canvas.bucketFill(new ColoredCoordinate(1,1, 'o'));
        System.out.println(canvasAsString);
        String expectedResult =
                "----------------------\n" +
                "|oooooooooooooxxxxxoo|\n" +
                "|ooooooooooooox   xoo|\n" +
                "|oooooooooooooxxxxxoo|\n" +
                "|oooooooooooooooooooo|\n" +
                "|oooooooooooooooooooo|\n" +
                "----------------------\n";

        Assertions.assertEquals(canvasAsString, expectedResult);

        canvasAsString = canvas.bucketFill(new ColoredCoordinate(16,2, 'o'));
        System.out.println(canvasAsString);

        expectedResult =
                "----------------------\n" +
                "|oooooooooooooxxxxxoo|\n" +
                "|oooooooooooooxoooxoo|\n" +
                "|oooooooooooooxxxxxoo|\n" +
                "|oooooooooooooooooooo|\n" +
                "|oooooooooooooooooooo|\n" +
                "----------------------\n";

        Assertions.assertEquals(canvasAsString, expectedResult);

        canvasAsString = canvas.bucketFill(new ColoredCoordinate(2,2, 'b'));
        System.out.println(canvasAsString);

        expectedResult =
                "----------------------\n" +
                "|bbbbbbbbbbbbbxxxxxbb|\n" +
                "|bbbbbbbbbbbbbxoooxbb|\n" +
                "|bbbbbbbbbbbbbxxxxxbb|\n" +
                "|bbbbbbbbbbbbbbbbbbbb|\n" +
                "|bbbbbbbbbbbbbbbbbbbb|\n" +
                "----------------------\n";

        Assertions.assertEquals(expectedResult, canvasAsString);

    }

    @Test
    public void bucketFillTest2()
    {
        Canvas canvas = new Canvas();
        initCanvas(canvas);

        canvas.createCanvas(5, 20);
        String canvasAsString = canvas.drawRectangle(new Coordinate(14, 1), new Coordinate(18, 3));
        canvasAsString = canvas.drawRectangle(new Coordinate(1,1), new Coordinate(18, 3));
        canvasAsString = canvas.drawRectangle(new Coordinate(1,1), new Coordinate(20, 5));


        System.out.println(canvasAsString);


        canvasAsString = canvas.bucketFill(new ColoredCoordinate(1,1, 'o'));
        System.out.println(canvasAsString);
        String expectedResult =
                "----------------------\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "|x            x   x x|\n" +
                "|xxxxxxxxxxxxxxxxxx x|\n" +
                "|x                  x|\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "----------------------\n";

        Assertions.assertEquals(expectedResult, canvasAsString);

        canvasAsString = canvas.bucketFill(new ColoredCoordinate(16,2, 'o'));
        System.out.println(canvasAsString);

        expectedResult =
                "----------------------\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "|x            xooox x|\n" +
                "|xxxxxxxxxxxxxxxxxx x|\n" +
                "|x                  x|\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "----------------------\n";

        Assertions.assertEquals(expectedResult, canvasAsString);

        canvasAsString = canvas.bucketFill(new ColoredCoordinate(2,2, 'b'));
        System.out.println(canvasAsString);

        expectedResult =
                "----------------------\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "|xbbbbbbbbbbbbxooox x|\n" +
                "|xxxxxxxxxxxxxxxxxx x|\n" +
                "|x                  x|\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "----------------------\n";

        Assertions.assertEquals(expectedResult, canvasAsString);

        canvasAsString = canvas.bucketFill(new ColoredCoordinate(4,4, 'c'));
        expectedResult =
                "----------------------\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "|xbbbbbbbbbbbbxoooxcx|\n" +
                "|xxxxxxxxxxxxxxxxxxcx|\n" +
                "|xccccccccccccccccccx|\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "----------------------\n";

        Assertions.assertEquals(expectedResult, canvasAsString);
    }

    private void initCanvas(Canvas canvas)
    {
        CoordinateWithColorValidator coordinateWithColorValidator = new CoordinateWithColorValidator();
        coordinateWithColorValidator.setColorValidator(new ColorValidator());
        coordinateWithColorValidator.setCoordinateValidator(new SingleCoordinateValidator());
        canvas.setCoordinateWithColorValidator(coordinateWithColorValidator);
    }
}
