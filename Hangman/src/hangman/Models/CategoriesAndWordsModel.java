package hangman.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CategoriesAndWordsModel {

	 private static HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();

	public static HashMap<String, ArrayList<String>> getData() {
		return data;
	}

	public static void setData(String key,ArrayList<String> value) {
		data.put(key, value);
	}
	
	public static Set<String> getCategories() {
		return data.keySet();
	}
	
}
