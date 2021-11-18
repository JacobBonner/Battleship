package edu.colorado.objectgrind;

import edu.colorado.objectgrind.gui.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the test of the overall game.
 *
 * @see BattleshipGame
 * @see ControlWindow
 * @see GameSetting
 * @see SetupPlayerFleetWindow
 */
public class GUITest {
    /**
     * The GameSetting that is used throughout the tests.
     */
    private GameSetting battleshipGame;

    /**
     * Initialize the game setting, game size, and players setup before each test.
     */
    @BeforeEach
    public void setUp(){
        battleshipGame = new GameSetting();
        battleshipGame.selectGameSize("M");
        battleshipGame.setUpPlayer("Player 1", "Player 1 Opponent");
        battleshipGame.setUpPlayer("Player 2", "Player 2 Opponent");
    }

    /**
     * Check whether the user can select the game size.
     */
    @Test
    public void canSelectGameSize(){
        BattleshipGame frame = new BattleshipGame();
        battleshipGame.selectGameSize("S");
        JButton button;
        button = frame.getButton(0);
        button.doClick();
        assertEquals(7, battleshipGame.getSize());

        battleshipGame.selectGameSize("L");
        button = frame.getButton(2);
        button.doClick();
        assertEquals(13, battleshipGame.getSize());

        BattleshipGame.main(null);
    }

    /**
     * Check if player 1 can place the ship.
     */
    @Test
    public void canPlacedPlayer1Ships(){
        SetupPlayerFleetWindow frame = new SetupPlayerFleetWindow(battleshipGame, 0, "Player 1");

        // Can place ship
        JTextField text;
        text = frame.getTextField(0);
        assertNotNull(text);
        text.setText("12A,12D");
        text.postActionEvent();
        assertEquals("12A,12D", text.getText());

        // Head and tail coordinates does not feet the length of the ship
        text = frame.getTextField(1);
        assertNotNull(text);
        text.setText("13B,13C");
        text.postActionEvent();
        assertEquals("", text.getText());

        // Overlapping the ship
        text = frame.getTextField(2);
        assertNotNull(text);
        text.setText("12A,13A");
        text.postActionEvent();
        assertEquals("", text.getText());

        // Submarine can be placed
        text = frame.getTextField(3);
        assertNotNull(text);
        text.setText("03B,03E");
        text.postActionEvent();
        assertEquals("03B,03E", text.getText());

        // Submarine can be placed under the ships
        text = frame.getTextField(3);
        assertNotNull(text);
        text.setText("02A,05A");
        text.postActionEvent();
        assertEquals("02A,05A", text.getText());

        // Incorrect Coordinates
        text = frame.getTextField(1);
        assertNotNull(text);
        text.setText("13E");
        text.postActionEvent();
        assertEquals("", text.getText());
    }

    /**
     * Check the player 1 fleet is placed.
     */
    @Test
    public void canPlacedAllShipsOnGridForPlayer1(){
        SetupPlayerFleetWindow frame = new SetupPlayerFleetWindow(battleshipGame, 0, "Player 1");
        JButton button;
        JTextField text1;

        // All ships are not placed on the grid
        button = frame.getButton();
        button.doClick();
        assertFalse(frame.fleetPlaced());

        // All ships are placed on the grid
        // Battleship
        text1 = frame.getTextField(0);
        text1.setText("12A,12D");
        text1.postActionEvent();
        // Destroyer
        text1 = frame.getTextField(1);
        text1.setText("14C,16C");
        text1.postActionEvent();
        // Minesweeper
        text1 = frame.getTextField(2);
        text1.setText("17C,17D");
        text1.postActionEvent();
        // Submarine
        text1 = frame.getTextField(3);
        text1.setText("02A,02D");
        text1.postActionEvent();

        button = frame.getButton();
        button.doClick();
        assertTrue(frame.fleetPlaced());
    }

    /**
     * Check if player 2 can place the ship.
     */
    @Test
    public void canPlacedPlayer2Ships(){
        SetupPlayerFleetWindow frame = new SetupPlayerFleetWindow(battleshipGame, 1, "Player 2");

        // Can place ship
        JTextField text;
        text = frame.getTextField(0);
        assertNotNull(text);
        text.setText("12A,12D");
        text.postActionEvent();
        assertEquals("12A,12D", text.getText());

        // Head and tail coordinates does not feet the length of the ship
        text = frame.getTextField(1);
        assertNotNull(text);
        text.setText("13B,13C");
        text.postActionEvent();
        assertEquals("", text.getText());

        // Overlapping the ship
        text = frame.getTextField(2);
        assertNotNull(text);
        text.setText("12A,13A");
        text.postActionEvent();
        assertEquals("", text.getText());

        // Submarine can be placed
        text = frame.getTextField(3);
        assertNotNull(text);
        text.setText("03B,03E");
        text.postActionEvent();
        assertEquals("03B,03E", text.getText());

        // Submarine can be placed under the ships
        text = frame.getTextField(3);
        assertNotNull(text);
        text.setText("02A,05A");
        text.postActionEvent();
        assertEquals("02A,05A", text.getText());

        // Incorrect Coordinates
        text = frame.getTextField(1);
        assertNotNull(text);
        text.setText("13E");
        text.postActionEvent();
        assertEquals("", text.getText());
    }

