package anilmathew.net.dff

import groovy.io.FileType;

import org.apache.commons.lang.StringUtils;

class DuplicateFileFinder {

	def reportFile
	def fileExceptionList = [".jpg", "desktop.ini"]
	def directoryExceptionList = []
	boolean exactSizeFilterEnable = false

	public DuplicateFileFinder(reportFile) {
		this.reportFile = reportFile
	}

	void reportDuplicatesByExactNameAndSize(rootDir) {
		Map resultMap = findDuplicatesByExactNameAndSize(rootDir)
		printDuplicatesMap(resultMap, rootDir)
	}	
	
	void reportDuplicatesByExactName(rootDir) {
		Map resultMap = findDuplicatesByExactName(rootDir)
		printDuplicatesMap(resultMap, rootDir)
	}	
	
	void reportDuplicatesByNameFuzzyMatch(rootDir, int matchStrength) {
		Map resultMap = findDuplicatesByNameFuzzyMatch(rootDir, matchStrength)
		printDuplicatesMap(resultMap, rootDir)
	}	
	
	/**
	 * @param rootDir directory to search
	 * @return Map of file name and size to list of paths
	 */
	Map findDuplicatesByExactNameAndSize(rootDir) {
		return findDuplicates(rootDir, exactNameAndSizeKeyGenerator)
	}

	/**
	 * @param rootDir directory to search
	 * @return Map of file name to list of paths
	 */
	Map findDuplicatesByExactName(File rootDir) {
		return findDuplicates(rootDir, exactNameKeyGenerator)
	}

	/**
	 * @param rootDir directory to search
	 * @param keyGenerationMethod closure that returns map key
	 * @return Map of file name to list of paths
	 */
	private Map findDuplicates(rootDir, keyGenerationMethod) {
		def resultMap = [:]

		rootDir.eachFile(FileType.FILES) { file ->
			def list = resultMap[keyGenerationMethod(file)]
			if (!list) {
				list = [];
				resultMap[keyGenerationMethod(file)] = list
			}
			list << "${rootDir.path}:${file.size()}".toString()
		}

		rootDir.eachDirRecurse { dir ->
			println dir.path
			if (!inExceptionList(dir.path)) {
				dir.eachFile(FileType.FILES) { file ->
					def list = resultMap[keyGenerationMethod(file)]
					if (!list) {
						list = []
						resultMap[keyGenerationMethod(file)] = list
					}
					list << "${dir.path}:${file.size()}".toString()
				}
			}
		}
		
		resultMap = resultMap.findAll{ it.value.size() > 1} // filter for just duplicates
		println "nameMap size : $resultMap.size()"
		if (resultMap.size() == 0) {println "No files found in $rootDir"}
		return resultMap
	}

	/**
	 * @param rootDir directory to search
	 * @param matchStrength match strength from 1 to 10
	 * @return Map of file name to list of paths
	 */
	Map findDuplicatesByNameFuzzyMatch(rootDir, matchStrength) {

		def resultMap = [:]
		
		rootDir.eachFile(FileType.FILES) { file ->
			def list = resultMap[findFuzzyMatchedKey( file.name, resultMap, matchStrength )]
			if (!list) {
				list = [];
				resultMap[findFuzzyMatchedKey( file.name, resultMap, matchStrength )] = list
			}
			list << "${rootDir.path}${File.separator}${file.name}:${file.size()}"
		}
						
		rootDir.eachDirRecurse { dir ->
			println dir.path
			if (!inExceptionList(dir.path)) {
				println "entered"
				dir.eachFile(FileType.FILES) { file ->
					def list = resultMap[findFuzzyMatchedKey( file.name, resultMap, matchStrength )]
					if (!list) {
						list = []
						resultMap[findFuzzyMatchedKey( file.name, resultMap, matchStrength )] = list
					}
					list << "${dir.path}${File.separator}${file.name}:${file.size()}".toString()
				}
			}
		}
		resultMap = resultMap.findAll{ it.value.size() > 1} // filter for just duplicates
		println "nameMap size : $resultMap.size()"
		if (resultMap.size() == 0) {println "No files found in $rootDir"}
		return resultMap

//		def resultMap = [:]
//		rootDir.eachDirRecurse { dir ->
//			dir.eachFile { file ->
//				def key =  findFuzzyMatchedKey( file.name, resultMap, matchStrength )
//				if (key != file.name) {
//					//					println "fuzzy match for ${file.name} is ${key}"
//				}
//				def list = resultMap[key]
//				if (!list) {
//					list = [];
//					resultMap[key] = list
//				}
//				list << "${file.path}:${file.size()}"
//			}
//		}
//		return resultMap
	}

	def exactNameKeyGenerator = { file ->
		return "${file.name}"
	}

	def exactNameAndSizeKeyGenerator = { file ->
		return "${file.name}:${file.size()}"
	}

	private String findFuzzyMatchedKey(fileName, Map nameMap, matchStrength) {
		def candidateMatches = []
		nameMap.keySet().each { fileNameFromMap ->
			if (!fileName) { println "fileName null"; System.exit(1) }
			if (!fileNameFromMap) { println "fileNameFromMap null"; System.exit(1) }
			int distance = StringUtils.getLevenshteinDistance( fileName, fileNameFromMap )
			if ( withinMatchDistance ( distance, fileName.length() , matchStrength ) ) {
				candidateMatches << [name:fileNameFromMap, distance:distance]
			}
		}
		def closestMatchedName = fileName
		def closestMatch = closestMatchFromCandidates ( candidateMatches )
		if ( closestMatch != null ) {
			closestMatchedName = closestMatch.name
		}
		return closestMatchedName
	}

	private boolean withinMatchDistance(distance, fileNameLength, minMatchStrength) {
		def matchStrength = 10 - ( (distance / fileNameLength) * 10 )
		return  matchStrength >  minMatchStrength
	}

	private def closestMatchFromCandidates(List candidateMatches) {
		candidateMatches.min { it.distance }
	}

	private void printDuplicatesMap(nameMap, root) {
		nameMap.each { key, value ->
			reportFile << key + "\n"
			reportFile << value.collect{ stripRootPath(it, root.path) } + "\n"
			reportFile << "\n"
		}
	}

	private String stripRootPath(filePath, rootPath) {
		filePath.replace(rootPath, "")
	}

	private boolean inExceptionList(String path) {
		boolean inExceptionList = false
		directoryExceptionList.each {excludedDir ->
			retrieveDirectoryNames(new File(path)).each {part ->
				if (part == excludedDir) {
					inExceptionList = true
					return
				}
			}
			if (inExceptionList) {return}
		}
		return inExceptionList
	}

	private List<String> retrieveDirectoryNames(File fullPath) {
		if (fullPath == null) {return []}
		List directoryNames = retrieveDirectoryNames(fullPath.getParentFile())
		directoryNames << fullPath.getName()
		return directoryNames
	}
}