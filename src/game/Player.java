package edu.colorado.objectgrind.game;

import edu.colorado.objectgrind.game.factories.GamePartsFactory;
import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.weapons.arsenal.Arsenal;
import edu.colorado.objectgrind.commands.*;
import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.ships.fleet.Fleet;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.ships.ship_types.Ship;
import edu.colorado.objectgrind.weapons.Weapon;

import java.util.Stack;

/**
 * This class defines a player of the battleship game and the components and functions of the game they need.
 *
 * @see Grid
 * @see Arsenal
 * @see Command
 * @see Fleet
 * @see GamePartsFactory
 */
public class Player {

    /**
     * The grid that this player will use throughout a game.
     */
    private final Grid grid;

    /**
     * The arsenal of weapons that this player will use throughout a game.
     */
    private final Arsenal arsenal;

    /**
     * The stack of commands that this player calls throughout a game.
     */
    private Stack<Command> player_commands;

    /**
     * The fleet of ships that this player has and will use throughout the game.
     */
    private final Fleet fleet;

    /**
     * Class constructor specifying the GamePartsFactory that will be used to create the parts for this player.
     *
     * @param factory_parts the factory that is needed to create the parts/attributes for this player
     * @see GamePartsFactory#createGrid()
     * @see GamePartsFactory#createArsenal()
     * @see GamePartsFactory#createFleet()
     */
    public Player(GamePartsFactory factory_parts) {
        this.grid = factory_parts.createGrid();
        this.arsenal = factory_parts.createArsenal();
        this.player_commands= new Stack<>();
        this.fleet = factory_parts.createFleet();
    }

    /**
     * A getter for this player's grid.
     *
     * @return this player's <code>Grid</code>
     */
    public Grid getGrid() {
        return this.grid;
    }

    /**
     * A getter for this player's stack of commands.
     *
     * @return this player's <code>Stack<Command></code>
     */
    public Stack<Command> getCommands() {
        return this.player_commands;
    }

    /**
     * Executes a command for placing the ship at the given index on this player's grid.
     * <p>
     *     If the index of the ship is valid, the coordinates are valid, and the ship can actually be 
     *     placed between head and tail on this player's grid, then a new PlaceShipCommand is created, 
     *     executed, and pushed onto this player's command stack.
     *     If any of the above conditions are not met, then the function does nothing.
     * </p>
     *
     * @param ship_index index of the ship in this player's fleet that will be placed
     * @param head coordinate of where the head of the ship will be placed
     * @param tail coordinate of where the tail of the ship will be placed
     * @see Fleet#getSize()
     * @see Fleet#getShipByIndex(int)
     * @see Grid#shipCanBePlaced(Ship, String, String)
     * @see Ship#isShipPlaced()
     * @see Grid#stringCoordToIntCoord(String)
     * @see PlaceShipCommand
     */
    public void playerPlaceShip(int ship_index, String head, String tail) {

        // Check that the ship index is valid
        if ( (ship_index >= 0) & (ship_index < this.fleet.getSize()) ) {

            // Check that the coordinates are valid, that the ship can be placed, and that it has not yet been placed
            Ship to_place = this.fleet.getShipByIndex(ship_index);
            if ( this.grid.shipCanBePlaced(to_place, head, tail) & !to_place.isShipPlaced() ) {

                // if so, create a command, execute it, and push it onto the stack
                Coordinate coord_head = this.grid.stringCoordToIntCoord(head);
                Coordinate coord_tail = this.grid.stringCoordToIntCoord(tail);
                Command new_command = new PlaceShipCommand(to_place, this.grid, coord_head, coord_tail);

                // Execute the command and push it onto the stack
                new_command.execute();
                this.player_commands.push(new_command);
            }
        }
    }

    /**
     * Executes a command for moving the fleet on this player's grid in the given direction.
     * <p>
     *     IF all of the player's ships have been placed, and the fleet can actually
     *     move in the given direction on this player's grid, then a new MoveFleetCommand 
     *     corresponding to the given direction is created, executed, and pushed onto this player's
     *     command stack. If any of the conditions are not met, then the function does nothing.
     * </p>
     * 
     * @param direction the direction which the fleet will be moved
     * @see Direction
     * @see Fleet#allShipsPlaced()
     * @see Grid#canMoveFleet(Direction)
     * @see NoCommand
     * @see MoveFleetCommand
     */
    public void playerMoveFleet(Direction direction) {

        // If all of this player's ships have been placed
        if ( this.fleet.allShipsPlaced() ) {

            // Check that the player can move the fleet in the given direction
            if (this.grid.canMoveFleet(direction)) {

                // if so, create a command execute it and push it onto the stack
                Command new_command = new NoCommand();

                switch (direction) {
                    case NORTH:
                        new_command = new MoveNorthCommand(this.grid);
                        break;

                    case EAST:
                        new_command = new MoveEastCommand(this.grid);
                        break;

                    case SOUTH:
                        new_command = new MoveSouthCommand(this.grid);
                        break;

                    case WEST:
                        new_command = new MoveWestCommand(this.grid);
                        break;

                }

                // Execute the command and push it onto the stack
                new_command.execute();
                this.player_commands.push(new_command);
            }
        }
    }

