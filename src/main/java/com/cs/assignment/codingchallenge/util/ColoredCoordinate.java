package com.cs.assignment.codingchallenge.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ColoredCoordinate extends Coordinate{

    private char color;

    public ColoredCoordinate(int x, int y) {
        super(x, y);
        this.color = ' ';
    }

    public ColoredCoordinate(int x, int y, char c)
    {
        super(x, y);
        this.color = c;
    }

    public ColoredCoordinate(Coordinate coordinate, char color)
    {
        super(coordinate);
        this.color = color;
    }


}
