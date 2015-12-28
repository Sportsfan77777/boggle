package boggle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Boggle Board
 *
 */
public abstract class Board {
	ArrayList<Cube> cubes;
	ArrayList<Cube> currentBoard;
	
	int square; // length of board
	char[][] board;
	
	public Board(int length) {
		this.cubes = new ArrayList<Cube>();
		this.currentBoard = new ArrayList<Cube>();
		
		this.square = length;
		board = new char[this.square][this.square];
		
		addCubes();
		setupBoard();
	}
	
	/**
	 * 
	 */
	public abstract void addCubes();
	
	public void setupBoard() {
		this.shuffleBoard();
		this.generateBoard();
	}
	
	public void shuffleBoard() {
		// Copy cubes to tmp array
		ArrayList<Cube> tmp = new ArrayList<Cube>(); 
		for (int i = 0; i < cubes.size(); i++) {
			tmp.add(cubes.get(i));
		}
		// Set up new order of cubes
		ArrayList<Cube> newBoard = new ArrayList<Cube>();
		
		// Randomize Board (Cube Order)
		long seed = System.currentTimeMillis();
		Random random = new Random(seed); // random number generator
		
		for (int i = 0; i < cubes.size(); i++) {
			int number = cubes.size() - i;
			int chosen = random.nextInt(number);
			
			newBoard.add(tmp.get(chosen));
			tmp.remove(chosen);
		}
		
		this.currentBoard = newBoard;
	}
	
	public void generateBoard() {
		for (Cube cube : this.currentBoard) {
			cube.randomizeFace();
			cube.randomizeOrientation();
		}
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				int index = board.length * i + j;
				char cubeLetter = this.currentBoard.get(index).getLetter();
				board[i][j] = cubeLetter;
			}
		}
	}
	
	public void prettyPrint() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
	
}
