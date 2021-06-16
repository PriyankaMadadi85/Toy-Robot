package com.au.iress.toyrobot.api.api;

import com.au.iress.toyrobot.api.exception.ToyRobotException;
import com.au.iress.toyrobot.api.model.Command;
import com.au.iress.toyrobot.api.model.Direction;
import com.au.iress.toyrobot.api.model.Position;
import com.au.iress.toyrobot.api.model.SimulationCommand;
import com.au.iress.toyrobot.api.response.ToyRobotResponse;
import com.au.iress.toyrobot.api.service.ToyRobotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ToyRobotController {
    private static final Logger logger = LoggerFactory.getLogger("ToyRoboController");

    @Autowired
    private ToyRobotService toyRoboService;

    private static final int lenghtOfSquare = 5;

    @PostMapping
    @RequestMapping("/robot/simulate")
    public ToyRobotResponse simulateRobot(@RequestBody SimulationCommand simulationCommand) throws ToyRobotException {
        logger.info("Received command: "+simulationCommand.getCommand());
        String[] args = simulationCommand.getCommand().split(" ");
        String newPos = null;
        ToyRobotResponse response = new ToyRobotResponse();
        Command command;
        try {
            //check if the Command enum has the value passed in request
            command = Command.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            throw new ToyRobotException("Not a valid command");
        }
        if (command == Command.PLACE && args.length < 2) {
            throw new ToyRobotException("Invalid Place command, Position/Direction missing");
        }

        String[] params;
        int x = 0;
        int y = 0;
        Direction commandDirection = null;

        if (command == Command.PLACE) {
            //split the second argument for position values
            params = args[1].split(",");

            x = Integer.parseInt(params[0]);
            y = Integer.parseInt(params[1]);

            //validate coordinates. Should not be more than the dimensions of square
            if(x > lenghtOfSquare || y > lenghtOfSquare){
                throw new ToyRobotException("Robot must be placed on a 5*5 square");
            }
            //verify if direction is valid
            try {
                commandDirection = Direction.valueOf(params[2]);
            } catch (Exception e) {
                throw new ToyRobotException("Invalid Direction in Place command");
            }

        }

        try{
            switch (command) {
                case PLACE:
                    logger.info("From command PLACE >>>");
                    Position position = new Position(x, y, commandDirection);
                    newPos = toyRoboService.place(position);
                    response.setPosition(newPos);
                    logger.info(newPos);
                    break;
                case MOVE:
                    logger.info("From command MOVE >>>");
                    newPos = toyRoboService.move();
                    response.setPosition(newPos);
                    logger.info(newPos);
                    break;
                case LEFT:
                    logger.info("From command LEFT >>>");
                    newPos = toyRoboService.rotateLeft();
                    response.setPosition(newPos);
                    logger.info(newPos);
                    break;
                case RIGHT:
                    logger.info("From command RIGHT >>>");
                    newPos = toyRoboService.rotateRight();
                    response.setPosition(newPos);
                    logger.info(newPos);
                    break;
                case REPORT:
                    logger.info("From command REPORT >>>");
                    newPos = toyRoboService.report();
                    response.setPosition(newPos);
                    logger.info(newPos);
                    break;
                default:
                    logger.info("From Default.");
                    break;
            }
        }  catch (ToyRobotException e) {
            response.setErrorCode(500);
            response.setErrorMessage(e.getMessage());
        }


        return response;
    }

}