    /**
     * Check the player 2 fleet is placed.
     */
    @Test void canPlacedAllShipsOnGridForPlayer2(){
        SetupPlayerFleetWindow frame = new SetupPlayerFleetWindow(battleshipGame, 1, "Player 2");
        JButton button;
        JTextField text1;

        // All ships are not placed on the grid
        button = frame.getButton();
        button.doClick();
        assertFalse(frame.fleetPlaced());

        // All ships are placed on the grid
        // Battleship
        text1 = frame.getTextField(0);
        text1.setText("12A,12D");
        text1.postActionEvent();
        // Destroyer
        text1 = frame.getTextField(1);
        text1.setText("14C,16C");
        text1.postActionEvent();
        // Minesweeper
        text1 = frame.getTextField(2);
        text1.setText("17C,17D");
        text1.postActionEvent();
        // Submarine
        text1 = frame.getTextField(3);
        text1.setText("02A,02D");
        text1.postActionEvent();

        button = frame.getButton();
        button.doClick();
        assertTrue(frame.fleetPlaced());
    }

    /**
     * Check the user can enter weapon index to textfield.
     */
    @Test void canEnterWeaponIndex(){
        ControlWindow frame = new ControlWindow(battleshipGame);
        JTextField text;

        // Can use weapon
        text = frame.getTextWeaponIndex();
        text.setText("0");
        text.postActionEvent();
        assertEquals("0", text.getText());
    }

    /**
     * Check the user can enter coordinate to textfield.
     */
    @Test
    public void canEnterCoordinate(){
        ControlWindow frame = new ControlWindow(battleshipGame);
        JTextField text;

        // Can set coordinate
        text = frame.getTextCoordinate();
        text.setText("12A");
        text.postActionEvent();
        assertEquals("12A", text.getText());
    }

    /**
     * Check the error handle for invalid Weapon index.
     */
    @Test
    public void haveInvalidWeaponIndex(){
        ControlWindow frame = new ControlWindow(battleshipGame);
        JTextField text;

        // Select out of bound weapon index
        text = frame.getTextWeaponIndex();
        text.setText("2");
        text.postActionEvent();
        frame.getButton().doClick();
        assertEquals("", text.getText());

        // Select out of bound weapon index
        text = frame.getTextWeaponIndex();
        text.setText("AE2");
        text.postActionEvent();
        frame.getButton().doClick();
        assertEquals("", text.getText());
    }

    /**
     * Check the error handle for invalid Coordinate index.
     */
    @Test
    public void haveInvalidCoordinate(){
        ControlWindow frame = new ControlWindow(battleshipGame);
        JTextField text;

        // Out of bound coordinate
        text = frame.getTextCoordinate();
        text.setText("10A");
        text.postActionEvent();
        frame.getButton().doClick();
        assertEquals("", text.getText());

        // Incorrect coordinate format
        text = frame.getTextCoordinate();
        text.setText("10A123");
        text.postActionEvent();
        frame.getButton().doClick();
        assertEquals("", text.getText());
    }

    /**
     * Check the grid after the user uses Sonar Pulse.
     */
    @Test
    public void canUseSonarPulse(){
        battleshipGame.placeShips(0, 2, "15C", "15D");
        battleshipGame.placeShips(1, 2, "11C", "11D");
        ControlWindow frame = new ControlWindow(battleshipGame);
        JTextField text1, text2;
        JButton button;

        // Player 1 turns
        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("11C");
        text2.postActionEvent();
        button = frame.getButton();
        button.doClick();
        assertEquals("X", battleshipGame.getOpponentButtons(0)[0][2].getText());

        // Player 2 turns
        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("14F");
        text2.postActionEvent();
        button = frame.getButton();
        button.doClick();
        assertEquals("O", battleshipGame.getOpponentButtons(1)[3][5].getText());

        // Player 1 turns use Sonar Pulse
        text1 = frame.getTextWeaponIndex();
        text1.setText("1");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("13C");
        text2.postActionEvent();
        button = frame.getButton();
        button.doClick();
        assertEquals("blue", battleshipGame.getOpponentButtons(0)[2][2].getName());
        assertEquals("red", battleshipGame.getOpponentButtons(0)[0][2].getName());
    }

