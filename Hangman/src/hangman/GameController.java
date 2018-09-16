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
	private ArrayList<Character> letterMatch;
	private final String USER_INPUT_PATTERN = "^[a-zA-Z]{1}$"; // tudu Extract all Regular Expressions here!
	private char[] gameWord;
	private String selectedWordLowerCase;
	private Player player = new Player();

	public void readFile() {
		CategoriesAndWords.readFile();
	}

	public void selectCategory() {

		Set<String> allCategories = CategoriesAndWordsModel.getCategories();
		allCategories.forEach((category) -> System.out.println(category));
		System.out.print(GameEnums.SELECT_CATEGORY.value());
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
		this.selectedWordLowerCase = this.selectedWord.toLowerCase();
		CategoriesAndWordsModel.removeWord(selectedCategory, selectedWord);
	}

	public void begginGameLoop() {
		letterMatch = new ArrayList<Character>();
		this.selectCategory();
		this.selectRandomWord();
		this.gameWord = this.selectedWord.replaceAll("[\\w]", "_").toCharArray();
		
		do {
			this.uncoverLetter();
			this.printWord();
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
			System.out.print(GameEnums.USER_INPUT.value());
			userInput = sc.nextLine();
		} while (!validateUserInput(userInput));
		char temp = Character.toLowerCase(userInput.charAt(0));
		if (!letterMatch.contains(temp))
			letterMatch.add(temp);
		else {
			System.out.println(GameEnums.USED_LETTER.value());
			this.getUserInput();
		}
	}

	private void uncoverLetter() {

		char charInput = this.getLatestInputChar();
		if (charInput != ' ') {
			boolean guessed = false;
			for (int index = selectedWordLowerCase.indexOf(charInput); index >= 0; index = selectedWordLowerCase
					.indexOf(charInput, index + 1)) {
				this.gameWord[index] = selectedWord.charAt(index);
				guessed = true;
			}
			if (!guessed) {
				this.player.minusMove();
				System.out.println(GameEnums.USER_ATTEMPTS_LEFT.value()+this.player.getMoves());
				if(this.player.getMoves()==0) {
					this.gameOver();
				}
			}else this.gameContinue();
		}

	}

	private void printWord() {

		String tempValue = String.valueOf(this.gameWord).replaceAll("\\b(?!\\s)|\\B(?!\\s)", " ").trim();
		System.out.println(tempValue);

	}

	private char getLatestInputChar() {
		char latestInput = ' ';
		int size = this.letterMatch.size();
		if (size > 0) {
			latestInput = this.letterMatch.get(size - 1);
		}
		return latestInput;
	}
	
	private void gameOver() {
		System.out.println(GameEnums.USER_GAME_OVER.value());
		this.printGameScore();
		System.exit(0);
		
	}
	
	private void printGameScore() {
		System.out.println(GameEnums.USER_CURRENT_SCORE.value()+this.player.getScore());
	}
	
	private boolean wordGuessed() {
		return !String.valueOf(this.gameWord).contains("_");
	}
	
	private void gameContinue() {
		if(this.wordGuessed()) {
			this.player.addScore();
			this.player.resetMoves();
			System.out.println(GameEnums.USER_WIN.value()+GameEnums.USER_CURRENT_SCORE.value()+this.player.getScore());
			this.begginGameLoop();
		}		
	}
}
