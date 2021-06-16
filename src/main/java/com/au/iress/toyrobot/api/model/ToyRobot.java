package com.au.iress.toyrobot.api.model;

import com.au.iress.toyrobot.api.exception.ToyRobotException;
import org.springframework.stereotype.Component;

@Component
public class ToyRobot {
    private Position position;
    private boolean isPlaced = false;

    public ToyRobot() {
    }

    public boolean setPosition(Position position) {
        if (position == null)
            return false;
        isPlaced = true;
        this.position = position;
        return true;
    }

    /**
     * Will move the toy robot one unit forward in the direction it is currently facing
     * @return
     * @throws ToyRobotException
     */
    public void move() throws ToyRobotException {
        if(!isPlaced) {
            throw new ToyRobotException("Robot not placed on table");
        }
        move(position.getNextPosition());
    }

    /**
     * Will move the toy robot one unit forward in the direction it is currently facing
     * @return
     * @throws ToyRobotException
     */
    public void rotateLeft() throws ToyRobotException {
        if(!isPlaced) {
            throw new ToyRobotException("Robot not placed on table");
        }
        move(position.rotateLeft());
    }

    /**
     * Will move the toy robot one unit forward in the direction it is currently facing
     * @return
     * @throws ToyRobotException
     */
    public void rotateRight() throws ToyRobotException {
        if(!isPlaced) {
            throw new ToyRobotException("Robot not placed on table");
        }
        move(position.rotateRight());
    }

    /**
     * Moves the toy robot to new position
     *
     * @return
     */
    public void move(Position newPosition) {
        if (newPosition == null)
            return;
        this.position = newPosition;
    }

    /**
     * Gets the current position of Robot
     * @return
     */
    public Position getPosition() {
        if(isPlaced) {
            return this.position;
        }
        return null;
    }


    public boolean isPlaced() {
        return isPlaced;
    }
}
