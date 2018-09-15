package hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import hangman.Models.CategoriesAndWordsModel;

public abstract  class CategoriesAndWords {

	
	private static final String DEFAULT_SRC = "src/hangman/Resourses/CategoriesAndWords.txt";
	private static void addValues(String currentKey, ArrayList<String> currentValues) {
		ArrayList<String> tempValues = new ArrayList<String>(currentValues);
		CategoriesAndWordsModel.setData(currentKey, tempValues);
	}
	public static void readFile() {
		System.out.println("Reading from file " + DEFAULT_SRC); // READ START
		try (BufferedReader br = new BufferedReader(new FileReader(DEFAULT_SRC))) {
			String line;
			String currentKey = null;
			ArrayList<String> currentValues = new ArrayList<String>();

			while ((line = br.readLine()) != null) { // read each line from the stream
				if (line.startsWith("_")) { // if the current line is a category-push current category and values
					if (currentKey != null) {
						addValues(currentKey, currentValues);
					}
					currentValues.clear();
					currentKey = line.toString().substring(1); // delete "_" before category
				} else {
					currentValues.add(line.toString());
				}
			}
			addValues(currentKey, currentValues);
			System.out.println("Succesfully read file info!"); // READ END
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
	}

}
