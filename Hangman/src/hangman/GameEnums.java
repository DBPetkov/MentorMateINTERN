package hangman;

public enum GameEnums {

	SELECT_CATEGORY("Please enter the category you wish to play: "),
	SELECT_WRONG_CATEGORY("Prease chose a valid category!\n");

	private String value;

	GameEnums(String value) {
		this.value = value;
	}
	public String value() {
        return value;
    }
}
