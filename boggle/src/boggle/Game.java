package boggle;

import java.util.ArrayList;

import boggle.Solver.Score;

public class Game {
	
	Player[] players;
	
	public Game(int maxPlayers) {
		initGame(10);
	}
	
	public void initGame(int maxPlayers) {
		players = new Player[maxPlayers];
		for (int i = 0; i < maxPlayers; i++) {
			players[i] = new ComputerPlayer(i + 1);
			((ComputerPlayer)players[i]).setSearchSkills(i + 1);
			((ComputerPlayer)players[i]).setVocabularySkills(i + 1);
		}
	}
	
	public void generateWordLists(ArrayList<Score> words) {
		for (int i = 0; i < players.length; i++) {
			if (players[i].computer) {
				((ComputerPlayer)players[i]).findWordsOnBoard(words);
			}
		}
	}
	
	public void printWordLists(ArrayList<Score> words) {
		for (int i = 0; i < players.length; i++) {
			players[i].printWordList(words);
		}
	}
	
}
