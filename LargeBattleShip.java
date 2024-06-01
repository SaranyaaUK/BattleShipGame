/*
 *  LargeBattleShip.java - Class to represents the large battleship and its meta data in the game
 *  A game can have 1 large battleship of size 3
 *  
 *  @author SaranyaLakshmi ArulJayaKumar
 *  @version 1 - October 2023                                           
 */

public class LargeBattleShip extends BattleShip {
	static int numberOfShips = 1;

	/*
	 * Constructor
	 * 
	 * @param shipSize - give the size of the ship
	 * 
	 */
	LargeBattleShip() {
		super(3);
	}

}
