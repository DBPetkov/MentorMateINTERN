package hangman;

public class Main {

	public static void main(String[] args) {
		
		GameController game = new GameController();
		CategoriesAndWords.readFile();
		game.begginGameLoop();
		
	}
}
