package queuebrowserplus

import java.net.URLEncoder;

import groovy.json.JsonSlurper;
import queuebrowserplus.items.QueueItem;
import queuebrowserplus.RTRatings;

class RottenTomatoesBridgeService {

//	String apiKey = "qcp9b49x2n47tu75yqxr67q4"
	String apiKey = "qcp9b49x2n47tu75yqxr67q4"
//                     qcp9b49x2n47tu75yqxr67q4
    // http://api.rottentomatoes.com/api/public/v1.0/movies.json?q=The+Escapist&apikey=qcp9b49x2n47tu75yqxr67q4

	URLEncoder enc = new URLEncoder()
    JsonSlurper slurper = new JsonSlurper()

	long expPeriod = 24*60*60*1000

	def populateRatings(List<QueueItem> queueItems) {
		log.info "Populating ratings"
		queueItems.each { queueItem ->
            def rtItem = RTItem.findByNetflixId(queueItem.id)
            if(rtItem) {
                queueItem.rtRatings = rtItem.rtRatings
            } else {
                log.trace("Persisting new RTItem placeholder.")
                (new RTItem(netflixId: queueItem.id, netflixTitle: queueItem.title)).save()
            }
		}
		return queueItems
	}

	def search(term) {
        def response = null
		try {
            term = enc.encode(term, "UTF-8")
            URL url = new URL("http://api.rottentomatoes.com/api/public/v1.0/movies.json?q=${term}&apikey=${apiKey}")
            response = slurper.parseText(url.text)
        } catch (Exception ex) {
            log.error("Could not get rotten tomatoes response for $term", ex)
        }
        return response
	}

	void refreshRatings() {
		log.info "Starting refreshRatings"

		Date now = new Date()
		long timeInMillis = now.time - expPeriod
		Date expirationTime = new Date(timeInMillis)
		List items = RTItem.findAllByRtRatingsIsNullOrLastCheckLessThan(expirationTime, [max:30])

        log.debug "Refreshing ratings for ${items.size()} items."

		items.each { RTItem item ->
			log.trace "Finding rating for ${item.netflixTitle}"
			def result = search(item.netflixTitle)
            if (result) {
                def rtMovieMatch = result.movies.find {it.title.equalsIgnoreCase(item.netflixTitle) }
                if (rtMovieMatch) {
                    item.rtTitle = rtMovieMatch.title
                    item.rtId = rtMovieMatch.id
                    def ratingsFromRT = rtMovieMatch.ratings
                    item.rtRatings = new RTRatings(criticsRating: ratingsFromRT?.critics_rating, criticsScore: ratingsFromRT?.critics_score,
                            audienceRating: ratingsFromRT?.audience_rating, audienceScore: ratingsFromRT?.audience_score)
                } else {
                    log.trace "Could not find RT match for netflix title ${item.netflixTitle}"
                }
                log.trace "Saving $item.netflixTitle"
                item.lastCheck = new Date()

                if (!item.save(flush: true, failOnError : true)) {
                    item.errors.each {
                        log.error it
                    }
                }
            }
		}
	}

	RTRatings findRatingOfMatch(movies, QueueItem queueItem) {
		String title = queueItem.title
		log.info "Finding rating for $title in list from rotten tomatoes"
		def rtMovieMatch = movies.find {it.title.equalsIgnoreCase(title) }
		RTItem rtItem = persistMatchToDB(queueItem.netflixId, queueItem.netflixTitle, rtMovieMatch)
		return rtItem.rtRatings
	}

	private RTItem persistMatchToDB(String netflixId, String netflixTitle, rtMovie) {
		log.debug("Persisting RT Item to database for ${netflixTitle}[${rtMovie?.title}]")
		RTItem rtItem = new RTItem(netflixId: (netflixId), rtId: rtMovie?.title , netflixTitle: netflixTitle, rtTitle: rtMovie?.title)
		def ratingsFromRT = rtMovie?.ratings
		if (ratingsFromRT != null) {
			rtItem.rtRatings = new RTRatings(criticsRating: ratingsFromRT?.critics_rating, criticsScore: ratingsFromRT?.critics_score,
			audienceRating: ratingsFromRT?.audience_rating, audienceScore: ratingsFromRT?.audience_score)
		}
		rtItem.save(flush:true, failOnError:true)
	}

}
