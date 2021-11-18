package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

/**
 * This class defines the Command for moving the fleet on the given grid SOUTH.
 * <p>
 *     The execute() method will move the fleet SOUTH, and the undo method will move the fleet NORTH - the opposite of
 *     SOUTH.
 * </p>
 *
 * @see MoveFleetCommand
 * @see Grid
 * @see Direction
 */
public class MoveSouthCommand extends MoveFleetCommand {

    /**
     * Class constructor specifying the grid that will have its fleet moved.
     * <p>
     *     This constructor passes to its super class South for dir_execute, and its opposite - North - for dir_undo.
     * </p>
     *
     * @param grid The grid that this Command will execute and undo fleet movement on.
     */
    public MoveSouthCommand(Grid grid) {
        super(grid, Direction.SOUTH, Direction.NORTH);
    }
}
