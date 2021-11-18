package edu.colorado.objectgrind.gui;

import javax.swing.*;
import java.awt.*;

/**
 * This class defines the game selection window that the users chooses the game size for the Battleship game.
 *
 * @see GameSetting
 * @see SetupPlayerFleetWindow
 */
public class BattleshipGame extends JFrame{
    /**
     * GUI panel stores the selection buttons.
     */
    final JPanel panel;

    /**
     * GUI button array that assign to 3 different selection buttons.
     */
    final JButton[] button;

    /**
     * The game setting that applies throughout the game.
     *
     * @see GameSetting
     */
    final GameSetting battleship_game;

    /**
     * Array of game size that uses in JLabel(label the button).
     */
    final String[] game_size;

    /**
     * Array of game size with first capital letter that uses for selectGameSize() method
     */
    final String[] size_input;

    /**
     * Array of player name that will use throughout the game.
     */
    final String[] player_name;

    /**
     * Array of player opponent name that will use throughout the game.
     */
    final String[] opponent_name;

    /**
     * Class constructor that initialize the string array of game_size, size_input, player_name, and opponent_name.
     * It constructs the game size selection window and the action each buttons. When the user press the buttons, it
     * will go to the next window where player 1 places the ship.
     */
    public BattleshipGame(){
        // Initialize the game
        battleship_game = new GameSetting();
        game_size = new String[]{"Small (7x7)", "Medium (10x10)", "Large (13x13)"};
        size_input = new String[]{"S", "M", "L"};
        player_name = new String[]{"Player 1", "Player 2"};
        opponent_name = new String[]{"Player 1 Opponent", "Player 2 Opponent"};

        // Initialize the components
        panel = new JPanel();
        button = new JButton[3];

        // Setup the frame
        setTitle("Select Game"); // Title of the frame
        setSize(300, 300); // Frame size
        setLocationRelativeTo(null); // Puts the window in the middle of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close button and exit

        for(int i = 0; i<3; i++){
            int x = i;
            button[i] = new JButton(game_size[i]);
            button[i].addActionListener(e -> {
                battleship_game.selectGameSize(size_input[x]);
                // Setup Player
                for(int n = 0; n<2; n++){
                    battleship_game.setUpPlayer(player_name[n], opponent_name[n]);
                }
                SetupPlayerFleetWindow frame = new SetupPlayerFleetWindow(battleship_game, 0, player_name[0]);
                frame.setVisible(true);
                dispose();
            });
            panel.add(button[i]);
        }
        panel.setLayout(new GridLayout(3,1));
        add(panel);
    }

    /**
     * Get the button of the game size.
     *
     * @param i the index of each game size
     * @return <code>JButton</code> button for selecting game size
     */
    public JButton getButton(int i){
        return this.button[i];
    }

    /**
     * Run the Battleship game.
     *
     * @param args states as null
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            // Create object of SelectGameWindow
            BattleshipGame frame = new BattleshipGame();
            // Set frame visible true
            frame.setVisible(true);
        });
    }
}
