/*
 *  Player.java - Class to represents the players	
 *  
 *  Methods
 *  -------
 *  
 *  getScore	- Return the player's score	
 *  setScore	- Set the player's score
 *  getName		- Return the player's name
 *  setName		- Set the player's name
 *  takeTurn	- Players take turn to play the game
 *  
 *  @author SaranyaLakshmi ArulJayaKumar
 *  @version 1 - October 2023                                           
 */

import java.util.Scanner;

public class Player {
	/*
	 * Attributes
	 */

	// Reference to the board object on which the game is played
	private Board myBoard;
	// Name of the player
	private String name;
	// Score of the player - indicates number of the ship sunk by the player
	private int score;
	// Player's row guess
	private int inputX;
	// Player's column guess
	private int inputY;

	/*
	 * Methods
	 */

	/*
	 * Constructor
	 * 
	 * @param name Name of the player (String)
	 * 
	 * @param myBoard Reference to the board object on which the players play
	 * (Board).
	 * 
	 */
	Player(String name, Board myBoard) {
		// Initialize the attributes
		this.name = name;
		this.myBoard = myBoard;
		this.score = 0;
	}

	/*
	 * Getters and Setters
	 */

	/*
	 * Get the player's score
	 * 
	 * @return score score of the player (integer).
	 * 
	 */
	public int getScore() {
		return score;
	}

	/*
	 * Set the player's score
	 * 
	 * @param score Set player's score (integer).
	 * 
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/*
	 * Get the player's name
	 * 
	 * @return name player's name (String).
	 * 
	 */
	public String getName() {
		return name;
	}

	/*
	 * Set the player's name
	 * 
	 * @param Set the player's name (String).
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Players take guess, and return the status based on the actions that follow
	 * when players play their turn.
	 * 
	 * @return The game status, true represents the end of the game (boolean).
	 * 
	 */
	public boolean takeTurn(Scanner inputScanner) {
		// Scan the player's guess at each turn
		this.scanPlayerGuess(inputScanner);
		// Based on the guess, assess and get the game status
		return this.getGameStatus();
	}

	/*
	 * Helper Methods
	 * 
	 */

	/*
	 * Scan each player's guess from the console.
	 * 
	 * @private
	 * 
	 */
	private void scanPlayerGuess(Scanner inputScanner) {
		System.out.println(this.name + ", please enter the coordinates in \"X Y\" format."
				+ " X represents the row, and Y represents the column.");

		// Assign the given inputs to the attributes in the class for access in other
		// methods
		this.inputX = inputScanner.nextInt();
		this.inputY = inputScanner.nextInt();
	}

	/*
	 * Scan the player's coordinate guess from the console.
	 * 
	 * @private
	 * 
	 * @return gameStatus True if game ended, false otherwise (boolean).
	 * 
	 */
	private boolean getGameStatus() {
		boolean gameStatus = false;

		// Get the square and battleship references
		Square mySquare = this.myBoard.getSquare(this.inputX, this.inputY);
		BattleShip myBattleShip = mySquare.getMyBattleShip();

		// Update the square status based on the guess,
		// and give the result for the turn.
		boolean guessStatus = mySquare.getSquareGuessStatus(inputX, inputY);

		// If the guess is a hit update the ship status
		if (guessStatus) {
			// Update BattleShip's Health
			myBattleShip.updateShipStatus();
			// If the ship has sunk update player's score
			if (myBattleShip.isSunk()) {
				this.setScore(this.score + 1);
			}
		}

		// Check if all ships are sunk in which case the game has ended
		if (BattleShip.getTotalBattleShipsCount() == BattleShip.numberOfSunkShips) {
			System.out.println("The game ended.");
			gameStatus = true;
		}

		return gameStatus;
	}
}
