package boggle;

public class ComputerPlayer extends Player {
	
	int skill;
	int vocabulary;
	
	int mostCommonPercent = 0;
	int commonPercent = 0;
	int rarePercent = 0;
	int otherPercent = 0;
	
	public ComputerPlayer() {
		super();
	}
	
	public void setSearchSkills(int skill) {
		this.skill = skill;
		
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
				otherPercent = 1;
			    break;
		case 3: mostCommonPercent = 70;
				commonPercent = 40;
				rarePercent = 15;
				otherPercent = 2;
			    break;
		case 4: mostCommonPercent = 80;
				commonPercent = 45;
				rarePercent = 20;
				otherPercent = 3;
			    break;
		case 5: mostCommonPercent = 85;
				commonPercent = 55;
				rarePercent = 25;
				otherPercent = 4;
			    break;
		case 6: mostCommonPercent = 90;
				commonPercent = 65;
				rarePercent = 30;
				otherPercent = 5;
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
