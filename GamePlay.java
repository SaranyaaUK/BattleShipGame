
/*
 *  GamePlay.java - Class to play the game.
 *  
 *  This is the main class that runs the BattleShip game.
 *  
 *  @author SaranyaLakshmi ArulJayaKumar
 *  @version 1 - October 2023                                           
 */
import java.util.Scanner;

public class GamePlay {

	private static String player1Name;
	private static String player2Name;
	static Player myFirstPlayer;
	static Player mySecondPlayer;

	public static void main(String[] args) {
		// Create the board to play the game 10 * 10
		int myGridSize = 10;
		Board myGameBoard = new Board(myGridSize, myGridSize);
		Scanner myNameScanner = new Scanner(System.in);

		// Get the player names and create the Player objects
		createPlayers(myNameScanner, myGameBoard);

		// Players take turn until all ships are sunk
		playGame(myNameScanner, myGameBoard);

		// Announce the winner or match status based on the score
		announceWinner(myFirstPlayer, mySecondPlayer);

		// Close the scanner object
		myNameScanner.close();
	}

	/*
	 * Create player objects
	 * 
	 */
	protected static void createPlayers(Scanner myNameScanner, Board myGameBoard) {
		// Get the player names
		scanPlayerNames(myNameScanner);

		// Create the Player objects
		GamePlay.myFirstPlayer = new Player(GamePlay.player1Name, myGameBoard);
		GamePlay.mySecondPlayer = new Player(GamePlay.player2Name, myGameBoard);
	}

	/*
	 * Get the players name
	 * 
	 * @param Scanner object used to scan the inputs from the console (Scanner).
	 * 
	 */
	private static void scanPlayerNames(Scanner myNameScanner) {
		System.out.println("Welcome to the BattleShip Game \n");
		// Scan players name
		// Player 1
		System.out.println("Player1 enter your name");
		GamePlay.player1Name = myNameScanner.next();
		// Player 2
		System.out.println("Player2 enter your name");
		GamePlay.player2Name = myNameScanner.next();
	}

	/*
	 * Players take turn to play the game
	 * 
	 */

	protected static void playGame(Scanner myNameScanner, Board myGameBoard) {
		// Players take turn
		boolean firstPlayerGameStatus = false;
		boolean secondPlayerGameStatus = false;
		while (!firstPlayerGameStatus && !secondPlayerGameStatus) {
			firstPlayerGameStatus = myFirstPlayer.takeTurn(myNameScanner);
			// Print grid after each player's turn
			printGrid(myGameBoard);
			printScore(myFirstPlayer, mySecondPlayer);
			if (firstPlayerGameStatus) {
				// If all ships are sunk do not continue the game announce the winner
				// and break from the loop.
				// This happens if first player ends the game.
				break;
			}
			secondPlayerGameStatus = mySecondPlayer.takeTurn(myNameScanner);
			// Print grid after each player's turn
			printGrid(myGameBoard);
			printScore(myFirstPlayer, mySecondPlayer);
		}
	}

	/*
	 * Find the winner
	 * 
	 * @param Player object (Player)
	 * 
	 * @param Player object (Player)
	 * 
	 */
	protected static void announceWinner(Player myFirstPlayer, Player mySecondPlayer) {
		// Compare the scores of the player and announce the game winner
		if (myFirstPlayer.getScore() > mySecondPlayer.getScore()) {
			System.out.println(myFirstPlayer.getName() + " is the winner. Congratulations!");
		} else if (myFirstPlayer.getScore() < mySecondPlayer.getScore()) {
			System.out.println(mySecondPlayer.getName() + " is the winner. Congratulations!");
		} else {
			System.out.println("Players have same score. So it is a draw!");
		}
	}

	/*
	 * Print the score
	 * 
	 * @param Player object (Player)
	 * 
	 * @param Player object (Player)
	 * 
	 */
	private static void printScore(Player myFirstPlayer, Player mySecondPlayer) {
		// Print the score board after each player's turn
		System.out.println("Score Board \n" + myFirstPlayer.getName() + " : " + myFirstPlayer.getScore() + "\n" +
				mySecondPlayer.getName() + " : " + mySecondPlayer.getScore() + "\n");
	}

	/*
	 * Print the grid representing the player's guess status
	 * 
	 * @param Board object (Board)
	 * 
	 */
	private static void printGrid(Board myGameBoard) {
		for (int i = 0; i < myGameBoard.getRowSize(); i++) {
			for (int j = 0; j < myGameBoard.getColumnSize(); j++) {
				System.out.print(String.format("%3s", myGameBoard.toString(i, j)));
			}
			System.out.println("");
		}
	}
}
