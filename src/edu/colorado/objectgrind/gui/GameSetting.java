package edu.colorado.objectgrind.gui;

import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.game.Game;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.game.factories.*;
import edu.colorado.objectgrind.grid.Location;
import edu.colorado.objectgrind.weapons.Weapon;
import edu.colorado.objectgrind.weapons.damage_weapons.HorizontalAirStrike;
import edu.colorado.objectgrind.enums.LocationStatus;
import edu.colorado.objectgrind.game.Player;
import edu.colorado.objectgrind.ships.ship_types.Ship;
import edu.colorado.objectgrind.enums.Size;
import edu.colorado.objectgrind.weapons.utility_weapons.SonarPulse;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The class defines the game setting of the game.
 *
 * @see Coordinate
 * @see Game
 * @see Grid
 * @see LocationStatus
 * @see GameFactory
 * @see HorizontalAirStrike
 * @see LocationStatus
 * @see Player
 * @see Ship
 * @see Size
 * @see SonarPulse
 * @see Weapon
 */
public class GameSetting {
    /**
     * The game throughout the Battleship game.
     *
     * @see Game
     */
    private Game game;

    /**
     * The grid size of the selecting game.
     */
    private int grid_size;

    /**
     * The AirStrike offset when user use Horizontal AirStrike.
     */
    private int airstrike_offset;

    /**
     * Array of player 1 and player 2 corresponding to Player class.
     *
     * @see Player
     */
    private final ArrayList<Player> players;

    /**
     * Array of player 1 and player 2 grid corresponding to Grid class.
     *
     * @see Grid
     */
    private final ArrayList<Grid> grid_players;

    /**
     * Array of player 1 and player 2 buttons.
     */
    private final ArrayList<JButton[][]> button_players;

    /**
     * Array of player 1's and player 2's opponent buttons.
     */
    private final ArrayList<JButton[][]> button_opponent;

    /**
     * Array of player 1's and player 2's frame.
     */
    private final ArrayList<JFrame> frame_players;

    /**
     * Array of player 1's and player 2's opponent frame.
     */
    private final ArrayList<JFrame> frame_opponent;

    /**
     * The constructor that initialize the variable that assigns above.
     *
     * @see Game
     * @see Player
     * @see Grid
     */
    public GameSetting(){
        this.game = null;
        this.players = new ArrayList<>();
        this.grid_players = new ArrayList<>();
        this.button_players = new ArrayList<>();
        this.button_opponent = new ArrayList<>();
        this.frame_players = new ArrayList<>();
        this.frame_opponent = new ArrayList<>();
        this.grid_size = 0;
        this.airstrike_offset = 0;
    }

    /**
     * Selecting the game size.
     * <p>
     *     When the user enters the grid size name, it will assign the grid_size and airStrike_offset then create the
     *     game.
     * </p>
     *
     * @param game_size name of game size
     * @see #game
     * @see GameFactory#createGame(Size)
     * @see Size
     */
    public void selectGameSize(String game_size){
        Size size = null;
        switch (game_size){
            case "S":
                size = Size.SMALL;
                this.grid_size = 7;
                this.airstrike_offset = 1;
                break;
            case "M":
                size = Size.MEDIUM;
                this.grid_size = 10;
                this.airstrike_offset = 2;
                break;
            case "L":
                size = Size.LARGE;
                this.grid_size = 13;
                this.airstrike_offset = 3;
                break;
            default:
                break;
        }
        assert size != null;
        GameFactory factory_game = new GameFactory();
        this.game = factory_game.createGame(size);
    }

    /**
     * Get the size of the grid.
     *
     * @return grid size
     * @see #grid_size
     */
    public int getSize(){
        return this.grid_size;
    }

    /**
     * The character of the columns.
     */
    public char[] columnLetters(){
        return "ABCDEFGHIJKLM".toCharArray();
    }

    /**
     * Create the frame of the player.
     *
     * @param player player name
     * @return <code>JFrame</code> frame of the player
     * @see #setUpGridButton(String)
     */
    public JFrame setUpPlayerFrame(String player){
        // Creating the Frame
        JFrame frame = new JFrame(player);
        frame.setSize(400, 400); // setup the size

        // The frame exit when the frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add Grid Button to the Frame
        frame.getContentPane().add(BorderLayout.CENTER, setUpGridButton(player));

        return frame;
    }

