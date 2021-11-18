package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

public class MoveWestCommand implements Command {

    // Private attributes
    final Grid grid;

    // Constructor
    public MoveWestCommand(Grid grid) {
        this.grid = grid;
    }

    // Executes the movement of fleet to the West
    public void execute() {
        this.grid.moveFleet(Direction.WEST);
    }

    // Undoes the movement of fleet North, i.e. moves fleet East
    public void undo() {
        this.grid.moveFleet(Direction.EAST);
    }

}
