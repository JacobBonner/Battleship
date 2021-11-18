package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

/**
 * This class defines the Command for moving the fleet on the given grid WEST.
 * <p>
 *     The execute() method will move the fleet WEST, and the undo method will move the fleet EAST - the opposite of
 *     WEST.
 * </p>
 *
 * @see MoveFleetCommand
 * @see Grid
 * @see Direction
 */
public class MoveWestCommand extends MoveFleetCommand {

    /**
     * Class constructor specifying the grid that will have its fleet moved.
     * <p>
     *     This constructor passes to its super class West for dir_execute, and its opposite - East - for dir_undo.
     * </p>
     *
     * @param grid The grid that this Command will execute and undo fleet movement on.
     */
    public MoveWestCommand(Grid grid) {
        super(grid, Direction.WEST, Direction.EAST);
    }
}
