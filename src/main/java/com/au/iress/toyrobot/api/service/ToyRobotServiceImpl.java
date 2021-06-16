package com.au.iress.toyrobot.api.service;

import com.au.iress.toyrobot.api.exception.ToyRobotException;
import com.au.iress.toyrobot.api.model.Position;
import com.au.iress.toyrobot.api.model.ToyRobot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToyRobotServiceImpl implements ToyRobotService {
    @Autowired
    private ToyRobot robot;

    @Override
    public String place(Position position) {
        robot.setPosition(position);
        return String.valueOf(robot.getPosition().toString());
    }

    @Override
    /**
     * Function to move robot 1 step ahead in the direction it is facing
     */
    public String move() throws ToyRobotException {
        robot.move();
        return String.valueOf(robot.getPosition().toString());
    }

    @Override
    /**
     * Function to rotate robot to the left with out changing its position
     */
    public String rotateLeft() throws ToyRobotException {
        robot.rotateLeft();
        return String.valueOf(robot.getPosition().toString());
    }

    @Override
    /**
     * Function to rotate robot to the right with out changing its position
     */
    public String rotateRight() throws ToyRobotException {
        robot.rotateRight();
        return String.valueOf(robot.getPosition().toString());
    }

    @Override
    /**
     * Function to report current position of robot
     */
    public String report() throws ToyRobotException{
        if(robot == null || robot.getPosition() == null || !robot.isPlaced())
            throw new ToyRobotException("Robot missing");
        return String.valueOf(robot.getPosition());
    }


}
