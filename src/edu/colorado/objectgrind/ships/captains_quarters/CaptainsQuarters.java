package edu.colorado.objectgrind.ships.captains_quarters;

import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.ships.ship_types.Ship;

/**
 * This class defines the behavior of and how to determine if a location is the captain's quarters of a ship.
 *
 * @see Ship
 * @see Coordinate
 */
public class CaptainsQuarters {

    /**
     * The offset values for the rows that must have the same ship.
     */
    private final int [] same_ship_rows;

    /**
     * The offset values for the rows that must NOT have the same ship.
     */
    private final int [] not_ship_rows;

    /**
     * The offset values for the columns that must have the same ship.
     */
    private final int [] same_ship_cols;

    /**
     * The offset values for the columns that must NOT have the same ship.
     */
    private final int [] not_ship_cols;

    /**
     * Class constructor specifying all of the offset arrays for this captains quarters.
     *
     * @param same_rows the row offsets that same_ship_rows will be set to
     * @param not_rows the row offsets that not_ship_rows will be set to
     * @param same_cols the column offsets that same_ship_cols will be set to
     * @param not_cols the column offsets that not_ship_cols will be set to
     */
    public CaptainsQuarters(int[] same_rows, int[] not_rows, int[] same_cols, int[] not_cols) {
        this.same_ship_rows = same_rows;
        this.not_ship_rows = not_rows;
        this.same_ship_cols = same_cols;
        this.not_ship_cols = not_cols;
    }

    /**
     * Checks if the coordinate of the given grid is the captains quarters of the ship at that location.
     * <p>
     *     The same_ship_offsets and the not_ship_offsets define the orientation which the check for captains quarters
     *     is executed.
     * </p>
     *
     * @param same_ship_offsets offset values for the rows that must have the same ship
     * @param not_ship_offsets offset values for the rows that must NOT have the same ship
     * @param coord the coordinate that is being checked for captain's quarters
     * @param grid the that the coordinate is indexing
     * @return <code>true</code> if the given coordinate is the captain's quarters of the
     *         given ship; <code>false/code> otherwise
     */
    public boolean checkCaptainQuarters(int[] same_ship_offsets, int[] not_ship_offsets, Coordinate coord, Grid grid) {

        // Get the row, column, and layer from the coordinate
        int row = coord.getRow();
        int col = coord.getColumn();
        int layer = coord.getLayer();

        // Get the ship at the given coordinate
        Ship ship_of_interest = grid.getLocationByIndex(layer,row,col).getShip();

        // Check all of the same ship locations, given all of the row offsets
        int new_row = row;
        int new_col = col;
        for ( int offset : same_ship_offsets ) {

            // if the offset array is same_ship_rows
            if ( same_ship_offsets == this.same_ship_rows ) {
                new_row = row + offset;
                System.out.print("They are the same");
            }
            else {
                new_col = col + offset;
            }

            // If the location is out of bounds, return false
            if ( !grid.isCoordinateInBounds( new Coordinate(layer,new_row,new_col) ) ) return false;

            // If the location has a different ship, return false
            if ( grid.getLocationByIndex(layer,new_row,new_col).getShip() != ship_of_interest ) return false;
        }

        // Reset the row and column
        new_row = row;
        new_col = col;

        // Check all of the different ship locations
        for ( int offset : not_ship_offsets ) {

            // if the offset array is same_ship_rows
            if ( not_ship_offsets == this.not_ship_rows ) {
                new_row = row + offset;
            }
            else {
                new_col = col + offset;
            }

            // If the location is out of bounds, return false
            if ( !grid.isCoordinateInBounds( new Coordinate(layer,new_row,new_col) ) ) return false;

            // If the location has a different ship, return false
            if ( grid.getLocationByIndex(layer,new_row,new_col).getShip() == ship_of_interest ) return false;
        }

        // Otherwise, all of the locations satisfy the conditions, so return true
        return true;
    }

    /**
     * Determines if the given location is a captain's quarters, by checking vertically and horizontally.
     *
     * @param grid_p the grid that the given coordinate will index
     * @param coord the coordinate that is being checked for captains quarters
     * @return <code>true</code> if the given coordinate is the captain's quarters of the
     *         given ship; <code>false/code> otherwise
     * @see #checkCaptainQuarters(int[], int[], Coordinate, Grid) 
     * @see Grid#getLocationByIndex(int, int, int)
     * @see Coordinate
     */
    public boolean isCaptainsQuarters(Grid grid_p, Coordinate coord) {

        // Checks for the captain's quarters according to a horizontal orientation
        boolean horizontal_cap_quarters = checkCaptainQuarters(same_ship_cols, not_ship_cols, coord, grid_p);

        // If the location is a captain's quarters
        if( horizontal_cap_quarters ) return true;

        // Checks for the captain's quarters according to a vertical orientation
        return checkCaptainQuarters(same_ship_rows, not_ship_rows, coord, grid_p);
    }
}
