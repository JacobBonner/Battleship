package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.grid.Grid;

public class MoveNorthCommand implements Command {

    // Private attributes
    final Grid grid;

    // Constructor
    public MoveNorthCommand(Grid grid) {
        this.grid = grid;
    }

    // Executes the movement of fleet to the North
    public void execute() {
        this.grid.moveFleet(Direction.NORTH);
    }

    // Undoes the movement of fleet North, i.e. moves fleet South
    public void undo() {
        this.grid.moveFleet(Direction.SOUTH);
    }

}
