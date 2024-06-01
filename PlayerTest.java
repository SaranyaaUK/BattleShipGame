/*
 *  PlayerTest.java - Class to test the Player class and its functionality

 *  
 *  @author SaranyaLakshmi ArulJayaKumar
 *  @version 1 - November 2023                                           
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Scanner;

class PlayerTest {

	/*
	 * Test the Player constructor and its defaults
	 * 
	 */
	@Test
	void testDefaults() {
		String playerName = "Harvey";
		Board myBoard = new Board(4, 4);

		// Player Object
		Player myPlayer = new Player(playerName, myBoard);

		// Check Player attributes
		assertEquals(myPlayer.getName(), playerName);
		assertEquals(myPlayer.getScore(), 0);
	}

	/*
	 * Test the set methods in the class
	 * 
	 */
	@Test
	void testSetters() {
		String playerName = "Harvey";
		Board myBoard = new Board(4, 4);

		// Player Object
		Player myPlayer = new Player(playerName, myBoard);

		// Check setName()
		String newName = "Spectre";
		myPlayer.setName(newName);
		assertEquals(myPlayer.getName(), newName);

		// Check the setScore()
		int newScore = 3;
		myPlayer.setScore(newScore);
		assertEquals(myPlayer.getScore(), newScore);
	}

	/*
	 * Test the takeTurn method
	 * 
	 */
	@Test
	void testTakeTurn() {
		// Assuming a board size and generate ships
		String playerName = "Harvey";
		// A mocked board class that generates 6 SmallBattleShips
		Board myBoard = new MyPlayingBoard(1, 6);
		// Player Object
		Player myPlayer = new Player(playerName, myBoard);
		int score = 0;

		// Check for scores when each ship sinks
		// Should return false until all ships sink
		assertFalse(myPlayer.takeTurn(new Scanner("0 0")));
		assertEquals(myPlayer.getScore(), ++score);

		assertFalse(myPlayer.takeTurn(new Scanner("0 1")));
		assertEquals(myPlayer.getScore(), ++score);

		assertFalse(myPlayer.takeTurn(new Scanner("0 2")));
		assertEquals(myPlayer.getScore(), ++score);

		assertFalse(myPlayer.takeTurn(new Scanner("0 3")));
		assertEquals(myPlayer.getScore(), ++score);

		assertFalse(myPlayer.takeTurn(new Scanner("0 4")));
		assertEquals(myPlayer.getScore(), ++score);

		// Matches the total ship allowed in the game and hence should return true -
		// game ended
		assertTrue(myPlayer.takeTurn(new Scanner("0 5")));
		assertEquals(myPlayer.getScore(), ++score);

	}

}

class MyPlayingBoard extends Board {
	// Local mock class to create a test board for testing in PlayerTest
	MyPlayingBoard(int row, int column) {
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
		for (int i = 0; i < 6; i++) {
			SmallBattleShip mySmallBattleShip = new SmallBattleShip(); // SmallBattleShip
			mySmallBattleShip.generateBattleShip(rowSize, columnSize);
		}
	}
}
