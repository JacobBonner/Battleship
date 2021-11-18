package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

public class MoveFleetCommand implements Command {

    // Private attributes
    private final Grid grid;
    private final Direction dir_execute;
    private final Direction dir_undo;

    // Constructor
    public MoveFleetCommand(Grid grid, Direction dir_execute, Direction dir_undo) {
        this.grid = grid;
        this.dir_execute = dir_execute;
        this.dir_undo = dir_undo;
    }

    // Executes the movement of the fleet in the direction of dir_execute
    public void execute() {
        this.grid.moveFleet(this.dir_execute);
    }

    // Undoes the movement of the fleet in the direction of dir_execute, i.e. moves fleet dir_undo
    public void undo() {
        this.grid.moveFleet(this.dir_undo);
    }
}
