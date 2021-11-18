package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

/**
 * This class defines the Command for moving the fleet on the given grid NORTH.
 * <p>
 *     The execute() method will move the fleet NORTH, and the undo method will move the fleet SOUTH - the opposite of
 *     NORTH.
 * </p>
 *
 * @see MoveFleetCommand
 * @see Grid
 * @see Direction
 */
public class MoveNorthCommand extends MoveFleetCommand {

    /**
     * Class constructor specifying the grid that will have its fleet moved.
     * <p>
     *     This constructor passes to its super class North for dir_execute, and its opposite - South - for dir_undo.
     * </p>
     *
     * @param grid The grid that this Command will execute and undo fleet movement on.
     */
    public MoveNorthCommand(Grid grid) {
        super(grid, Direction.NORTH, Direction.SOUTH);
    }
}
