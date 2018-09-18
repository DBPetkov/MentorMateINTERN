package hangman;

public enum GameEnums {

	SELECT_CATEGORY("\nPlease enter the category you wish to play: "),
	SELECT_WRONG_CATEGORY("\nPlease chose a valid category!"),
	USED_LETTER("\nThis letter has already been used!"),
	USER_INPUT("\nPlease enter a letter: "),
	USER_CURRENT_SCORE("\nCurrent score: "),
	USER_WIN("\nCongratulations you have revealed the word/phrase"),
	USER_ATTEMPTS_LEFT("\nThe letter you picked doesn't exist!\nAttempts left: "),
	USER_GAME_OVER("\nYou have depleted your attempts!"),
	GAME_WORD("\nThe word is: "), 
	USER_WRONG_INPUT("\nPlease enter only a single latin letter!");

	private String value;

	GameEnums(String value) {
		this.value = value;
	}
	public String value() {
        return value;
    }
}
