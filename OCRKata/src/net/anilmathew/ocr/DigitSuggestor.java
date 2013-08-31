package net.anilmathew.ocr;

import java.util.List;

public interface DigitSuggestor {

	List<Character> getSuggestions(char digit);

}