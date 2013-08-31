package net.anilmathew.ocr;

import net.anilmathew.ocr.exception.InvalidOCRSegmentCharException;

public enum Segment {

	
	vertical('|'), horizontal('_'), blank(' ');
	
	
	private char val;
	
	private Segment(char val) {
		this.val = val;
	}
	
	public static Segment fromChar(char character) throws InvalidOCRSegmentCharException {
		
		if (character == vertical.val) {
			return vertical;
		} else if (character == horizontal.val) {
			return horizontal;
		} else if (character == blank.val) {
			return blank;
		} else {
			throw new InvalidOCRSegmentCharException();
		}
	}

	public static Segment[] fromChars(char[] chars) throws InvalidOCRSegmentCharException {
		
		Segment[] segments = new Segment[3];
		
		segments[0] = Segment.fromChar(chars[0]);
		segments[1] = Segment.fromChar(chars[1]);
		segments[2] = Segment.fromChar(chars[2]);
		
		return segments;
	}
	
}
