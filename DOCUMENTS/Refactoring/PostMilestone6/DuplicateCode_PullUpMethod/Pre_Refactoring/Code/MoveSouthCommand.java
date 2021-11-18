package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

public class MoveSouthCommand implements Command {

    // Private attributes
    final Grid grid;

    // Constructor
    public MoveSouthCommand(Grid grid) {
        this.grid = grid;
    }

    // Executes the movement of fleet to the South
    public void execute() {
        this.grid.moveFleet(Direction.SOUTH);
    }

    // Undoes the movement of fleet South, i.e. moves fleet North
    public void undo() {
        this.grid.moveFleet(Direction.NORTH);
    }

}
