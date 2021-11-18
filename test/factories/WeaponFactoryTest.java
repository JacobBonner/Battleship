package edu.colorado.objectgrind.factories;

import edu.colorado.objectgrind.enums.Size;
import edu.colorado.objectgrind.weapons.factories.*;
import edu.colorado.objectgrind.weapons.Weapon;
import edu.colorado.objectgrind.weapons.damage_weapons.Bomb;
import edu.colorado.objectgrind.weapons.damage_weapons.HorizontalAirStrike;
import edu.colorado.objectgrind.weapons.damage_weapons.SpaceLaser;
import edu.colorado.objectgrind.weapons.damage_weapons.VerticalAirStrike;
import edu.colorado.objectgrind.weapons.utility_weapons.SonarPulse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the tests for the WeaponFactory class.
 *
 * @see WeaponFactory
 * @see Weapon
 */
public class WeaponFactoryTest {

    /**
     * Checks that one of each weapon type can be created from a each type of weapon factory.
     */
    @Test
    public void canCreateWeaponFromEachWeaponFactory() {

        // Array of all weapon names
        String[] weapon_names = new String[] {"Bomb", "SpaceLaser", "SonarPulse", "HorizontalAirStrike", "VerticalAirStrike"};

        WeaponFactory factory = null;
        int [] num_uses = null;

        // For every size
        for ( Size size : Size.values() ) {

            switch (size) {
                case SMALL:
                    num_uses = new int[] {7*7, 7*7, 1, 1, 1};
                    factory = new SmallWeaponFactory();
                    break;

                case MEDIUM:
                    num_uses = new int[] {10*10, 10*10, 2, 1, 1};
                    factory = new MediumWeaponFactory();
                    break;

                case LARGE:
                    num_uses = new int[] {13*13, 13*13, 3, 1, 1};
                    factory = new LargeWeaponFactory();
                    break;
            }

            // Create one of each weapon and assert the number of uses
            for ( int i=0; i< weapon_names.length; i++) {
                Weapon test_weap = factory.createWeapon(weapon_names[i]);
                assertEquals( num_uses[i], test_weap.getNumUses());

                switch (i) {
                    case 0:
                        assertTrue( test_weap instanceof Bomb);
                        break;

                    case 1:
                        assertTrue( test_weap instanceof SpaceLaser);
                        break;

                    case 2:
                        assertTrue( test_weap instanceof SonarPulse);
                        break;

                    case 3:
                        assertTrue( test_weap instanceof HorizontalAirStrike);
                        break;

                    case 4:
                        assertTrue( test_weap instanceof VerticalAirStrike);
                        break;
                }
            }
        }
    }
}
