package boggle;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Display extends JPanel implements ActionListener {
	
	Board board;
	
	// Display Board
	public final int CUBE_SIZE = 75;
	
	// Display Properties
	public final int WIDTH = 400;
	public final int HEIGHT = 400;
	
	public final int BOARD_OFFSET_X = 5;
	public final int BOARD_OFFSET_Y = 10;
	
	public Display() {
		this.initDisplay();
		this.board = new FiveBoard();
	}
	
	private void initDisplay() {
		// Display Properties
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(true);
	}
	
	public void drawBoard(Graphics2D g) {	
		ArrayList<Cube> current = board.currentBoard;
		
		int row = 0;
		int col = 0;
		for (int i = 0; i < current.size(); i++) {
			// Calculate cube coordinates
			int cube_x = col * CUBE_SIZE + BOARD_OFFSET_X;
			int cube_y = row * CUBE_SIZE + BOARD_OFFSET_Y;
			
			this.drawCube(current.get(i), cube_x, cube_y, g);
			
			// Check for end of row
			if (col == board.square - 1) {
				row += 1;
				col = 0;
			}
			else {
				col += 1;
			}
		}
	}
	
	public void drawCube(Cube c, int x, int y, Graphics2D g) {
		Image img = c.getImage();
		
		// Rotate + Translate
		int rotationAngle = c.orientation * 90;
		AffineTransform transform = new AffineTransform();
		transform.translate(x, y);
		transform.rotate(Math.toRadians(rotationAngle), CUBE_SIZE / 2.0, CUBE_SIZE / 2.0);
		
		// Draw Image
		g.drawImage(img, transform, this);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        this.drawBoard(g2d);            
    }

	public void actionPerformed(ActionEvent ae) {
		// pass
	}

}
