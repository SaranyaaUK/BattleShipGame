/*
 *  SquareTest.java - Class to test the Square class and its functionality

 *  
 *  @author SaranyaLakshmi ArulJayaKumar
 *  @version 1 - November 2023                                           
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SquareTest {

	/*
	 * Test the Square constructor and its defaults
	 * 
	 */
	@Test
	void testDefaults() {
		int row = 5;
		int column = 4;
		Square mySquare = new Square(row, column);

		// Check if isShip and isFired attributes are false by default
		assertFalse(mySquare.isHasShip());
		assertFalse(mySquare.isFired());

		// Check if the row and column attributes are initialized
		// to the value passed to the constructor
		assertEquals(mySquare.getRow(), row);
		assertEquals(mySquare.getColumn(), column);

		// Check if the myBattleShip attribute is null
		assertEquals(mySquare.getMyBattleShip(), null);
	}

	/*
	 * Test the set methods in the class
	 * 
	 */
	@Test
	void testSetters() {
		int row = 5;
		int column = 4;
		Square mySquare = new Square(row, column);

		// Check if isShip and isFired attributes are false by default
		assertFalse(mySquare.isHasShip());
		assertFalse(mySquare.isFired());
		// Changed to appropriate value on using the setter
		mySquare.setFired(true);
		mySquare.setHasShip(true);

		assertTrue(mySquare.isHasShip());
		assertTrue(mySquare.isFired());

		// Check if the row and column attributes are initialized
		// to the value passed to the constructor
		assertEquals(mySquare.getRow(), row);
		assertEquals(mySquare.getColumn(), column);

		// Changed to appropriate value on using the setters
		mySquare.setRow(10);
		mySquare.setColumn(10);

		assertEquals(mySquare.getRow(), 10);
		assertEquals(mySquare.getColumn(), 10);
	}

	/*
	 * Test the getSquareGuessStatus() method
	 * 
	 */
	@Test
	void testGetSquareGuessStatus() {
		// A square object representing a fired square
		Square myFiredSquare = new Square(2, 2);
		myFiredSquare.setFired(true);

		// A square object that has a ship in it
		Square myShipSquare = new Square(1, 1);
		myShipSquare.setHasShip(true);

		// A square object that does not have a ship in it
		Square myNoShipSquare = new Square(3, 1);

		// If the Square has a ship then the status is returned true
		assertTrue(myShipSquare.getSquareGuessStatus(1, 1));

		// If the Square either fired already or does not have a ship then the status is
		// returned false
		assertFalse(myNoShipSquare.getSquareGuessStatus(3, 1));
		assertFalse(myNoShipSquare.getSquareGuessStatus(2, 2));
	}

	/*
	 * Test the toString() method
	 * 
	 */
	@Test
	void testToString() {
		// A square object representing a fired square with no ship (MISS, O)
		Square myFiredNoShipSquare = new Square(2, 2);
		myFiredNoShipSquare.setFired(true);

		// A square object that has a ship in it and is fired (HIT, X)
		Square myFiredShipSquare = new Square(1, 1);
		myFiredShipSquare.setHasShip(true);
		myFiredShipSquare.setFired(true);

		// A square object that is not yet been interacted (-)
		Square mySquare = new Square(3, 1);

		// If it is a miss it should be represented as "O"
		assertEquals(myFiredNoShipSquare.toString(), "O");

		// If it is a hit it should be represented as "X"
		assertEquals(myFiredShipSquare.toString(), "X");

		// If it is not yet been interacted it should be represented by "-"
		assertEquals(mySquare.toString(), "-");
	}

}
