package boggle;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

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
	 * returns corresponding letter image (rotation not included)
	 * @return
	 */
	public Image getImage() {
		char letter = this.getLetter();
		char upperLetter = Character.toUpperCase(letter);
		String fn = String.format("images/letter%c.jpg", upperLetter);
	    BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fn));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
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
