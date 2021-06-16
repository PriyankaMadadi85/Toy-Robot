package com.au.iress.toyrobot.api.model;

import com.au.iress.toyrobot.api.exception.ToyRobotException;

public class Position {
    int x;
    int y;
    Direction direction;

    public Position() {
        this.x = 0;
        this.y = 0;
        this.direction = Direction.NORTH;
    }

    public Position(Position position) {
        this.x = position.getX();
        this.y = position.getY();
        this.direction = position.getDirection();
    }

    public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Position getNextPosition() throws ToyRobotException {
       //work out next position by incrementing/decrementing co-ordinates based on current direction
        switch (this.direction) {
            case NORTH:
                if(y == 5){
                    throw new ToyRobotException("Robot cannot move outside the square");
                }
                this.y++;
                break;
            case EAST:
                if(x == 5){
                    throw new ToyRobotException("Robot cannot move outside the square");
                }
                this.x++;
                break;
            case SOUTH:
                if(y == 0){
                    throw new ToyRobotException("Robot cannot move outside the square");
                }
                this.y--;
                break;
            case WEST:
                if(x == 0){
                    throw new ToyRobotException("Robot cannot move outside the square");
                }
                this.x--;
                break;
        }
        Position newPosition = new Position(this);
        return newPosition;
    }

    public Position rotateLeft() throws ToyRobotException {
        if (this.direction == null)
            throw new ToyRobotException("Invalid robot direction");

        //rotate robot based on direction
        switch (this.direction) {
            case NORTH:
               this.direction = Direction.WEST;
                break;
            case EAST:
                this.direction = Direction.NORTH;
                break;
            case SOUTH:
                this.direction = Direction.EAST;
                break;
            case WEST:
                this.direction = Direction.SOUTH;
                break;
        }
        Position newPosition = new Position(this);
        return newPosition;
    }

    public Position rotateRight() throws ToyRobotException {
        if (this.direction == null)
            throw new ToyRobotException("Invalid robot direction");

        //rotate robot based on current direction
        switch (this.direction) {
            case NORTH:
                this.direction = Direction.EAST;
                break;
            case EAST:
                this.direction = Direction.SOUTH;
                break;
            case SOUTH:
                this.direction = Direction.WEST;
                break;
            case WEST:
                this.direction = Direction.NORTH;
                break;
        }
        Position newPosition = new Position(this);
        return newPosition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String pos = String.format("(%s, %s)",
                getX(),
                getY());
        sb.append("Robot is at position : ").append(pos)
        .append(" facing "+direction.toString());

        return sb.toString();

    }
}
