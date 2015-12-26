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
	
	char[][] board;
	
	public Board() {
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
		ArrayList<Cube> tmp = (ArrayList<Cube>)cubes.clone();
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
