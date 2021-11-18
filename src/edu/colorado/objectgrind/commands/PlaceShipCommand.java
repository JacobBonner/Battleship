package edu.colorado.objectgrind.commands;

import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.ships.ship_types.Ship;

/**
 * Defines the command for placing a ship on a grid between two coordinates.
 *
 * @see Command
 * @see Ship
 * @see Grid
 * @see Coordinate
 */
public class PlaceShipCommand implements Command{

    /**
     * The ship that will be placed by this Command.
     */
    private final Ship ship;

    /**
     * The grid that will have the ship placed on it.
     */
    private final Grid grid;

    /**
     * The head coordinate of where the ship will be placed
     */
    private final Coordinate head;

    /**
     * The tail coordinate of where the ship will be placed.
     */
    private final Coordinate tail;

    /**
     * Class constructor specifying the ship, grid, and head and tail coordinates for this Command.
     *
     * @param ship The ship that this Command will place on the grid.
     * @param grid The grid that this Command will place the ship on.
     * @param head The coordinate that this Command will start placing the ship at.
     * @param tail The coordinate that this Command will stop placing the ship at.
     */
    public PlaceShipCommand(Ship ship, Grid grid, Coordinate head, Coordinate tail) {
        this.ship = ship;
        this.grid = grid;
        this.head = head;
        this.tail = tail;
    }

    /**
     * Executes the placement of this ship on this grid between the head and tail coordinates.
     */
    public void execute() {
        this.grid.placeShip(ship, head, tail);
    }

    /**
     * Un-executes/undoes the placement of this ship on this grid, i.e. removes the ship from the grid.
     */
    public void undo() {
        this.grid.removeShip(ship);
    }
}
