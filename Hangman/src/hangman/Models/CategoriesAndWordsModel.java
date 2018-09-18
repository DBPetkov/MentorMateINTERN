package hangman.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CategoriesAndWordsModel {

	private static HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>(); //data container

	public static HashMap<String, ArrayList<String>> getData() { //data get
		return data;
	}

	public static void setData(String key,ArrayList<String> value) { //data set
		data.put(key, value);
	}
	
	public static void removeWord(String key, String value) {  //data remove
		
		data.get(key).remove(value);
	}
	
	public static Set<String> getCategories() { //category get
		return data.keySet();
	}
	
}
