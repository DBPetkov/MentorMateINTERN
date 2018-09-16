package hangman;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hangman.Models.CategoriesAndWordsModel;

public class GameController {

	private Scanner sc = new Scanner(System.in);
	private String selectedCategory = "";
	private String selectedWord = "";
	private ArrayList<Character> letterMatch = new ArrayList<Character>();
	private final String USER_INPUT_PATTERN = "^\\w{1}$";
	private char[] gameWord;

	public void readFile() {
		CategoriesAndWords.readFile();
	}

	public void selectCategory() {
		System.out.println(GameEnums.SELECT_CATEGORY.value());
		Set<String> allCategories = CategoriesAndWordsModel.getCategories();
		allCategories.forEach((category) -> System.out.println(category));
		this.selectedCategory = sc.nextLine();

		while (!allCategories.contains(selectedCategory)) {
			System.out.println(GameEnums.SELECT_WRONG_CATEGORY.value());
			System.out.print(GameEnums.SELECT_CATEGORY.value());
			this.selectedCategory = sc.nextLine();
		}
	}

	public void selectRandomWord() {

		int num = CategoriesAndWordsModel.getData().get(this.selectedCategory).size();
		Random rnd = new Random();
		int rndValue = rnd.nextInt(num);
		this.selectedWord = CategoriesAndWordsModel.getData().get(this.selectedCategory).get(rndValue);
	}

	private void printWord() {

		System.out.println(String.valueOf(this.gameWord).replaceAll("", " ").trim());

	}

	public void begginGameLoop() {

		this.gameWord = this.selectedWord.replaceAll("[A-Za-z]", "_").toCharArray();
		do {
			this.uncoverLetter();
			this.printWord();
			// Select Player
			this.getUserInput();

		} while (true);

	}

	private boolean validateUserInput(String userInput) {
		Pattern r = Pattern.compile(this.USER_INPUT_PATTERN);
		Matcher m = r.matcher(userInput);
		return m.find();
	}

	private void getUserInput() {
		String userInput;
		do {
			System.out.println("Player" + " please enter a single valid letter: ");
			userInput = sc.nextLine();
		} while (!validateUserInput(userInput));
		char temp = userInput.charAt(0);
		if (!letterMatch.contains(temp))
			letterMatch.add(temp);
		else {
			System.out.println("");
			this.getUserInput();
		}
	}

	private void uncoverLetter() {

		
		char charInput = this.getLatestInputChar();
		if (charInput != ' ') {
			for (int index = this.selectedWord.indexOf(charInput);
					 index >= 0;
					 index = this.selectedWord.indexOf(charInput, index + 1)) 
			{
				this.gameWord[index] = charInput;
			}
		}

	}

	private char getLatestInputChar() {
		char latestInput = ' ';
		int size = this.letterMatch.size();
		if (size > 0) {
			latestInput = this.letterMatch.get(size - 1);
		}
		return latestInput;
	}
}
