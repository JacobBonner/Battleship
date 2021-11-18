package edu.colorado.objectgrind.weapons.arsenal;

import edu.colorado.objectgrind.weapons.factories.WeaponFactory;
import edu.colorado.objectgrind.weapons.damage_weapons.Bomb;
import edu.colorado.objectgrind.weapons.Weapon;

import java.util.ArrayList;

public class Arsenal {

    // Private Attributes
    protected ArrayList<Weapon> weapons;
    protected WeaponFactory factory_weapon;

    // Constructor
    public Arsenal(WeaponFactory factory) {
        this.weapons =  new ArrayList<Weapon>();
        this.factory_weapon = factory;
        this.weapons.add( this.factory_weapon.createWeapon("Bomb") );
    }

    // Gets the weapon at a given index
    public Weapon getWeaponAtIndex(int index) {
        return this.weapons.get(index);
    }

    // Returns the number of weapons in the arsenal
    public int getNumberOfWeapons() {
        return this.weapons.size();
    }

    // Add the given weapon if the number of ships sunk is met
    public void addWeapon(String weapon_name, int num_sunk) {
        Weapon weapon = this.factory_weapon.createWeapon(weapon_name);
        if ( weapon.getSinksNeeded() == num_sunk ) this.weapons.add(weapon);
    }

    // Removes weapons when they have no uses left
    public void removeWeapons() {

        // Array of indices to remove from the weapons
        ArrayList<Integer> to_remove = new ArrayList<Integer>();

        // Determine which weapons need to be removed
        for(int i = 0; i<this.weapons.size(); i++) {
            if ( this.weapons.get(i).getNumUses() == 0 ) to_remove.add(i);
        }

        // Remove the weapons
        for( int index : to_remove) {
            this.weapons.remove(index);
        }
    }

    // Upgrades the Bomb to SpaceLaser
    public void upgradeBombToSpaceLaser() {
        this.weapons.set(0, this.factory_weapon.createWeapon("SpaceLaser"));
    }

    // Adds all of the weapons for the given arsenal
    protected void addNewWeapons(int num_sunk) {

        addWeapon( "SonarPulse", num_sunk);
        addWeapon( "HorizontalAirStrike", num_sunk);
    }

    // Adjusts the arsenal by adding weapons and removing weapons
    public void adjustArsenal(int num_sunk_total, int num_sunk_this_turn) {

        // Add weapons if the number of ships sunk this turn is not equal to zero
        if( num_sunk_this_turn != 0 ) {
            System.out.print(num_sunk_this_turn);
            System.out.print(num_sunk_total);
            if( this.weapons.get(0) instanceof Bomb ) upgradeBombToSpaceLaser();
            addNewWeapons(num_sunk_total);
        }

        // Remove weapons
        removeWeapons();
    }
}
