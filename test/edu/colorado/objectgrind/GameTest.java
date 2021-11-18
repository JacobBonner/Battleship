package edu.colorado.objectgrind;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.enums.LocationStatus;
import edu.colorado.objectgrind.enums.Size;
import edu.colorado.objectgrind.game.factories.GameFactory;
import edu.colorado.objectgrind.game.Game;
import edu.colorado.objectgrind.game.factories.GamePartsFactory;
import edu.colorado.objectgrind.game.Player;
import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.ships.ship_types.Ship;
import edu.colorado.objectgrind.weapons.damage_weapons.Bomb;
import edu.colorado.objectgrind.weapons.damage_weapons.HorizontalAirStrike;
import edu.colorado.objectgrind.weapons.utility_weapons.SonarPulse;
import edu.colorado.objectgrind.weapons.damage_weapons.SpaceLaser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the tests for the Game class.
 *
 * @see Game
 * @see Player
 * @see GameFactory
 * @see GamePartsFactory
 * @see Grid
 * @see edu.colorado.objectgrind.ships.ship_types.Ship
 * @see edu.colorado.objectgrind.weapons.Weapon
 */
public class GameTest {

    /**
     * This test simulates game play between the two players.
     * <p>
     *     It checks that that an entire game from start to finish - ship placement to surrender - can be completed
     *     successfully.
     * </p>
     */
    @Test
    public void playGame() throws Exception {

        // Create Game
        GameFactory factory_game = new GameFactory();
        Game test_game = factory_game.createGame(Size.MEDIUM);
        Player player_1 = test_game.getPlayer_1();
        Player player_2 = test_game.getPlayer_2();
        Grid player_1_grid = player_1.getGrid();
        Grid player_2_grid = player_2.getGrid();


        // Place all of the ships for player 1

        //place battleship
        player_1.playerPlaceShip(0, "11A","14A");

        // place Destroyer
        player_1.playerPlaceShip(1, "110B","110D");

            // Try to move fleet before all ships have been placed, but no command is created
            player_1.playerMoveFleet(Direction.NORTH);
            assertEquals(2,player_1.getCommands().size());

        // place minesweeper
        player_1.playerPlaceShip(2, "16B","16C");

        // place submarine
        player_1.playerPlaceShip(3, "14F","14I");


        // Place all of the ships for player 2

        //place battleship2
        player_2.playerPlaceShip(0, "14C","14F");

        // place Destroyer2
        player_2.playerPlaceShip(1, "16F","18F");

        // place minesweeper2
        player_2.playerPlaceShip(2, "13I","14I");

        // place submarine2
        player_2.playerPlaceShip(3, "02C","02F");


        // PRE GAME

            // Both players should only have Bomb in their arsenal (weapons array)
            assertEquals(1,player_1.getSizeOfArsenal());
            assertEquals(1,player_2.getSizeOfArsenal());
            assertTrue(player_1.getWeaponAtIndex(0) instanceof Bomb);
            assertTrue(player_2.getWeaponAtIndex(0) instanceof Bomb);

        // ROUND 1

            // player 1 attacks an empty location with missile, causing a MISS
            test_game.player1TakeTurn(0, "19A");
            assertEquals(LocationStatus.MISS, test_game.getPlayer_2().getGrid().getLocationByIndex(1,8,0).getLocationStatus());

            // player 2 attacks an empty location with missile, causing a MISS
            test_game.player2TakeTurn(0, "18H");
            assertEquals(LocationStatus.MISS, player_1_grid.getLocationByIndex(1,7,7).getLocationStatus());


        // ROUND 2

            // player 1 attacks battleship2 with missile
            int battleship2_health;
            test_game.player1TakeTurn(0, "14D");
            battleship2_health = player_2.getShipAtIndex(0).getHealth();
            assertEquals(3,battleship2_health);
            assertEquals(LocationStatus.HIT, player_2_grid.getLocationByIndex(1,3,3).getLocationStatus());

            // player 2 attacks battleship1 with missile
            int battleship1_health;
            test_game.player2TakeTurn(0, "11A");
            battleship1_health = player_1.getShipAtIndex(0).getHealth();
            assertEquals(3,battleship1_health);
            assertEquals(LocationStatus.HIT, player_1_grid.getLocationByIndex(1,0,0).getLocationStatus());


        // ROUND 3

            // player 1 attacks battleship2 captain quarters with missile, but counts as MISS
            test_game.player1TakeTurn(0, "14E");
            int battleship2_cap_quarter_health = player_2.getShipAtIndex(0).getCapQuartersHealth();
            assertEquals(1,battleship2_cap_quarter_health);
            assertFalse(player_2.getShipAtIndex(0).isCapQuartersDestroyed());
            assertEquals(LocationStatus.MISS, player_2_grid.getLocationByIndex(1,3,4).getLocationStatus());

            // player 2 attacks battleship1 captain quarters with missile, but counts as MISS
            test_game.player2TakeTurn(0, "13A");
            int battleship1_cap_quarter_health = player_1.getShipAtIndex(0).getCapQuartersHealth();
            assertEquals(1,battleship1_cap_quarter_health);
            assertFalse(player_1.getShipAtIndex(0).isCapQuartersDestroyed());
            assertEquals(LocationStatus.MISS, player_1_grid.getLocationByIndex(1, 2,0).getLocationStatus());


        // ROUND 4

            // player 1 attacks battleship2 captain quarters with missile, and sinks it
            test_game.player1TakeTurn(0, "14E");
            battleship2_cap_quarter_health = player_2.getShipAtIndex(0).getCapQuartersHealth();
            assertEquals(0,battleship2_cap_quarter_health);
            assertTrue(player_2.getShipAtIndex(0).isCapQuartersDestroyed());
            assertEquals(LocationStatus.HIT, player_2_grid.getLocationByIndex(1,3,4).getLocationStatus());
            assertTrue(player_2.getShipAtIndex(0).hasSunk());

                // Bomb should be replaced with SpaceLaser, and SonarPulse should be added
                assertTrue(player_1.getWeaponAtIndex(0) instanceof SpaceLaser);
                assertTrue(player_1.getWeaponAtIndex(1) instanceof SonarPulse);
                assertTrue(player_1.getWeaponAtIndex(2) instanceof HorizontalAirStrike);
                assertEquals(3, player_1.getSizeOfArsenal());

            // player 2 attacks battleship1 captain quarters with missile, and sinks it
            test_game.player2TakeTurn(0, "13A");
            battleship1_cap_quarter_health = player_1.getShipAtIndex(0).getCapQuartersHealth();
            assertEquals(0,battleship1_cap_quarter_health);
            assertTrue(player_2.getShipAtIndex(0).isCapQuartersDestroyed());
            assertEquals(LocationStatus.HIT, player_1_grid.getLocationByIndex(1,2,0).getLocationStatus());
            assertTrue(player_1.getShipAtIndex(0).hasSunk());

                // Bomb should be replaced with SpaceLaser, adn SonarPulse should be added
                assertTrue(player_2.getWeaponAtIndex(0) instanceof SpaceLaser);
                assertTrue(player_2.getWeaponAtIndex(1) instanceof SonarPulse);
                assertTrue(player_2.getWeaponAtIndex(2) instanceof HorizontalAirStrike);
                assertEquals(3, player_2.getSizeOfArsenal());

        // ROUND 5

            // player 1 uses sonar pulse
            test_game.player1TakeTurn(1, "18C");
            int row = 7;
            int col = 2;
            int layer = 1;
            int offset;
            LocationStatus status;
            for(int i=row-2; i < row+3; i++){ // check the location status in sonar pulse
                if(i == row-2 | i == row + 2) offset = 0;
                else if(i == row-1 | i == row+1) offset = 1;
                else offset = 2;
                for(int j=col-offset; j < col+offset+1; j++){
                    if ( player_2_grid.isCoordinateInBounds(new Coordinate(layer,i,j)) ) {
                        status = player_2_grid.getLocationByIndex(1, i, j).getLocationStatus(); // check status
                        assertEquals(LocationStatus.FREE, status, "The location is free.");
                    }
                }
            }

            // player 2 attacks destroyer1 captain's quarters, causing MISS
            int destroyer1_cap_quarter_health;
            test_game.player2TakeTurn(0, "110C");
            destroyer1_cap_quarter_health = player_1.getShipAtIndex(1).getCapQuartersHealth();
            assertEquals(1,destroyer1_cap_quarter_health);
            assertFalse(player_1.getShipAtIndex(1).isCapQuartersDestroyed());
            assertEquals(LocationStatus.MISS, player_1_grid.getLocationByIndex(1, 9,2).getLocationStatus());
            assertFalse(player_1.getShipAtIndex(1).hasSunk());


        // ROUND 6

            // player 1 uses sonar pulse
            test_game.player1TakeTurn(1, "17H");
            row = 6;
            col = 7;
            for(int i=row-2; i < row+3; i++){ // check the location status in sonar pulse
                if(i == row-2 | i == row + 2) offset = 0;
                else if(i == row-1 | i == row+1) offset = 1;
                else offset = 2;
                for(int j=col-offset; j < col+offset+1; j++){
                    if ( player_2_grid.isCoordinateInBounds(new Coordinate(layer,i,j)) ) {
                        status = player_2_grid.getLocationByIndex(1, i, j).getLocationStatus(); // check status
                        if ( (i==6) & (j==5) ) assertEquals(LocationStatus.OCCUPIED, status, "The location is occupied.") ;
                        else assertEquals(LocationStatus.FREE, status, "The location is free.");
                    }
                }
            }
                // SonarPulse is out of uses so it should be deleted
                assertTrue(player_1.getWeaponAtIndex(0) instanceof SpaceLaser);
                assertTrue(player_1.getWeaponAtIndex(1) instanceof HorizontalAirStrike);
                assertEquals(2, player_1.getSizeOfArsenal());

            // player 2 attacks destroyer1 captain's quarters, sinking it
            test_game.player2TakeTurn(0, "110C");
            destroyer1_cap_quarter_health = player_1.getShipAtIndex(1).getCapQuartersHealth();
            assertEquals(0,destroyer1_cap_quarter_health);
            assertTrue(player_1.getShipAtIndex(1).isCapQuartersDestroyed());
            assertEquals(LocationStatus.HIT, player_1_grid.getLocationByIndex(1, 9,2).getLocationStatus());
            assertTrue(player_1.getShipAtIndex(1).hasSunk());


        // ROUND 7

            // player 1 attacks but misses
            test_game.player1TakeTurn(0, "110J");
            assertEquals(LocationStatus.MISS, player_2_grid.getLocationByIndex(1,9,9).getLocationStatus());

            // player 2 attacks minesweeper1 captain's quarters, sinking it
            int minesweeper1_cap_quarter_health;
            test_game.player2TakeTurn(0, "16B");
            minesweeper1_cap_quarter_health = player_1.getShipAtIndex(2).getCapQuartersHealth();
            assertEquals(0,minesweeper1_cap_quarter_health);
            assertTrue(player_1.getShipAtIndex(2).isCapQuartersDestroyed());
            assertEquals(LocationStatus.HIT, player_1_grid.getLocationByIndex(1, 5,1).getLocationStatus());
            assertTrue(player_1.getShipAtIndex(2).hasSunk());


        // Round 8

            // player 1 attacks but misses
            test_game.player1TakeTurn(0, "12D");
            assertEquals(LocationStatus.MISS, player_2_grid.getLocationByIndex(1,1,3).getLocationStatus());

            // player 2 uses Horizontal Air strike, sinking player1's submarine
            test_game.player2TakeTurn(2, "14G");
            Ship p1_sub = player_1.getShipAtIndex(3);
            assertEquals(0, p1_sub.getHealth());
            assertTrue(p1_sub.hasSunk());

            // Player 2 air strike should be gone
            assertTrue(player_2.getWeaponAtIndex(0) instanceof SpaceLaser);
            assertTrue(player_2.getWeaponAtIndex(1) instanceof SonarPulse);
            assertEquals(2, player_1.getSizeOfArsenal());

            // Player 2 has now sunk all of player 1's ships, thus player 1 must surrender.

                // check player2 surrenders
                boolean check2_surrender;
                check2_surrender = player_2.surrender();
                assertFalse(check2_surrender);

                // check player1 surrenders
                boolean check1_surrender;
                check1_surrender = player_1.surrender();
                assertTrue(check1_surrender);
    }

}

