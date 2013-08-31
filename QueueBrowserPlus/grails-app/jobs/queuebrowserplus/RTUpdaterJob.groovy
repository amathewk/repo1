package queuebrowserplus

import java.util.logging.Logger;



class RTUpdaterJob {

	def rottenTomatoesBridgeService

    def concurrent = false
	
	static triggers = {
      simple name:'Simple Trigger', repeatInterval: 30000
    }

    def execute() {
		log.debug "Executing job"
        rottenTomatoesBridgeService.refreshRatings()
//		RTItem.findAllWhere({})
		
//		netflixBridgeService.test()
    }
}
