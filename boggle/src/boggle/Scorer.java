package boggle;

import java.util.ArrayList;

public class Scorer {
	
	// 4 x 4
	static int[] pointsFour = new int[]{0, 0, 0, 1, 1,
		                                 2, 3, 5, 11};  
	
	// 5 x 5
	static int[] pointsFive = new int[]{0, 0, 0, 0, 1,
									     2, 3, 5, 11};
	
	public static int scoreAllWords(int square, ArrayList<String> words, ArrayList<String> allWords) {
		int score = 0;
		for (int i = 0; i < words.size(); i++) {
			String word = words.get(i);
			if (allWords.contains(word)) {
				int wordLength = word.length();
				if (square == 4) {
					if (wordLength >= pointsFour.length)
						wordLength = pointsFour.length - 1;
					// Add Score
					score += pointsFour[wordLength];
				}
				else {
					if (wordLength >= pointsFive.length)
						wordLength = pointsFive.length - 1;
					// Add Score
					score += pointsFive[wordLength];
				}
			}
		}
		return score;
	}
}
