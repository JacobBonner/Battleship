package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

public class MoveEastCommand extends MoveFleetCommand {

    // Constructor
    public MoveEastCommand(Grid grid) {
        super(grid, Direction.EAST, Direction.WEST);
    }
}
