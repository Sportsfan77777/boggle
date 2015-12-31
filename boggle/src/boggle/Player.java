package boggle;

import java.util.ArrayList;
import java.util.Random;

import boggle.Solver.Score;

public class Player {
	
	int number;
	boolean computer;
	
	ArrayList<String> myListOfWords;
	
	// Computer Options
	int skill = 0;
	int vocabulary = 0;
	
	int[] skillPercentages = new int[]{100, 100, 100, 100, 100, 
										100, 100, 100, 100, 100};
	
	int mostCommonPercent = 0;
	int commonPercent = 0;
	int rarePercent = 0;
	int otherPercent = 0;
	
	public Player(int number) {
		this.number = number;
		computer = true;
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
	
	// Computer Play
	public void findWordsOnBoard(ArrayList<Score> words) {
		// Reset list
		myListOfWords.clear();
		
		// Build new list of words
		
		Random random = new Random(System.nanoTime());
		// Future: First, randomize the list
		
		for (int i = 0; i < words.size(); i++) {
			Score word = words.get(i);
			int randomInt = random.nextInt(10000);
			
			// Vocabulary
			int vocab = word.vocabScore;
			
			int vocabPercent = 0;
			if (vocab == 3)
				vocabPercent = mostCommonPercent;
			else if (vocab == 2)
				vocabPercent = commonPercent;
			else if (vocab == 1)
				vocabPercent = rarePercent;
			else
				vocabPercent = otherPercent;
			
			// Skill
			int skill = word.skillScore;
			if (skill < 0)
				skill = 0;
			if (skill >= skillPercentages.length)
				skill = skillPercentages.length - 1; 
			int skillPercent = skillPercentages[skill];
			
			// Combine
			int comboPercent = vocabPercent * skillPercent;
			int deduction = 100 * myListOfWords.size(); // Prevent computer from having too many words
			
			comboPercent -= deduction;
			
			if (comboPercent > randomInt) {
				myListOfWords.add(word.word);
			}
		}
	}
	
	public void setSearchSkills(int skill) {
		this.skill = skill;
		switch (this.skill) {
		case 0: skillPercentages = new int[]{100, 100, 100, 0, 0,
										    0, 0, 0, 0, 0};
				break;
		case 1: skillPercentages = new int[]{100, 100, 100, 60, 50,
				                            45, 30, 15, 10, 2};
				break;
		case 2: skillPercentages = new int[]{100, 100, 100, 75, 60,
											55, 40, 25, 20, 5};
				break;
		case 3: skillPercentages = new int[]{100, 100, 100, 85, 70,
											60, 50, 30, 25, 8};
				break;
		case 4: skillPercentages = new int[]{100, 100, 100, 90, 80,
											70, 60, 35, 30, 15};
				break;
		case 5: skillPercentages = new int[]{100, 100, 100, 95, 90,
											75, 65, 45, 35, 25};
				break;
		case 6: skillPercentages = new int[]{100, 100, 100, 100, 95,
											85, 75, 55, 40, 30};
				break;
		case 7: skillPercentages = new int[]{100, 100, 100, 100, 100,
											90, 80, 70, 55, 40};
				break;
		case 8: skillPercentages = new int[]{100, 100, 100, 100, 100,
											95, 85, 80, 65, 50};
				break;
		case 9: skillPercentages = new int[]{100, 100, 100, 100, 100,
											100, 95, 90, 80, 70};
				break;
		case 10: skillPercentages = new int[]{100, 100, 100, 100, 100,
											100, 100, 98, 95, 90};
				break;
		}
		
	}
	
	public void setVocabularySkills(int vocabulary) {
		this.vocabulary = vocabulary;
		switch (this.vocabulary) {
		case 0: mostCommonPercent = 0;
				commonPercent = 0;
				rarePercent = 0;
				otherPercent = 0;
			    break;
		case 1: mostCommonPercent = 45;
				commonPercent = 30;
				rarePercent = 5;
				otherPercent = 0;
			    break;
		case 2: mostCommonPercent = 60;
				commonPercent = 35;
				rarePercent = 10;
				otherPercent = 0;
			    break;
		case 3: mostCommonPercent = 70;
				commonPercent = 40;
				rarePercent = 15;
				otherPercent = 0;
			    break;
		case 4: mostCommonPercent = 80;
				commonPercent = 45;
				rarePercent = 20;
				otherPercent = 1;
			    break;
		case 5: mostCommonPercent = 85;
				commonPercent = 55;
				rarePercent = 25;
				otherPercent = 2;
			    break;
		case 6: mostCommonPercent = 90;
				commonPercent = 65;
				rarePercent = 30;
				otherPercent = 4;
			    break;
		case 7: mostCommonPercent = 95;
				commonPercent = 75;
				rarePercent = 35;
				otherPercent = 7;
			    break;
		case 8: mostCommonPercent = 100;
				commonPercent = 80;
				rarePercent = 40;
				otherPercent = 10;
			    break;
		case 9: mostCommonPercent = 100;
				commonPercent = 85;
				rarePercent = 60;
				otherPercent = 15;
			    break;
		case 10: mostCommonPercent = 100;
				commonPercent = 95;
				rarePercent = 90;
				otherPercent = 30;
			    break;
		}
	}
}
