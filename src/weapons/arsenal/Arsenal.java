package edu.colorado.objectgrind.weapons.arsenal;

import edu.colorado.objectgrind.weapons.factories.WeaponFactory;
import edu.colorado.objectgrind.weapons.damage_weapons.Bomb;
import edu.colorado.objectgrind.weapons.Weapon;

import java.util.ArrayList;

/**
 * This class defines the arsenal of weapons that a player will have in a game.
 *
 * @see Weapon
 * @see WeaponFactory
 */
public class Arsenal {

    /**
     * The list of weapons that this arsenal contains.
     */
    private final ArrayList<Weapon> weapons;

    /**
     * A factory that creates the weapons for this arsenal.
     */
    private final WeaponFactory factory_weapon;

    /**
     * Class constructor specifying the factory that it will take to create its weapons.
     *
     * @param factory the factory that this ship will use to create ships
     * @see WeaponFactory#createWeapon(String)
     */
    public Arsenal(WeaponFactory factory) {
        this.weapons = new ArrayList<>();
        this.factory_weapon = factory;
        this.weapons.add( this.factory_weapon.createWeapon("Bomb") );
    }

    /**
     * Gets the weapon from this arsenal at the given index.
     *
     * @param index the index of the weapon to return
     * @return the weapon at the given index
     */
    public Weapon getWeaponAtIndex(int index) {
        return this.weapons.get(index);
    }

    /**
     * Gets the number of weapons in this arsenal.
     *
     * @return the size of this arsenal's weapons list
     */
    public int getNumberOfWeapons() {
        return this.weapons.size();
    }

    /**
     * Adds the given weapon to this arsenal, if the number of ships sunk is met.
     *
     * @param weapon_name the name of the weapon to be added
     * @param num_sunk the number of ships a player has sunk
     * @see WeaponFactory#createWeapon(String)
     * @see Weapon#getSinksNeeded()
     */
    private void addWeapon(String weapon_name, int num_sunk) {
        Weapon weapon = this.factory_weapon.createWeapon(weapon_name);
        if ( weapon.getSinksNeeded() == num_sunk ) this.weapons.add(weapon);
    }

    /**
     * Removes weapons from this arsenal when they have no uses left.
     * 
     * @see Weapon#getNumUses()
     */
    public void removeWeapons() {

        // Array of indices to remove from the weapons
        ArrayList<Integer> to_remove = new ArrayList<>();

        // Determine which weapons need to be removed
        for(int i = 0; i<this.weapons.size(); i++) {
            if ( this.weapons.get(i).getNumUses() == 0 ) to_remove.add(i);
        }

        // Remove the weapons
        for( int index : to_remove) {
            this.weapons.remove(index);
        }
    }

    /**
     * Upgrades the Bomb in this arsenal to a SpaceLaser.
     * @see Bomb
     * @see edu.colorado.objectgrind.weapons.damage_weapons.SpaceLaser
     * @see WeaponFactory#createWeapon(String)
     */
    public void upgradeBombToSpaceLaser() {
        this.weapons.set(0, this.factory_weapon.createWeapon("SpaceLaser"));
    }

    /**
     * Adds new weapons to this arsenal, determined by a list of weapon names inside the function.
     *
     * @param num_sunk the number of ships a player has sunk
     * @see #addWeapon(String, int)
     */
    public void addNewWeapons(int num_sunk) {

        // Names of weapons to add
        String[] weapon_names = new String[] {"SonarPulse", "HorizontalAirStrike"};

        // Add each weapon from the array of names
        for ( String weapon_name : weapon_names ) {
            this.addWeapon(weapon_name, num_sunk);
        }
    }

    /**
     * Adjusts this arsenal by adding weapons and removing weapons.
     *
     * @param num_sunk_total the total number of ships a player has sunk
     * @param num_sunk_this_turn the number of ships a player has sunk on a given turn
     * @see #upgradeBombToSpaceLaser()
     * @see #addNewWeapons(int)
     * @see #removeWeapons()
     */
    public void adjustArsenal(int num_sunk_total, int num_sunk_this_turn) {

        // Add weapons if the number of ships sunk this turn is not equal to zero
        if( num_sunk_this_turn != 0 ) {

            // Upgrade Bomb to SpaceLaser
            if( this.weapons.get(0) instanceof Bomb ) upgradeBombToSpaceLaser();

            // Add all other weapons
            addNewWeapons(num_sunk_total);
        }

        // Remove weapons
        removeWeapons();
    }
}
