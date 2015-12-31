package boggle;

import java.util.ArrayList;

import boggle.Solver.Score;

public class Game {
	
	Display display;
	
	Player[] players;
	Board board;
	
	Solver solver;
	
	public Game(Display display) {
		this.display = display;
		this.solver = new Solver();
		
		initGame(display.maxPlayers);
		this.getNewBoard();
	}
	
	public void initGame(int maxPlayers) {
		players = new Player[maxPlayers];
		for (int i = 0; i < maxPlayers; i++) {
			players[i] = new Player(i + 1);
			players[i].setSearchSkills(5);
			players[i].setVocabularySkills(5);
		}
	}
	
	public void getNewBoard() {
		this.board = new FiveBoard();
	}
	
	public void solveBoard() {
		this.solver.setBoard(board.board);
		this.solver.findAllWords();
		this.solver.scoreWords();
		this.solver.printAll();
		
		generateWordLists(this.solver.allWords);
		printWordLists(this.solver.allWords);
	}
	
	public void generateWordLists(ArrayList<Score> words) {
		for (int i = 0; i < players.length; i++) {
			if (players[i].computer) {
				players[i].findWordsOnBoard(words);
				enterWordList(i);
			}
		}
	}
	
	public void enterWordList(int number) {
		// Clear Old List
		display.wordLists[number].setText("");
		
		// Enter New List
		String newList = "";
		ArrayList<String> words = players[number].myListOfWords;
		for (int i = 0; i < words.size(); i++) {
			newList += words.get(i) + "\n";
		}
		display.wordLists[number].setText(newList);
	}
	
	public void printWordLists(ArrayList<Score> words) {
		for (int i = 0; i < players.length; i++) {
			players[i].printWordList(words);
		}
	}
	
	public void scoreWordLists() {
		ArrayList<String> allWords = solver.getAllWords();
		
		ArrayList<String> masterList = new ArrayList<String>();
		ArrayList<String> duplicates = new ArrayList<String>();
		
		ArrayList<String> wordList;
		for (int i = 0; i < players.length; i++) {
			wordList = players[i].myListOfWords;
			for (int j = 0; j < wordList.size(); j++) {
				String word = wordList.get(j);
				if (masterList.contains(word)) {
					duplicates.add(word);
				}
				masterList.add(word);
			}
		}
		
		// Scoring
		int[] points;
		if (board.board.length == 4)
			points = Scorer.pointsFour;
		else
			points = Scorer.pointsFive;
		
		for (int i = 0; i < players.length; i++) {
			int totalScore = 0;
			
			wordList = players[i].myListOfWords;
			for (int j = 0; j < wordList.size(); j++) {
				String word = wordList.get(j);
				// Check if it is a word and not a duplicate
				if (allWords.contains(word) && !duplicates.contains(word)) {
					int wordLength = word.length();
					if (wordLength > points.length) {
						wordLength = points.length - 1;
					}
					totalScore += points[wordLength];
				}
			}
			
			// Update Score
			int currentScore = Integer.parseInt(display.scoreFields[i].getText());
			int newScore = currentScore + totalScore;
			display.scoreFields[i].setText("" + newScore);
		}
	}
	
}
