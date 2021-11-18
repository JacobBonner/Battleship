package edu.colorado.objectgrind.Weapons;

import edu.colorado.objectgrind.Grid.Grid;
import edu.colorado.objectgrind.Grid.Location;
import edu.colorado.objectgrind.Player;

public class SonarPulse extends Weapon {

    public SonarPulse() {
        super(2);
    }

    @Override
    public void useWeapon(Player attacked_player, Grid.Coordinate coord) {

        // Save the attacked player's grid
        Grid attacked_grid = attacked_player.getGrid();

        // Save the row and column of the attacked coordinate
        int row = coord.getRow();
        int col = coord.getColumn();
        int layer = coord.getLayer();

        // Centered on the given coordinate, iterate over the sonar's range and update locations accordingly
        for (int i=row-2; i<row+2+1; i++) { // rows

            // Determine the offset on both sides of the column for the given row
            int offset = -1;
            if ( (i == row-2) | (i == row+2) ) offset = 0;
            else if ( (i == row-1) | (i == row+1) ) offset = 1;
            else if ( i == row ) offset = 2;

            for (int j=col-offset; j<col+offset+1; j++) { // columns

                // If the location is in bounds
                if ( attacked_grid.isCoordinateInBounds(new Grid.Coordinate(layer,i,j)) ) {

                    // Get the location at the current position
                    Location curr_location = attacked_grid.getLocationByIndex(1, i, j);

                    // Set the status according to whether or not there is a ship at the current location
                    if ( curr_location.hasShip() ) curr_location.setLocationStatus(Location.LocationStatus.OCCUPIED);
                    else curr_location.setLocationStatus(Location.LocationStatus.FREE);
                }
            }
        }

        // Decrease the number of uses for the weapon
        super.decreaseNumUses();
    }
}
