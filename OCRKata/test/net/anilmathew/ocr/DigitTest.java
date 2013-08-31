package net.anilmathew.ocr;

import net.anilmathew.ocr.exception.ImproperSegmentStringException;

import org.junit.Assert;
import org.junit.Test;

public class DigitTest {

	@Test(expected=ImproperSegmentStringException.class)
	public void testThrowsExceptionWhenPassingNullSegmentsString() throws Exception {
		
		Digit digit = new Digit();
		digit.addSegments(null);
	}
	
	@Test(expected=ImproperSegmentStringException.class)
	public void testThrowsExceptionWhenPassingShortSegmentsString() throws Exception {
		
		Digit digit = new Digit();
		digit.addSegments("   " +
						  "|"   +
		  				  " | ");
			
	}
	
	@Test(expected=ImproperSegmentStringException.class)
	public void testThrowsExceptionWhenPassingLongSegmentsString() throws Exception {
		
		Digit digit = new Digit();
		digit.addSegments("   " +
						  "| |  ||  |" +
						  " | ");
			
	}
	
	@Test
	public void testIdentifyDigitForValidDigit() throws Exception {
		
		Digit digit = new Digit();
		digit.addSegments("   " +
           				  "  |" +
           				  "  |");
		
		Assert.assertEquals('1',digit.identifyDigit());
	}

	@Test
	public void testIdentifyDigitForValidDigitZero() throws Exception {
		
		Digit digit = new Digit();
		digit.addSegments(" _ " +
						  "| |" +
						  "|_|");
		
		Assert.assertEquals('0',digit.identifyDigit());
	}
	
	@Test
	public void testIdentifyDigitForDigitConstant() throws Exception {
		
		Assert.assertEquals('2',Digit.TWO.identifyDigit());
	}
	
	@Test
	public void testReturnsQuestionMarkSymbolForInvalidDigit() throws Exception{

		Digit digit = new Digit();
		digit.addSegments("   " +
						  " _ " +
						  " | ");
		
		char identifiedDigitChar = digit.identifyDigit();
		
		Assert.assertEquals('?', identifiedDigitChar);
	}
	
}
