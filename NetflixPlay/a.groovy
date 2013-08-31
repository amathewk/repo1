import groovy.util.FileNameByRegexFinder;

import java.io.File;


class Splitter {

	FileNameByRegexFinder finder = new FileNameByRegexFinder()

	boolean hasFlac(File directory) {
		! finder.getFileNames(directory.absolutePath, ".*\\.flac").isEmpty()
	}

	void copy(File commonBasePath, File source, File destRoot) {
		println commonBasePath
		println source.absolutePath
		println destRoot.absolutePath
//		String regex = (commonBasePath.absolutePath + "\\").replace("\\","\\\\")
		String regex = (commonBasePath.absolutePath + "/")
		println source.absolutePath.replaceFirst(regex, "")

		def command = "mv $source $destRoot/"
		println command		
	}
	
	void split(commonBasePathDir, startingDirectory, destDirectory) {
		
		startingDirectory.eachDirRecurse { File dir ->
				println "processing $dir"
				println hasFlac(dir)
				if (hasFlac(dir)) {
					String newPath = dir.absolutePath.replace("music","HD")
					String command = "mkdir -p $newPath"
					println command
					command = "mv $dir $newPath"
					println command
				}
				System.exit(0)
		}
		System.exit(0)
		copy(commonBasePathDir, startingDirectory, destDirectory)
	}
}

String commonBasePath = "/media/store/audio/"
File commonBasePathDir = new File(commonBasePath)
File startingDirectory = new File(commonBasePath + "music")
File destDirectory = new File(commonBasePath+"HD")

new Splitter().split(commonBasePathDir, startingDirectory, destDirectory)