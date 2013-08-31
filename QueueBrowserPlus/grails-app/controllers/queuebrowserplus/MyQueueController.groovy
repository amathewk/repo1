package queuebrowserplus

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import queuebrowserplus.items.QueueItem;
import queuebrowserplus.items.QueueResult;

class MyQueueController {

    def netflixBridgeService
    def rottenTomatoesBridgeService

    String accessToken = "BQAJAAEDEAEhjfInAY7FBD08Db9r9FAwdu0EESW0vybJttdVZH8hKY-d7e21CM7k8LUKrcwqo9FfiAkw4n9cKYrIxokbdCC1"
    String tokenSecret = "aJknMGuzrgAK"
    String userId = "T1__JyEskfMDDgkOldxCf708Mob4K8qdn5ZvZPGdybsXA-"

    private static Long queueRefreshIntervalInMillis = 120 * 1000;

    def index() {

        RTItem rtItem = new RTItem(netflixId: "123")
        rtItem.save(flush: true, failOnError: true)
        render("saved")
    }

    def index2() {
        RTItem rt2 = RTItem.findByNetflixId("123")
        render(rt2.netflixId)
    }

    def queue() {

        log.info "Entering queue controller"
        log.debug "params: $params"

//        List.metaClass.paginate = { max, offset=0 ->
//            delegate.subList( Math.min( offset, delegate.size() ), Math.min( offset + max, delegate.size() ) )
//        }

        int startIndex = params.offset ? params.offset.toInteger() : 0
        int maxResults = params.max ? params.max.toInteger() : 50

        if (params.sort == null) {
            params.sort = "position"
        }

        QueueResult queueResult = retrieveCompleteQueue()

        def items = queueResult.items
        items.sort { a, b ->
            if (params.order == "desc") {
                getProperty(b, "${params.sort}") <=> getProperty(a, "${params.sort}")
            } else {
                getProperty(a, "${params.sort}") <=> getProperty(b, "${params.sort}")
            }

        }
//        params.max=50
//        params.offset=startIndex
//        render(view: "queue", model: [items: queueResult.items.paginate(50, startIndex), queueCount: queueResult.queueCount, max: 50, offset: startIndex])

        items = queueResult.items.subList( startIndex, Math.min( startIndex + maxResults, queueResult.items.size() ) )

        log.debug " size : ${items.size()}"

        render(view: "queue", model: [items: items, queueCount: queueResult.queueCount, max: 50, offset: startIndex])
    }

    private retrieveCompleteQueue() {
        if (session["queue"] == null || queueNeedsRefreshed()) {
            log.info "Retrieving queue from Netflix"
            QueueResult queueResult = netflixBridgeService.getCompleteInstantQueue(userId, accessToken, tokenSecret)
            rottenTomatoesBridgeService.populateRatings(queueResult.items)
            session["queue"] = queueResult
            session["lastQueueRefreshTime"] = System.currentTimeMillis()
        }
        log.info "Queue from session has ${(session['queue']).items.size()} times"
        return session["queue"]
    }

    private boolean queueNeedsRefreshed() {
        boolean needsRefresh = false
        Long lastQueueRefreshTime = session["lastQueueRefreshTime"]
        log.debug "lastQueueRefreshTime: $lastQueueRefreshTime"
        if (lastQueueRefreshTime != null) {
            long currentTimeMillis = System.currentTimeMillis()
            log.debug "currentTimeMillis: $currentTimeMillis, queueRefreshIntervalInMillis: $queueRefreshIntervalInMillis"
            if (currentTimeMillis - lastQueueRefreshTime > queueRefreshIntervalInMillis) {
                needsRefresh = true
            }
        } else {
            needsRefresh = true
        }
        log.info "Queue needs refreshed ? $needsRefresh"
        return needsRefresh
    }

    def getProperty(object, String property) {

        property.tokenize('.').inject object, {obj, prop ->
            if (obj) obj[prop]
        }
    }
}