    /**
     * Create the player's grid in the frame.
     * <p>
     *     If the player name is player's opponent, the grid will be stored in button_opponent. Otherwise it will be
     *     stored in button_players. Each button is the location of the grid. For example, if the game is 10x10 grid,
     *     there will be button[10][10].
     * </p>
     *
     * @param player player name
     * @return <code>JPanel</code> panel that stores grid buttons
     */
    public JPanel setUpGridButton(String player){
        int size = this.grid_size;
        char[] c = columnLetters();
        String[] s = player.split(" ");

        JPanel panel = new JPanel();
        JButton[][] b = new JButton[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++) {
                b[i][j] = new JButton(String.valueOf(i+1) + c[j]);
                panel.add(b[i][j]);
            }
        }
        if(s[s.length-1].equals("Opponent")) {
            this.button_opponent.add(b);
        }else{
            this.button_players.add(b);
        }
        panel.setLayout(new GridLayout(size, size, 0, 0));

        return panel;
    }

    /**
     * Set the symbol to the button depends on the location status.
     *
     * @param status location status
     * @param button location's button
     * @see LocationStatus
     */
    public void setSymbolOrColor(LocationStatus status, JButton button){
        switch (status) {
            case HIDDEN:
                break;
            case HIT:
                button.setText("X");
                button.setFont(new Font("Arial", Font.PLAIN, 20));
                break;
            case MISS:
                button.setText("O");
                button.setFont(new Font("Arial", Font.PLAIN, 20));
                break;
            case FREE:
                button.setName("blue");
                button.setBackground(Color.BLUE);
                button.setOpaque(true);
                break;
            case OCCUPIED:
                button.setName("red");
                button.setBackground(Color.RED);
                button.setOpaque(true);
                break;
        }
    }

    /**
     * Setup the player and its grid. Also setup up player's and its opponent frames.
     *
     * @param player_name player name
     * @param opponent_name player's opponent name
     * @see #setUpPlayerFrame(String)
     * @see Game#getPlayer_1()
     * @see Game#getPlayer_2()
     * @see Player#getGrid()
     */
    public void setUpPlayer(String player_name, String opponent_name){
        Player player;
        if(player_name.equals("Player 1")){
            player = this.game.getPlayer_1();
        }else{
            player = this.game.getPlayer_2();
        }
        this.players.add(player);
        this.grid_players.add(player.getGrid());
        this.frame_players.add(setUpPlayerFrame(player_name));
        this.frame_opponent.add(setUpPlayerFrame(opponent_name));
    }

    /**
     * Get player from players.
     *
     * @param player_index player index
     * @return <code>Player</code> object for player
     * @see #players
     */
    public Player getPlayer(int player_index){
        return this.players.get(player_index);
    }

    /**
     * Get player's grid from grid_players
     *
     * @param player_index player index
     * @return <code>Grid</code> object for grid
     */
    public Grid getPlayerGrid(int player_index){
        return this.grid_players.get(player_index);
    }

    /**
     * Get player's buttons from button_players.
     *
     * @param player_index player index
     * @return <code>JButton[][]</code> player's buttons
     */
    public JButton[][] getPlayerButtons(int player_index){ return this.button_players.get(player_index); }

    /**
     * Get player's opponent buttons from button_opponents.
     *
     * @param player_index player index
     * @return <code>JButton[][]</code> player's opponent buttons
     */
    public JButton[][] getOpponentButtons(int player_index){ return this.button_opponent.get(player_index); }

    /**
     * Get player's frame from frame_players.
     *
     * @param player_index player_index
     * @return <code>JFrame</code> player's grid frame
     */
    public JFrame getPlayerFrame(int player_index){ return this.frame_players.get(player_index); }

    /**
     * Get player's opponent frame from frame_opponents.
     *
     * @param player_index player index
     * @return <code>JFrame</code> player's opponent grid frame
     */
    public JFrame getOpponentFrame(int player_index){ return this.frame_opponent.get(player_index); }

    /**
     * The user places the ship on the grid.
     * <p>
     *     The user enter the head and tail coordinates of the ship, then playerPlaceShip places the ship on the grid.
     *     After the user place the ship, it will be set on the grid frame by using setShipPlacementOnGrid.
     * </p>
     *
     * @param player_index player index
     * @param ship_index ship index
     * @param head head coordinate
     * @param tail tail coordinate
     * @see #getPlayer(int)
     * @see Player#getShipAtIndex(int)
     * @see Player#playerPlaceShip(int, String, String)
     * @see Ship
     */
    public void placeShips(int player_index, int ship_index, String head, String tail){
        Player player = getPlayer(player_index);
        Ship ship = player.getShipAtIndex(ship_index);

        player.playerPlaceShip(ship_index, head, tail);
        setShipPlacementOnGrid(player_index, head, tail);
        System.out.println(ship.printShip() + " is placed.");
        System.out.println("Head: " + head);
        System.out.println("Tail: " + tail);
    }

    /**
     * Set color on the buttons where the ships is placed.
     *
     * @param player_index player index
     * @param head head coordinate
     * @param tail tail coordinate
     * @see #getPlayerGrid(int)
     * @see #getPlayerButtons(int)
     * @see Coordinate#getRow()
     * @see Coordinate#getColumn()
     * @see Grid#stringCoordToIntCoord(String)
     */
    public void setShipPlacementOnGrid(int player_index, String head, String tail){
        JButton[][] buttons;
        Coordinate coord_head = getPlayerGrid(player_index).stringCoordToIntCoord(head);
        Coordinate coord_tail = getPlayerGrid(player_index).stringCoordToIntCoord(tail);
        if(coord_head.getRow() == coord_tail.getRow()){
            // In the given row, set the color at every location from head to tail coordinates
            for(int j = coord_head.getColumn(); j < coord_tail.getColumn()+1; j++){
                buttons = getPlayerButtons(player_index);
                buttons[coord_head.getRow()][j].setBackground(Color.GREEN);
                buttons[coord_head.getRow()][j].setOpaque(true);
            }
        }else{
            // In the given column, set the color at every location from head to tail coordinates
            for(int i = coord_head.getRow(); i < coord_tail.getRow()+1; i++){
                buttons = getPlayerButtons(player_index);
                buttons[i][coord_head.getColumn()].setBackground(Color.GREEN);
                buttons[i][coord_head.getColumn()].setOpaque(true);
            }
        }
    }

    /**
     * Check if the ship can be placed.
     *
     * @param player_index player index
     * @param ship_index ship index
     * @param head head coordinate
     * @param tail tail coordinate
     * @return <code>true</code> if the ship can placed on grid <code>false</code> otherwise
     * @see #getPlayerGrid(int)
     * @see Grid#shipCanBePlaced(Ship, String, String)
     * @see Player#getShipAtIndex(int)
     */
    public boolean canPlaceShips(int player_index, int ship_index, String head, String tail){
        return getPlayerGrid(player_index).shipCanBePlaced(getPlayer(player_index).getShipAtIndex(ship_index), head, tail);
    }

    /**
     * Display player and its opponent frames.
     *
     * @param player_index player index
     * @see #getPlayerFrame(int)
     * @see #getOpponentFrame(int)
     */
    public void displayFrame(int player_index){
        JFrame frame1 = getPlayerFrame(player_index);
        JFrame frame2 = getOpponentFrame(player_index);

        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);

        frame2.setLocation(frame1.getX() + frame1.getWidth(), frame1.getY());
        frame2.setVisible(true);
    }

    /**
     * Remove player and its opponent frames.
     *
     * @param player_index player index
     * @see #getPlayerFrame(int)
     * @see #getOpponentFrame(int)
     */
    public void removeFrame(int player_index){
        getPlayerFrame(player_index).dispose();
        getOpponentFrame(player_index).dispose();
    }

    /**
     * Set symbol or colors on its opponent frame and next player's frame after the player attacks opponent.
     * <p>
     *     If the user uses the sonar pulse, it will be displays on player's opponent frame
     * </p>
     *
     * @param player_index player index
     * @param opponent_index opponent index
     * @param weapon weapon index
     * @param coordinate attack coordinate
     * @throws Exception invalid weapon index/attack coordinate
     * @see #playerAttackOpponent(int, int, String)
     * @see #setSonarPulseOnGrid(int, int)
     * @see #getPlayer(int)
     * @see #setAirStrikeOnGrid(int, int, String)
     * @see #getPlayerGrid(int)
     * @see #checkCaptainQuarterStatus(int, Coordinate)
     * @see #setCaptainQuarterAttackOnGrid(int, int)
     * @see #setSymbolOrColor(LocationStatus, JButton)
     * @see Coordinate#getRow()
     * @see Coordinate#getColumn()
     * @see Coordinate#getLayer()
     * @see Grid#getLocationByIndex(int, int, int)
     * @see Grid#stringCoordToIntCoord(String)
     * @see Location#getLocationStatus()
     * @see Location#hasShip()
     * @see Player#getWeaponAtIndex(int)
     * @see HorizontalAirStrike
     */
    public void setPlayerAttackOnGrid(int player_index, int opponent_index, String weapon, String coordinate) throws Exception {
        int weapon_index = Integer.parseInt(weapon);
        Weapon weapon1 = getPlayer(player_index).getWeaponAtIndex(weapon_index);
        playerAttackOpponent(player_index, weapon_index, coordinate);

        // If the weapon is Sonar Pulse, it will be placed on the grid with diamond pattern
        // Else if the weapon is HorizontalAirStrike, it will be placed on the gird with line pattern
        if(weapon1 instanceof SonarPulse){
            setSonarPulseOnGrid(player_index, opponent_index);
        }else if(weapon1 instanceof HorizontalAirStrike){
            setAirStrikeOnGrid(player_index, opponent_index, coordinate);
        }else {
            Grid grid_opponent = getPlayerGrid(opponent_index);
            Coordinate coord = grid_opponent.stringCoordToIntCoord(coordinate);
            LocationStatus status = grid_opponent.getLocationByIndex(coord.getLayer(), coord.getRow(), coord.getColumn()).getLocationStatus();

            // If there is ship, check if the location is captain quarter
            if(grid_opponent.getLocationByIndex(coord.getLayer(), coord.getRow(), coord.getColumn()).hasShip()){
                // If the location is captain quarter, set the whole ship to HIT symbol on the grid frame
                if(checkCaptainQuarterStatus(opponent_index, coord)) {
                    setCaptainQuarterAttackOnGrid(player_index, opponent_index);
                    return;
                }
            }
            if(!getOpponentButtons(player_index)[coord.getRow()][coord.getColumn()].getText().equals("X")){
                setSymbolOrColor(status, getOpponentButtons(player_index)[coord.getRow()][coord.getColumn()]);
                setSymbolOrColor(status, getPlayerButtons(opponent_index)[coord.getRow()][coord.getColumn()]);
            }
        }
    }

    /**
     * Player attack opponents.
     *
     * @param player_index player index
     * @param weapon_index weapon index
     * @param coordinate attack coordinate
     * @throws Exception invalid weapon index/attack coordinate
     * @see Game#player1TakeTurn(int, String)
     * @see Game#player2TakeTurn(int, String)
     */
    public void playerAttackOpponent(int player_index, int weapon_index, String coordinate) throws Exception {
        if(player_index == 0){
            this.game.player1TakeTurn(weapon_index, coordinate);
        }else{
            this.game.player2TakeTurn(weapon_index, coordinate);
        }
    }

    /**
     * Set up sonar pulse on player's opponent frame.
     *
     * @param player_index player index
     * @param opponent_index opponent index
     * @see #getPlayerGrid(int)
     * @see #setSymbolOrColor(LocationStatus, JButton)
     * @see #getOpponentButtons(int)
     * @see Grid#getLocationByIndex(int, int, int)
     * @see Location#getLocationStatus()
     * @see LocationStatus
     */
    public void setSonarPulseOnGrid(int player_index, int opponent_index){
        for(int i = 0; i<this.grid_size; i++){
            for(int j = 0; j<this.grid_size; j++){
                LocationStatus status = getPlayerGrid(opponent_index).getLocationByIndex(1, i, j).getLocationStatus();
                if(status != LocationStatus.HIT & status != LocationStatus.MISS) {
                    setSymbolOrColor(status, getOpponentButtons(player_index)[i][j]);
                }
            }
        }
    }

    /**
     * Set AirStrike on the player's opponent frame and next player's frame.
     *
     * @param player_index player index
     * @param opponent_index opponent index
     * @param coordinate attack coordinate
     * @see #getPlayerGrid(int)
     * @see #setSymbolOrColor(LocationStatus, JButton)
     * @see #getOpponentButtons(int)
     * @see #getPlayerButtons(int)
     * @see #checkCaptainQuarterStatus(int, Coordinate)
     * @see #setCaptainQuarterAttackOnGrid(int, int)
     * @see Grid#stringCoordToIntCoord(String)
     * @see Coordinate#getRow()
     * @see Coordinate#getColumn()
     * @see Location#getLocationStatus()
     * @see Location#hasShip()
     * @see LocationStatus
     */
    public void setAirStrikeOnGrid(int player_index, int opponent_index, String coordinate){
        LocationStatus status;
        Coordinate coord = getPlayerGrid(opponent_index).stringCoordToIntCoord(coordinate);
        int row = coord.getRow();
        int col = coord.getColumn();
        for (int i = col - this.airstrike_offset; i < col + this.airstrike_offset + 1; i++) {
            if (i >= 0 & i < this.grid_size) {
                status = getPlayerGrid(opponent_index).getLocationByIndex(1, row, i).getLocationStatus();
                if (!getOpponentButtons(player_index)[row][i].getText().equals("X")) {
                    setSymbolOrColor(status, getOpponentButtons(player_index)[row][i]);
                    setSymbolOrColor(status, getPlayerButtons(opponent_index)[row][i]);
                }
                String s = String.valueOf(row+1);
                String coordinate1 = "1" + s + columnLetters()[i];
                Coordinate coord1 = getPlayerGrid(opponent_index).stringCoordToIntCoord(coordinate1);
                if(getPlayerGrid(opponent_index).getLocationByIndex(coord1.getLayer(), coord1.getRow(), coord1.getColumn()).hasShip()){
                    if(checkCaptainQuarterStatus(opponent_index, coord1)){
                        setCaptainQuarterAttackOnGrid(player_index, opponent_index);
                    }
                }
            }
        }
    }

    /**
     * Set symbol on frame when captain quarter is destroyed.
     *
     * @param player_index player index
     * @param opponent_index opponent index
     * @see #getPlayerGrid(int)
     * @see #getOpponentButtons(int)
     * @see #getPlayerButtons(int)
     * @see Grid#getLocationByIndex(int, int, int)
     * @see Location#getLocationStatus()
     * @see LocationStatus
     */
    public void setCaptainQuarterAttackOnGrid(int player_index, int opponent_index){
        for(int l = 0; l<2; l++){
            for (int i = 0; i < this.grid_size; i++) {
                for (int j = 0; j < this.grid_size; j++) {
                    LocationStatus status = getPlayerGrid(opponent_index).getLocationByIndex(l,i,j).getLocationStatus();
                    if(status == LocationStatus.HIT & !getOpponentButtons(player_index)[i][j].getText().equals("X")){
                        setSymbolOrColor(status, getOpponentButtons(player_index)[i][j]);
                        setSymbolOrColor(status, getPlayerButtons(opponent_index)[i][j]);
                    }
                }
            }
        }
    }

    /**
     * Check if the location is captain quarter and its status.
     *
     * @param opponent_index opponent index
     * @param coordinate attack coordinate
     * @return <code>true</code> the captain quarter destroyed
     * @see #getPlayerGrid(int)
     * @see Grid#getLocationByIndex(int, int, int)
     * @see Location#getShip()
     * @see Ship#checkCaptainsQuarter(Grid, Coordinate)
     * @see Ship#isCapQuartersDestroyed()
     */
    public boolean checkCaptainQuarterStatus(int opponent_index, Coordinate coordinate){
        Grid opponent_grid = getPlayerGrid(opponent_index);
        Ship ship = opponent_grid.getLocationByIndex(coordinate.getLayer(), coordinate.getRow(), coordinate.getColumn()).getShip();
        return ship.checkCaptainsQuarter(opponent_grid, coordinate) & ship.isCapQuartersDestroyed();
    }

    /**
     * Print the player's arsenal in terminal.
     *
     * @param player_index player index
     * @see #getPlayer(int)
     * @see Player#getWeaponAtIndex(int)
     * @see Weapon#printWeapon()
     */
    public void printArsenal(int player_index){
        Player player = getPlayer(player_index);
        System.out.println("Arsenal");
        for(int i = 0; i<player.getSizeOfArsenal(); i++){
            System.out.print(i + ": ");
            player.getWeaponAtIndex(i).printWeapon();
            System.out.print("\n");
        }
    }

    /**
     * Print the status of a player in the terminal.
     * Include: which player attack, player's arsenal, and number of ship sunks.
     *
     * @param player_index player index
     * @param player_name player name
     * @see #printArsenal(int)
     * @see #getPlayer(int)
     * @see Player#getNumShipsSunk()
     */
    public void printStatus(int player_index, String player_name){
        System.out.println("\n" + player_name + " attacks.");
        printArsenal(player_index);
        System.out.println("Ship sunks: " + getPlayer(player_index).getNumShipsSunk());
    }
}
