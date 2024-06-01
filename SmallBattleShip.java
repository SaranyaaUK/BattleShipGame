/*
 *  SmallBattleShip.java - Class to represents the small battleship and its meta data in the game
 *  A game can have 3 small battleships each of size 1.
 *  
 *  @author SaranyaLakshmi ArulJayaKumar
 *  @version 1 - October 2023                                           
 */

public class SmallBattleShip extends BattleShip {

	static int numberOfShips = 3;

	/*
	 * Constructor
	 * 
	 * @param shipSize - give the size of the ship
	 * 
	 */
	SmallBattleShip() {
		super(1);
	}

	/*
	 * Generate Small Battleship
	 * 
	 */
	public void generateBattleShip(int rowSize, int columnSize) {
		boolean isShipOverlapping = false;

		// For Small BattleShip because of size 1 it just needs one position (randomly
		// generated, this will be bounded already) and check that position for only
		// overlap.
		do {
			// Get the ship start position
			this.getShipStartPosition(rowSize, columnSize);
			// Validate for any overlap
			Square cellData = Board.cellData[this.shipStartRow][this.shipStartColumn];
			isShipOverlapping = cellData.isHasShip();
		} while (isShipOverlapping);

		// Update the status of the Square object to reflect the ship's presence
		this.setShipStatusInSquare(this.shipStartRow, this.shipStartColumn);
	}

}
