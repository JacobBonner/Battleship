package edu.colorado.objectgrind.ships.ship_types;

import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.ships.captains_quarters.CaptainsQuarters;
import edu.colorado.objectgrind.ships.placement.PlacementBehavior;

/**
 * This class defines a ship that a player will have for a game of battleship.
 *
 * @see PlacementBehavior
 * @see CaptainsQuarters
 */
public abstract class Ship {

    /**
     * The length of this ship.
     */
    private final int length;

    /**
     * The health of this ship.
     */
    private int health;

    /**
     * The health of this ship's captain's quarters.
     */
    private int captains_quarters_health;

    /**
     * Indicates whether or not this ship has been placed.
     */
    private boolean is_placed;

    /**
     * The placement behavior of this ship.
     */
    private final PlacementBehavior place_behavior;

    /**
     * The captain's quarters of this ship.
     */
    private final CaptainsQuarters captain_quarter;

    /**
     * Class constructor specifying values for all of this ship's attributes.
     *
     * @param health what will be the health of this ship
     * @param length what will be the length of this ship
     * @param cq_health what will be the captain's quarters health of this ship
     * @param place_behavior what will be the placement behavior of this ship
     * @param capquarter what will be the captain's quarters of this ship
     */
    public Ship(int health, int length, int cq_health, PlacementBehavior place_behavior, CaptainsQuarters capquarter) {
        this.health = health;
        this.length = length;
        this.captains_quarters_health = cq_health;
        this.place_behavior = place_behavior;
        this.is_placed = false;
        this.captain_quarter = capquarter;
    }

    /**
     * Gets the health of this ship.
     *
     * @return the current value of health
     * @see #health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Gets the captain's quarters health of this ship.
     *
     * @return the current value of captains_quarters_health
     * @see #captains_quarters_health
     */
    public int getCapQuartersHealth() {
        return this.captains_quarters_health;
    }

    /**
     * Determines whether or not the ship has been placed.
     *
     * @return the current value of is_placed
     * @see #is_placed
     */
    public boolean isShipPlaced() {
        return this.is_placed;
    }

    /**
     * Sets this ship to not placed.
     *
     * @see #is_placed
     */
    public void setShipNotPlaced() {
        this.is_placed = false;
    }

    /**
     * Gets the length of this ship.
     *
     * @return the current value of length
     * @see #length
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Decrease this ship's health by one.
     *
     * @see #health
     */
    public void hit() {
        this.health--;
    }

    /**
     * Sinks this ship, i.e. sets its health to zero.
     *
     * @see #health
     */
    public void sink() {
        this.health = 0;
    }

    /**
     * Checks if this ship has sunk.
     *
     * @return <code>true</code> if this ship's health is equal to zero; <code>false</code> otherwise
     */
    public boolean hasSunk() {
        return this.health == 0;
    }

    /**
     * Decrease this ship's captain's quarters health by one.
     *
     * @see #captains_quarters_health
     */
    public void hitCapQuarters() {
        this.captains_quarters_health--;
    }

    /**
     * Checks if this ship's captains quarters has been destroyed.
     *
     * @return <code>true</code> if this ship's captain's quarters health is equal to zero; <code>false</code> otherwise
     * @see #captains_quarters_health
     */
    public boolean isCapQuartersDestroyed() {
        return this.captains_quarters_health==0;
    }

    /**
     * Prints the name of this ship
     */
    public abstract String printShip();

    /**
     * Checks that placement of the body of the this ship is valid.
     * <p>
     *     This function simply calls isValidBodyPlacement from this ship's placement behavior.
     * </p>
     *
     * @param head the head coordinate for the ship
     * @param tail the tail coordinate for the ship
     * @return <code>true</code> if the placement is valid; <code>false</code> otherwise
     * @see PlacementBehavior#isValidBodyPlacement(int, Coordinate, Coordinate)
     * @see #place_behavior
     */
    public boolean performIsValidPlacement(Coordinate head, Coordinate tail) {
        return this.place_behavior.isValidBodyPlacement(this.length, head, tail);
    }

    /**
     * Checks that this ship can be placed on the given grid between the given head and tail coordinates.
     * <p>
     *     This function simply calls canPlaceShipHere from this ship's placement behavior.
     * </p>
     *
     * @param grid the grid that the ship will be placed on
     * @param head the head coordinate where the ship will be placed
     * @param tail the tail coordinate where the ship will be placed
     * @return <code>true</code> if the ship can be placed between head and tail; <code>false</code> otherwise
     * @see PlacementBehavior#canPlaceShipHere(Grid, Coordinate, Coordinate)
     * @see #place_behavior
     */
    public boolean performCanPlaceShip(Grid grid, Coordinate head, Coordinate tail) {
        return this.place_behavior.canPlaceShipHere(grid, head, tail);
    }

    /**
     * Checks that the layer of the given coordinate is valid for this ship.
     * <p>
     *     This function simply calls isValidLayer from this ship's placement behavior.
     * </p>
     *
     * @param head the head coordinate of this ship
     * @return <code>true</code> if the given layer is in this ship's placement behavior's valid layers;
     *         <code>false</code> otherwise
     * @see PlacementBehavior#isValidLayer(int)
     * @see #place_behavior
     */
    public boolean performIsValidLayer(Coordinate head)
    {
        return this.place_behavior.isValidLayer( head.getLayer() );
    }

    /**
     * Place this ship.
     * <p>
     *     This function simply calls placeShip from this ship's placement behavior.
     * </p>
     *
     * @param grid the grid that this ship will be placed on
     * @param head the head coordinate where this ship will be placed
     * @param tail the tail coordinate where the ship will be placed
     * @see PlacementBehavior#placeShip(Ship, Grid, Coordinate, Coordinate)
     * @see #is_placed
     * @see #place_behavior
     */
    public void performPlacement(Grid grid, Coordinate head, Coordinate tail) {
        this.place_behavior.placeShip(this, grid, head, tail);
        this.is_placed = true;
    }

    /**
     * Checks if the given location on the given grid is the captain's quarter's of this ship.
     * <p>
     *     This function simply calls isCaptainsQuarters from this ship's captains quarters.
     * </p>
     *
     * @param grid the grid that the given coordinate will index
     * @param coord the coordinate that is being checked for captains quarters
     * @return <code>true</code> if the given coordinate is the captain's quarters of this ship; 
     *         <code>false/code> otherwise
     * @see #captain_quarter
     * @see CaptainsQuarters#isCaptainsQuarters(Grid, Coordinate) 
     */
    public boolean checkCaptainsQuarter(Grid grid, Coordinate coord){
        return this.captain_quarter.isCaptainsQuarters(grid, coord);
    }

}