    /**
     * Check the user can attack opponent on Grid.
     */
    @Test
    public void canAttackOpponentOnGrid(){
        ControlWindow frame = new ControlWindow(battleshipGame);
        JTextField text1, text2;

        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("12A");
        text2.postActionEvent();
        frame.getButton().doClick();
        assertEquals("O", battleshipGame.getOpponentButtons(0)[1][0].getText());
    }

    /**
     * Check the game will end after the user surrender.
     */
    @Test
    public void playGame(){
        battleshipGame.placeShips(0, 0, "12A", "15A");
        battleshipGame.placeShips(0, 1, "14E", "14G");
        battleshipGame.placeShips(0, 2, "18D", "19D");
        battleshipGame.placeShips(0, 3, "08D", "08G");
        ControlWindow frame = new ControlWindow(battleshipGame);
        JTextField text1, text2;

        // Player 1 turns: Miss
        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("18D");
        text2.postActionEvent();
        frame.getButton().doClick();
        assertEquals("O", battleshipGame.getOpponentButtons(0)[7][3].getText());

        // Player 2 turns: Hit Minesweeper Captain quarter
        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("18D");
        text2.postActionEvent();
        frame.getButton().doClick();
        assertEquals("X", battleshipGame.getOpponentButtons(1)[7][3].getText());
        assertEquals("X", battleshipGame.getOpponentButtons(1)[8][3].getText());

        // Player 1 turns: Miss
        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("15F");
        text2.postActionEvent();
        frame.getButton().doClick();
        assertEquals("O", battleshipGame.getOpponentButtons(0)[4][5].getText());

        // Player 2 turns: Miss Destroyer Captain quarter 1 times
        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("14F");
        text2.postActionEvent();
        frame.getButton().doClick();
        assertEquals("O", battleshipGame.getOpponentButtons(1)[3][5].getText());

        // Player 1 turns: Miss
        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("15H");
        text2.postActionEvent();
        frame.getButton().doClick();
        assertEquals("O", battleshipGame.getOpponentButtons(0)[4][7].getText());

        // Player 2 turns: Hit Destroyer Captain quarter
        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("14F");
        text2.postActionEvent();
        frame.getButton().doClick();
        assertEquals("X", battleshipGame.getOpponentButtons(1)[3][5].getText());
        assertEquals("X", battleshipGame.getOpponentButtons(1)[3][4].getText());
        assertEquals("X", battleshipGame.getOpponentButtons(1)[3][6].getText());

        // Player 1 turns: Miss
        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("15A");
        text2.postActionEvent();
        frame.getButton().doClick();
        assertEquals("O", battleshipGame.getOpponentButtons(0)[4][0].getText());

        // Player 2 turns: Miss Battleship Captain quarter 1 times
        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("14A");
        text2.postActionEvent();
        frame.getButton().doClick();
        assertEquals("O", battleshipGame.getOpponentButtons(1)[3][0].getText());

        // Player 1 turns: Miss
        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("16J");
        text2.postActionEvent();
        frame.getButton().doClick();
        assertEquals("O", battleshipGame.getOpponentButtons(0)[5][9].getText());

        // Player 2 turns: Hit Battleship Captain quarter
        text1 = frame.getTextWeaponIndex();
        text1.setText("2");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("14A");
        text2.postActionEvent();
        frame.getButton().doClick();
        assertEquals("X", battleshipGame.getOpponentButtons(1)[3][0].getText());
        assertEquals("X", battleshipGame.getOpponentButtons(1)[1][0].getText());
        assertEquals("X", battleshipGame.getOpponentButtons(1)[2][0].getText());
        assertEquals("X", battleshipGame.getOpponentButtons(1)[4][0].getText());

        // Player 1 turns: Miss
        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("12J");
        text2.postActionEvent();
        frame.getButton().doClick();
        assertEquals("O", battleshipGame.getOpponentButtons(0)[1][9].getText());

        // Player 2 turns: Hit Submarine Captain quarter
        text1 = frame.getTextWeaponIndex();
        text1.setText("0");
        text1.postActionEvent();
        text2 = frame.getTextCoordinate();
        text2.setText("08G");
        text2.postActionEvent();
        frame.getButton().doClick();
        assertEquals("X", battleshipGame.getOpponentButtons(1)[7][6].getText());
        assertEquals("X", battleshipGame.getOpponentButtons(1)[7][3].getText());
        assertEquals("X", battleshipGame.getOpponentButtons(1)[7][4].getText());
        assertEquals("X", battleshipGame.getOpponentButtons(1)[7][5].getText());
        assertEquals("X", battleshipGame.getOpponentButtons(1)[6][5].getText());

        assertFalse(battleshipGame.getPlayer(1).surrender());
        assertTrue(battleshipGame.getPlayer(0).surrender());
    }
}
