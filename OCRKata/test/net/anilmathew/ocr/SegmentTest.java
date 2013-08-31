package net.anilmathew.ocr;

import net.anilmathew.ocr.exception.InvalidOCRSegmentCharException;

import org.junit.Assert;
import org.junit.Test;

public class SegmentTest {

	@Test
	public void testFromCharWorksForVertical() throws Exception {
		Assert.assertEquals(Segment.vertical, Segment.fromChar('|'));
	}

	@Test(expected=InvalidOCRSegmentCharException.class)
	public void testFromCharThrowsExceptionForDollarCharacter() throws Exception {
		
		Segment.fromChar('$');
	}
	
	@Test
	public void testFromCharsReturnSegmentArrayForThreeChars() throws Exception {
		
		char[] chars = {'|', '_', ' '};
		Segment[] segments = Segment.fromChars(chars);
		
		Assert.assertEquals(3, segments.length);
		Assert.assertEquals(Segment.vertical, segments[0]);
		Assert.assertEquals(Segment.horizontal, segments[1]);
		Assert.assertEquals(Segment.blank, segments[2]);
	}
	
	@Test
	public void testFromCharsReturnSegmentArrayForFirstThreeCharsOnly() throws Exception {
		
		char[] chars = {'|', '_', ' ', '_', '|'};
		Segment[] segments = Segment.fromChars(chars);
		
		Assert.assertEquals(3, segments.length);
		Assert.assertEquals(Segment.vertical, segments[0]);
		Assert.assertEquals(Segment.horizontal, segments[1]);
		Assert.assertEquals(Segment.blank, segments[2]);
	}
	
	@Test(expected=InvalidOCRSegmentCharException.class)
	public void testFromCharsThrowsExceptionForInvalidSegmentCharacterInArray() throws Exception {
		
		char[] chars = { '|', '^', ' ', };
		Segment.fromChars(chars);
	}

}
