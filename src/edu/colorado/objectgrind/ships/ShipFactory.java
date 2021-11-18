package edu.colorado.objectgrind.ships;

import edu.colorado.objectgrind.ships.ship_types.*;

/**
 * This class creates Ship objects given a ship name.
 *
 * @see Ship
 */
public class ShipFactory {

    /**
     * Creates a Ship object given a string ship name.
     *
     * @param ship_name The name of the ship that will be created.
     * @return A <code>Ship</code> object, depending on the given name; <code>null</code> if name is not valid.
     */
    public Ship createShip(String ship_name){

        Ship ship = null;

        switch (ship_name){
            case "Battleship":
                ship = new Battleship();
                break;

            case "Destroyer":
                ship = new Destroyer();
                break;

            case "Minesweeper":
                ship = new Minesweeper();
                break;

            case "Submarine":
                ship = new Submarine();
                break;
        }

        return ship;
    }
}
