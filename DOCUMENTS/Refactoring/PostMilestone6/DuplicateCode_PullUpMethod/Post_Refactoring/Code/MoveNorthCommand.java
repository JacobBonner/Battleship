package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

public class MoveNorthCommand extends MoveFleetCommand {

    // Constructor
    public MoveNorthCommand(Grid grid) {
        super(grid, Direction.NORTH, Direction.SOUTH);
    }
}
