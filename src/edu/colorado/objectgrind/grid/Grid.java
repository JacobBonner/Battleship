package edu.colorado.objectgrind.grid;
import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.enums.LocationStatus;
import edu.colorado.objectgrind.ships.ship_types.Ship;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class defines the grid that a player will have throughout a game of battleship.
 *
 * @see Location
 * @see Ship
 */
public class Grid {

    /**
     * The 3D array of Locations that represents the structure of the grid.
     */
    private final Location[][][] grid;

    /**
     * Class constructor specifying the dimensions of the grid: number of layers, rows, and columns.
     * 
     * @param size the number of rows and columns that the grid will have
     * @param num_layers the number of layers that the grid will have
     * @see Location
     */
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

    /**
     * Returns the entire 3D grid.
     * 
     * @return the 3D array of locations from this grid
     */
    public Location[][][] getGrid() {
        return this.grid;
    }

    /**
     * Returns a Location in this grid, given a row, column, and layer.
     * 
     * @param layer the layer of the Location to be returned
     * @param row the row of the Location to be returned
     * @param col the column of the location to be returned
     * @return the <code>Location</code> at the given coordinate
     */
    public Location getLocationByIndex(int layer, int row, int col) {
        return grid[layer][row][col];
    }

    /**
     * Converts an input coordinate as a string to a Coordinate object with row, col, and layer.
     * <p>
     *     This function assumes that the input string is already a valid format to be converted to a 
     *     Coordinate object.
     * </p>
     * 
     * @param coord_str the coordinate in string format
     * @return the <code>Coordinate</code> converted from the input string
     * @see Coordinate
     */
    public Coordinate stringCoordToIntCoord(String coord_str) {

        // Extract the layer, row and column from the coordinate
        int new_coord_layer = Integer.parseInt( coord_str.substring(0, 1) );
        int new_coord_row = Integer.parseInt( coord_str.substring(1, coord_str.length()-1) ) - 1;
        int new_coord_col = coord_str.charAt(coord_str.length()-1) - 'A';

        return new Coordinate(new_coord_layer, new_coord_row, new_coord_col);
    }

    /**
     * Determines if the entered string coordinate is a valid format - '[layer][row][column]'.
     * 
     * @param coordinate the coordinate in string format
     * @return <code>true</code> if the input is a valid format; <code>false</code> otherwise
     * 
     * @see Pattern
     * @see Matcher
     */
    public boolean isValidCoordinateFormat(String coordinate) {

        // Set up regex to determine if the coordinate has the proper format
        Pattern coord_pattern = Pattern.compile("^[0-9]{2,}[A-Z]$");
        Matcher coord_matcher = coord_pattern.matcher(coordinate);

        return coord_matcher.find();
    }

