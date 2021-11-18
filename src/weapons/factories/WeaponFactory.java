package edu.colorado.objectgrind.weapons.factories;

import edu.colorado.objectgrind.weapons.Weapon;

/**
 * This interface defines the function that creates Weapon objects given a name.
 *
 * @see Weapon
 */
public interface WeaponFactory {

    /**
     * Creates a Weapon object given a string weapon name.
     *
     * @param weapon_name The name of the weapon that will be created
     * @return <code>Weapon</code> if the weapon name is valid;
     *         <code>null</code> otherwise.
     */
    Weapon createWeapon(String weapon_name);
}
