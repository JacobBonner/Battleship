package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

public class MoveSouthCommand extends MoveFleetCommand {

    // Constructor
    public MoveSouthCommand(Grid grid) {
        super(grid, Direction.SOUTH, Direction.NORTH);
    }
}
