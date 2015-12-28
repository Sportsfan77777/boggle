package boggle;

import java.util.ArrayList;

public class Solver {
	
	Dictionary dict;
	
	int minWordLength;
	
	char[][] board;
	ArrayList<Score> allWords;
	
	public Solver() {
		dict = new Dictionary();
		allWords = new ArrayList<Score>();
	}
	
	public void setBoard(char[][] board) {
		this.board = board;
		
		int square = board.length;
		if (square <= 4)
			minWordLength = 3;
		else
			minWordLength = 4;
	}
	
	public int findWord(String s) {
		ArrayList<Integer> skillScores = new ArrayList<Integer>();
		
		return -1;
	}
	
	public ArrayList<Score> findAllWords() {
		int square = board.length;
		boolean[][] visited = new boolean[square][square];
		
		for (int i = 0; i < square; i++) {
			for (int j = 0; j < square; j++) {
				String start = String.valueOf(board[i][j]);
				finderHelper(start, i, j, visited);
			}
		}
		return allWords;
	}
	
	private void finderHelper(String s, int row, int col, boolean[][] visited) {
		boolean[][] visited_copy = copyArray(visited);
		visited_copy[row][col] = true;
		
		// Replace 'q' with 'qu'
		if (s.charAt(s.length() - 1) == 'q')
			s += 'u';
		
		// Check if this is a word
		int contains = dict.contains(s);
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
			boolean found = false;
			for (int i = 0; i < allWords.size(); i++) {
				if (allWords.get(i).word.equals(s)) {
					found = true;
					break;
				}
			}
			// it is longer than the minimum word length
			if (!found && s.length() >= minWordLength)
				allWords.add(new Score(s));
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
					if (visited_copy[x][y]) {
						// pass (visited already)
					}
					else {
						String newWord = s + board[x][y];
						finderHelper(newWord, x, y, visited_copy);
					}
				}
			}
		}
	}
	
	public void scoreWords() {
		scoreWords(allWords);
	}
	
	public void scoreWords(ArrayList<Score> words) {
		for (int i = 0; i < words.size(); i++) {
			String word = words.get(i).word;
			if (dict.isMostCommon(word)) {
				words.get(i).vocabScore = 3;
			}
			else if (dict.isCommon(word)) {
				words.get(i).vocabScore = 2;
			}
			else if (dict.isRare(word)) {
				words.get(i).vocabScore = 1;
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
		int rare = 0;
		int common = 0;
		int mostCommon = 0;
		
		System.out.println("All Words");
		for (int i = 0; i < allWords.size(); i++) {
			int vocab = allWords.get(i).vocabScore;
			System.out.println(allWords.get(i).word + " " + vocab);
			if (vocab == 3)
				mostCommon++;
			else if (vocab == 2)
				common++;
			else if (vocab == 1)
				rare++;
		}
		System.out.println("Number of Words: " + allWords.size());
		System.out.println("Number of Rare Words: " + rare);
		System.out.println("Number of Common Words: " + common);
		System.out.println("Number of Most Common Words: " + mostCommon);
		System.out.println();
	}
	
	public class Score {
		
		String word;
		int vocabScore; // 0, 1, 2, or 3 (not common, rare, common, most common)
		int skillScore;
		
		public Score(String word) {
			this.word = word;
			this.vocabScore = 0;
			this.setSkillScore();
		}
		
		public Score(String word, int vocab, int skill) {
			this.word = word;
			this.vocabScore = vocab;
			this.skillScore = skill;
		}
		
		private void setSkillScore() {
			skillScore = word.length();
			// Make words that end in 's' more noticeable
			char lastLetter = word.charAt(word.length() - 1);
			if (lastLetter == 's')
				skillScore -= 1;
		}
	}

}
