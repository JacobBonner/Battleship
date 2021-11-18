package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

public class MoveEastCommand implements Command {

    // Private attributes
    final Grid grid;

    // Constructor
    public MoveEastCommand(Grid grid) {
        this.grid = grid;
    }

    // Executes the movement of fleet to the East
    public void execute() {
        this.grid.moveFleet(Direction.EAST);
    }

    // Undoes the movement of fleet East, i.e. moves fleet West
    public void undo() {
        this.grid.moveFleet(Direction.WEST);
    }

}
