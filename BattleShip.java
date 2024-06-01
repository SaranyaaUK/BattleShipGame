/*
 *  BattleShip.java - Class to represents each battleship and its meta data in the game
 *  
 *  Methods
 *  -------
 *  
 *  isSunk						- Return the ship sink status	
 *  setSunk						- Set the ship sink status
 *  getShipSize					- Return the ship size
 *  getShipHealth				- Return the ship health
 *  updateShipStatus			- Update the ship status
 *  generateBattleShip			- Generate ship of a given type
 *  getTotalBattleShipsCount	- Get the total number of ships allowed in the game play
 *  
 *  @author SaranyaLakshmi ArulJayaKumar
 *  @version 1 - October 2023                                           
 */

import java.util.Random;

public class BattleShip {

	/*
	 * Attributes
	 */

	// Indicates if the ship is sank or not
	private boolean isSunk;
	// Specifies number of hits the battleship can take
	private int shipHealth;
	// Specifies the size of the ship
	private int shipSize;
	// Indicates the number of ships sunk
	static int numberOfSunkShips = 0;

	// Attributes for ship placement usage (should be private)
	protected int shipStartRow;
	protected int shipStartColumn;
	private int shipEndRow;
	private int shipEndColumn;
	private boolean shipOrientation;
	private boolean shipDirection;

	/*
	 * Methods
	 */

	/*
	 * Constructor
	 * 
	 * @param size - give the size of the ship
	 * 
	 */
	BattleShip(int size) {
		// Size of the ship corresponds to the health of the ship
		// that is, if ship is of size N units (occupies N squares) then
		// its initial health is N units.

		this.shipSize = size;
		this.shipHealth = size;
	}

	/*
	 * Getters and Setters
	 */

	/*
	 * Get the sink status of the ship
	 * 
	 * @return isSunk True if the ship has sunk, false otherwise (boolean)
	 * 
	 */
	public boolean isSunk() {
		return isSunk;
	}

	/*
	 * Set the ship's sink status
	 * 
	 * @param isSunk Set true if the ship has sunk, false otherwise (boolean)
	 * 
	 */
	public void setSunk(boolean isSunk) {
		this.isSunk = isSunk;
	}

	/*
	 * Get the health of the ship
	 * 
	 * @return shipHealth Gives the current health of the ship (integer).
	 * 
	 */
	public int getShipHealth() {
		return shipHealth;
	}

	/*
	 * Set the health of the ship
	 * 
	 * @param shipHealth Set the health of the ship (integer).
	 * 
	 */
	private void setShipHealth(int shipHealth) {
		this.shipHealth = shipHealth;
	}

	/*
	 * Get the ship size
	 * 
	 * @return shipSize Gives the size of the ship (integer).
	 * 
	 */
	public int getShipSize() {
		return shipSize;
	}

	/*
	 * Update the BattleShip health
	 * 
	 * If the ship is fired decrease its health by 1 and if the ship has sunk
	 * update the sink status of the ship, and the sunk ships count.
	 * 
	 */
	public void updateShipStatus() {
		// Set the ship health
		this.setShipHealth(this.getShipHealth() - 1);
		// Check and update BattleShip's sink status
		if (this.getShipHealth() == 0) {
			this.setSunk(true);
			// Update the sunk ships count
			BattleShip.numberOfSunkShips += 1;
		}
	}

	/*
	 * Ship Placement helper methods
	 */

	/*
	 * Generate battleship of a given type (invoking ship object)
	 * 
	 * @param rowSize Specify the row of the grid (integer)
	 * 
	 * @param columnSize Specify the column of the grid (integer)
	 * 
	 */
	public void generateBattleShip(int rowSize, int columnSize) {
		boolean isShipPlacementValid = false;

		// Randomize the ship placement and check its validity (done until valid
		// positions are obtained)
		do {

			// Get the ship start position, orientation and direction.
			this.getShipStartPosition(rowSize, columnSize);
			this.getShipOrientation(); // Vertical or horizontal
			this.getShipDirection(); // North-South/East-West

			// Calculate the end position based on the above data
			this.getShipEndPosition();

			// Validate the ship's placement by checking for overlap and unbound position
			// values
			isShipPlacementValid = this.validateShipPlacement(rowSize, columnSize);
		} while (!isShipPlacementValid);

		// Update the status of the Square object to reflect the ship's presence
		this.setShipStatus();
	}

