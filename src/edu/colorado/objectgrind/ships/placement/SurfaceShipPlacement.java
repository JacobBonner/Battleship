package edu.colorado.objectgrind.ships.placement;

import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.ships.ship_types.Ship;

/**
 * This class defines the behavior for placing a surface ship - battleship, destroyer, minesweeper - on a grid.
 *
 * @see PlacementBehavior
 */
public class SurfaceShipPlacement extends PlacementBehavior {

    /**
     * Class constructor that passes a valid layer of 1 to the super class constructor.
     */
    public SurfaceShipPlacement() {
        super( new int[] {1} );
    }

    /**
     * Determines if the placement of this ship overlaps with another ship already on the given grid.
     *
     * @param grid the grid that the ship will try to be placed on
     * @param coord_head the head coordinate where the ship will be placed
     * @param coord_tail the tail coordinate where the ship will be placed
     * @return <code>false</code> if any of the relevant locations have a ship; <code>true</code> otherwise
     */
    public boolean canPlaceShipHere(Grid grid, Coordinate coord_head, Coordinate coord_tail) {
        return canPlaceBodyHere(grid, coord_head, coord_tail);
    }

    /**
     * Places the entire given ship on the given grid at the given coordinates.
     * <p>
     *     If the placement of the body is valid, and the layer of placement is valid, and the ship can be placed between
     *     the given head and tail coordinates (i.e. not interfere with any other ships), then place the ship on the grid.
     * </p>
     *
     * @param ship_to_place the ship to be placed
     * @param grid_of_placement the grid that the ship will be placed on
     * @param coord_head the head coordinate where the ship will be placed
     * @param coord_tail the tail coordinate where the ship will be placed
     */
    public void placeShip(Ship ship_to_place, Grid grid_of_placement, Coordinate coord_head, Coordinate coord_tail) {

        // If the placement of the ship is valid, i.e. not diagonal, tail to the right of or below head
        if ( isValidBodyPlacement(ship_to_place.getLength(), coord_head, coord_tail) ) {

            // If the layer that the ship is being placed on is valid
            if( isValidLayer( coord_head.getLayer() ) ) {

                // Check that the ship can be placed at the given position, i.e. no ship occupies any of the positions that it will cover
                if ( canPlaceShipHere(grid_of_placement, coord_head, coord_tail) ) {

                    // Place the ship
                    placeBody(ship_to_place, grid_of_placement, coord_head, coord_tail);
                }
            }
        }
    }
}
