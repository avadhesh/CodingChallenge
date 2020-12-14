package com.cs.assignment.codingchallenge.receiver;

import com.cs.assignment.codingchallenge.receiver.helper.CanvasHelper;
import com.cs.assignment.codingchallenge.util.ColoredCoordinate;
import com.cs.assignment.codingchallenge.util.Coordinate;
import com.cs.assignment.codingchallenge.validator.CoordinateWithColorValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

import static com.cs.assignment.codingchallenge.common.Constants.*;

@NoArgsConstructor
@Getter
@Service
@Setter
public class Canvas {

    private char[][] matrix;
    private int height;
    private int width;

    @Autowired
    private CoordinateWithColorValidator coordinateWithColorValidator;

    public Canvas(int height, int width)
    {
        this.height = height;
        this.width = width;
        this.matrix = new char[height + 2][width + 2];
    }

    public String createCanvas(int height, int width)
    {
        int rowWidth = width + 2;
        int colHeight = height + 2;

        this.height = height;
        this.width = width;

        matrix = new char[colHeight][rowWidth];

        for(int i = 0; i < colHeight; i++)
        {
            for(int j = 0; j < rowWidth; j++)
            {
                char toAppend = ' ';
                if(i == 0 || i == (colHeight - 1) )
                    matrix[i][j] = HORIZONTAL_BORDER_CHAR;
                else if(j == 0 || j == (rowWidth - 1))
                    matrix[i][j] = VERTICAL_BORDER_CHAR;
                else
                    matrix[i][j] = ' ';

            }
        }
        return CanvasHelper.getCanvasAsString(this, height, width);
    }

    public String drawLine(Coordinate from, Coordinate to)
    {
        if(from.getX() == to.getX())
            for(int i = Math.min(from.getY(), to.getY()); i <= Math.max(from.getY(), to.getY()); i++)
                matrix[i][from.getX()] = OBSTACLE_CHAR;


        else if(from.getY() == to.getY())
            for(int i = Math.min(from.getX(), to.getX()); i <= Math.max(from.getX(), to.getX()); i++)
                matrix[from.getY()][i] = OBSTACLE_CHAR;

        return CanvasHelper.getCanvasAsString(this, height, width);
    }

    public String drawRectangle(Coordinate point1, Coordinate point2)
    {
        for(int i = Math.min(point1.getY(), point2.getY()); i <= Math.max(point1.getY(), point2.getY()); i++)
            for(int j = Math.min(point1.getX(), point2.getX()); j <= Math.max(point1.getX(), point2.getX()); j++)
                if(i == point1.getY() || i == point2.getY() || j == point1.getX() || j == point2.getX())
                    matrix[i][j] = OBSTACLE_CHAR;

        return CanvasHelper.getCanvasAsString(this, height, width);
    }

    public String bucketFill(ColoredCoordinate coloredCoordinate)
    {
        Queue<ColoredCoordinate> queue = new LinkedList<>();
        char colorToPaint = coloredCoordinate.getColor();
        char existingColorAtStartPoint = matrix[coloredCoordinate.getY()][coloredCoordinate.getY()];

        ColoredCoordinate startPoint = new ColoredCoordinate(coloredCoordinate.getX(), coloredCoordinate.getY(), existingColorAtStartPoint);

        boolean isPaintAllowed = coordinateWithColorValidator.validate(this, startPoint).isValid();
        //If starting point is one of the blockers or boundary characters then it should be omitted
        if(isPaintAllowed)
            queue.offer(coloredCoordinate);

        int[] directionX = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] directionY = {-1, 0, 1, -1, 1, -1, 0, -1};

        while(!queue.isEmpty())
        {
            ColoredCoordinate curr = queue.poll();
            int currX = curr.getX();
            int currY = curr.getY();

            if(coordinateWithColorValidator.validate(this, curr).isValid()) {
                matrix[currY][currX] = colorToPaint;

                for (int i = 0; i < 8; i++) {
                    ColoredCoordinate nextDirection = new ColoredCoordinate(
                            currX + directionX[i],
                            currY + directionY[i],
                            matrix[currY + directionY[i]][currX + directionX[i]]);
                    if (coordinateWithColorValidator.validate(this, nextDirection).isValid()
                        && nextDirection.getColor() != colorToPaint)
                        queue.offer(nextDirection);
                }
            }

        }

        return CanvasHelper.getCanvasAsString(this, height, width);
    }


}
