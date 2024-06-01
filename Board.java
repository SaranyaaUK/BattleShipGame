/*
 *  Board.java - Class to represent the board where the game is played.
 *  
 *  Methods
 *  -------
 *  getSquare			- Get the square object given the row and column
 *  toString			- Get a visual representation of the board
 *  populateCellData	- Populate the board cells with square objects
 *  generateBattleShips	- Generate different battleships for the game play
 *  getRowSize			- Return the row size of the board
 *  setRowSize			- Set the row size of the board
 *  getColumnSize		- Return the column size of the board
 *  setColumnSize		- Set the column size of the board
 *  
 *  
 *  @author SaranyaLakshmi ArulJayaKumar
 *  @version 1 - October 2023                                           
 */

public class Board {

	/*
	 * Attributes
	 */

	// Specifies the row size of the board
	private int rowSize;
	// Specifies the column size of the board
	private int columnSize;
	// Data structure to hold the square objects
	static Square[][] cellData;

	/*
	 * Methods
	 */

	/*
	 * Constructor
	 * 
	 * @param row Gives the row size of the board
	 * 
	 * @param column Gives the column size of the board
	 */
	Board(int row, int column) {

		// Initialize the attributes
		this.rowSize = row;
		this.columnSize = column;
		// Initialize the cellData
		Board.cellData = new Square[this.rowSize][this.columnSize];
		// Populate the cells with square object and generate the ship positions
		this.populateCellData();
		this.generateBattleShips();
	}

	/*
	 * Getters and Setters
	 */

	/*
	 * Get the board's row size
	 * 
	 * @return rowSize Row size of the board (integer).
	 */
	public int getRowSize() {
		return rowSize;
	}

	/*
	 * Set the board's row size
	 * 
	 * @param rowSize Row size of the board (integer).
	 * 
	 */
	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	/*
	 * Get the board's column size
	 * 
	 * @return columnSize Column size of the board (integer).
	 * 
	 */
	public int getColumnSize() {
		return columnSize;
	}

	/*
	 * Set the board's column size
	 * 
	 * @return columnSize Column size of the board (integer).
	 * 
	 */
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	/*
	 * Get a specific square object given the row and column
	 * 
	 * @param rowSize Row size of the board (integer).
	 * 
	 * @param columnSize Column size of the board (integer).
	 * 
	 */
	public Square getSquare(int row, int column) {
		return Board.cellData[row][column];
	}

	/*
	 * A visual representation of the board.
	 * 
	 * @return a symbol (String).
	 * 
	 */
	public String toString(int row, int column) {
		return Board.cellData[row][column].toString();
	}

	/*
	 * Private Methods
	 */

	/*
	 * Populate board cells with square object
	 * 
	 */
	protected void populateCellData() {
		// Run loop to initialize the cell details held in 2d array (cellData)
		for (int i = 0; i < this.rowSize; i++) {
			for (int j = 0; j < this.columnSize; j++) {
				Board.cellData[i][j] = new Square(i, j);
			}
		}
	}

	/*
	 * generateBattleShips()
	 * 
	 */
	protected void generateBattleShips() {
		// 3 Small BattleShips
		// 2 Medium BattleShips
		// 1 Large BattleShip

		// Generate permissible number of small sized battle ships
		for (int i = 0; i < SmallBattleShip.numberOfShips; i++) {
			SmallBattleShip mySmallBattleShip = new SmallBattleShip(); // SmallBattleShip
			mySmallBattleShip.generateBattleShip(this.rowSize, this.columnSize);
		}

		// Generate permissible number of medium sized battle ships
		for (int j = 0; j < MediumBattleShip.numberOfShips; j++) {
			MediumBattleShip myMediumBattleShip = new MediumBattleShip(); // MediumBattleShip
			myMediumBattleShip.generateBattleShip(this.rowSize, this.columnSize);
		}

		// Generate permissible number of large sized battle ship/s
		for (int k = 0; k < LargeBattleShip.numberOfShips; k++) {
			LargeBattleShip myLargeBattleShip = new LargeBattleShip(); // LargeBattleShip
			myLargeBattleShip.generateBattleShip(this.rowSize, this.columnSize);
		}
	}

}
