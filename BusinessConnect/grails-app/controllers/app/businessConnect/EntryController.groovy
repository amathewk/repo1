package app.businessConnect

import java.util.Date;

import org.apache.commons.logging.Log;
import org.codehaus.groovy.grails.web.json.*
import grails.converters.*
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils


class EntryController {

	final static int mobileDefaultPageSize = 3
	final static int webDefaultPageSize = 3

	def scaffold = Entry
	def searchService
	def geoService
	def preferenceService
	def springSecurityService

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		List entries
		if(SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')) {
			if (params.memberId) {
				entries = Entry.findAllByMember(User.get(params.memberId),params)
			} else {
				entries = Entry.list(params)
			}
		} else if(SpringSecurityUtils.ifAllGranted('ROLE_MEMBER')) {
			User member = springSecurityService.getCurrentUser()
			entries = Entry.findAllByMember(member,params)
		}
		withFormat {
			html { [entryInstanceList: entries, entryInstanceTotal: entries.size()] }
			xml {
				render entries as XML
			}
		}
	}

	def searchM = {
		log.debug "in searchM"
		log.debug "Params = $params"

		params.max = Math.min(params.max ? params.int('max') : mobileDefaultPageSize, 100)
		params.offset = params.offset ? params.offset : 0
		int offset = params.int('offset')
		int max = params.int('max')
		double lat = params.lat.toDouble()
		double lng = params.lng.toDouble()

		log.debug "lat: $lat, lng: $lng"
		log.debug "offset : $offset, max : $max"

		//		SearchResult searchResult = searchService.search ( (double)40.1421713, (double)-82.89636039999999, 500, offset, max )
		SearchResult searchResult = searchService.search ( lat, lng, 500, offset, max )
		populateDistanceAndDurationFrom ([lat: lat, lng: lng], searchResult.results)

		List entries = searchResult.results
		int totalCount = searchResult.totalResultsCount
		log.info "Total Count : ${totalCount }"

		withFormat {
			html {
				def criteria = Entry.createCriteria()
				def adEntries = criteria {
					isNotNull("ad")
					member {
						membershipLevel {
							eq("displayAdsOnMobile",true)
						}
					}
					maxResults(GlobalPreferences.get(1)['mobileAdDisplayCount'])
				}
				def nextOffset = offset + max < totalCount ? offset + max : ""
				def prevOffset = offset - max >= 0 ? offset - max : ""
				render (view:'mobile/searchResults', model: [entryInstanceList: entries, entryInstanceTotal: totalCount , nextOffset: nextOffset, prevOffset: prevOffset, max: params.max, lat: params.lat, lng: params.lng, adEntries:adEntries] )
			}
			xml {
				render entries as XML
			}
		}
	}

	def searchMByAddress = { SearchCommand searchCommand ->
		log.debug "in searchMByAddress"
		log.debug "Params = $params"

		if(searchCommand.hasErrors()) {
			render ( view : "/mobile/mobileSearchForm", model : [ searchCriteria : searchCommand ] )
			return
		} else {
			def originGeoCode = geoService.getGeoCode ( searchCommand.address )

			log.debug "hasNoresults : ${originGeoCode.hasNoResults}"
			if ( originGeoCode.hasNoResults ) {
				log.debug "Rendering invalid address message"
				flash.message="The address could not be found. Please check the address you have entered."
				render ( view : "/mobile/mobileSearchForm", model : [ message : "The address could not be found. Please check the address you have entered.", searchCriteria : searchCommand ] )
				return
			}

			params.max = Math.min(params.max ? params.int('max') : mobileDefaultPageSize, 100)
			params.offset = params.offset ? params.offset : 0
			int offset = params.int('offset')
			int max = params.int('max')
			double lat = originGeoCode.lat
			double lng = originGeoCode.lng

			log.debug "lat: $lat, lng: $lng"
			log.debug "offset : $offset, max : $max"

			//		SearchResult searchResult = searchService.search ( (double)40.1421713, (double)-82.89636039999999, 500, offset, max )
			SearchResult searchResult = searchService.search ( lat, lng, searchCommand.range as int, offset, max )
			populateDistanceAndDurationFrom ([lat: lat, lng: lng], searchResult.results)

			List entries = searchResult.results
			int totalCount = searchResult.totalResultsCount
			log.info "Total Count : ${totalCount }"

			withFormat {
				html {
					def criteria = Entry.createCriteria()
					def adEntries = criteria {
						isNotNull("ad")
						member {
							membershipLevel {
								eq("displayAdsOnMobile",true)
							}
						}
						maxResults(GlobalPreferences.get(1)['mobileAdDisplayCount'])
					}
					def nextOffset = offset + max < totalCount ? offset + max : ""
					def prevOffset = offset - max >= 0 ? offset - max : ""
					render (view:'mobile/searchResults', model: [entryInstanceList: entries, entryInstanceTotal: totalCount , nextOffset: nextOffset, prevOffset: prevOffset, max: params.max, lat: lat, lng: lng, adEntries:adEntries] )
				}
				xml {
					render entries as XML
				}
			}
		}
	}

	def save = {
		Entry entry = new Entry(params)
		User user
		if (params["member.username"] != null) {
			user = User.findByUsername(params["member.username"])
		} else {
			user = springSecurityService.getCurrentUser()
		}

		if (user.atMembershipLevelMax()) {
			flash.message = "You are at the maximum number of entries allowed under this membership level ($user.membershipLevel.entriesLimit). Either delete some entries or upgrade your membership level."
			render(view: "create", model: [entryInstance: entry])
		} else {
			entry.setMember(user)
			if (!params.bytes) { entry.setAd null } // Otherwise GORM sets empty byte array
			if (entry.save(flush: true)) {
				flash.message = "${message(code: 'app.businessConnect.Entry.created.message', args: [message(code: 'app.businessConnect.Entry.label', default: 'Entry'), entry.id, entry.correctedAddress])}"
				redirect(action: "show", id: entry.id)
			}
			else {
				render(view: "create", model: [entryInstance: entry])
			}
		}
	}

	def update = {
		def entryInstance = Entry.get(params.id)
		if (entryInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (entryInstance.version > version) {
					entryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'app.businessConnect.Entry.label', default: 'Entry')] as Object[], "Another user has updated this app.businessConnect.Entry while you were editing")
					render(view: "edit", model: [entryInstance: entryInstance])
					return
				}
			}
			entryInstance.properties = params
			entryInstance.setMember(springSecurityService.getCurrentUser())
			if (!params.bytes) { entryInstance.setAd null } // Otherwise GORM sets empty byte array
			if (!entryInstance.hasErrors() && entryInstance.save(flush: true)) {
				flash.message = "${message(code: 'app.businessConnect.Entry.updated.message', args: [message(code: 'app.businessConnect.Entry.label', default: 'Entry'), entryInstance.id, entryInstance.correctedAddress])}"
				redirect(action: "show", id: entryInstance.id)
			}
			else {
				render(view: "edit", model: [entryInstance: entryInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'app.businessConnect.Entry.label', default: 'app.businessConnect.Entry'), params.id])}"
			redirect(action: "list")
		}
	}

	def showImage = {
		def entry = Entry.get( params.id ) // get the record
		response.outputStream << entry.ad // write the image to the outputstream
		response.outputStream.flush()
	}

	def states = {
		def states = ['OH','NC']
		//Create XML response
		render(contentType: "text/xml") {
			results() {
				states.each { state ->
					result(){
						name(state)
						//Optional id which will be available in onItemSelect
						id(state)
					}
				}
			}
		}
	}

	def batchUpload = {
		def f = request.getFile('entriesFile')
		if(!f.empty) {
			f.transferTo( new File((new Date()).toString()) )
			response.sendError(200,'Done');
		}
		else {
			flash.message = 'file cannot be empty'
			render(view:'admin')
		}
	}

	class SearchCommand {

		String address
		int range

		static constraints = {
			address ( blank : false )
			range ( blank : false, range : 1..500 )
		}
	}

	def searchForm = {
		def criteria = Entry.createCriteria()
		def adEntries = criteria {
			isNotNull("ad")
			member {
				membershipLevel {
					eq("displayAdsOnWeb",true)
				}
			}
			maxResults(preferenceService.getGlobalConfigValue("adDisplayCount"))
		}
		return [adEntries:adEntries]
	}

	def search = { SearchCommand searchCommand ->
		if(searchCommand.hasErrors()) {
			render ( template : "/common/errors", model : [ command : searchCommand ] )
		}
		else {
			def originGeoCode = geoService.getGeoCode ( searchCommand.address )
			if ( originGeoCode.hasNoResults ) {
				log.debug "Rendering invalid address message"
				render ( template : "/common/errorMessage", model : [ message : "The address could not be found. Please check the address you have entered." ] )
			}
			else {
				session.originGeoCodeResult = originGeoCode;
				params.max = Math.min(params.max ? params.int('max') : webDefaultPageSize, 100)
				params.offset = params.offset ? params.int('offset') : 0;
				def searchResult = searchService.search ( originGeoCode, searchCommand.range.toInteger(), params.offset, params.max )
//				populateDistanceAndDurationFrom (originGeoCode, searchResult.results)
				return [ entryInstanceList : searchResult.results, entryInstanceTotal : searchResult.totalResultsCount, searchCommand : searchCommand ]
			}
		}
	}

	void populateDistanceAndDurationFrom (originGeoCode, results) {
		results.each { Entry entry ->
			//			entry.populateDistanceAndDurationFrom (originAddress)
			entry.populateDistanceFrom (originGeoCode.lat, originGeoCode.lng)
		}
	}

}