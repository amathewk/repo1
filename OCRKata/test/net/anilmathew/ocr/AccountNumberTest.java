package net.anilmathew.ocr;

import junit.framework.Assert;

import org.junit.Test;

public class AccountNumberTest {

		
	@Test public void testAccountNumberAllOnes() throws Exception {
		
		AccountNumber accountNumber = new AccountNumber("                           ",
														"  |  |  |  |  |  |  |  |  |",
														"  |  |  |  |  |  |  |  |  |");

		Assert.assertEquals("111111111 ERR", accountNumber.toString());
	}
	
	@Test public void testAccountNumberAllNines() throws Exception {
		
		AccountNumber accountNumber = new AccountNumber(" _  _  _  _  _  _  _  _  _ ",
														"|_||_||_||_||_||_||_||_||_|",
														" _| _| _| _| _| _| _| _| _|");

		Assert.assertEquals("999999999 ERR", accountNumber.toString());
	}
	
	@Test public void testAccountNumberAllDigits() throws Exception {
		
		AccountNumber accountNumber = new AccountNumber("    _  _     _  _  _  _  _ ",
														"  | _| _||_||_ |_   ||_||_|",
														"  ||_  _|  | _||_|  ||_| _|");
		
		Assert.assertEquals("123456789", accountNumber.toString());
	}
	
	@Test public void testAccountNumberMixedDigits() throws Exception {
		
		AccountNumber accountNumber = new AccountNumber(" _  _  _  _  _  _  _  _    ",
														"  | _| _||_ |_  _||_||_|  |",
														"  | _| _||_| _||_  _||_|  |");
		Assert.assertEquals("733652981 ERR", accountNumber.toString());
	}
	
	@Test public void testValidAccountNumber() {
		Assert.assertTrue(new AccountNumber("345882865").isValid());
	}
	
	@Test public void testInvalidAccountNumber() {
		Assert.assertFalse(new AccountNumber("345882862").isValid());
	}
	
	@Test public void testToStringForInvalidAccountNumberDisplaysERRStatus() throws Exception {
		
		AccountNumber accountNumber = new AccountNumber(" _     _  _  _  _  _  _  _ ",
														" _||_||_ |_||_| _||_||_  _|",
														" _|  | _||_||_||_ |_||_||_  ");
		
		Assert.assertEquals("345882862 ERR",accountNumber.toString());
	}
	
	@Test public void testToStringForAccountNumberWithUnrecognizedDigitCharacterDisplaysILLStatus() throws Exception {
		
		AccountNumber accountNumber = new AccountNumber(" _     _  _  _  _  _  _  _ ",
														" _ |_||_ |_||_| _||_||_  _|",
														" _| _| _||_||_||_ |_||_||_  ");

		Assert.assertEquals("??5882862 ILL",accountNumber.toString());
	}
	
	@Test public void testInvalidAccountNumberLength() {
		Assert.assertFalse(new AccountNumber("34588286512").isValid());
	}
	
	@Test public void testInvalidAccountNumberCharacter() {
		Assert.assertFalse(new AccountNumber("35588397c").isValid());
	}

	@Test public void testToStringWithoutStatusForInvalidAccountNumberDoesNotDisplayERRStatus() throws Exception {
		
		AccountNumber accountNumber = new AccountNumber(" _     _  _  _  _  _  _  _ ",
														" _||_||_ |_||_| _||_||_  _|",
														" _|  | _||_||_||_ |_||_||_  ");
		
		Assert.assertEquals("345882862",accountNumber.toStringWithoutStatus());
	}
	
	@Test public void testToStringWithoutStatusForAccountNumberWithUnrecognizedDigitCharacterDoesNotDisplayILLStatus() throws Exception {
		
		AccountNumber accountNumber = new AccountNumber(" _     _  _  _  _  _  _  _ ",
														" _ |_||_ |_||_| _||_||_  _|",
														" _| _| _||_||_||_ |_||_||_  ");

		Assert.assertEquals("??5882862",accountNumber.toStringWithoutStatus());
	}
	

}