package edu.colorado.objectgrind.Grid;
import edu.colorado.objectgrind.Direction;
import edu.colorado.objectgrind.Ships.*;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.core.ColumnOrderDependent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grid {

    // A data structure to represent a coordinate
    static public class Coordinate {

        // Row and column values
        private int row;
        private int col;
        private int layer;

        // Constructor
        public Coordinate(int new_layer, int new_row, int new_col) {
            row = new_row;
            col = new_col;
            layer = new_layer;
        }

        // Get the row
        public int getRow() {
            return this.row;
        }

        // Get the column
        public int getColumn() {
            return this.col;
        }

        // Get the layer
        public int getLayer() {
            return this.layer;
        }
    }

    // Private Attributes
    private Location[][][] grid;
    protected Ship[] ships;

    // Constructor
    public Grid(int size, int num_layers) {

        // Create the grid
        this.grid = new Location[num_layers][size][size];

        // Initialize each location in the grid
        for(int layer = 0; layer < num_layers; layer++) {
            for(int row = 0; row < size; row++ ){
                for(int column = 0; column < size; column++){
                    this.grid[layer][row][column] = new Location();
                }
            }
        }
    }

    // Getter for grid
    public Location[][][] getGrid() {
        return this.grid;
    }

    // Get a location in the grid given the coordinates
    public Location getLocationByIndex(int layer, int row, int col) {
        return grid[layer][row][col];
    }

    // Getter for ship
    public Ship[] getShips() {
        return this.ships;
    }

    // Determines if exactly one ship has sunk
    public boolean oneShipSunk() {

        int num_sunk = 0;

        // If any of the ships has sunk, return true
        for (Ship ship : this.ships) {
            if (ship.hasSunk()) num_sunk++;
        }

        return num_sunk == 1;
    }

    // Determines if all of the ships have been sunk
    public boolean allShipsSunk() {

        // If any of the ships has not sunk, then return false
        for (Ship ship : this.ships) {
            if (!ship.hasSunk()) return false;
        }
        return true;
    }

    // Converts an input coordinate as a string to a Coordinate object with row and col
    public Coordinate stringCoordToIntCoord(String coord_str) {

        // Extract the layer, row and column from the coordinate
        int new_coord_layer = Integer.parseInt( coord_str.substring(0, 1) );
        int new_coord_row = Integer.parseInt( coord_str.substring(1, coord_str.length()-1) ) - 1;
        int new_coord_col = coord_str.charAt(coord_str.length()-1) - 'A';

        return new Coordinate(new_coord_layer, new_coord_row, new_coord_col);
    }

    // Determines if the entered location is a valid format - '[layer][row][column]'
    public boolean isValidCoordinateFormat(String coordinate) {

        // Set up regex to determine if the coordinate has the proper format
        Pattern coord_pattern = Pattern.compile("^[0-9]{2,}[A-Z]$");
        Matcher coord_matcher = coord_pattern.matcher(coordinate);

        return coord_matcher.find();
    }

    // Determines if the given coordinate is in bounds
    public boolean isCoordinateInBounds(Coordinate coord) {

        int row = coord.row;
        int layer = coord.layer;
        int col = coord.col;

        // If the layer is in bounds
        if ( (0 <= layer) & (layer < this.grid.length) ) {
            // If the row and column are BOTH within bounds, then return true
            boolean valid_row = (0 <= row) & (row < this.grid[layer].length);
            boolean valid_col = (0 <= col) & (col < this.grid[layer].length);
            return valid_row & valid_col;
        }

        // otherwise the layer is out of bounds
        return false;
    }

    // Ensures that the given ship can be placed -- including checking the coordinates and overall ship placement
    public boolean shipCanBePlaced(Ship ship_to_place, String head, String tail) {

        // If both coordinates are a valid format
        if ( isValidCoordinateFormat(head) & isValidCoordinateFormat(tail) ) {

            // Convert the input coordinates to a Coordinate object
            Coordinate coord_head = stringCoordToIntCoord(head);
            Coordinate coord_tail = stringCoordToIntCoord(tail);

            // If both coordinates are in bounds
            if ( isCoordinateInBounds(coord_head) & isCoordinateInBounds(coord_tail) ) {

                // If the placement of the ship is valid, i.e. not diagonal, tail to the right of or below head
                if ( ship_to_place.performIsValidPlacement(coord_head, coord_tail) ) {

                    // If the ship can be placed at the given position, i.e. no ship occupies any of the positions that it will cover
                    if ( ship_to_place.performCanPlaceShip(this, coord_head, coord_tail) ) return true;
                }
            }
        }

        return false;
    }

    // Places a ship on the grid, assuming that the placement is valid
    public void placeShip(Ship ship_to_place, Coordinate head, Coordinate tail) {

        // place the given ship
        ship_to_place.performPlacement(this, head, tail);
    }

    // Removes the given ship from the grid
    public void removeShip(Ship ship_to_remove) {

        // Find all location where the current ship is and remove it
        for (int layer = 0; layer < this.grid.length; layer++) {
            for (int row = 0; row < this.grid[0].length; row++) {
                for (int col = 0; col < this.grid[0][0].length; col++) {
                    Location curr_location = grid[layer][row][col];
                    if ( curr_location.getShip() == ship_to_remove ) curr_location.removeShip();
                }
            }
        }

        // Set the ship's is_placed boolean to false
        ship_to_remove.setShipNotPlaced();
    }

    // Gets the row and col offset for moving fleet in teh given direction
    public int[] getOffset(Direction direction) {

        int row_offset = 0;
        int col_offset = 0;

        switch(direction) {

            case NORTH:
                row_offset = -1;
                break;

            case EAST:
                col_offset = 1;
                break;

            case SOUTH:
                row_offset = 1;
                break;

            case WEST:
                col_offset = -1;
                break;
        }

        return new int[] {row_offset, col_offset};
    }

    // Determines if the fleet of ships can be moved in the given Direction
    public boolean canMoveFleet(Direction direction) {

        // Set the offsets for where we want to move the fleet
        int[] offset = getOffset(direction);
        int row_offset = offset[0];
        int col_offset = offset[1];

        // If there is any ship that is not sunk that would run into another ship or go out of bounds, then we do NOT move the fleet
        for (int layer = 0; layer < this.grid.length; layer++) {
            for (int row = 0; row < this.grid[0].length; row++) {
                for (int col = 0; col < this.grid[0][0].length; col++) {

                    // If the current coordinate is in bounds
                    if ( isCoordinateInBounds(new Coordinate(layer,row,col)) ) {

                        // Get the current location
                        Location curr_location = grid[layer][row][col];

                        // if the current location has a ship
                        if ( curr_location.hasShip() ) {

                            // If the ship has not sunk
                            if ( !curr_location.getShip().hasSunk() ) {

                                // If where the ship will move is out of bounds
                                if (!isCoordinateInBounds(new Coordinate(layer,row+row_offset,col+col_offset))) return false;
                                else {

                                    // Get the new location
                                    Location new_location = grid[layer][row+row_offset][col+col_offset];

                                    // if the new location has a ship
                                    if ( new_location.hasShip()) {

                                        // if the ships are different, then return false
                                        if (curr_location.getShip() != new_location.getShip() ) return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // If no ships will go out of bounds and no ships run into any other ships, then we will move the fleet
        return true;

    }

    // Tries to move a ship corresponding to the given offset
    public void moveShip(Coordinate curr_coord, int row_offset, int col_offset) {

        // Get the layer, row, column of the current coordinate
        int layer = curr_coord.getLayer();
        int row = curr_coord.getRow();
        int col = curr_coord.getColumn();

        // If both locations are in bounds
        if ( isCoordinateInBounds(curr_coord) & isCoordinateInBounds( new Coordinate(layer, row+row_offset, col+col_offset) ) ) {

            // Get a copy of the grid for reference
            Location[][][] old_grid = this.grid;

            // Get the current location
            Location curr_location = old_grid[layer][row][col];

            // if the current location has a ship
            if (curr_location.hasShip()) {

                // If the ship has not sunk
                if (!curr_location.getShip().hasSunk()) {

                    // Get the new location that the current location's ship is moving to
                    Location new_location = grid[layer][row + row_offset][col + col_offset];

                    // Set the ship of the new location equal to the the ship at the current location
                    new_location.setShip(curr_location.getShip());

                    // Remove the ship from the current location
                    grid[layer][row][col].removeShip();

                    // If the location of the current status is HIT
                    if (curr_location.getLocationStatus() == Location.LocationStatus.HIT) {

                        // make the new location HIT
                        new_location.setLocationStatus(Location.LocationStatus.HIT);

                        // make the old location HIDDEN
                        grid[layer][row][col].setLocationStatus(Location.LocationStatus.HIDDEN);
                    }

                    // otherwise keep the location status the same
                }
            }
        }
    }


    // Moves the fleet in a given direction
    public void moveFleet(Direction direction) {

        // Set the offsets for where we want to move the fleet
        int[] offset = getOffset(direction);
        int row_offset = offset[0];
        int col_offset = offset[1];

        // Loop over the entire grid and move all of the ships that have not sunk
        for (int layer = 0; layer < this.grid.length; layer++) {

            if ( (col_offset == -1) | (row_offset == -1) ) { // NORTH or WEST

                for (int row = 0; row < this.grid[0].length; row++) {
                    for (int col = 0; col < this.grid[0][0].length; col++) {

                        // If there is a ship at the current location, try to move it
                        moveShip(new Coordinate(layer, row, col), row_offset, col_offset);
                    }
                }
            }
            else { // EAST or SOUTH

                for (int row = this.grid[0].length-1; row >= 0; row--) {
                    for (int col = this.grid[0][0].length-1; col >= 0; col--) {

                        // If there is a ship at the current location, try to move it
                        moveShip(new Coordinate(layer, row, col), row_offset, col_offset);
                    }
                }
            }
        }
    }
}

