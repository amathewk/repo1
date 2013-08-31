package net.anilmathew.ocr;

import java.util.Arrays;

import net.anilmathew.ocr.exception.InvalidDigitException;
import net.anilmathew.ocr.exception.InvalidOCRSegmentCharException;

public class AccountNumber {

	private String accNum;
	private char[][] data = new char[3][27];
	
	AccountNumber(String line1, String line2, String line3) throws InvalidDigitException, InvalidOCRSegmentCharException 
	{
		
		data[0] = line1.toCharArray();
		data[1] = line2.toCharArray();
		data[2] = line3.toCharArray();
		
		accNum = extractAccountNumber();
	}
	
	
	AccountNumber(String accountNumber) 
	{
		accNum = accountNumber;
	}

	
	public boolean isValid() 
	{

		boolean valid = false;
		
		if (accNum.length() == 9 && hasOnlyDigits()) 
		{
			int total = calculateChecksum();
			valid = (total%11 == 0); 
		}
		
		return valid;
	}
	

	public String toString() 
	{
		
		String accountNumberDisplay = accNum;
		
		if (accNum.contains("?")) 
		{
			accountNumberDisplay = accNum + " ILL";
		} 
		else if (!isValid()) 
		{
			accountNumberDisplay = accNum + " ERR";
		} 
		
		return accountNumberDisplay;
	}

	
	private Digit extractDigit(int n) throws InvalidOCRSegmentCharException 
	{
		
		Digit digit = new Digit();

		Segment[] segments = Segment.fromChars(Arrays.copyOfRange(data[0], 3*n, 3*n+3));
		digit.setRow(1, segments);
		
		segments = Segment.fromChars(Arrays.copyOfRange(data[1], 3*n, 3*n+3));
		digit.setRow(2, segments);
		
		segments = Segment.fromChars(Arrays.copyOfRange(data[2], 3*n, 3*n+3));
		digit.setRow(3, segments);
		
		return digit;
	}

	
	private String extractAccountNumber() throws InvalidDigitException, InvalidOCRSegmentCharException 
	{

		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0 ; i < 9 ; i++) {
			stringBuilder.append(extractDigit(i).identifyDigit());
		}
		
		return stringBuilder.toString();
	}

	
	private boolean hasOnlyDigits() 
	{
		
		char[] charArray = accNum.toCharArray();
		
		for (int i = 0; i < charArray.length; i++) {
			if (!Character.isDigit(charArray[i])) {
				return false;
			}
		}
		
		return true;
	}

	
	private int calculateChecksum() 
	{

		
		int total = 0;
		char[] accountNumberChars = accNum.toCharArray();
		
		for (int i=0; i < 9; i++)
		{
			char accountNumberChar = accountNumberChars[i];
			int value = Character.digit(accountNumberChar, 10);
			total += value * (9-i); 
		}
		
		return total;
	}

	
	public String toStringWithoutStatus() 
	{
		return accNum;
	}

}
