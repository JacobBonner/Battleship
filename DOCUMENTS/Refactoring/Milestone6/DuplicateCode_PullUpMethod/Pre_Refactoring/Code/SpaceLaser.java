package edu.colorado.objectgrind.Weapons;

import edu.colorado.objectgrind.Grid.Grid;
import edu.colorado.objectgrind.Grid.Location;
import edu.colorado.objectgrind.Player;
import edu.colorado.objectgrind.Ships.Ship;

public class SpaceLaser extends Weapon {

    // Constructor
    public SpaceLaser() {
        super(10*10);
    }

    public void basicAttack(Location attacked_location, Grid attacked_grid, Grid.Coordinate coord, Player attacked_player) {
        // Get the ship that is at this location
        Ship attacked_ship = attacked_location.getShip();

        // If the location is the captain's quarters
        if ( attacked_ship.checkCaptainsQuarter(attacked_grid,coord) ) {

            // Decrease the health of the captain's quarters
            attacked_ship.hitCapQuarters();

            // If the captain's quarters health is zero
            if( attacked_ship.isCapQuartersDestroyed() ) {

                // Indicate that the attack was a HIT
                System.out.println("HIT the Captain's Quarters of the");
                attacked_ship.printShip();

                // Sink the ship
                attacked_ship.sink();

                // Update every location the ship is at to HIT, by scanning the row and col of the attacked location
                for (int j=0; j<attacked_grid.getGrid()[0].length; j++) {

                    Location row_location = attacked_grid.getLocationByIndex(coord.getLayer(), coord.getRow(), j);
                    Location col_location = attacked_grid.getLocationByIndex(coord.getLayer(), j,coord.getColumn());

                    if ( row_location.getShip() == attacked_ship ) row_location.setLocationStatus(Location.LocationStatus.HIT);
                    else if ( col_location.getShip() == attacked_ship ) col_location.setLocationStatus(Location.LocationStatus.HIT);
                }
            }
            else {

                // Inform the user that it is a MISS

                System.out.println("MISS ON LAYER " + coord.getLayer());


                // Update the location status to MISS
                attacked_location.setLocationStatus(Location.LocationStatus.MISS);
            }
        }
        else { // if it is not the captain's quarters

            // Indicate that the attack was a HIT
            System.out.println("HIT ON LAYER " + coord.getLayer());

            // Hit the ship, i.e. decrease its health by one
            attacked_ship.hit();

            // Update the location status to HIT
            attacked_location.setLocationStatus(Location.LocationStatus.HIT);
        }

        // If the ship has sunk
        if ( attacked_ship.hasSunk() ) {

            // Inform the attacker that they sunk a ship, and give the type
            System.out.println("You SUNK your opponent's ");
            attacked_ship.printShip();

            // If all the ships of the attacked player are sunk, then they surrender, and print it to the user
            if( attacked_player.surrender() ) System.out.print("Your opponent has SURRENDERED");
        }
    }

    @Override
    public void useWeapon(Player attacked_player, Grid.Coordinate coord) {

        // For both layers of the grid, implements the same behavior as the Bomb

            // Save the attacked player's grid and the attacked location
            Grid attacked_grid = attacked_player.getGrid();

            Location above_water = attacked_grid.getLocationByIndex(1, coord.getRow(), coord.getColumn());
            Location under_water = attacked_grid.getLocationByIndex(0, coord.getRow(), coord.getColumn());

            Grid.Coordinate awater = new Grid.Coordinate(1, coord.getRow(), coord.getColumn());
            Grid.Coordinate uwater = new Grid.Coordinate(0, coord.getRow(), coord.getColumn());

            if( above_water.hasShip() && under_water.hasShip()) {
                basicAttack(above_water,attacked_grid,awater,attacked_player);
                basicAttack(under_water,attacked_grid,uwater,attacked_player);
            }
            else if(above_water.hasShip()) {

                basicAttack(above_water,attacked_grid,coord,attacked_player);

            }
            else if(under_water.hasShip()) {

                basicAttack(under_water,attacked_grid,coord,attacked_player);

            }
            else { // there is no ship at the location of attack

                // Inform the user that it is a MISS
                System.out.println("MISS");

                // Update the location status to MISS
                above_water.setLocationStatus(Location.LocationStatus.MISS);
                under_water.setLocationStatus(Location.LocationStatus.MISS);
            }

            // Decrease the number of uses for the weapon
            super.decreaseNumUses();
        }
    }