	/*
	 * Randomly generate the ship start position
	 * 
	 * @param rowSize Specify the row of the grid (integer)
	 * 
	 * @param columnSize Specify the column of the grid (integer)
	 * 
	 */
	protected void getShipStartPosition(int rowSize, int columnSize) {
		// Get a start position for the ship
		Random randomGridPosition = new Random();
		this.shipStartRow = randomGridPosition.nextInt(rowSize);
		this.shipStartColumn = randomGridPosition.nextInt(columnSize);
	}

	/*
	 * Randomize the ship orientation
	 * 
	 * TRUE - VERTICAL placement
	 * FALSE - HORIZONTAL placement
	 */
	private void getShipOrientation() {
		Random randomOrientation = new Random();
		this.shipOrientation = randomOrientation.nextBoolean();
	}

	/*
	 * Randomize the ship direction
	 * 
	 * TRUE - NORTH/WEST placement
	 * FALSE - SOUTH/EAST placement
	 */
	private void getShipDirection() {
		Random randomOrientation = new Random();
		this.shipDirection = randomOrientation.nextBoolean();
	}

	/*
	 * Get the ship end position based on its start position, orientation, and
	 * direction.
	 * 
	 */
	private void getShipEndPosition() {
		/*
		 * ==========================================================================================================
		 * ORIENTATION 		DIRECTION 					ROW 									COLUMN
		 * ===========================================================================================================
		 * False (H) 		False (East) 		startPosition[0](Fixed) 			startPostition[1] + (ShipSize-1)
		 * False (H) 		True (West) 		startPosition[0](Fixed) 			startPostition[1] - (ShipSize-1)
		 * True (V) 		False (South)	 	startPosition[0] - (ShipSize-1) 	startPostition[1] (Fixed)
		 * True (Vertical) 	True (North) 		startPosition[0] + (ShipSize-1) 	startPostition[1] (Fixed)
		 *  
		 * 
		 */
		int myRow = this.shipStartRow;
		int myColumn = this.shipStartColumn;

		if (this.shipOrientation) {
			// Ship is oriented VERTICALLY
			if (this.shipDirection) {
				// Ship facing NORTH
				myRow += (this.shipSize - 1); // row = row + (shipSize-1)
			} else {
				// Ship facing SOUTH
				myRow -= (this.shipSize - 1); // row = row - (shipSize-1)
			}
		} else {
			// Ship is oriented HORIZONTALLY
			if (this.shipDirection) {
				// Ship facing WEST
				myColumn -= (this.shipSize - 1); // column = column - (shipSize-1)
			} else {
				// Ship facing EAST
				myColumn += (this.shipSize - 1); // column = column + (shipSize-1)
			}
		}

		this.shipEndRow = myRow;
		this.shipEndColumn = myColumn;
	}

	/*
	 * Validate the ship placement to avoid ships hanging out of the board and
	 * overlapping with other ships.
	 * 
	 * @param {integer} - Row of the board
	 * 
	 * @param {integer} - Column of the board
	 * 
	 * @return {boolean} - Specifies the validity of the ship placement
	 * 
	 */
	private boolean validateShipPlacement(int rowSize, int columnSize) {
		/*
		 * Use the start and end positions calculated to validate the ship's positions.
		 * The ships cannot hang out the board and their positions cannot overlap.
		 * 
		 */

		int myEndRow = this.shipEndRow;
		int myEndColumn = this.shipEndColumn;
		boolean isValidPlacement = false;

		// Check the end position to make sure it is bounded within the given grid size
		boolean isColumnValid = ((0 <= myEndColumn) && (myEndColumn <= (columnSize - 1)));
		boolean isRowValid = ((0 <= myEndRow) && (myEndRow <= (rowSize - 1)));

		// If row and column values are valid, then ship is bounded within the board.
		// Now check for the overlapping, if the ships are not overlapping then return
		// true to indicate it is a valid placement.
		if (isColumnValid && isRowValid && !this.getShipsOverlapStatus()) {
			isValidPlacement = true;
		}

		return isValidPlacement;
	}

