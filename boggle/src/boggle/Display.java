package boggle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Display extends JPanel implements ActionListener {
	
	Board board;
	
	// Game Elements
	public final int maxPlayers = 4;
	
	// Display Board
	public final int CUBE_SIZE = 75;
	public int BOARD_SIZE = 375;
	
	// Display Properties
	public final int WIDTH = 1200;
	public final int HEIGHT = 550;
	
	public final int BOARD_OFFSET_X = (WIDTH - BOARD_SIZE) / 2;
	public final int BOARD_OFFSET_Y = 50;
	
	public final int SMALL_BOARD_OFFSET_X = 10;
	public final int SMALL_BOARD_OFFSET_Y = 10;
	
	// GUI Components
	JComboBox[] comboBoxes = new JComboBox[maxPlayers];
	JTextField[] textFields = new JTextField[maxPlayers];
	JTextArea[] wordLists = new JTextArea[maxPlayers];
	JScrollPane[] scrollPanes = new JScrollPane[maxPlayers];
	
	public Display() {
		this.initDisplay();
		this.board = new FiveBoard();
		
		Solver s = new Solver();
		s.setBoard(board.board);
		s.findAllWords();
		s.scoreWords();
		s.printAll();
		
		Game g = new Game(maxPlayers);
		g.generateWordLists(s.allWords);
		g.printWordLists(s.allWords);
	}
	
	private void initDisplay() {
		// Display Properties
		setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(true);
        
        // Button
        JButton button;
        
        // Layout
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        // BOARD
        int boardLocation = 2;
        Component glue = javax.swing.Box.createGlue();
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = BOARD_SIZE;
        c.gridx = boardLocation;
        this.add(glue, c);
        
        for (int i = 0; i < maxPlayers; i++) {
        	textFields[i] = new JTextField(10);
        	wordLists[i] = new JTextArea(200, 1);
        	scrollPanes[i] = new JScrollPane(wordLists[i], JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        	
            textFields[i].addActionListener(new textActionListener(i, textFields[i], wordLists[i]));
            c.weightx = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            if (i < boardLocation)
            	c.gridx = i;
            else
            	c.gridx = i+1;
            c.gridy = 0;
            c.insets = new Insets(0, 0, 0, 0);
            c.ipadx = 0;
            c.ipady = 0;
            this.add(textFields[i], c);
            
            wordLists[i].setEditable(false);
            
            c.weightx = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            if (i < boardLocation)
            	c.gridx = i;
            else
            	c.gridx = i+1;
            c.gridy = 1;
            int inset = 4;
            c.insets = new Insets(inset, inset, inset, inset);
            c.ipadx = 0;
            c.ipady = 200;
            this.add(scrollPanes[i], c);
            
        }
        
        
     /*
        button = new JButton("Button 2");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        this.add(button, c);
     
        button = new JButton("Button 3");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.25;
        c.gridx = 2;
        c.gridy = 0;
        this.add(button, c);
     
        button = new JButton("Long-Named Button 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      // make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        this.add(button, c);
     
        button = new JButton("5");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       // reset to default
        c.weighty = 1.0;   // request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; // bottom of space
        c.insets = new Insets(10,0,0,0);  // top padding
        c.gridx = 1;       // aligned with button 2
        c.gridwidth = 2;   // 2 columns wide
        c.gridy = 2;       // third row
        this.add(button, c);
        */
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
        
        Toolkit.getDefaultToolkit().sync();
    }

	public void actionPerformed(ActionEvent ae) {
		// pass
	}
	
	private class textActionListener implements ActionListener {
		int player;
		JTextField tf;
		JTextArea wordList;
		
		public textActionListener(int player, JTextField tf, JTextArea wordList) {
			this.player = player;
			this.tf = tf;
			this.wordList = wordList;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String word = tf.getText();
			String words = wordList.getText();
			
			String newWordList = "";
			if (words.length() == 0)
				newWordList = word;
			else
				newWordList = words + "\n" + word; 
			wordList.setText(newWordList);
			tf.setText("");
		}
		
	}

}
