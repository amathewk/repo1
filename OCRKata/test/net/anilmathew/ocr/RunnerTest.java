package net.anilmathew.ocr;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class RunnerTest {

	@Test public void testMainDisplaysUsageMessageWhenPassedLessThanTwoArguments() throws Exception {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		
		System.setOut(ps);
		
		Runner.main(new String[]{});
		
		ps.flush();
		String output = baos.toString();

		Assert.assertEquals("Usage: <inputFile> <outputFile>\n", output);
		
	}

	@Test public void testMain() throws Exception {
		
		String outputFilePath = "testData/output.txt";
		Runner.main(new String[]{"testData/500.txt", outputFilePath});
		File outputFile = new File(outputFilePath);
		
		Assert.assertTrue(outputFile.exists());
		
		String outputFileContent = FileUtils.readFileToString(outputFile);
		
		int numberOfDigits = 9;
		int spacePlusAMBStatus = 4;
		int newLine = 1;
		int lengthPerAccountNumberLine = numberOfDigits + spacePlusAMBStatus + newLine;
		
		Assert.assertEquals((lengthPerAccountNumberLine)*500, outputFileContent.length());
		
	}

}
