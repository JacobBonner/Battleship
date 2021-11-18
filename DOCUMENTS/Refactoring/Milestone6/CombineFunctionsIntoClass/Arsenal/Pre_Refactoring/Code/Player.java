package edu.colorado.objectgrind;

import edu.colorado.objectgrind.Commands.*;
import edu.colorado.objectgrind.Grid.Grid;
import edu.colorado.objectgrind.Ships.Ship;
import edu.colorado.objectgrind.Weapons.Bomb;
import edu.colorado.objectgrind.Weapons.SonarPulse;
import edu.colorado.objectgrind.Weapons.SpaceLaser;
import edu.colorado.objectgrind.Weapons.Weapon;

import java.util.ArrayList;
import java.util.Stack;

public class Player {

    // Private attributes
    private final Grid grid;
    private final ArrayList<Weapon> weapons;
    Stack<Command> player_commands;

    // Constructor
    public Player(Grid grid) {
        this.grid = grid;
        this.weapons = new ArrayList<Weapon>();
        this.weapons.add(new Bomb());
        this.player_commands= new Stack<Command>();
    }

    // Getter for grid
    public Grid getGrid() {
        return this.grid;
    }

    // Getter for commands
    public Stack<Command> getCommands() {
        return this.player_commands;
    }

    // Player places a ship on their grid
    public void playerPlaceShip(int ship_index, String head, String tail) {

        // Check that the ship index is valid
        if ( (ship_index >= 0) & (ship_index < this.grid.getShips().length) ) {

            // Check that the coordinates are valid, that the ship can be placed, and that it has not yet been placed
            Ship to_place = this.grid.getShips()[ship_index];
            if ( this.grid.shipCanBePlaced(to_place, head, tail) & !to_place.isShipPlaced() ) {

                // if so, create a command, execute it, and push it onto the stack
                Grid.Coordinate coord_head = this.grid.stringCoordToIntCoord(head);
                Grid.Coordinate coord_tail = this.grid.stringCoordToIntCoord(tail);
                Command new_command = new PlaceShipCommand(to_place, this.grid, coord_head, coord_tail);

                // Execute the command and push it onto the stack
                new_command.execute();
                this.player_commands.push(new_command);
            }
        }
    }

    // Player moves the fleet on their grid
    public void playerMoveFleet(Direction direction) {

        // Check that all of the player's ship have been placed
        boolean all_ships_placed = true;
        for ( Ship ship : this.grid.getShips() ) {
            if ( !ship.isShipPlaced() ) {
                all_ships_placed = false;
                break;
            }
        }

        // If all of this player's ships have been placed
        if ( all_ships_placed ) {

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

    // Player undoes their most recent move
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


    // Function that adjusts this player's weapons
    public void adjustWeapons(Grid opponent_grid) {
        removeWeapons();
        addWeapons(opponent_grid);
    }

    // Function that removes any weapons that have no uses left
    public void removeWeapons() {

        // Array of indices to remove from the weapons
        ArrayList<Integer> to_remove = new ArrayList<Integer>();

        // Determine which weapons need to be removed
        for(int i = 0; i<this.weapons.size(); i++) {
            if ( this.weapons.get(i).getNumUses() == 0 ) to_remove.add(i);
        }

        // Remove the weapons
        for( int index : to_remove) {
           this.weapons.remove(index);
        }
    }

    // Function that adds/replaces weapons
    public void addWeapons(Grid opponent_grid) {

        // if the number of opponent's ships sunk is exactly 1, and the player only has the Bomb
        if ( opponent_grid.oneShipSunk() & (this.weapons.get(0) instanceof Bomb) ) {

            // Replace Bomb with SpaceLaser
            for (int i = 0; i< this.weapons.size(); i++ ) {
                if ( this.weapons.get(i) instanceof Bomb) this.weapons.set(i, new SpaceLaser());
            }

            // Add the SonarPulse
            this.weapons.add(new SonarPulse());
        }
    }

    // The player takes a turn
    public void takeTurn(Player opponent, int index_of_weapon, String coordinate) {

        // If the coordinate is properly formatted, adn the weapon index is in bounds
        if ( this.grid.isValidCoordinateFormat(coordinate) & (index_of_weapon >= 0) & (index_of_weapon < this.weapons.size())) {

            // Convert the coordinate to a Coordinate object
            Grid.Coordinate new_coord = this.grid.stringCoordToIntCoord(coordinate);

            // If the coordinate is within the bounds of the grid
            if ( this.grid.isCoordinateInBounds(new_coord) ) {

                // Use the weapon on the opponent
                Weapon weapon_of_choice = this.weapons.get(index_of_weapon);
                weapon_of_choice.useWeapon(opponent, new_coord);

                // Re-set the weapon that was used
                //this.weapons.set(index_of_weapon, weapon_of_choice);

                // Adjust this player's weapons
                adjustWeapons(opponent.getGrid());

                // Reset this player's Command stack, because they cannot undo moves from a previous turn
                this.player_commands = new Stack<Command>();
            }
        }
    }

    // Determines if the player must surrender, i.e. all of their ships have sunk
    public boolean surrender() {
        return this.grid.allShipsSunk();
    }

    // Gets all of this player's weapons
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    // Gets the weapon at the given index
    public Weapon getWeaponAtIndex(int index) {
        return this.weapons.get(index);
    }
}
