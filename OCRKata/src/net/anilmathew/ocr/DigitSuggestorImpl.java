package net.anilmathew.ocr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DigitSuggestorImpl implements DigitSuggestor {

	private static Map<Character, List<Character>> suggestions = new HashMap<Character, List<Character>>(); 
	
	static {
		
		suggestions.put('0', Arrays.asList(new Character[] {'8'}));
		suggestions.put('1', Arrays.asList(new Character[] {'7'}));
		suggestions.put('2', Arrays.asList(new Character[] {}));
		suggestions.put('3', Arrays.asList(new Character[] {'9'}));
		suggestions.put('4', Arrays.asList(new Character[] {}));
		suggestions.put('5', Arrays.asList(new Character[] {'6'}));
		suggestions.put('6', Arrays.asList(new Character[] {'5','8'}));
		suggestions.put('7', Arrays.asList(new Character[] {'1'}));
		suggestions.put('8', Arrays.asList(new Character[] {'6','9','0'}));
		suggestions.put('9', Arrays.asList(new Character[] {'3','8'}));
		
	}
	
	@Override
	public List<Character> getSuggestions(char digit) {
		return suggestions.get(digit);
	}

}
