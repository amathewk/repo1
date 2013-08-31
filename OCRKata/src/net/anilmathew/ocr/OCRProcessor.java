package net.anilmathew.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.anilmathew.ocr.exception.InvalidDigitException;
import net.anilmathew.ocr.exception.InvalidOCRSegmentCharException;

public class OCRProcessor {

	private List<AccountNumber> accountNumbers = new ArrayList<AccountNumber>();
	private AccountNumberCorrector accountNumberCorrector = new AccountNumberCorrector();

	
	public void process(File file) throws IOException, InvalidDigitException, InvalidOCRSegmentCharException 
	{
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String line1, line2, line3;
		
		while (    (line1 = bufferedReader.readLine()) != null
				&& (line2 = bufferedReader.readLine()) != null
				&& (line3 = bufferedReader.readLine()) != null)
		{
			
			AccountNumber accountNumber = new AccountNumber(line1, line2, line3);
			accountNumbers.add(accountNumber);
			bufferedReader.readLine();
		}
	}

	
	public int getAccountNumberCount() {
		return accountNumbers.size();
	}

	
	public String getAccountNumber(int n) {
		return accountNumbers.get(n - 1).toString();
	}

	
	public String getAccountNumberWithCorrection(int n) 
	{

		AccountNumber accountNumber = accountNumbers.get(n - 1);
		String accountNumberString = accountNumber.toString(); 
		
		if ( ! accountNumber.isValid() ) {
			accountNumberString = accountNumberCorrector.getCorrectedAccountNumber(accountNumber);
		}
		
		return accountNumberString;
	}
	
}