    /**
     * Determines if the given coordinate is in bounds.
     * <p>
     *     It checks that the row, column, and layer of the input coordinate do not exceed the
     *     respective dimensions in this grid's 3D array.
     * </p>
     * 
     * @param coord the coordinate to be bounds checked
     * @return <code>true</code> if the coordinate is in bounds; <code>false</code> otherwise
     */
    public boolean isCoordinateInBounds(Coordinate coord) {

        // Get the row, layer, and column of the coordinate
        int row = coord.getRow();
        int layer = coord.getLayer();
        int col = coord.getColumn();

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

    /**
     * Checks that the given ship can be placed between the given head and tail coordinates.
     * <p>
     *     If both coordinates are of a valid format, and both coordinates are in bounds, and the orientation of the ship
     *     is valid, and if the ship will not interfere with another ship on the grid, then return true.
     * </p>
     * 
     * @param ship_to_place the ship that is going to be placed
     * @param head the head coordinate where the ship will be placed
     * @param tail the tail coordinate where the ship will be placed
     * @return <code>true</code> if the ship can be placed between the given head and tail; <code>false</code> otherwise
     * @see #isValidCoordinateFormat(String) 
     * @see #stringCoordToIntCoord(String) 
     * @see Ship#performIsValidPlacement(Coordinate, Coordinate) 
     * @see Ship#performCanPlaceShip(Grid, Coordinate, Coordinate) 
     */
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
                    return ship_to_place.performCanPlaceShip(this, coord_head, coord_tail);
                }
            }
        }

        return false;
    }

    /**
     * Places a ship on this grid between the head and tail coordinates.
     * <p>
     *     This function assumes that the ship can in fact be placed.
     * </p>
     *
     * @param ship_to_place the ship to be placed
     * @param head the head coordinate of where the ship will be placed
     * @param tail the tail coordinate of where the ship will be placed
     * @see Ship#performPlacement(Grid, Coordinate, Coordinate)
     */
    public void placeShip(Ship ship_to_place, Coordinate head, Coordinate tail) {

        // place the given ship
        ship_to_place.performPlacement(this, head, tail);
    }

    /**
     * Removes the given ship from this grid.
     *
     * @param ship_to_remove the ship to be removed
     * @see Location#removeShip()
     * @see Ship#setShipNotPlaced()
     */
    public void removeShip(Ship ship_to_remove) {

        // Find all location where the current ship is and remove it
        for (Location[][] locations : this.grid) {

            for (int row = 0; row < this.grid[0].length; row++) {

                for (int col = 0; col < this.grid[0][0].length; col++) {

                    Location curr_location = locations[row][col];
                    if (curr_location.getShip() == ship_to_remove) curr_location.removeShip();
                }
            }
        }

        // Set the ship's is_placed boolean to false
        ship_to_remove.setShipNotPlaced();
    }

    /**
     * Gets the row and col offset for moving a fleet in the given direction.
     *
     * @param direction the direction that will determine the values returned
     * @return the row and column offset for moving a fleet in the given direction
     * @see Direction
     */
    public int[] getOffset(Direction direction) {

        // Initialize the offsets
        int row_offset = 0;
        int col_offset = 0;

        switch(direction) {

            // Move the fleet up one row
            case NORTH:
                row_offset = -1;
                break;

            // Move the fleet right one column
            case EAST:
                col_offset = 1;
                break;

            // Move the fleet down one row
            case SOUTH:
                row_offset = 1;
                break;

            // Move the fleet left one column
            case WEST:
                col_offset = -1;
                break;
        }

        return new int[] {row_offset, col_offset};
    }

    /**
     * Determines if the fleet of ships on this grid can be moved in the given direction.
     * <p>
     *     If, by moving the fleet in the given direction, no ship will exceed the boundary of this grid, and no ships
     *     will interfere or collide with any other ships, then return true.
     * </p>
     *
     * @param direction the direction that the fleet will try to move
     * @return <code>true</code> if the fleet can move in the given direction; <code>false</code> otherwise
     * @see #getOffset(Direction)
     * @see #isCoordinateInBounds(Coordinate)
     * @see Location#hasShip()
     * @see Location#getShip()
     */
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

    /**
     * Moves a ship at a given coordinate according to the given offsets, if the coordinate has a ship.
     * <p>
     *     This function assumes that if there is a ship at the given coordinate, that it can be moved in the given direction.
     * </p>
     * <p>
     *     If the current coordinate and the coordinate that the ship may be moved to are both in bounds, then continue.
     *     If the current location has a ship and that ship has not sunk, then move the ship according to the given row
     *     and column offsets. Set the old location to HIDDEN, and ff the ship had been HIT at the previous location,
     *     then set the new location to HIT.
     * </p>
     *
     * @param curr_coord the coordinate that will have its ship moved in the given direction
     * @param row_offset the row offset corresponding to the direction the ship will move
     * @param col_offset the column offset corresponding to the direction the ship will move
     * @see #isCoordinateInBounds(Coordinate)
     * @see Location
     * @see Ship#hasSunk()
     */
    public void moveShip(Coordinate curr_coord, int row_offset, int col_offset) {

        // Get the layer, row, column of the current coordinate
        int layer = curr_coord.getLayer();
        int row = curr_coord.getRow();
        int col = curr_coord.getColumn();

        // If both locations are in bounds
        if ( isCoordinateInBounds(curr_coord) & isCoordinateInBounds( new Coordinate(layer, row+row_offset, col+col_offset) ) ) {

            // Get the current location
            Location curr_location = this.grid[layer][row][col];

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
                    if (curr_location.getLocationStatus() == LocationStatus.HIT) {

                        // make the new location HIT
                        new_location.setLocationStatus(LocationStatus.HIT);

                        // make the old location HIDDEN
                        grid[layer][row][col].setLocationStatus(LocationStatus.HIDDEN);
                    }

                    // otherwise keep the location status the same
                }
            }
        }
    }

    /**
     * Moves the fleet of ships on this grid in the given direction.
     * <p>
     *     This function assumes that the fleet can in fact move in the given direction.
     * </p>
     *
     * @param direction the direction to move the fleet
     * @see #moveShip(Coordinate, int, int)
     */
    public void moveFleet(Direction direction) {

        // Get the offsets for where we want to move the fleet
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

