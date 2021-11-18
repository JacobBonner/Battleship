package edu.colorado.objectgrind;

import edu.colorado.objectgrind.commands.*;
import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.ships.fleet.Fleet;
import edu.colorado.objectgrind.ships.fleet.MediumFleet;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.grid.MediumGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class defines the tests for the Command interface and classes that implement it.
 *
 * @see Command
 * @see Fleet
 */
public class CommandTest {

    /**
     * The command that will be used throughout the tests.
     */
    Command my_command;

    /**
     * The grid that the commands will be executed on.
     */
    Grid my_grid;

    /**
     * The fleet that will be moved by some of the commands.
     */
    Fleet my_fleet;

    /**
     * Initialize the grid and the fleet before each test.
     */
    @BeforeEach
    public void setUp() {
        my_grid = new MediumGrid(2 );
        my_fleet = new MediumFleet();
    }

    /**
     * Check that a NoCommand can be executed and undone.
     */
    @Test
    public void canExecuteNoCommand() {
        my_command = new NoCommand();
        my_command.execute();
        my_command.undo();
    }

    /**
     * Check that a PlaceShipCommand can be executed and undone.
     */
    @Test
    public void canExecutePlaceShip() {
        Coordinate head = new Coordinate(1, 0, 0);
        Coordinate tail = new Coordinate(1, 0, 3);
        my_command = new PlaceShipCommand(my_fleet.getShipByIndex(0), my_grid, head, tail);
        my_command.execute();
        my_command.undo();
    }

    /**
     * Check that a MoveNorthCommand can be executed and undone.
     */
    @Test
    public void canExecuteMoveNorth() {
        my_command = new MoveNorthCommand(my_grid);
        my_command.execute();
        my_command.undo();
    }

    /**
     * Check that a MoveEastCommand can be executed and undone.
     */
    @Test
    public void canExecuteMoveEast() {
        my_command = new MoveEastCommand(my_grid);
        my_command.execute();
        my_command.undo();
    }

    /**
     * Check that a MoveSouthCommand can be executed and undone.
     */
    @Test
    public void canExecuteMoveSouth() {
        my_command = new MoveSouthCommand(my_grid);
        my_command.execute();
        my_command.undo();
    }

    /**
     * Check that a MoveWestCommand can be executed and undone.
     */
    @Test
    public void canExecuteMoveWest() {
        my_command = new MoveWestCommand(my_grid);
        my_command.execute();
        my_command.undo();
    }
}
