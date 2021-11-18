package edu.colorado.objectgrind.factories;

import edu.colorado.objectgrind.ships.ShipFactory;
import edu.colorado.objectgrind.ships.ship_types.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the tests for the ShipFactory class.
 *
 * @see ShipFactory
 * @see Ship
 */
public class ShipFactoryTest {

    /**
     * Checks that one of each type of ship can be created.
     */
    @Test
    public void canCreateShipFromFactory() {
        ShipFactory factory = new ShipFactory();

        Ship test_ship = factory.createShip("Minesweeper");
        assertTrue( test_ship instanceof Minesweeper);

        test_ship = factory.createShip("Destroyer");
        assertTrue( test_ship instanceof Destroyer);

        test_ship = factory.createShip("Battleship");
        assertTrue( test_ship instanceof Battleship);

        test_ship = factory.createShip("Submarine");
        assertTrue( test_ship instanceof Submarine);
    }

}
