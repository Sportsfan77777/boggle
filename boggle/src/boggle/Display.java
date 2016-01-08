package boggle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Display extends JPanel implements ActionListener {
	
	Game game;
	
	// Game Elements
	public final int maxPlayers = 4;
	
	// Display Board
	public final int CUBE_SIZE = 75;
	public int BOARD_SIZE = 375;
	
	// Display Properties
	public final int WIDTH = 1200;
	public final int HEIGHT = 550;
	
	public final int BOARD_OFFSET_X = (WIDTH - BOARD_SIZE) / 2;
	public final int BOARD_OFFSET_Y = 35;
	
	public final int SMALL_BOARD_OFFSET_X = 10;
	public final int SMALL_BOARD_OFFSET_Y = 10;
	
	// Timer
	Hourglass timer;
	
	// Board Controls
	JButton newBoardButton;
	JButton solveBoardButton;
	JButton scoreButton;
	JButton timerButton;
	
	// GUI Components
	JTextField[] nameFields = new JTextField[maxPlayers]; 
	JTextField[] scoreFields = new JTextField[maxPlayers]; 
	JLabel[] skillLabels = new JLabel[maxPlayers];
	JComboBox[] skillComboBoxes = new JComboBox[maxPlayers];
	JLabel[] vocabLabels = new JLabel[maxPlayers];
	JComboBox[] vocabComboBoxes = new JComboBox[maxPlayers];
	JTextField[] textFields = new JTextField[maxPlayers];
	JTextArea[] wordLists = new JTextArea[maxPlayers];
	JScrollPane[] scrollPanes = new JScrollPane[maxPlayers];
	
	public Display() {
		this.initDisplay();

		game = new Game(this);
		
		
	}
	
	private void initDisplay() {
		// Display Properties
		setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(true);
        
        // Layout
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        // BOARD
        int boardLocation = 2;
        int boardGridWidth = 1; 
        Component glue = javax.swing.Box.createGlue();
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = BOARD_SIZE - 250;
        c.gridx = boardLocation;
        //this.add(glue, c);
               
        newBoardButton = new JButton("New Board");
        newBoardButton.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				game.getNewBoard();
        				repaint();
        			}
        		});
        c.weightx = 1;
        c.ipadx = BOARD_SIZE - 100;
        c.gridx = boardLocation;
        c.gridy = 10;
        c.anchor = GridBagConstraints.ABOVE_BASELINE;
        this.add(newBoardButton, c);
        
        solveBoardButton = new JButton("Solve Board");
        solveBoardButton.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				game.solveBoard();
        				repaint();
        			}
        		});
        c.weightx = 1;
        c.ipadx = BOARD_SIZE - 100;
        c.gridx = boardLocation;
        c.gridy = 11;
        c.anchor = GridBagConstraints.ABOVE_BASELINE;
        this.add(solveBoardButton, c);
        
        scoreButton = new JButton("Score Word Lists");
        scoreButton.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				game.scoreWordLists();;
        				repaint();
        			}
        		});
        c.weightx = 1;
        c.ipadx = BOARD_SIZE - 100;
        c.gridx = boardLocation;
        c.gridy = 12;
        c.anchor = GridBagConstraints.ABOVE_BASELINE;
        this.add(scoreButton, c);
        
        // Timer
        //timer = new Hourglass(0, this); // initialize to zero
        timerButton = new JButton("Flip the Hourglass");
        timerButton.addActionListener(new HourglassListener(this));
        c.ipadx = BOARD_SIZE - 100;
        c.gridx = boardLocation;
        c.gridy = 14;
        c.anchor = GridBagConstraints.ABOVE_BASELINE;
        this.add(timerButton, c);
        
        // Different Players
        
        for (int i = 0; i < maxPlayers; i++) {
        	textFields[i] = new JTextField(50);
        	wordLists[i] = new JTextArea(200, 50);
        	scrollPanes[i] = new JScrollPane(wordLists[i], JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        	
        	// Name
        	
        	nameFields[i] = new JTextField(20);
        	nameFields[i].setText("Player " + (i+1));
        	nameFields[i].setHorizontalAlignment(SwingConstants.CENTER);
            c.weightx = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.ABOVE_BASELINE;
            if (i < boardLocation)
            	c.gridx = i;
            else
            	c.gridx = i+1;
            c.gridy = 0;
            c.insets = new Insets(5, 5, 5, 65);
            c.ipadx = 0;
            c.ipady = 0;
            this.add(nameFields[i], c);
            
            // Score
            
            scoreFields[i] = new JTextField(10);
            scoreFields[i].setText("0");
            scoreFields[i].setEditable(false);
            scoreFields[i].setHorizontalAlignment(SwingConstants.CENTER);
            c.weightx = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.NORTHWEST;
            if (i < boardLocation)
            	c.gridx = i;
            else
            	c.gridx = i+1;
            c.gridy = 0;
            c.insets = new Insets(5, 135, 5, 5);
            c.ipadx = 0;
            c.ipady = 0;
            this.add(scoreFields[i], c);
        	
        	// Select Skill Level
        	int skillY = 2;
        	
        	skillLabels[i] = new JLabel("Skill:");
        	skillLabels[i].setForeground(Color.WHITE);
        	c.weightx = 1;
        	c.fill = GridBagConstraints.NONE;
        	c.anchor = GridBagConstraints.WEST;
        	if (i < boardLocation)
            	c.gridx = i;
            else
            	c.gridx = i+1;
        	c.gridy = skillY;
        	c.insets = new Insets(5, 5, 5, 5);
        	c.ipadx = 0;
        	c.ipady = 0;
        	this.add(skillLabels[i], c);
        	
        	String[] skillLevels = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        	skillComboBoxes[i] = new JComboBox(skillLevels);
        	skillComboBoxes[i].setSelectedIndex(0);
        	skillComboBoxes[i].addActionListener(new ComboBoxListener("skill", i));
        	c.weightx = 1;
        	c.fill = GridBagConstraints.NONE;
        	c.anchor = GridBagConstraints.EAST;
        	if (i < boardLocation)
            	c.gridx = i;
            else
            	c.gridx = i+1;
        	c.gridy = skillY;
        	c.insets = new Insets(0, 0, 0, 0);
        	c.ipadx = 0;
        	c.ipady = 0;
        	this.add(skillComboBoxes[i], c);
        	
        	// Select Vocabulary Level
        	int vocabY = 3;
        	
        	vocabLabels[i] = new JLabel("Vocabulary:");
        	vocabLabels[i].setForeground(Color.WHITE);
        	
        	c.weightx = 1;
        	c.fill = GridBagConstraints.NONE;
        	c.anchor = GridBagConstraints.WEST;
        	if (i < boardLocation)
            	c.gridx = i;
            else
            	c.gridx = i+1;
        	c.gridy = vocabY;
        	c.insets = new Insets(5, 5, 5, 5);
        	c.ipadx = 0;
        	c.ipady = 10;
        	this.add(vocabLabels[i], c);
        	
        	String[] vocabLevels = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        	vocabComboBoxes[i] = new JComboBox(vocabLevels);
        	vocabComboBoxes[i].setSelectedIndex(0);
        	vocabComboBoxes[i].addActionListener(new ComboBoxListener("vocabulary", i));
        	c.weightx = 1;
        	c.fill = GridBagConstraints.NONE;
        	c.anchor = GridBagConstraints.EAST;
        	if (i < boardLocation)
            	c.gridx = i;
            else
            	c.gridx = i+1;
        	c.gridy = vocabY;
        	c.insets = new Insets(0, 0, 0, 0);
        	c.ipadx = 0;
        	c.ipady = 0;
        	this.add(vocabComboBoxes[i], c);
        	
        	// Add words to word list
        	
            textFields[i].addActionListener(new TextActionListener(i, textFields[i], wordLists[i]));
            c.weightx = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.BASELINE;
            if (i < boardLocation)
            	c.gridx = i;
            else
            	c.gridx = i+1;
            c.gridy = 4;
            c.insets = new Insets(0, 0, 0, 0);
            c.ipadx = 0;
            c.ipady = 10;
            this.add(textFields[i], c);
            
            // Word Lists

            wordLists[i].setEditable(false);            
            c.weightx = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            if (i < boardLocation)
            	c.gridx = i;
            else
            	c.gridx = i+1;
            c.gridy = 5;
            int inset = 4;
            c.insets = new Insets(inset, inset, inset, inset);
            c.ipadx = 0;
            c.ipady = 250;
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
		if (game.board == null)
			return; // not initialized
		
		ArrayList<Cube> current = game.board.currentBoard;
		
		int row = 0;
		int col = 0;
		for (int i = 0; i < current.size(); i++) {
			// Calculate cube coordinates
			int cube_x = col * CUBE_SIZE + BOARD_OFFSET_X;
			int cube_y = row * CUBE_SIZE + BOARD_OFFSET_Y;
			
			this.drawCube(current.get(i), cube_x, cube_y, g);
			
			// Check for end of row
			if (col == game.board.square - 1) {
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
	
	public void drawTimer(Graphics2D g) {
		if (timer != null) {
			String time = timer.getTime();
			g.setColor(Color.ORANGE);
			g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 16));
			g.drawString(time, (WIDTH - 35) / 2, 25);
		}
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        this.drawBoard(g2d);
        this.drawTimer(g2d);
        
        Toolkit.getDefaultToolkit().sync();
    }

	public void actionPerformed(ActionEvent ae) {
		// pass
	}
	
	private class TextActionListener implements ActionListener {
		int player;
		JTextField tf;
		JTextArea wordList;
		
		public TextActionListener(int player, JTextField tf, JTextArea wordList) {
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
	
	private class ComboBoxListener implements ActionListener {
		
		String type;
		int number;
		
		public ComboBoxListener(String s, int number) {
			this.type = s;
			this.number = number;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox box = (JComboBox)e.getSource();
			String selection = (String) box.getSelectedItem();
			
			if (type == "skill") {
				if (selection == "0") {
					game.players[number].computer = false;
				}
				else {
					game.players[number].computer = true;
					game.players[number].setSearchSkills(Integer.parseInt(selection));
					
				}
			}
			else {
				if (selection == "0") {
					game.players[number].computer = false;
				}
				else {
					game.players[number].computer = true;
					game.players[number].setVocabularySkills(Integer.parseInt(selection));
				}
			}
		}
	}
	
	private class HourglassListener implements ActionListener {
		
		Display d;
		
		public HourglassListener(Display d) {
			this.d = d;
		}

		public void actionPerformed(ActionEvent e) {
			timer = new Hourglass(d); // initialize to countdown
			timer.start(); // start countdown
		}
	}

}
