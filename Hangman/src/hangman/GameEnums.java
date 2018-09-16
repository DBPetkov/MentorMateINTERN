package hangman;

public enum GameEnums {

	SELECT_CATEGORY("\nPlease enter the category you wish to play: "),
	SELECT_WRONG_CATEGORY("Prease chose a valid category!\n"),
	USED_LETTER("This letter has already been used!"),
	USER_INPUT("Please enter a valid letter: "),
	USER_CURRENT_SCORE("Current score: "),
	USER_WIN("Congratulations you have revealed the word/phrase:"),
	USER_ATTEMPTS_LEFT("The letter you picked doesn't exist!\nAttempts left: "),
	USER_GAME_OVER("You have depleted your attempts!");

	private String value;

	GameEnums(String value) {
		this.value = value;
	}
	public String value() {
        return value;
    }
}
