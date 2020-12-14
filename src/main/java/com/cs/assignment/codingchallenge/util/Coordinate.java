package com.cs.assignment.codingchallenge.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Coordinate {

    private final int x;
    private final int y;

    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate c)
    {
        this.x = c.getX();
        this.y = c.getY();
    }

}
