package net.anilmathew.ocr;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { AccountNumberTest.class, OCRProcessorTest.class, DigitTest.class, SegmentTest.class, RunnerTest.class, 
					   AccountNumberCorrectorTest.class, DigitSuggestorImplTest.class })
public class AllTests {

}