    /**
     * Undoes the most recent command that the player executed.
     * <p>
     *     If there is a command on this player's command stack, then it is popped of and the undo()
     *     operation is called for that command. If the command stack is empty, then it does nothing.
     * </p>
     * 
     * @see Command
     */
    public void playerUndo() {

        // If the stack is not empty
        if( !this.player_commands.empty() ) {

            // Pop the most recent command off the stack
            Command cmd_to_undo = this.player_commands.pop();

            // Undo the command
            cmd_to_undo.undo();
        }
        // otherwise, there is nothing to undo, so do nothing
    }

    /**
     * Uses the weapon in this player's arsenal given by the index, and then adjusts the arsenal accordingly.
     * 
     * @param opponent the opposing Player who will be attacked
     * @param index_of_weapon index of the weapon from this player's arsenal that will be used
     * @param coord the coordinate where the weapon will be used
     * @throws Exception if the Method used in useWeapon is invalid
     * @see #getNumShipsSunk() 
     * @see Arsenal#getWeaponAtIndex(int) 
     * @see Weapon#useWeapon(Player, Coordinate) 
     * @see Arsenal#adjustArsenal(int, int) 
     */
    private void useWeapon(Player opponent, int index_of_weapon, Coordinate coord) throws Exception {

        // Get the number of the opponent's ships that are sunk before the attack
        int pre_num_sunk = opponent.getNumShipsSunk();

        // Use the weapon on the opponent
        Weapon weapon_of_choice = this.arsenal.getWeaponAtIndex(index_of_weapon);
        weapon_of_choice.useWeapon(opponent, coord);

        // Get the number of ships sunk after the attack
        int post_num_sunk = opponent.getNumShipsSunk();

        // Adjust this player's weapons
        int num_sunk_this_turn = post_num_sunk - pre_num_sunk;
        this.arsenal.adjustArsenal(post_num_sunk, num_sunk_this_turn);
    }

    /**
     * Takes a turn for this player by using a weapon from their arsenal at the given index.
     * <p>
     *     If the given coordinate is properly formatted, and the weapon index is in bounds, and the coordinate is in
     *     bounds of this player's grid, then use the weapon in this player's arsenal at the given index on the given
     *     opponent player.
     * </p>
     *
     * @param opponent the opponent of this player
     * @param index_of_weapon the index of the weapon from this player's arsenal to be used
     * @param coordinate the coordinate that this player will use their weapon on
     * @throws Exception if the attack method is invalid
     */
    public void takeTurn(Player opponent, int index_of_weapon, String coordinate) throws Exception {

        // If the coordinate is properly formatted, adn the weapon index is in bounds
        if ( this.grid.isValidCoordinateFormat(coordinate) & (index_of_weapon >= 0) & (index_of_weapon < this.arsenal.getNumberOfWeapons())) {

            // Convert the coordinate to a Coordinate object
            Coordinate new_coord = this.grid.stringCoordToIntCoord(coordinate);

            // If the coordinate is within the bounds of the grid
            if ( this.grid.isCoordinateInBounds(new_coord) ) {

                // Use the weapon at the index
                this.useWeapon(opponent, index_of_weapon, new_coord);

                // Reset this player's Command stack, because they cannot undo moves from a previous turn
                this.player_commands = new Stack<>();
            }
        }
    }

    /**
     * Returns the number of ships sunk in this player's fleet
     *
     * @return number of ships in this player's fleet that have sunk
     * @see Fleet#getNumShipsSunk()
     */
    public int getNumShipsSunk() {
        return this.fleet.getNumShipsSunk();
    }

    /**
     * Returns the ship in this player's fleet at the given index.
     *
     * @param index index of the ship to be returned
     * @return the ship at the given index in this player's fleet
     * @see Fleet#getShipByIndex(int)
     */
    public Ship getShipAtIndex(int index) {
        return this.fleet.getShipByIndex(index);
    }

    /**
     * Determines if the player must surrender, i.e. all of their ships have sunk
     *
     * @return <code>true</code> if all of the ships in this player's fleet have sunk; <code>false</code> otherwise
     * @see Fleet#allShipsSunk()
     */
    public boolean surrender() {
        return this.fleet.allShipsSunk();
    }

    /**
     * Returns the number of weapons in this player's arsenal.
     *
     * @return the number of weapons in this player's arsenal
     * @see Arsenal#getNumberOfWeapons()
     */
    public int getSizeOfArsenal() {
        return this.arsenal.getNumberOfWeapons();
    }

    /**
     * Returns the number of ships in this player's fleet.
     *
     * @return the number of weapons in this player's fleet
     * @see Fleet#getSize()
     */
    public int getSizeOfFleet() {
        return this.fleet.getSize();
    }

    /**
     * Returns the weapon in this player's arsenal at the given index.
     *
     * @param index index of the weapon to be returned
     * @return the weapon at the given index in this player's arsenal
     * @see Arsenal#getWeaponAtIndex(int)
     */
    public Weapon getWeaponAtIndex(int index) {
        return this.arsenal.getWeaponAtIndex(index);
    }
}
