package net.anilmathew.ocr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Runner {

	public static void main(String[] args) throws Exception 
	{

		if(args.length < 2) {

			System.out.println("Usage: <inputFile> <outputFile>");
			return;
		}
		
		
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
		
		OCRProcessor ocrProcessor = new OCRProcessor();
		ocrProcessor.process(inputFile);
		
		
		int accountNumberCount = ocrProcessor.getAccountNumberCount();
		
		for(int i = 1 ; i <= accountNumberCount; i++)
		{
			
			String accountNumber = ocrProcessor.getAccountNumberWithCorrection(i);
			bufferedWriter.write(accountNumber + "\n");
		}
		
		bufferedWriter.close();
	}

}
