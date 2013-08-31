package net.anilmathew.ocr;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import net.anilmathew.ocr.exception.InvalidDigitException;
import net.anilmathew.ocr.exception.InvalidOCRSegmentCharException;

import org.junit.Test;


public class OCRProcessorTest {

	@Test public void testReadFile() throws IOException, InvalidDigitException, InvalidOCRSegmentCharException {
		
		File file = new File("testData/multipleAccountNumbers.txt");
		
		OCRProcessor processor = new OCRProcessor();
		
		processor.process(file);
		int accountNumberCount = processor.getAccountNumberCount();
		
		Assert.assertEquals(3, accountNumberCount);
		
		String accountNumber = processor.getAccountNumber(1);
		Assert.assertEquals("733652981 ERR", accountNumber);
		
	}

	@Test public void testReadFileWith500AccountNumbers() throws Exception
	{
		File file = new File("testData/500.txt");
		
		OCRProcessor processor = new OCRProcessor();
		
		processor.process(file);
		int accountNumberCount = processor.getAccountNumberCount();
		
		Assert.assertEquals(500, accountNumberCount);
		
		String accountNumber = processor.getAccountNumber(1);
		Assert.assertEquals("999999999 ERR", accountNumber);
	}

	@Test public void testGetAccountNumberWithCorrection() throws Exception
		{
				
			File file = new File("testData/200000000.txt");
			
			OCRProcessor processor = new OCRProcessor();
			
			processor.process(file);
			int accountNumberCount = processor.getAccountNumberCount();
			
			Assert.assertEquals(1, accountNumberCount);
			
			String accountNumber = processor.getAccountNumber(1);
			Assert.assertEquals("200000000 ERR", accountNumber);
			
			String accountNumberWithCorrection = processor.getAccountNumberWithCorrection(1);
			Assert.assertEquals("200800000", accountNumberWithCorrection);
		}
	
}