	/*
	 * Get the ships' overlap status
	 * 
	 * @return {boolean} - Specifies whether there is an overlap in the ship
	 * placement
	 * 
	 */
	private boolean getShipsOverlapStatus() {
		// Initialize variables for ship position and overlap check
		int myStartRow = this.shipStartRow;
		int myStartColumn = this.shipStartColumn;
		int myEndRow = this.shipEndRow;
		int myEndColumn = this.shipEndColumn;
		boolean shipsOverlap = false;
		Square[][] cellData = Board.cellData;

		// Define boundaries for loop
		int minRow = Math.min(myStartRow, myEndRow);
		int maxRow = Math.max(myStartRow, myEndRow);
		int minColumn = Math.min(myStartColumn, myEndColumn);
		int maxColumn = Math.max(myStartColumn, myEndColumn);

		// Loop over the ship positions to determine if there's a overlap

		// For vertically oriented ships loop over the row and for horizontally
		// oriented ships loop over the column to check for overlap
		if (this.shipOrientation) {
			// Check the vertically oriented ship positions for any overlap
			for (int i = minRow; i <= maxRow; i++) {
				// If the square has a ship already indicate that the ships overlap
				if (cellData[i][myStartColumn].isHasShip()) {
					shipsOverlap = true;
					break;
				}
			}
		} else {
			// Check the horizontally oriented ship positions for any overlap
			for (int i = minColumn; i <= maxColumn; i++) {
				// If the square has a ship already indicate that the ships overlap
				if (cellData[myStartRow][i].isHasShip()) {
					shipsOverlap = true;
					break;
				}
			}
		}
		return shipsOverlap;
	}

	/*
	 * Set the ship status
	 * 
	 */
	private void setShipStatus() {
		// Initialize variables for ship position and overlap check
		int myStartRow = this.shipStartRow;
		int myStartColumn = this.shipStartColumn;
		int myEndRow = this.shipEndRow;
		int myEndColumn = this.shipEndColumn;

		// Define boundaries for loop
		int minRow = Math.min(myStartRow, myEndRow);
		int maxRow = Math.max(myStartRow, myEndRow);
		int minColumn = Math.min(myStartColumn, myEndColumn);
		int maxColumn = Math.max(myStartColumn, myEndColumn);

		// Loop over the ship positions and set the ship status
		if (this.shipOrientation) {
			// Vertically oriented ship
			for (int i = minRow; i <= maxRow; i++) {
				this.setShipStatusInSquare(i, myStartColumn);
			}
		} else {
			// Horizontally oriented ship
			for (int i = minColumn; i <= maxColumn; i++) {
				this.setShipStatusInSquare(myStartRow, i);
			}
		}
	}

	/*
	 * Update the square object to reflect the ship placement
	 * 
	 */
	protected void setShipStatusInSquare(int row, int column) {
		Square[][] cellData = Board.cellData;
		// Set the sqaure's (referred by row and column) ship status to true, to
		// indicate the ship placement
		cellData[row][column].setMyBattleShip(this); // Pass the current ship's reference (invoking object)
		cellData[row][column].setHasShip(true);
	}

	/*
	 * Return the total number of ships allowed in the game play
	 * 
	 */
	public static int getTotalBattleShipsCount() {
		// Add all permissble number of ship types to get the total ship count in the
		// game
		return SmallBattleShip.numberOfShips +
				MediumBattleShip.numberOfShips +
				LargeBattleShip.numberOfShips;
	}
}
