package core;

/**
 * Stores basic coordinate information about a location / point in 
 * the board/map grid.
 * 
 * @author Sanket Chandorkar
 *
 */
public class Pair {
	
	private int xCordinate;
	
	private int yCordinate;

	public Pair(int x, int y) {
		xCordinate = x;
		yCordinate = y;
	}

	public int getxCordinate() {
		return xCordinate;
	}

	public int getyCordinate() {
		return yCordinate;
	}
	
}