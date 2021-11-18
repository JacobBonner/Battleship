package edu.colorado.objectgrind.ships.placement;

import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.ships.ship_types.Ship;

/**
 * This class defines the behavior for placing a submarine on a grid.
 *
 * @see PlacementBehavior
 */
public class SubmarinePlacement extends PlacementBehavior {

    /**
     * Class constructor that passes valid layers of 0 and 1 to the super class constructor.
     */
    public SubmarinePlacement() {
        super( new int[] {0,1} );
    }

    /**
     * Checks if the top side of a submarine can be placed at the given locations on the grid.
     *
     * @param grid the grid that the topside of the submarine will try to be placed on
     * @param coord_head the head coordinate of where the submarine will be placed
     * @param coord_tail the tail coordinate of where the submarine will be placed
     * @return <code>false</code> if the topside is out of bounds of interferes with another ship; <code>true</code> otherwise
     * @see Coordinate
     * @see Grid#isCoordinateInBounds(Coordinate) 
     */
    public boolean canPlaceTopSide(Grid grid, Coordinate coord_head, Coordinate coord_tail) {

        // If the ship is horizontal
        Coordinate topside;
        if (coord_head.getRow() == coord_tail.getRow()) {

            // Create a new coordinate for the topside
            topside = new Coordinate(coord_head.getLayer(), coord_head.getRow() - 1, coord_head.getColumn() + 2);

        } else { // if the ship is vertical

            // Create a new coordinate for the topside
            topside = new Coordinate(coord_head.getLayer(), coord_head.getRow() + 2, coord_head.getColumn() + 1);

        }

        // if the topside is in bounds, check that it does not interfere with another ship
        if ( grid.isCoordinateInBounds(topside) ) return !grid.getLocationByIndex(topside.getLayer(), topside.getRow(), topside.getColumn()).hasShip();

        // otherwise return false
        return false;
    }

    /**
     * Determines if an entire submarine, both topside and body, can be placed at the given locations on the grid.
     *
     * @param grid the grid that the submarine (ship) will try to be placed on
     * @param coord_head the head coordinate where the submarine (ship) will be placed
     * @param coord_tail the tail coordinate where the submarine (ship) will be placed
     * @return <code>true</code> if both the body and the top side can be placed; <code>false/code> otherwise
     * @see #canPlaceTopSide(Grid, Coordinate, Coordinate) 
     * @see #canPlaceBodyHere(Grid, Coordinate, Coordinate) 
     */
    public boolean canPlaceShipHere(Grid grid, Coordinate coord_head, Coordinate coord_tail) {

        return canPlaceBodyHere(grid, coord_head, coord_tail) & canPlaceTopSide(grid, coord_head, coord_tail);
    }

    /**
     * Places the top side of the given submarine (ship).
     *
     * @param ship_to_place the submarine (ship) that will be placed
     * @param grid_of_placement the grid where the submarine (ship) will be placed
     * @param coord_head the head coordinate where the submarine (ship) will be placed
     * @param coord_tail the tail coordinate where the submarine (ship) will be placed
     */
    public void placeTopSide(Ship ship_to_place, Grid grid_of_placement, Coordinate coord_head, Coordinate coord_tail) {

        // If the ship is horizontal
        if (coord_head.getRow() == coord_tail.getRow()) {
            grid_of_placement.getLocationByIndex(coord_head.getLayer(), coord_head.getRow() - 1, coord_head.getColumn() + 2).setShip(ship_to_place);

        } else { // if the ship is vertical
            grid_of_placement.getLocationByIndex(coord_head.getLayer(), coord_head.getRow() + 2, coord_head.getColumn() + 1).setShip(ship_to_place);
        }
    }

    /**
     * Places the entire given submarine (ship) on the given grid at the given coordinates
     * <p>
     *     If, for both the topside and body, the placement is valid, and the layer of placement is valid, and the part
     *     can be placed between the given head and tail coordinates (i.e. not interfere with any other ships),
     *     then place the entire submarine (ship) on the grid.
     * </p>
     * @param ship_to_place the submarine (ship) to be placed
     * @param grid_of_placement the grid that the submarine (ship) will be placed on
     * @param coord_head the head coordinate where the submarine (ship) will be placed
     * @param coord_tail the tail coordinate where the submarine (ship) will be placed
     */
    public void placeShip(Ship ship_to_place, Grid grid_of_placement, Coordinate coord_head, Coordinate coord_tail) {

        // If the placement of the ship is valid, i.e. not diagonal, tail to the right of or below head
        if ( isValidBodyPlacement(ship_to_place.getLength(), coord_head, coord_tail) ) {

            // If the layer that the ship is being placed on is valid
            if( isValidLayer( coord_head.getLayer() ) ) {

                // Check that the ship can be placed at the given position, i.e. no ship occupies any of the positions that it will cover
                if (canPlaceShipHere(grid_of_placement, coord_head, coord_tail)) {

                    // Place the submarine
                    placeBody(ship_to_place, grid_of_placement, coord_head, coord_tail);
                    placeTopSide(ship_to_place, grid_of_placement, coord_head, coord_tail);
                }
            }
        }
    }
}
