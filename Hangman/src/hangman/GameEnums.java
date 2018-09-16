package hangman;

public enum GameEnums {

	SELECT_CATEGORY("Please enter the category you wish to play: "),
	SELECT_WRONG_CATEGORY("Prease chose a valid category!\n"),
	USED_LETTER("This letter has already been used!");

	private String value;

	GameEnums(String value) {
		this.value = value;
	}
	public String value() {
        return value;
    }
}
