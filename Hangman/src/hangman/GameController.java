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
	private ArrayList<Character> letterMatch= new ArrayList<Character>();		// a list to hold all the characters inputed by the user
	private final String USER_INPUT_PATTERN = "^[a-zA-Z]{1}$";
	private final String WORD_SHOW_PATTERN = "[\\w]";
	private final String WORD_PRINT_PATTERN = "\\b(?!\\s)|\\B(?!\\s)";		//^ regular expressions
	private char[] gameWord;		// array of characters for easier comparing
	private String selectedWordLowerCase;
	private Player player = new Player();

	public void begginGameLoop() {		// main game loop
		letterMatch.clear();		// every time a word is guessed the list is cleared
		this.selectCategory();
		this.selectRandomWord();
		this.gameWord = this.selectedWord.replaceAll(WORD_SHOW_PATTERN, "_").toCharArray();		// converting the picked random word to a hidden word
		
		do {		// runs the game loop until word is guessed or there are no moves
			this.uncoverLetter();
			this.printWord();
			this.getUserInput();
			} while (true);

	}

	private void selectCategory() {		// category select from user input

		Set<String> allCategories = CategoriesAndWordsModel.getCategories();		// filling the Set with categories from file reader
		allCategories.forEach((category) -> System.out.println(category));		// printing on console all categories
		System.out.print(GameEnums.SELECT_CATEGORY.value());
		this.selectedCategory = sc.nextLine();		// getting user input for selected categories

		while (!allCategories.contains(selectedCategory)) {		// validation for selected category
			System.out.println(GameEnums.SELECT_WRONG_CATEGORY.value());
			System.out.print(GameEnums.SELECT_CATEGORY.value());
			this.selectedCategory = sc.nextLine();		// new user input until correct category is chosen
		}
	}

	private void selectRandomWord() {		// random word generator

		int num = CategoriesAndWordsModel.getData().get(this.selectedCategory).size();		// getting the number of words in chosen category
		Random rnd = new Random();
		int rndValue = rnd.nextInt(num);		// getting a random number from 1 - chosen category length
		this.selectedWord = CategoriesAndWordsModel.getData().get(this.selectedCategory).get(rndValue);		// selectedWord inherits the random word chosen from the category
		this.selectedWordLowerCase = this.selectedWord.toLowerCase();		// setting the word to lower case for easier comparing 
		CategoriesAndWordsModel.removeWord(selectedCategory, selectedWord);		// removing the used word from the Hash Map
	}

	

	private boolean validateUserInput(String userInput) {		// validating user input to match the requirements 
		Pattern p = Pattern.compile(this.USER_INPUT_PATTERN);
		Matcher m = p.matcher(userInput);
		return m.find();
	}

	private void getUserInput() {		// getting user input from console and validating it using the validate function
		String userInput;
		do {
			System.out.print(GameEnums.USER_INPUT.value());
			userInput = sc.nextLine();
			if(!validateUserInput(userInput)) System.out.println(GameEnums.USER_WRONG_INPUT.value());
		} while (!validateUserInput(userInput));
		char temp = Character.toLowerCase(userInput.charAt(0));	// added a temporary char to inherit the user input(if correct)
		if (!letterMatch.contains(temp))		// if it's not contained in the list - add it
			letterMatch.add(temp);
		else {
			System.out.println(GameEnums.USED_LETTER.value());
			this.getUserInput();		// if letter is used recursively get another letter
		}
	}

	private void uncoverLetter() {

		char charInput = this.getLatestInputChar();
		if (charInput != ' ') {		// check if character is not empty
			boolean guessed = false;
			for (int index = selectedWordLowerCase.indexOf(charInput);		// looks for the first letter that occurs the user-picked one 
					 index >= 0;
					 index = selectedWordLowerCase.indexOf(charInput, index + 1)) { // and every other letter that is the same
				this.gameWord[index] = selectedWord.charAt(index);		// uncovers the entered letter if contained in the hidden word
				guessed = true;
			}
			if (!guessed) {
				this.player.minusMove();		// minus 1 move if wrong
				System.out.println(GameEnums.USER_ATTEMPTS_LEFT.value()+this.player.getMoves());
				if(this.player.getMoves()<1) {
					this.gameOver();		// game over if there are no more moves
				}
			}else this.gameContinue();		// continue if the word is guessed within the 10 moves
		}

	}

	private void printWord() {		//printing the hidden word in the console with given requirements from the task

		System.out.println(String.valueOf(this.gameWord).replaceAll(WORD_PRINT_PATTERN, " ").trim());

	}

	private char getLatestInputChar() {		// getting the latest input character from user with all validations and returns it
		char latestInput = ' ';
		int size = this.letterMatch.size();
		if (size > 0) {
			latestInput = this.letterMatch.get(size - 1);		// the last valid character from the list Last
		}
		return latestInput;
	}
	
	private void gameOver() {		// simple game over
		System.out.println(GameEnums.USER_GAME_OVER.value()+GameEnums.GAME_WORD.value()+selectedWord+"\n");
		this.printGameScore();
		System.exit(0);
		
	}
	
	private void printGameScore() {		// simple score print
		System.out.println(GameEnums.USER_CURRENT_SCORE.value()+this.player.getScore());
	}
	
	private boolean wordGuessed() {		// boolean for word guessed
		return !String.valueOf(this.gameWord).contains("_");
	}
	
	private void gameContinue() {		// if the word is guessed begin the game loop again
		if(this.wordGuessed()) {
			this.player.addScore();		// add +1 to score
			this.player.resetMoves();		// reset the number of moves for the next word
			System.out.println(GameEnums.USER_WIN.value()+GameEnums.USER_CURRENT_SCORE.value()+this.player.getScore()+GameEnums.GAME_WORD.value()+selectedWord+"\n");
			this.begginGameLoop();
		}		
	}

}
