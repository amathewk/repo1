package net.anilmathew.ocr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.anilmathew.ocr.exception.ImproperSegmentStringException;
import net.anilmathew.ocr.exception.InvalidOCRSegmentCharException;

public class Digit {

	private static final char INVALID_DIGIT_CHARACTER = '?';

	public static final Digit ZERO = new Digit('0'), ONE = new Digit('1'), TWO = new Digit('2'), THREE = new Digit('3'), FOUR = new Digit('4'), FIVE = new Digit('5'), SIX = new Digit('6'), SEVEN = new Digit('7'), EIGHT = new Digit('8'), NINE = new Digit('9');
	
	public static final List<Digit> NUMBERS = new ArrayList<Digit>(Arrays.asList(new Digit[]{ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE}));
	
	static {
		
		try {
			
			ZERO.addSegments(" _ " +
	           				 "| |" +
       						 "|_|");
			
			ONE.addSegments("   " +
			           		"  |" +
			           		"  |");
			
			TWO.addSegments(" _ " +
							" _|" +
							"|_ ");
			
			
			THREE.addSegments(" _ " +
							  " _|" +
							  " _|");
			
			FOUR.addSegments("   " +
	 						 "|_|" +
							 "  |");
			
			FIVE.addSegments(" _ " +
							 "|_ " +
							 " _|");
			
			SIX.addSegments(" _ " +
							"|_ " +
							"|_|");
			
			SEVEN.addSegments(" _ " +
							  "  |" +
					 		  "  |");
			
			EIGHT.addSegments(" _ " +
							  "|_|" +
							  "|_|");
			
			NINE.addSegments(" _ " +
							 "|_|" +
							 " _|");
			
			
		} catch (Exception exception) {
			throw new RuntimeException("Unexpected Exception in Digit constants initialization.", exception);
		}
	}
	
	
	private Segment[][] segments = new Segment[3][3];
	private char value;
	
	
	public Digit(){
		
	};
	
	
	public Digit(char value){
		this.value = value;
	};
	
	
	public Segment[][] getSegments() {
		return segments;
	}
	
	
	public void addSegments(String data) throws InvalidOCRSegmentCharException, ImproperSegmentStringException 
	{
		
		if (data == null || data.length() != 9) {
			throw new ImproperSegmentStringException();
		}
		
		char[] charData1 = data.substring(0, 3).toCharArray();
		char[] charData2 = data.substring(3, 6).toCharArray();
		char[] charData3 = data.substring(6, 9).toCharArray();
		
		setRow(1, Segment.fromChars(charData1));
		setRow(2, Segment.fromChars(charData2));
		setRow(3, Segment.fromChars(charData3));
	}
	
	
	public void setRow(int rowNum, Segment[] row) {
		segments[rowNum-1] = row; 
	}
	
	
	public char identifyDigit() 
	{
		
		char identifiedDigit = INVALID_DIGIT_CHARACTER;
		
		for(Digit digit: NUMBERS) {
			if (digit.equals(this)) {
				identifiedDigit = digit.value;
			}
		}
		
		return identifiedDigit;
	}

	public boolean equals(Object obj) 
	{
		
		if ( this == obj ) {
			return true;
		}
		
		if ( !(obj instanceof Digit) ) {
			return false;
		}
		
		Digit digit = (Digit) obj;
		
		return areSegmentsEqual(digit);
	}

	
	private boolean areSegmentsEqual(Digit digit) 
	{
		
		boolean equals = true;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (segments[i][j] != digit.getSegments()[i][j]) {
					equals = false;
				}
			}
		}
		
		return equals;
	}
	
	//TODO: hashcode
	
}
