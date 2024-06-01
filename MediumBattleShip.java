/*
 *  MediumBattleShip.java - Class to represents the medium battleship and its meta data in the game
 *  A game can have 2 medium battleships each of size 2.
 *  
 *  @author SaranyaLakshmi ArulJayaKumar
 *  @version 1 - October 2023                                           
 */

public class MediumBattleShip extends BattleShip {
	static int numberOfShips = 2;

	/*
	 * Constructor
	 * 
	 * @param shipSize - give the size of the ship
	 * 
	 */
	MediumBattleShip() {
		super(2);
	}

}
