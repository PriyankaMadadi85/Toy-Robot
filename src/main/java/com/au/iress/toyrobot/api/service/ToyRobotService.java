package com.au.iress.toyrobot.api.service;

import com.au.iress.toyrobot.api.exception.ToyRobotException;
import com.au.iress.toyrobot.api.model.Position;

public interface ToyRobotService {
    public String place(Position position);

    public String move() throws ToyRobotException;

    public String rotateLeft() throws ToyRobotException;

    public String rotateRight() throws ToyRobotException;

    public String report() throws ToyRobotException;

}
