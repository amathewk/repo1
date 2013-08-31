package net.filebuilder

import net.filebuilder.FileBuilder;

class FileBuilderTest extends GroovyTestCase {

	FileBuilder fileBuilder
	
	void setUp() {
		File res = new File("test/resources")
		if (res.exists()) {
			res.deleteDir()
		}
		res.mkdirs()
		fileBuilder = new FileBuilder()
	}
	
	void tearDown() {
		File res = new File("test/resources")
		if (res.exists()) {
			res.deleteDir()
		}
	}
	
	void testCreatesSingleDirectory() {
		fileBuilder.dir1(parentPath : "test/resources")
		assert (new File("test/resources/dir1").exists())
	}

	void testCreatesSingleFile() {
		fileBuilder.file1(parentPath : "test/resources", "some content") {
		}
		assert (new File("test/resources/file1")).exists()
		assert "some content".equals(new File("test/resources/file1").text)
	}
	
	void testCreatesSingleDirectoryWithSingleFileWithContent() {
		fileBuilder.dir1(parentPath : "test/resources") {
			file1("some content") {}
		}
		assert (new File("test/resources/dir1")).exists()
		assert (new File("test/resources/dir1/file1")).exists()
		assert "some content".equals(new File("test/resources/dir1/file1").text)
	}
	
	void testCreatesSingleDirectoryWithSingleFileWithNoContent() {
		fileBuilder.dir1(parentPath : "test/resources") {
			file1("") {
			}
		}
		assert (new File("test/resources/dir1")).exists()
		assert (new File("test/resources/dir1/file1")).exists()
		assert (new File("test/resources/dir1/file1").text == "")
	}
	
	void testCreatesSingleDirectoryWithMultipleFiles() {
		fileBuilder.dir1(parentPath : "test/resources") {
			file1("") {
			}
			file2("some content") {
			}
		}
		assert (new File("test/resources/dir1")).exists()
		assert (new File("test/resources/dir1/file1")).exists()
		assert (new File("test/resources/dir1/file2")).exists()
		assertEquals("some content", (new File("test/resources/dir1/file2")).text)
	}
	
	void testCreatesMultipleDirectoriesAndMultipleFiles() {
		fileBuilder.dir1(parentPath : "test/resources") {
			file1("") {
			}
			file2("some content") {
			}
			dir2() {
			}
			dir3() {
			}
		}
		assert (new File("test/resources/dir1")).exists()
		assert (new File("test/resources/dir1/file1")).exists()
		assert (new File("test/resources/dir1/file2")).exists()
		assertEquals("some content", (new File("test/resources/dir1/file2")).text)
		assert (new File("test/resources/dir1/dir2")).exists()
		assert (new File("test/resources/dir1/dir3")).exists()
	}
	
	void testCreatesMultipleNestedDirectoriesWithMultipleFiles() {
		fileBuilder.dir1(parentPath : "test/resources") {
			file1("") {
			}
			file2("some content") {
			}
			dir2() {
				file21("some content") {
				}
			}
			dir3() {
				file31("") {
				}
				file32("some content") {
				}
			}
		}
		assert (new File("test/resources/dir1")).exists()
		assert (new File("test/resources/dir1/file1")).exists()
		assert (new File("test/resources/dir1/file2")).exists()
		assertEquals("some content", (new File("test/resources/dir1/file2")).text)
		assert (new File("test/resources/dir1/dir2")).exists()
		assert (new File("test/resources/dir1/dir3")).exists()
		assert (new File("test/resources/dir1/dir2/file21")).exists()
		assert (new File("test/resources/dir1/dir3/file31")).exists()
		assert (new File("test/resources/dir1/dir3/file32")).exists()
		assertEquals ("some content", (new File("test/resources/dir1/dir3/file32")).text)
	}
}