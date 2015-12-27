package boggle;

import java.util.ArrayList;

public class Solver {
	
	Dictionary d;
	
	int minWordLength;
	
	char[][] board;
	ArrayList<String> allWords;
	
	public Solver() {
		d = new Dictionary();
		allWords = new ArrayList<String>();
	}
	
	public void setBoard(char[][] board) {
		this.board = board;
		
		int square = board.length;
		if (square <= 4)
			minWordLength = 3;
		else
			minWordLength = 4;
	}
	
	public void findAllWords() {
		int square = board.length;
		boolean[][] visited = new boolean[square][square];
		
		for (int i = 0; i < square; i++) {
			for (int j = 0; j < square; j++) {
				String start = String.valueOf(board[i][j]);
				finderHelper(start, i, j, visited);
			}
		}
		
	}
	
	private void finderHelper(String s, int row, int col, boolean[][] visited) {
		visited[row][col] = true;
		
		// Check if this is a word
		int contains = d.contains(s);
		if (contains == -1) {
			// no more words with this prefix
			return;
		}
		else if (contains == 0) {
			// this isn't a word but there might be more words with this prefix
			// continue
		}
		else {
			// this is a word
			// it is not already in the list
			// it is longer than the minimum word length
			if (!allWords.contains(s) && s.length() >= minWordLength)
				allWords.add(s);
			// continue
		}
		
		// Check if there are more words with this prefix
		int square = board.length;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int x = row + i;
				int y = col + j;
				if (x < 0 || y < 0 || x >= square || y >= square) {
					// pass (out of bounds)
				}
				else {
					if (visited[x][y]) {
						// pass (visited already)
					}
					else {
						String newWord = s + board[x][y];
						boolean[][] visited_copy = copyArray(visited);
						finderHelper(newWord, x, y, visited_copy);
					}
				}
			}
		}
	}
	
	private boolean[][] copyArray(boolean[][] array) {
		int square = array.length;
		boolean[][] newArray = new boolean[square][square];
		
		for (int i = 0; i < square; i++) {
			for (int j = 0; j < square; j++) {
				newArray[i][j] = array[i][j];
			}
		}
		
		return newArray;
	}
	
	public void printAll() {
		System.out.println("All Words");
		for (int i = 0; i < allWords.size(); i++) {
			System.out.println(allWords.get(i));
		}
		System.out.println("Number of Words: " + allWords.size());
	}

}
