package edu.colorado.objectgrind.weapons.arsenal;

import edu.colorado.objectgrind.weapons.damage_weapons.HorizontalAirStrike;
import edu.colorado.objectgrind.weapons.utility_weapons.SonarPulse;
import edu.colorado.objectgrind.weapons.damage_weapons.SpaceLaser;
import edu.colorado.objectgrind.weapons.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the tests for the Arsenal class.
 *
 * @see Arsenal
 * @see Weapon
 */
public class ArsenalTest {

    /**
     * The arsenal that is used throughout these tests.
     */
    Arsenal arsenal;

    /**
     * Initializes the arsenal before each test.
     */
    @BeforeEach
    public void setUp() {
        arsenal = new LargeArsenal();
    }

    /**
     * Checks that weapons with no uses are removed from the arsenal successfully.
     */
    @Test
    public void canRemoveWeaponsWithNoUses() {
        decreaseAllUses(arsenal.getWeaponAtIndex(0));
        arsenal.removeWeapons();
        assertEquals(0, arsenal.getNumberOfWeapons());
    }

    /**
     * Checks that weapons are added to the arsenal successfully.
     */
    @Test
    public void canAddWeapons() {
        arsenal.upgradeBombToSpaceLaser();
        arsenal.addNewWeapons(1);
        arsenal.addNewWeapons(2);
        assertEquals(3, arsenal.getNumberOfWeapons());
        assertTrue( arsenal.getWeaponAtIndex(0) instanceof SpaceLaser);
        assertTrue( arsenal.getWeaponAtIndex(1) instanceof SonarPulse);
        assertTrue( arsenal.getWeaponAtIndex(2) instanceof HorizontalAirStrike);
    }

    /**
     * Decreases all uses of the given weapon.
     *
     * @param weapon the weapon to have its uses depleted
     */
    public void decreaseAllUses(Weapon weapon) {
        while(weapon.getNumUses() != 0) {
            weapon.decreaseNumUses();
        }
    }
}
