package edu.colorado.objectgrind.weapons.factories;

import edu.colorado.objectgrind.weapons.Weapon;
import edu.colorado.objectgrind.weapons.damage_weapons.Bomb;
import edu.colorado.objectgrind.weapons.damage_weapons.HorizontalAirStrike;
import edu.colorado.objectgrind.weapons.damage_weapons.SpaceLaser;
import edu.colorado.objectgrind.weapons.damage_weapons.VerticalAirStrike;
import edu.colorado.objectgrind.weapons.utility_weapons.SonarPulse;

/**
 * This class creates the weapons needed for a medium arsenal.
 *
 * @see WeaponFactory
 * @see Weapon
 */
public class MediumWeaponFactory implements WeaponFactory {

    /**
     *  Creates a Weapon object for a medium game, given the string name of a weapon.
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
                weapon = new Bomb(10*10);
                break;

            case "SpaceLaser":
                weapon = new SpaceLaser(10*10);
                break;

            case "SonarPulse":
                weapon = new SonarPulse(2);
                break;

            case "HorizontalAirStrike":
                weapon = new HorizontalAirStrike(2);
                break;

            case "VerticalAirStrike":
                weapon = new VerticalAirStrike(2);
                break;
        }

        return weapon;
    }
}
