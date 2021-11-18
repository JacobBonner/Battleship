package edu.colorado.objectgrind.weapons.factories;

import edu.colorado.objectgrind.weapons.Weapon;
import edu.colorado.objectgrind.weapons.damage_weapons.Bomb;
import edu.colorado.objectgrind.weapons.damage_weapons.HorizontalAirStrike;
import edu.colorado.objectgrind.weapons.damage_weapons.SpaceLaser;
import edu.colorado.objectgrind.weapons.damage_weapons.VerticalAirStrike;
import edu.colorado.objectgrind.weapons.utility_weapons.SonarPulse;

/**
 * This class creates the weapons needed for a large arsenal.
 *
 * @see WeaponFactory
 * @see Weapon
 */
public class LargeWeaponFactory implements WeaponFactory {

    /**
     *  Creates a Weapon object for a large game, given the string name of a weapon.
     *
     * @param weapon_name The name of the weapon that will be created
     * @return A <code>Weapon</code> object, depending on the given name; <code>null</code> if name is not valid.
     * @see Weapon
     * @see Bomb
     * @see SpaceLaser
     * @see SonarPulse
     * @see HorizontalAirStrike
     * @see VerticalAirStrike
     */
    public Weapon createWeapon(String weapon_name) {

        Weapon weapon = null;

        switch (weapon_name){
            case "Bomb":
                weapon = new Bomb(13*13);
                break;

            case "SpaceLaser":
                weapon = new SpaceLaser(13*13);
                break;

            case "SonarPulse":
                weapon = new SonarPulse(3);
                break;

            case "HorizontalAirStrike":
                weapon = new HorizontalAirStrike(3);
                break;

            case "VerticalAirStrike":
                weapon = new VerticalAirStrike(3);
                break;
        }

        return weapon;
    }
}
