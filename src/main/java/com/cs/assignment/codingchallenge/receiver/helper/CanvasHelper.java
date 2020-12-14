package com.cs.assignment.codingchallenge.receiver.helper;

import com.cs.assignment.codingchallenge.receiver.Canvas;
import com.cs.assignment.codingchallenge.util.Coordinate;

import static com.cs.assignment.codingchallenge.common.Constants.HORIZONTAL_BORDER_CHAR;
import static com.cs.assignment.codingchallenge.common.Constants.OBSTACLE_CHAR;
import static com.cs.assignment.codingchallenge.common.Constants.VERTICAL_BORDER_CHAR;

public class CanvasHelper {


    public static boolean isCoordinateSafe(Canvas canvas, Coordinate xy)
    {
        // Offsetting the boundary for valid coordinates with border characters
        return xy.getY() > 0 && xy.getY() < (canvas.getHeight() + 1) && xy.getX() > 0 && xy.getX() < (canvas.getWidth() + 1);
    }

    public static String getCanvasAsString(Canvas canvas, int height, int width)
    {
        StringBuilder sb = new StringBuilder();
        int rowWidth = width + 2;
        int colHeight = height + 2;

        for(int i = 0; i < colHeight; i++)
        {
            for(int j = 0; j < rowWidth; j++)
            {
                sb.append(canvas.getMatrix()[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
