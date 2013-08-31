package anilmathew.net.dff

class CommandLineInterface {

	CliBuilder cliBuilder
	DuplicateFileFinder dff
	
	static main(args) {
		CommandLineInterface cli = new CommandLineInterface()
		CliBuilder cliB = cli.cliBuilder
		def options = cliB.parse(args)
		if (options.help) {
			println cliB.usage()
		} else {
			String processingDirectory = options.d ? options.d : "."
			String reportDirectory = options.o ? options.o : "."
			File reportFile = new File(reportDirectory, "report.txt")
			cli.dff = new DuplicateFileFinder(reportFile)

			if (options.mf) {
				cli.dff.reportDuplicatesByNameFuzzyMatch(new File(processingDirectory), options.mf as int)
			} else if (options.mx) {
				cli.dff.reportDuplicatesByExactNameAndSize(new File(processingDirectory))
			} else {
				cli.dff.reportDuplicatesByExactName(new File(processingDirectory))
			}
						
//			Map result = cli.dff.findDuplicatesByExactNameAndSize(new File(processingDirectory))
//			println "Result map prior to filter : ${result.size()}"
//			result = result.findAll{ it.value.size() > 1}
//			println result
//			println "Result map ${result.size()}"
//			
//			File reportFile = new File(reportDirectory, "report.txt")
//			for (entry in result) {
//				reportFile << "${entry.key}\n"
//				entry.value.each {
//					reportFile << "\t${it}\n"
//				}
//				reportFile << "\n"
//			}
		}
		
	}
	
	CommandLineInterface() {
		cliBuilder = new CliBuilder(usage: 'dff')
		cliBuilder.o(args: 1, argName: 'report directory', 'output report directory.')
		cliBuilder.d(args: 1, argName: 'directory', 'starting directory to check for duplicates.')
		cliBuilder.mx('Exact name and size mode (default is just exact name)')
		cliBuilder.mf(args: 1, argName: 'match distance', 'Fuzzy match mode - match strength 1 is strongest')
		cliBuilder.help('Print this message')
	}

}