package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

/**
 * This class defines the command for moving a fleet on a grid, given a specific direction.
 *
 * @see Command
 */
public class MoveFleetCommand implements Command {

    /**
     * The grid whose fleet will be moved by the command.
     */
    private final Grid grid;

    /**
     * The direction that is used when calling execute().
     */
    private final Direction dir_execute;

    /**
     * The direction that is used when calling undo(). It is opposite of dir_execute.
     */
    private final Direction dir_undo;

    /**
     * Class constructor specifying the grid, and the two directions for execute and undo.
     *
     * @param grid The grid that this Command will execute and undo fleet movement on.
     * @param dir_execute The direction that this Command will use when calling execute().
     * @param dir_undo The direction that this Command will use when calling undo().
     */
    public MoveFleetCommand(Grid grid, Direction dir_execute, Direction dir_undo) {
        this.grid = grid;
        this.dir_execute = dir_execute;
        this.dir_undo = dir_undo;
    }

    /**
     * Executes the movement of this grid's fleet in the direction of dir_execute.
     */
    public void execute() {
        this.grid.moveFleet(this.dir_execute);
    }

    /**
     * Un-executes/undoes the movement of this grid's fleet in the direction of dir_execute.
     * <p>
     *     In other words, it moves this grid's fleet in the direction of dir_undo.
     * </p>
     */
    public void undo() {
        this.grid.moveFleet(this.dir_undo);
    }
}
