/*
 * Square.java - Class to represents the individual cell in the board (10 x 10 grid)
 * 
 * 
 * Methods
 * -------
 * 	getRow					- Return the row size
 *  setRow					- Set the row size
 *  getColumn				- Return the column size
 *  setColumn				- Set the column size
 *  isHasShip				- Return status of whether ship is present or not
 *  setHasShip				- Set ship presence status
 *  isFired					- Return the fired status of the square
 *  setFired				- Set the fired status of the square
 *  getMyBattleShip			- Return the BattleShip reference
 *  setMyBattleShip			- Set the BattleShip associated with a square
 *  getSquareGuessStatus	- Return the guess status 
 *  toString				- Return string for a visual representation of the square
 *  
 *  @author SaranyaLakshmi ArulJayaKumar
 *  @version 1 - October 2023                                           
 */

public class Square {

	/*
	 * Attributes
	 */

	// Represents the row of the grid [0 9]
	private int row;
	// Represents the column of the grid [0 9]
	private int column;
	// Indicates whether the cell has a ship (true) or not (false)
	private boolean hasShip;
	// Indicates whether the cell has been fired (true) by the player or not (false)
	private boolean isFired;
	// Reference to the BattleShip currently on the square
	private BattleShip myBattleShip;

	/*
	 * Methods
	 */

	/*
	 * Constructors
	 * 
	 * @param row Specify the row of the cell.
	 * 
	 * @param column Specify the column of the cell.
	 * 
	 */
	Square(int row, int column) {
		// Initialize the attributes
		this.row = row;
		this.column = column;
		this.hasShip = false;
		this.isFired = false;
	}

	/*
	 * Getters and Setters
	 */

	/*
	 * Get the row
	 * 
	 * @return row The value of the row (integer).
	 * 
	 */
	public int getRow() {
		return row;
	}

	/*
	 * Set the row
	 * 
	 * @param row The value of the row (integer).
	 * 
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/*
	 * Get the column
	 * 
	 * @return column The value of the column (integer).
	 * 
	 */
	public int getColumn() {
		return column;
	}

	/*
	 * Set the column
	 * 
	 * @param column The value of the column (integer).
	 * 
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/*
	 * Indicates if a ship is present in the square or not.
	 * 
	 * @return hasShip True if a ship is present, false otherwise (boolean).
	 * 
	 */
	public boolean isHasShip() {
		return hasShip;
	}

	/*
	 * Set the ship status of the square.
	 *
	 * @param hasShip Set true if ship is present, else false (boolean).
	 * 
	 */
	public void setHasShip(boolean hasShip) {
		this.hasShip = hasShip;
	}

	/*
	 * Indicates if the square is fired or not.
	 * 
	 * @return isFired True if the square is fired, false otherwise (boolean).
	 * 
	 */
	public boolean isFired() {
		return isFired;
	}

	/*
	 * Set the fired status of the square.
	 * 
	 * @param isFired Set true if square is fired, else false (boolean).
	 * 
	 */
	public void setFired(boolean isFired) {
		this.isFired = isFired;
	}

	/*
	 * Get the reference of the battleShip in the square.
	 * 
	 * @return myBattleShip The reference of the battleShip (BattleShip).
	 * 
	 */
	public BattleShip getMyBattleShip() {
		return myBattleShip;
	}

	/*
	 * Set the battleShip in the square.
	 * 
	 * @param myBattleShip The reference of the battleShip (BattleShip).
	 * 
	 */
	public void setMyBattleShip(BattleShip myBattleShip) {
		this.myBattleShip = myBattleShip;
	}

	/*
	 * Get the guess status based on the hasShip and isFired status of the square
	 * 
	 * @param inputX represents the row of the square cell (integer)
	 * 
	 * @param inputY represents the column of the the square cell (integer)
	 * 
	 * @return guessStatus True if the guess is a hit, false otherwise (boolean)
	 * 
	 */
	public boolean getSquareGuessStatus(int inputX, int inputY) {
		boolean guessStatus = false;

		if (this.isFired()) {
			// If the square is already fired, print that the guess is invalid.
			System.out.println("The guess is invalid; the square has already been fired.");
		} else {
			// Update the square status to indicate it is fired if it not already fired
			this.setFired(true);

			// Now if the square has a battleship in it, then return true
			if (this.isHasShip()) {
				guessStatus = true;
				System.out.println("The guess is a hit!");
			} else {
				// If there are no ships then the guess is a miss
				System.out.println("It is a miss; there is no ship in the guessed square.");
			}
		}

		return guessStatus;
	}

	/*
	 * A visual representation of the square.
	 * 
	 * @return a symbol (String).
	 * 
	 */
	public String toString() {
		/*
		 * Each symbol returned represents the following,
		 * 
		 * "-" - The player has not interacted with the square.
		 * "X" - The player has fired and it is a hit.
		 * "O" - The player has fired and it is a miss.
		 */
		if (this.isFired() && isHasShip()) {
			return "X";
		} else if (this.isFired() && !isHasShip()) {
			return "O";
		} else {
			return "-";
		}
	}

}
