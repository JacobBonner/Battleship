package edu.colorado.objectgrind.weapons;

import edu.colorado.objectgrind.enums.LocationStatus;
import edu.colorado.objectgrind.game.factories.MediumGamePartsFactory;
import edu.colorado.objectgrind.weapons.factories.MediumWeaponFactory;
import edu.colorado.objectgrind.weapons.factories.WeaponFactory;
import edu.colorado.objectgrind.game.Player;
import edu.colorado.objectgrind.ships.ship_types.Destroyer;
import edu.colorado.objectgrind.ships.ship_types.Ship;
import edu.colorado.objectgrind.ships.ship_types.Submarine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the tests for the SpaceLaser class.
 *
 * @see edu.colorado.objectgrind.weapons.damage_weapons.SpaceLaser
 * @see Ship
 * @see Player
 */
class SpaceLaserTest {

    /**
     * The submarine that the space laser will be used on throughout these tests.
     */
    Ship a_submarine;

    /**
     * The destroyer that the space laser will be used on throughout these tests.
     */
    Ship a_destroyer;

    /**
     * The player that the space laser will be used on throughout these tests.
     */
    Player dummy;

    /**
     * The space laser that will be used throughout these tests.
     */
    Weapon laser_test;

    /**
     * Initializes the submarine, destroyer, player, and space laser before each test.
     */
    @BeforeEach
    void setUp() {
        a_destroyer = new Destroyer();
        a_submarine = new Submarine();
        dummy = new Player( new MediumGamePartsFactory() );
        WeaponFactory factory = new MediumWeaponFactory();
        laser_test = factory.createWeapon("SpaceLaser");
    }

    /**
     * Checks that the space laser can successfully hit a ship on both layers of the grid.
     *
     * @throws Exception if the attack method is invalid
     */
    @Test
    public void canHitBothSurfaceAndUnderWater() throws Exception {

        // Place submarine underwater, and destroyer on the surface
        a_submarine.performPlacement(dummy.getGrid(), dummy.getGrid().stringCoordToIntCoord("04A"),dummy.getGrid().stringCoordToIntCoord("04D"));
        a_destroyer.performPlacement(dummy.getGrid(), dummy.getGrid().stringCoordToIntCoord("14A"),dummy.getGrid().stringCoordToIntCoord("14C") );

        // Use the space laser
        laser_test.useWeapon(dummy,dummy.getGrid().stringCoordToIntCoord("04C"));

        // The status of both above water and underwater where the space laser was used should be HIT
        LocationStatus abovewater_status;
        abovewater_status = dummy.getGrid().getLocationByIndex(1,3,2).getLocationStatus();
        LocationStatus underwater_status;
        underwater_status = dummy.getGrid().getLocationByIndex(0,3,2).getLocationStatus();
        assertEquals(LocationStatus.HIT,abovewater_status);
        assertEquals(LocationStatus.HIT,underwater_status);
    }

}