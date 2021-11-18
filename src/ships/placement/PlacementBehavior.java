package edu.colorado.objectgrind.ships.placement;

import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.ships.ship_types.Ship;

/**
 * This class defines the behavior for placing a ship on a grid.
 *
 * @see Coordinate
 * @see Ship
 * @see Grid
 */
public abstract class PlacementBehavior {

    /**
     * The layers of a grid that this placement behavior apply to.
     */
    private final int[] valid_layers;

    /**
     * Class constructor specifying the layers that are valid for this placement behavior.
     * @param valid_layers the layers that will be valid for this placement behavior
     */
    public PlacementBehavior(int[] valid_layers) {
        this.valid_layers = valid_layers;
    }

    /**
     * Checks that placement of the body of the given ship is valid.
     * <p>
     *     If the head and tail coordinates are in the same row (horizontal), then a valid placement is when the tail
     *     is to the right of head a number of locations equal to the given ship length. If the head and tail
     *     coordinates are in the same column, then a valid placement is when the tail is below the head a number of
     *     locations equal to the given ship length. If the head and tail coordinates do not share a row or layer or
     *     column, or if they do but do not satisfy the requirements listed before, then the placement will be invalid.
     * </p>
     *
     * @param ship_length the length of the ship
     * @param head the head coordinate for the ship
     * @param tail the tail coordinate for the ship
     * @return <code>true</code> if the placement is valid; <code>false</code> otherwise
     * @see Coordinate
     */
    public boolean isValidBodyPlacement(int ship_length, Coordinate head, Coordinate tail) {

        // If the head and tail are in the same layer
        if ( head.getLayer() == tail.getLayer() ) {

            // If the head and tail are in the same row, and the tail is to the right of the head by ship_length-1
            if ((head.getRow() == tail.getRow()) & (tail.getColumn() == (head.getColumn() + ship_length - 1)))
                return true;

            // If the head and tail are in the same column, and the tail is below the head by ship_length-1
            return ((head.getColumn() == tail.getColumn()) & (tail.getRow() == (head.getRow() + ship_length - 1)));
        }

        return false;
    }

    /**
     * Checks that the body of the ship can be placed between the given head and tail coordinates.
     * <p>
     *     If any of the locations between the given head and tail coordinates already have a ship, then return false.
     * </p>
     *
     * @param grid the grid that the ship will try to be placed on
     * @param coord_head the head coordinate where the ship body will be placed
     * @param coord_tail the tail coordinate where the ship body will be placed
     * @return <code>false</code> if any of the locations between head and tail have a ship; <code>true</code> otherwise
     * @see Coordinate
     * @see Grid#getLocationByIndex(int, int, int)
     */
    public boolean canPlaceBodyHere(Grid grid, Coordinate coord_head, Coordinate coord_tail) {

        // If the ship is horizontal
        if (coord_head.getRow() == coord_tail.getRow()) {

            // In the given row, return false if there is a ship there
            for (int j = coord_head.getColumn(); j < coord_tail.getColumn() + 1; j++) {
                if (grid.getLocationByIndex(coord_head.getLayer(), coord_head.getRow(), j).hasShip()) return false;
            }

        } else { // if the ship is vertical

            // In the given column, return false if there is a ship there
            for (int i = coord_head.getRow(); i < coord_tail.getRow() + 1; i++) {
                if (grid.getLocationByIndex(coord_head.getLayer(), i, coord_head.getColumn()).hasShip()) return false;
            }

        }

        return true;
    }

    /**
     * Checks that a ship can be placed on the given grid between the given head and tail coordinates.
     *
     * @param grid the grid that the ship will try to be placed on
     * @param coord_head the head coordinate where the ship will be placed
     * @param coord_tail the tail coordinate where the ship will be placed
     * @return <code>false</code> if any of the relevant locations have a ship; <code>true</code> otherwise
     */
    public abstract boolean canPlaceShipHere(Grid grid, Coordinate coord_head, Coordinate coord_tail);

    /**
     * Places the body of the ship on the given grid between the given head and tail coordinates.
     * <p>
     *     For every location on the given grid between the head and tail, set the ship equal to the given ship.
     * </p>
     *
     * @param ship_to_place the ship to be placed
     * @param grid_of_placement the grid that the ship body will be placed on
     * @param coord_head the head coordinate where the ship body will be placed
     * @param coord_tail the tail coordinate where the ship body will be placed
     * @see Grid
     * @see Ship
     */
    public void placeBody(Ship ship_to_place, Grid grid_of_placement, Coordinate coord_head, Coordinate coord_tail) {

        // If the ship is horizontal
        if (coord_head.getRow() == coord_tail.getRow()) {

            // In the given row, set the ship at every location from head to tail to the given ship
            for (int j = coord_head.getColumn(); j < coord_tail.getColumn() + 1; j++) {
                grid_of_placement.getLocationByIndex(coord_head.getLayer(), coord_head.getRow(), j).setShip(ship_to_place);
            }
        } else { // if the ship is vertical

            // In the given column, set the ship at every location from head to tail to the given ship
            for (int i = coord_head.getRow(); i < coord_tail.getRow() + 1; i++) {
                grid_of_placement.getLocationByIndex(coord_head.getLayer(), i, coord_head.getColumn()).setShip(ship_to_place);
            }
        }
    }

    /**
     * Checks that the given layer is a valid layer for this placement behavior.
     *
     * @param layer_of_placement the layer to be verified
     * @return <code>true</code> if the given layer is in this placement behavior's valid layers; <code>false</code> otherwise
     */
    public boolean isValidLayer(int layer_of_placement) {

        // Return true if the given layer matches any of the valid layers
        for ( int layer : this.valid_layers ) {
            if ( layer_of_placement == layer ) return true;
        }

        return false;
    }

    /**
     * Places the entire given ship on the given grid at the given coordinates.
     *
     * @param ship_to_place the ship to be placed
     * @param grid_of_placement the grid that the ship will be placed on
     * @param coord_head the head coordinate where the ship will be placed
     * @param coord_tail the tail coordinate where the ship will be placed
     */
    public abstract void placeShip(Ship ship_to_place, Grid grid_of_placement, Coordinate coord_head, Coordinate coord_tail);
}
