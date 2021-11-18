package edu.colorado.objectgrind.ships;

import edu.colorado.objectgrind.ships.ship_types.Battleship;
import edu.colorado.objectgrind.ships.ship_types.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class defines the tests for the Ship class.
 *
 * @see Ship
 */
public class ShipTest {

    /**
     * A ship that is used throughout the tests in this class.
     */
    private Ship my_battleship;

    /**
     * Creates a new Battleship before each test.
     */
    @BeforeEach
    public void setUp() {
        my_battleship = new Battleship();
    }

    /**
     * Checks that the printShip function gives the correct output.
     */
    @Test
    public void canPrintShip() {
        assertEquals("Battleship", my_battleship.printShip());
    }

    /**
     * Checks that the hit function decreases a ships health by 1.
     */
    @Test
    public void canHitShip() {
        my_battleship.hit();
        int health = my_battleship.getHealth();
        assertEquals(3, health);
    }

    /**
     * Checks that the hitCapQuarters function decreases a ships captain quarters health by 1.
     */
    @Test
    public void canHitCapQuarters() {
        my_battleship.hitCapQuarters();
        int cq_health = my_battleship.getCapQuartersHealth();
        assertEquals(1,cq_health);
    }

    /**
     * Checks that captains quarters health of a ship can be brought to zero by repeated hits.
     */
    @Test
    public void canDestroyCapQuarters() {
        int init_health = my_battleship.getCapQuartersHealth();
        for(int i=0; i<init_health; i++) my_battleship.hitCapQuarters();
        boolean cq_destroyed = my_battleship.isCapQuartersDestroyed();
        assertTrue(cq_destroyed);
    }

    /**
     * Checks that the sink method sets a ships has_sunk attribute to true.
     */
    @Test
    public void canSinkShip() {
        my_battleship.sink();
        boolean sunk = my_battleship.hasSunk();
        assertTrue(sunk);
    }
}
