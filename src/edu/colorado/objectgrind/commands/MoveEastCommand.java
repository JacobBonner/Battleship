package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

/**
 * This class defines the Command for moving the fleet on the given grid EAST.
 * <p>
 *     The execute() method will move the fleet EAST, and the undo method will move the fleet WEST - the opposite of
 *     EAST.
 * </p>
 *
 * @see MoveFleetCommand
 * @see Grid
 * @see Direction
 */
public class MoveEastCommand extends MoveFleetCommand {

    /**
     * Class constructor specifying the grid that will have its fleet moved.
     * <p>
     *     This constructor passes to its super class East for dir_execute, and its opposite - West - for dir_undo.
     * </p>
     *
     * @param grid The grid that this Command will execute and undo fleet movement on.
     */
    public MoveEastCommand(Grid grid) {
        super(grid, Direction.EAST, Direction.WEST);
    }
}
