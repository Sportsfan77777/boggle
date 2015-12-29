package boggle;

import java.util.ArrayList;

import boggle.Solver.Score;

public class Game {
	
	Display display;
	
	Player[] players;
	Board board;
	
	public Game(int maxPlayers, Display display) {
		this.display = display;
		initGame(10);
		this.startGame();
	}
	
	public void initGame(int maxPlayers) {
		players = new Player[maxPlayers];
		for (int i = 0; i < maxPlayers; i++) {
			players[i] = new ComputerPlayer(i + 1);
			((ComputerPlayer)players[i]).setSearchSkills(i + 1);
			((ComputerPlayer)players[i]).setVocabularySkills(i + 1);
		}
	}
	
	public void startGame() {
		this.board = new FiveBoard();
		
		Solver s = new Solver();
		s.setBoard(board.board);
		s.findAllWords();
		s.scoreWords();
		s.printAll();
		
		generateWordLists(s.allWords);
		printWordLists(s.allWords);
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
