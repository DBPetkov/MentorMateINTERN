package hangman;

public class Main {

	public static void main(String[] args) {
		
		GameController game = new GameController();
		game.readFile();
		game.selectCategory();
		game.selectRandomWord();
		game.begginGameLoop();
	}
}
