package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

public class MoveWestCommand extends MoveFleetCommand {

    // Constructor
    public MoveWestCommand(Grid grid) {
        super(grid, Direction.WEST, Direction.EAST);
    }
}
