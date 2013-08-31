package net.anilmathew.ocr;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class AccountNumberCorrector {

	DigitSuggestor digitSuggestor = new DigitSuggestorImpl();


	public void setDigitSuggestor(DigitSuggestor digitSuggestor) 
	{
		this.digitSuggestor = digitSuggestor;
	}

	
	public String getCorrectedAccountNumber(AccountNumber accountNumber) 
	{

		String accNum = accountNumber.toStringWithoutStatus();
		List<String> possibleCorrections = new ArrayList<String>();
	
		for ( int position = 1 ; position <= accNum.length() ; position++ ) 
		{
			List<String> possibleCorrectionsForPosition = getPossibleCorrectionsForPosition( position, accNum );
			possibleCorrections.addAll( possibleCorrectionsForPosition );
			
		}
		 
		if ( possibleCorrections.size() == 1 ) {
			
			return possibleCorrections.get(0);
		  
		} else if ( possibleCorrections.size() > 1 ) {
			
			return accNum + " AMB";
			
		} else if ( possibleCorrections.size() > 0 ) { 
			
			return accNum;
		}
		 
		return null;
	}
	
	
	private List<String> getPossibleCorrectionsForPosition(int position, String accNum)
	{
		
		List<String> possibleCorrections = new ArrayList<String>();
		
		char digit = accNum.charAt(position-1);
		
		List<Character> suggestions = digitSuggestor.getSuggestions(digit);
		
		for (Character suggestedDigit : suggestions) 
		{

			String possibleAccountNumberString = replaceDigitAtPosition(position, accNum, suggestedDigit);
			AccountNumber possibleAccountNumber = new AccountNumber(possibleAccountNumberString);
			
			if (possibleAccountNumber.isValid()) {
				possibleCorrections.add(possibleAccountNumberString);
			}
			
		}
		
		return possibleCorrections;
	}


	private String replaceDigitAtPosition(int position, String accNum, char suggestedDigit) 
	{
		return StringUtils.left(accNum, position - 1) + suggestedDigit + StringUtils.right(accNum, accNum.length() - position);
	}
	
}
