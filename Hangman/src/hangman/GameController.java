package hangman;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import hangman.Models.CategoriesAndWordsModel;

public class GameController {

	private Scanner sc = new Scanner(System.in);
	private String selectedCategory = "";
	private String selectedWord = "";

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
	
	public void wordShow(char letter) {
		
		
		
	}

}
