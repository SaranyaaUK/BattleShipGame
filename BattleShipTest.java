/*
 *  BattleShipTest.java - Class to test the Battleship class and its functionality

 *  
 *  @author SaranyaLakshmi ArulJayaKumar
 *  @version 1 - November 2023                                           
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BattleShipTest {

	/*
	 * Test the BattleShip constructor and its defaults
	 * 
	 */
	@Test
	void testDefaults() {

		int shipSize = 4;
		// Create Battle Ship
		BattleShip myBattleShip = new BattleShip(shipSize);

		// Check the defaults
		assertEquals(myBattleShip.getShipSize(), shipSize);
		assertEquals(myBattleShip.getShipHealth(), shipSize);
		assertFalse(myBattleShip.isSunk());
	}

	/*
	 * Test the setSunk
	 * 
	 */
	@Test
	void testSetSunk() {

		int shipSize = 2;
		// Create Battle Ship
		BattleShip myBattleShip = new BattleShip(shipSize);

		assertFalse(myBattleShip.isSunk());
		myBattleShip.setSunk(true);
		assertTrue(myBattleShip.isSunk());
	}

	/*
	 * Test updateShipStatus
	 * 
	 */
	@Test
	void testUpdateShipStatus() {

		int shipSize = 4;
		// Create Battle Ship
		BattleShip myBattleShip = new BattleShip(shipSize);

		myBattleShip.updateShipStatus();
		assertEquals(myBattleShip.getShipHealth(), --shipSize);
		assertFalse(myBattleShip.isSunk());
		myBattleShip.updateShipStatus();
		myBattleShip.updateShipStatus();
		myBattleShip.updateShipStatus();
		assertTrue(myBattleShip.isSunk());
	}

	/*
	 * Test BattleShip types' size and permissible number
	 */
	@Test
	void testBattleShipTypes() {
		int numSmallShips = 3;
		int numMediumShips = 2;
		int numLargeShips = 1;
		int smallShipSize = 1;
		int mediumShipSize = 2;
		int largeShipSize = 3;

		// Create the different type of ships
		SmallBattleShip mySmallShip = new SmallBattleShip();
		MediumBattleShip myMediumShip = new MediumBattleShip();
		LargeBattleShip myLargeShip = new LargeBattleShip();

		// Verify the ship sizes and number of permissible ships of each type
		assertEquals(mySmallShip.getShipSize(), smallShipSize);
		assertEquals(SmallBattleShip.numberOfShips, numSmallShips);
		assertEquals(myMediumShip.getShipSize(), mediumShipSize);
		assertEquals(MediumBattleShip.numberOfShips, numMediumShips);
		assertEquals(myLargeShip.getShipSize(), largeShipSize);
		assertEquals(LargeBattleShip.numberOfShips, numLargeShips);
	}

}
