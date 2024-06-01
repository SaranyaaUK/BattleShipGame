/*
 *  GamePlayTest.java - Test the game play.
 *  
 *  This class is used to test the BattleShip game play
 *  with 6 small battleships.
 *  
 *  @author SaranyaLakshmi ArulJayaKumar
 *  @version 1 - November 2023                                           
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.Scanner;

class GamePlayTest {

	private PrintStream originalSystemOut;
	private InputStream originalSystemIn;
	private ByteArrayOutputStream systemOutContent;
	private ByteArrayInputStream systemInContent;

	/*
	 * Method to run before each test
	 * 
	 */
	@BeforeEach
	void redirectSystemOutStream() {
		originalSystemOut = System.out; // backup System.out to restore it later
		originalSystemIn = System.in; // backup System.in to restore it later

		systemOutContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemOutContent));
	}

	/*
	 * Method to run after each test
	 * 
	 */
	@AfterEach
	void restoreSystemOutStream() {
		System.setOut(originalSystemOut);
		System.setIn(originalSystemIn);
		System.out.println(systemOutContent.toString().trim());
	}

	/*
	 * Test the scanPlayerNames
	 * 
	 */
	@Test
	void testCreatePlayerNames() {
		systemInContent = new ByteArrayInputStream("Saranya\nArul".getBytes());
		Board myBoard = new MyGameBoard(1, 6);
		MyGamePlay.createPlayers(new Scanner(systemInContent), myBoard);

		// Verify the player names
		assertEquals(MyGamePlay.myFirstPlayer.getName(), "Saranya");
		assertEquals(MyGamePlay.mySecondPlayer.getName(), "Arul");
	}

	/*
	 * Test the announceWinner
	 * 
	 */
	@Test
	void testAnnounceWinner() {

		systemInContent = new ByteArrayInputStream("Saranya\nArul".getBytes());
		Board myBoard = new MyGameBoard(1, 6);
		MyGamePlay.createPlayers(new Scanner(systemInContent), myBoard);

		// Winner
		systemOutContent.reset(); // Remove content in the stream
		MyGamePlay.myFirstPlayer.setScore(2);
		MyGamePlay.announceWinner(MyGamePlay.myFirstPlayer, MyGamePlay.mySecondPlayer);

		// Verify the output
		assertEquals(MyGamePlay.myFirstPlayer.getName() + " is the winner. Congratulations!",
				systemOutContent.toString().trim());

		// Match Draw
		systemOutContent.reset(); // Remove content in the stream
		MyGamePlay.mySecondPlayer.setScore(2);
		MyGamePlay.announceWinner(MyGamePlay.myFirstPlayer, MyGamePlay.mySecondPlayer);

		// Verify the output
		assertEquals("Players have same score. So it is a draw!", systemOutContent.toString().trim());
	}

	/*
	 * Test the gamePlay
	 * 
	 */
	@Test
	void testGamePlay() {
		Board myBoard = new MyGameBoard(1, 6);

		// Setup up sample players
		systemInContent = new ByteArrayInputStream("Saranya \nArul".getBytes());
		MyGamePlay.createPlayers(new Scanner(systemInContent), myBoard);

		// Play game
		systemOutContent.reset(); // Remove content in the stream
		systemInContent = new ByteArrayInputStream("0 0 \n 0 1 \n 0 2 \n 0 3\n 0 4\n 0 5".getBytes());
		MyGamePlay.playGame(new Scanner(systemInContent), myBoard);

		// Check if the players score match as we 6 small battleships with
		// each player firing a HIT each turn
		assertEquals(GamePlay.myFirstPlayer.getScore(), 3);
		assertEquals(GamePlay.mySecondPlayer.getScore(), 3);
		// Assert if the output contains the string with the grid representing the hits
		assertTrue(systemOutContent.toString().contains(
				String.format("%s %2s %2s %2s %2s %2s",
						"X", "X", "X", "X", "X", "X").trim()));
	}
}

class MyGamePlay extends GamePlay {
	// Local GamePlay mock to test the actual GamePlay
}

class MyGameBoard extends Board {
	// Local mock to create a test board for testing
	MyGameBoard(int row, int column) {
		this.setRowSize(row);
		this.setColumnSize(column);
		// Initialize the cellData
		Board.cellData = new Square[row][column];
		this.populateCellData();
		this.generateBattleShips();
	}

	private void generateBattleShips() {
		int rowSize = this.getRowSize();
		int columnSize = this.getColumnSize();

		// Generate 6 Small battleShips
		for (int i = 0; i < BattleShip.getTotalBattleShipsCount(); i++) {
			SmallBattleShip mySmallBattleShip = new SmallBattleShip(); // SmallBattleShip
			mySmallBattleShip.generateBattleShip(rowSize, columnSize);
		}
	}
}
