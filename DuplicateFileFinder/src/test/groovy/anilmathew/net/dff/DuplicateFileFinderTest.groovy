package anilmathew.net.dff;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import anilmathew.net.dff.DuplicateFileFinder

class DuplicateFileFinderTest {

	DuplicateFileFinder duplicateFileFinder
	File reportFile
	File testFileDirectory
	
	@Before
	public void setUp() throws Exception {
		reportFile = new File("report.txt")
		duplicateFileFinder = new DuplicateFileFinder(reportFile)
		duplicateFileFinder.setFileExceptionList(["ignore", ".svn"])
		testFileDirectory = new File("test/sampleDirs")
//		String sampleDirsBaseThread.currentThread().getContextClassLoader().getResource("sampleDirs").getFile()
		testFileDirectory =  new File(Thread.currentThread().getContextClassLoader().getResource("sampleDirs").getFile())
//		testFileDirectory.mkdir()
//		new File(testFileDirectory, "dir1").mkdir()
//		new File(testFileDirectory, "dir2").mkdir()
//		new File(testFileDirectory, "dir3").mkdir()
	}

	@After
	public void tearDown() throws Exception {
//		new AntBuilder().delete(dir: "testFiles")
		testFileDirectory.delete()
	}

	@Test
	public final void testFindDuplicatesByExactNameAndSize() {
//		File notes1 = new File(testFileDirectory, "notes1.txt");
//		println notes1.createNewFile()
//		notes1 << "1234"
		duplicateFileFinder.setDirectoryExceptionList([".svn", "ignore"])
		Map resultMap = duplicateFileFinder.findDuplicatesByExactNameAndSize(testFileDirectory)
		assertNotNull(resultMap)
		assertEquals(3, resultMap.size())
		assertTrue(resultMap.keySet().contains("notes.txt:5"))
		assertTrue(resultMap.keySet().contains("notes2.txt:5"))
		assertTrue(resultMap.keySet().contains("notes.txt:2"))
		assertEquals(2, resultMap["notes.txt:5"].size())
		assertEquals(1, resultMap["notes.txt:2"].size())
		assertEquals(1, resultMap["notes2.txt:5"].size())
		assertTrue(resultMap["notes.txt:5"].contains(new File(testFileDirectory,"docs").getPath()))
		assertTrue(resultMap["notes.txt:5"].contains(new File(testFileDirectory,"docs/temp").getPath()))
		assertFalse(resultMap["notes.txt:5"].contains(new File(testFileDirectory,"music").getPath()))
		assertFalse(resultMap["notes.txt:5"].contains(new File(testFileDirectory,"docs/ignore").getPath()))
	}

	@Test
	public final void testFindDuplicatesByExactName() {
		duplicateFileFinder.setDirectoryExceptionList([".svn", "ignore"])
		Map resultMap = duplicateFileFinder.findDuplicatesByExactName(testFileDirectory)
		
		assertNotNull(resultMap)
		assertEquals(2, resultMap.size())
		assertTrue(resultMap.keySet().contains("notes.txt"))
		assertTrue(resultMap.keySet().contains("notes2.txt"))
		assertEquals(3, resultMap["notes.txt"].size())
		assertEquals(1, resultMap["notes2.txt"].size())
		assertTrue(resultMap["notes.txt"].contains(new File(testFileDirectory,"docs").getPath()))
		assertTrue(resultMap["notes.txt"].contains(new File(testFileDirectory,"docs/temp").getPath()))
		assertTrue(resultMap["notes.txt"].contains(new File(testFileDirectory,"music").getPath()))
		assertFalse(resultMap["notes.txt"].contains(new File(testFileDirectory,"docs/ignore").getPath()))
	}

	@Test
	public final void testFindDuplicatesByNameFuzzyMatchWithMatchStrength3() {
		duplicateFileFinder.setDirectoryExceptionList([".svn", "ignore"])
		Map resultMap = duplicateFileFinder.findDuplicatesByNameFuzzyMatch(testFileDirectory, 3)
		assertNotNull(resultMap)
		println resultMap
		assertEquals(1, resultMap.size())
		assertTrue(resultMap.keySet().contains("notes.txt"))
		assertFalse(resultMap.keySet().contains("notes2.txt"))
		assertEquals(4, resultMap["notes.txt"].size())
		
		new File(testFileDirectory,"docs").getPath()
		
		assertTrue(resultMap["notes.txt"].contains(new File(testFileDirectory,"docs/notes.txt").getPath()))
		assertTrue(resultMap["notes.txt"].contains(new File(testFileDirectory,"docs/notes2.txt").getPath()))
		assertTrue(resultMap["notes.txt"].contains(new File(testFileDirectory,"docs/temp/notes.txt").getPath()))
		assertTrue(resultMap["notes.txt"].contains(new File(testFileDirectory,"music/notes.txt").getPath()))
		assertFalse(resultMap["notes.txt"].contains(new File(testFileDirectory,"docs/ignore/notes.txt").getPath()))
	}

	@Test
	public final void testFindDuplicatesByNameFuzzyMatchWithMatchStrength9() {
		duplicateFileFinder.setDirectoryExceptionList([".svn", "ignore"])
		Map resultMap = duplicateFileFinder.findDuplicatesByNameFuzzyMatch(testFileDirectory, 9)
		assertNotNull(resultMap)
		println resultMap
		assertEquals(2, resultMap.size())
		assertTrue(resultMap.keySet().contains("notes.txt"))
		assertTrue(resultMap.keySet().contains("notes2.txt"))
		assertEquals(3, resultMap["notes.txt"].size())
		assertTrue(resultMap["notes.txt"].contains(new File(testFileDirectory,"docs/notes.txt").getPath()))
		assertTrue(resultMap["notes.txt"].contains(new File(testFileDirectory,"docs/temp/notes.txt").getPath()))
		assertFalse(resultMap["notes.txt"].contains(new File(testFileDirectory,"docs/notes2.txt").getPath()))
		assertFalse(resultMap["notes.txt"].contains(new File(testFileDirectory,"docs/ignore/notes.txt").getPath()))
		assertTrue(resultMap["notes2.txt"].contains(new File(testFileDirectory,"docs/notes2.txt").getPath()))
	}
	
}