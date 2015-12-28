package boggle;

import java.util.ArrayList;

import boggle.Solver.Score;

public class Player {
	
	int number;
	boolean computer;
	
	ArrayList<String> myListOfWords;
	
	public Player(int number) {
		this.number = number;
		computer = false;
		myListOfWords = new ArrayList<String>();
	}
	
	
	public void printWordList(ArrayList<Score> allWords) {
		System.out.println("Word List for Player " + number);
		for (int i = 0; i < myListOfWords.size(); i++) {
			System.out.println(this.myListOfWords.get(i));
		}
		
		// Create All Word List
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i < allWords.size(); i++) {
			words.add(allWords.get(i).word);
		}
		
		int score = Scorer.scoreAllWords(5, myListOfWords, words);
		System.out.println("Total Score " + score);
		System.out.println();
	}
}
