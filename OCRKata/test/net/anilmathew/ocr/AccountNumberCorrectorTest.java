package net.anilmathew.ocr;

import static org.easymock.EasyMock.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AccountNumberCorrectorTest {

	
	private AccountNumberCorrector accountNumberCorrector = new AccountNumberCorrector();
	private DigitSuggestor digitSuggestor;
	
	
	@Before public void setupMocks()
	{
		
		digitSuggestor = createMock(DigitSuggestor.class);
		
		reset(digitSuggestor);

		
		expect(digitSuggestor.getSuggestions('7')).andStubReturn(Arrays.asList(new Character[]{'1'}));
		expect(digitSuggestor.getSuggestions('1')).andStubReturn(Arrays.asList(new Character[]{'7'}));
		expect(digitSuggestor.getSuggestions('0')).andStubReturn(Arrays.asList(new Character[]{'8'}));

		expect(digitSuggestor.getSuggestions('2')).andStubReturn(Arrays.asList(new Character[]{}));
		expect(digitSuggestor.getSuggestions('8')).andStubReturn(Arrays.asList(new Character[]{'0','6'}));
		
		accountNumberCorrector.setDigitSuggestor(digitSuggestor);
		
		
		replay(digitSuggestor);
		
	}
	
	
	@Test public void testCorrectsAccountNumberWithERRStatuswithOnlyOnePossibleCorrectionAll7s() throws Exception
	{
		
		AccountNumber accountNumber = new AccountNumber(
							" _  _  _  _  _  _  _  _  _ ", 
							"  |  |  |  |  |  |  |  |  |",
							"  |  |  |  |  |  |  |  |  |"
							);
		
		
		String correctedAccountNumber = accountNumberCorrector.getCorrectedAccountNumber(accountNumber);
		Assert.assertEquals("777777177", correctedAccountNumber);
		
	}
	
	
	@Test public void testCorrectsAccountNumberWithERRStatuswithOnlyOnePossibleCorrectionAllOnes() throws Exception
	{
		
		AccountNumber accountNumber = new AccountNumber(
				"                           ", 
				"  |  |  |  |  |  |  |  |  |",
				"  |  |  |  |  |  |  |  |  |"
		);
		
		String correctedAccountNumber = accountNumberCorrector.getCorrectedAccountNumber(accountNumber);
		Assert.assertEquals("711111111", correctedAccountNumber);
		
	}
	
	
	@Test public void testCorrectsAccountNumberWithERRStatuswithOnlyOnePossibleCorrectionAccNumIs200000000() throws Exception
	{
		
		AccountNumber accountNumber = new AccountNumber(
				" _  _  _  _  _  _  _  _  _ ", 
				" _|| || || || || || || || |",
				"|_ |_||_||_||_||_||_||_||_|"
		);
		
		String correctedAccountNumber = accountNumberCorrector.getCorrectedAccountNumber(accountNumber);
		Assert.assertEquals("200800000", correctedAccountNumber);
		
	}
	
	
	@Test public void testReturnsAmbiguousForAccountNumberWithERRStatusAndMultipleCorrections() throws Exception
	{
		
		AccountNumber accountNumber = new AccountNumber(
							 " _  _  _  _  _  _  _  _  _ ", 
							 "|_||_||_||_||_||_||_||_||_|",
							 "|_||_||_||_||_||_||_||_||_|"
							);
		
		
		String correctedAccountNumber = accountNumberCorrector.getCorrectedAccountNumber(accountNumber);
		Assert.assertEquals("888888888 AMB", correctedAccountNumber);
		
	}
	
}
