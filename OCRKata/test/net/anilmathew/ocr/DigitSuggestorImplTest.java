package net.anilmathew.ocr;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DigitSuggestorImplTest {

	private DigitSuggestor digitSuggestor = new DigitSuggestorImpl();
	
	@Test
	public void testSuggestionsFor0is8() {
		
		List<Character> suggestions = digitSuggestor.getSuggestions('0');
		
		Assert.assertTrue(suggestions.contains('8'));
		Assert.assertEquals(1, suggestions.size());
	}

	@Test
	public void testSuggestions8is0And6And9() {
		
		List<Character> suggestions = digitSuggestor.getSuggestions('8');
		
		Assert.assertTrue(suggestions.contains('0'));
		Assert.assertTrue(suggestions.contains('6'));
		Assert.assertTrue(suggestions.contains('9'));
		Assert.assertFalse(suggestions.contains('3'));
		Assert.assertEquals(3, suggestions.size());
	}
	
	@Test
	public void testSuggestionsFor5is6() {
		
		List<Character> suggestions = digitSuggestor.getSuggestions('5');
		
		Assert.assertTrue(suggestions.contains('6'));
		Assert.assertEquals(1, suggestions.size());
	}
	
	@Test
	public void testSuggestionsFor7is1() {
		
		List<Character> suggestions = digitSuggestor.getSuggestions('7');
		
		Assert.assertTrue(suggestions.contains('1'));
		Assert.assertEquals(1, suggestions.size());
	}
	
	@Test
	public void testNoSuggestionsFor2() {
		
		List<Character> suggestions = digitSuggestor.getSuggestions('2');
		
		Assert.assertEquals(0, suggestions.size());
	}
	
	@Test
	public void testSuggestionsForIllegalCharacter() {
		
		List<Character> suggestions = digitSuggestor.getSuggestions('2');
		
		Assert.assertEquals(0, suggestions.size());
	}
	
}
