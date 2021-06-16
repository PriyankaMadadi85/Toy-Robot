# Toy Robot Simulator

This is a Java Spring boot that simulates a toy robot movement on a sqaure tabletop of 5 units x 5 units.

## Description

The application that can read in commands of the following form

    PLACE X,Y,F
    MOVE
    LEFT
    RIGHT
    REPORT

- PLACE will put the toy robot on the table in position X,Y
  and facing NORTH, SOUTH, EAST or WEST.
- Default position of robot is (0,0) (which is the south west corner) facing North direction
- The first valid command to the robot should be a PLACE command. Any other commands before Place should be ignored 
- Placing the robot out of the square dimenions should be ignored
- Placing the robot in a direction other than (NORTH/EAST/WEST/SOUTH)  should be ignored
- MOVE will move the toy robot one unit forward in the direction it is currently
  facing.
- LEFT and RIGHT will rotate the robot 90 degrees in the specified direction with out changing the postion of robot
- REPORT will print X,Y and Direction  of the robot
- Any move that would cause the robot to fall off the table would be ignored

## Test Data
Added json collection which can be imported in postman. Also added commanbds.json which can be used as data file to execute multiple commads using postman runner
