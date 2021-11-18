# Battleship - Team Object Grind

## Description
This respository contains source code, test code, and documents that show both the current state and functionality of this program, as well as the development process and progress that was made throughout the project's timeline. 

The application/program that is contained within this repository is a modified version of the classic board game known as Battleship. The original version of the game consists of two players, who each have have a fleet of ships and a 10x10 grid that represents the ocean. At the beginning of the game both players place their fleet of ships on their own grid, positions unknown to the other player. The players then alternate turns attacking coordinates of the other player's grid. If the coordinate a player attacks has a ship, then it is a hit, otherwise it is a miss. If all spaces that a ship occupies are hit, then that ship sinks. The first player to sink all of their opponent's ship is the winner.
        
We expanded upon this standard version of Battleship by adding several new features to the game, including: three different game sizes, a second layer to the grid, a new type of ship, more weapons at each player's disposal, and the ability for players to move their entire fleet. 

The game is entirely coded in Java, and is playable through installation and compilation of the source code. The game is played via a graphical user interface (GUI), which is designed for two users to play against one another on the same machine. This interface gives each player a way to place their fleet on their grid, and select and use a weapon on their opponent's grid. Throughout the game, on a given player's turn they can see, side by side, their own grid - where they have placed their ships and where their opponent has attacked - as well their opponents grid, which shows the results of their attacks throughout the game. The interface switches between the player's until one of them sinks all of their opponent's ships, and thus wins the game.

## Playing the Game - Installation and Compilation
* Download: The user type "git clone https://github.com/JacobBonner/Battleship.git" in the terminal
* Compile and Run: compile and run BattleshipGame.java in IntelliJ

### Game Instruction
1. Select the Game size in the Select Game window.
2. Type in the head and tail coordinates for each ships in Place Fleet for player 1 window.  After finishing placing the fleet, presses submit button. 
3. Type in the head and tail coordinates for each ships in Place Fleet for player 2 window. After finishing placing the fleet, presses submit button. 
4. During the game, the players type in the weapon index and coordinate in the control window. 

### Need to know
* In each textfield, the user needs to press "enter" on the keyboard after they finish type in the weapon index/coordinate so the textfield will take action.
* The valid coordinate is format as layer + row + column. For example, "12A" means layer 1, row 2, column A. For the submarine, the user can choose layer 0 or layer 1. 
* The weapon index is a single value that corresponding to the weapons in player's arsenal.
* The player's arsenal, weapon index with type of weapon, and number of ships sunk will be shown in the terminal.

## Team Members

### Team Members for Milestones 1-4
<ol>
  <li> Abhijeet Srivastava </li>
  <li> Akshita Bhasin </li>
  <li> Jake Bonner </li>
  <li> Jennifer Gurtler </li>
 </ol>
 
 ### Team Members after Milestone 4
 <ol>
  <li> Jake Bonner </li>
  <li> Jennifer Gurtler </li>
  </ol>

## Additional Information
For additional information on the development process, requirements and specfications, architecture and design, and more, see the folowing resources:
<ol>
  <li> Documentation: https://github.com/JacobBonner/Battleship/blob/main/DOCUMENTS/project_documentation.pdf </li>
</ol>
