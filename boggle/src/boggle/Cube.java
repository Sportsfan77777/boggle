package boggle;

import java.util.Random;

/**
 * Cube on a boggle board
 *
 */
public class Cube {
	String letters;
	
	Random random;
	int face = 0; // 0 - 5 (Faces 1 through 6)
	int orientation = 0; // 0 - 3 (Up, Right, Down, Left)
	
	public Cube(String letters) {
		if (letters.length() != 6) {
			
		}
		this.letters = letters;
		
		long seed = System.currentTimeMillis();
		random = new Random(seed); // random number generator
	}
	
	/**
	 * get current letter of cube (based on orientation)
	 * @return
	 */
	public char getLetter() {
		return letters.charAt(face);
	}
	
	/**
	 * 
	 */
	public void randomizeFace() {
		face = random.nextInt(6);
	}
	
	/**
	 * 
	 */
	public void randomizeOrientation() {
		orientation = random.nextInt(4);
	}
}
