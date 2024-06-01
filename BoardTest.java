/*
 *  BoardTest.java - Class to test the Board class and its functionality

 *  
 *  @author SaranyaLakshmi ArulJayaKumar
 *  @version 1 - November 2023                                           
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.Scanner;

class BoardTest {

	private PrintStream originalSystemOut;
	private InputStream originalSystemIn;
	private ByteArrayOutputStream systemOutContent;
	private ByteArrayInputStream systemInContent;

	/*
	 * Method to run before each test
	 * 
	 */
	@BeforeEach
	void redirectSystemIOStream() {
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
	void restoreSystemIOStream() {
		System.setOut(originalSystemOut);
		System.setIn(originalSystemIn);
	}

	/*
	 * Test the Board constructor and its defaults
	 * 
	 */
	@Test
	void testDefaults() {
		// We use a test board (mock) that extends the main Board class
		int row = 2;
		int column = 1;
		Board myBoard = new MyTestBoard(row, column);

		// Check Board default values of the attributes
		assertEquals(myBoard.getRowSize(), row);
		assertEquals(myBoard.getColumnSize(), column);
	}

	/*
	 * Test the setters
	 * 
	 */
	@Test
	void testSetters() {
		// We use a test board (mock) that extends the main Board class
		int row = 2;
		int column = 1;
		Board myBoard = new MyTestBoard(row, column);

		myBoard.setRowSize(row + 1);
		// Check the new values
		assertEquals(myBoard.getRowSize(), row + 1);
		myBoard.setColumnSize(column - 1);
		assertEquals(myBoard.getColumnSize(), column - 1);
	}

	/*
	 * Test the toString Method
	 * 
	 */
	@Test
	void testToString() {
		// We use a test board (mock) that extends the main Board class
		int row = 2;
		int column = 1;
		Board myBoard = new MyTestBoard(row, column);
		Player myPlayer = new Player("Harvey", myBoard);

		// Check Board default values of the attributes
		assertEquals(myBoard.toString(0, 0), "-");

		systemInContent = new ByteArrayInputStream("0 0".getBytes());
		myPlayer.takeTurn(new Scanner(systemInContent));
		assertEquals(myBoard.toString(0, 0), "X");
	}

	/*
	 * Test populateCellData
	 * 
	 */
	@Test
	void testPopulateCellData() {
		// We use a test board (mock) that extends the main Board class
		int row = 6;
		int column = 2;
		Board myBoard = new MyTestBoard(row, column);

		// Check if the 2D array is created as intended
		assertEquals(Board.cellData.length, row);
		assertEquals(Board.cellData[0].length, column);
		// Check if the elements are instance of Square Class
		assertTrue(myBoard.getSquare(0, 0) instanceof Square);
		assertTrue(myBoard.getSquare(5, 1) instanceof Square);
	}

}

class MyTestBoard extends Board {

	MyTestBoard(int row, int column) {
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

		// Generate 2 Small battleShips
		for (int i = 0; i < 2; i++) {
			SmallBattleShip mySmallBattleShip = new SmallBattleShip(); // SmallBattleShip
			mySmallBattleShip.generateBattleShip(rowSize, columnSize);
		}
	}
}
