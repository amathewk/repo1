/**
 * 
 */
package net.soadna.webstat.core

import groovy.sql.Sql
import groovy.util.XmlNodePrinterimport groovy.xml.*
import java.sql.Timestampimport org.codehaus.groovy.control.io.URLReaderSourceimport org.codehaus.groovy.control.CompilerConfigurationimport java.io.FileNotFoundException
import java.util.Mapimport java.util.UUID

/**
 * @author Anil
 *
 */
public class StatProcessor{
	
	private String dbURL="jdbc:mysql://localhost:3306/WEBSTATS"
	private String username="root"
	private String password="dumpass"
	private String dbDriver="com.mysql.jdbc.Driver"
	
	public static void main(String[] args){
		
//		StatProcessor statProcessor = new StatProcessor()
//		
//		URL url = new URL("http://localhost/WebStats/clickInfo.txt")
//		if (statProcessor.collectClickStats(url)) {
//			File graphFile = new File("clickData.xml")
//			statProcessor.processClickStats(graphFile)
//		} else {
//			println "no remote click stat file"
//		}
//
//		url = new URL("http://localhost/WebStats/urlInfo.txt")
//		if (statProcessor.collectURLStats(url)) {
//			File graphFile = new File("urlData.xml")
//			statProcessor.processURLStatsUniqueVisitors(graphFile)
//			graphFile = new File("E:\\urlHitData.xml")
//			statProcessor.processURLStatsPageNavigationTargetBreak(graphFile, null)
//			
//		} else {
//			println "no remote url stat file"
//		}

		StatProcessor statProcessor = new StatProcessor()
		File graphFile;
		
//		graphFile = new File("clickData.xml")
//		statProcessor.processClickStats(graphFile)
		
		graphFile = new File("urlData.xml")
		statProcessor.processURLStatsUniqueVisitors(graphFile)
		
//		graphFile = new File("E:\\urlHitData.xml")
//		statProcessor.processURLStatsPageNavigationTargetBreak(graphFile, null)
			
		
	}
	
	StatProcessor() {
		this('jdbc.properties')
	}

	StatProcessor(File jdbcPropFile) {
		
		if (jdbcPropFile.exists()) 
		{
			def config = new ConfigSlurper().parse(jdbcPropFile.toURL())
			println config.url + config.username + config.password + config.driver
			
			dbURL = config.url
			username = config.username
			password = config.password
			dbDriver = config.driver
		}
		else
		{
			println "jdbc property file not found"
		}
		
	}
	
	/**
	 * Parse stat file on remote server and persist
	 */
	public boolean collectClickStats(URL remoteFileURL){ 

		URLReaderSource statSource = new URLReaderSource(remoteFileURL, CompilerConfiguration.DEFAULT);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(statSource.getReader());
			// TODO LOG file found
		} catch (FileNotFoundException ex){
			// file not found on the server
			// could be that file has already been consumed or an error?
			// TODO: LOG FILE NOT FOUND DEBUG LEVEL
			return false
		}
		
		Sql sql = Sql.newInstance(dbURL, username,
                password, dbDriver)

		String line
		
		String time = null
		String ip = null
		String sessionId = UUID.randomUUID().toString().substring(0,30)
		
		while (line = reader.readLine()) {
	       	
			Map paramMap = parseParams(line)
			
			// first iteration
			if (time == null){	time = paramMap["time"] }
			if (ip == null) { ip = paramMap["ip"] }
			
			if ((!ip.equals(paramMap["ip"]))  ||  (((time.toLong()) - ((paramMap["time"]).toLong())) > 30000) ) {
				// if different ip or time from last event more than 30 minutes
				sessionId = UUID.randomUUID().toString().substring(0,30)
			}
			
			ip = paramMap["ip"]
			time = paramMap["time"]

			
	       	List ids = 	sql.executeInsert(
					""" insert into EVENT (CLIENT_ID, IP, SESSION_ID,
					 TIME) values (?,?,?,?)
					"""
				, [paramMap["clientId"], paramMap["ip"], sessionId, new Timestamp((paramMap["time"]).toLong())])	

				def id = ids[0][0]
				
				println "PARAMMAP" + paramMap
			
				sql.executeInsert(
				""" insert into CLICK_EVENT (EVENT_ID, ELEM_ID,
					ELEM_NAME, ELEM_TYPE, VALUE) values (?,?,?,?,?)
				"""
				, [ids[0][0], paramMap["id"], paramMap["name"], paramMap["type"]
					, paramMap["value"]])
				
		}
		
		reader.close()

		return true
		
	}
	
	/**
	 * Parse stat file on remote server and persist
	 */
	public boolean collectURLStats(URL remoteFileURL){ 
		
		URLReaderSource statSource = new URLReaderSource(remoteFileURL, CompilerConfiguration.DEFAULT);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(statSource.getReader());
			// TODO LOG file found
		} catch (FileNotFoundException ex){
			// file not found on the server
			// could be that file has already been consumed or an error?
			// TODO: LOG FILE NOT FOUND DEBUG LEVEL
			return false
		}
		
		Sql sql = Sql.newInstance(dbURL, username,
                password, dbDriver)

		
		String line
		String time = null
		String ip = null
		String sessionId = UUID.randomUUID().toString().substring(0,30)
		
		while (line = reader.readLine()) {
	       	
		Map paramMap = parseParams(line)

		// first iteration
		if (time == null){	time = paramMap["time"] }
		if (ip == null) { ip = paramMap["ip"] }

		if ((!ip.equals(paramMap["ip"]))  ||  (((time.toLong()) - ((paramMap["time"]).toLong())) > 30000) ) {
			// if different ip or time from last event more than 30 minutes
			sessionId = UUID.randomUUID().toString().substring(0,30)
		}
		
		ip = paramMap["ip"]
		time = paramMap["time"]
		
	       	
	    List ids = 	sql.executeInsert(
				""" insert into EVENT (CLIENT_ID, IP, SESSION_ID, 
					 TIME, TARGET_HIT) values (?,?,?,?,?)
				"""
				, [paramMap["clientId"], paramMap["ip"], sessionId, new Timestamp((paramMap["time"]).toLong()), paramMap["targetHit"]])	

		sql.executeInsert(
				""" insert into URL_EVENT (EVENT_ID, URL) values (?,?)
				"""
				, [ids[0][0], paramMap["url"]])	

		}
		
		
		reader.close()

		return true
		
	}
	
	private Map parseParams(String line) {
		
		Map paramMap = new HashMap()
		
		def paramPairs = line.split("&")
		paramPairs.each {
			def matches = ( it =~ /(.+)=(.+)/ ) 
			paramMap[(matches[0][1]).toString()]=(matches[0][2]).toString()
		}
		
		return paramMap
		
	}
	
	/**
	 * write stats from db to fusion charts format xml file
	 */
	public void processClickStats(File graphFile) {
		
		def writer = new StringWriter()
		def xml = new MarkupBuilder(writer)
		
		xml.graph(caption:'Click hits', showNames:1, decimalPrecision:0) 
		{
		
			Sql sql = Sql.newInstance(dbURL, username,
	                password, dbDriver)
	                
	        sql.eachRow("select ELEM_ID, count(*) as num from foresee.EVENTVIEW where type='click' group by ELEM_ID", 
	        		{
	        			xml.set(name:"id="+it.ELEM_ID, value:it.num)
	        		}
	        )
		}
		
		// TODO: log debug string 
		
		graphFile.write(writer.toString());
		
	}

	/**
	 * line graph of unique visitors by day
	 * write stats from db to fusion charts format xml file
	 */
	public void processURLStatsUniqueVisitors(File graphFile) {
		
		def writer = new StringWriter()
		def xml = new MarkupBuilder(writer)
		
		Sql sql = Sql.newInstance(dbURL, username,
                password, dbDriver)

                
		
        // TODO: modify attributes below based on data values e.g. maxYValue, # days
		xml.graph(caption:'Unique Daily Visitors', subcaption:'(20 days)', hovercapbg:'FFECAA', hovercapborder:'F47E00', formatNumberScale:'0', decimalPrecision:'0', showvalues:'0', numdivlines:'3', numVdivlines:'0', yaxisminvalue:'0', yaxismaxvalue:'20',  rotateNames:'1') 
		{
        		xml.categories
        		{
        			sql.eachRow("select date(TIMESTAMP) as theDay, count(distinct REMOTEADDR) as num from EVENTVIEW group by date(TIMESTAMP) order by date(TIMESTAMP) asc", 
	        			{
							xml.category(name:it.theDay)
						}
        			)
        		}
				xml.dataset(seriesName:'Visitor Count', color:'F1683C', anchorBorderColor:'F1683C', anchorBgColor:'F1683C')
				{
					sql.eachRow("select date(TIMESTAMP) as theDay, count(distinct REMOTEADDR) as num from EVENTVIEW group by date(TIMESTAMP) order by date(TIMESTAMP) asc", 
						{
							xml.set(value:it.num)
						}
					)
				}
		}
		
		// TODO: log debug string 
		
		graphFile.write(writer.toString());
		
	}

	/**
	 * pie chart of break out by page url
	 * write stats from db to fusion charts format xml file
	 */
	public void processURLStatsPageNavigationTotal(File graphFile, String clientId) {
		
		def writer = new StringWriter()
		def xml = new MarkupBuilder(writer)
		
		Sql sql = Sql.newInstance(dbURL, username,
                password, dbDriver)

        // TODO: get the context url to truncate from database
        String context = "WebStats"
                
        xml.graph(caption:'Page hits', showNames:1, decimalPrecision:0)
        {
                
	        sql.eachRow("select URL, count(URL) num from foresee.EVENTVIEW GROUP BY URL")
	        {
	        	String url = it.url
	        	String truncatedURL = url.split(context)[1]
	        	
				xml.set(name:"url="+truncatedURL, value:it.num)
	        }
        
        }
		
		//TODO: log debug string 
		
		graphFile.write(writer.toString());
	
        
        //select date(time),count(distinct session_id) num from (select time time,session_id from event join url_event on event.event_id = url_event.event_id where target_hit is not null ) group by date(time)
	}
	
	/**
	 * bar chart break out by page url
	 * write stats from db to fusion charts format xml file
	 */
	public void processURLStatsPageNavigationTargetBreak(File graphFile, String clientId) {
		
		def writer = new StringWriter()
		def xml = new MarkupBuilder(writer)
		
		Sql sql = Sql.newInstance(dbURL, username,
                password, dbDriver)

        // TODO: get the context url to truncate from database
        String context = "WebStats"

        
        Map sessionCountByDate = new HashMap()
        Map targetSessionCountByDate = new HashMap()
		
		int maxCount = 10;
        
		// last 20 days - session count per day
        sql.eachRow("""select date(time) date,count(distinct COOKIE) num from (select TIMESTAMP time,session_id from EVENTVIEW where date(time) >= date_sub(curdate(), interval 20 day) ) 
       		as eventURL group by date(time) order by date(time) asc;""")
	    {
        	sessionCountByDate[it.date]=it.num
        	if (it.num > maxCount) {maxCount = it.num}  
	    }

		// last 20 days - session count per day with target hits
        sql.eachRow("""select date(time) date,count(distinct COOKIE) num from (select TIMESTAMP time,COOKIE from EVENTVIEW where date(TIMESTAMP) >= date_sub(curdate(), interval 20 day) 
        			and ELEM_TYPE='button' ) 
       				as eventURL group by date(time) order by date(time) asc;""")
	    {
        	targetSessionCountByDate[it.date]=it.num
        	if (it.num > maxCount) {maxCount = it.num}
	    }
                
        xml.graph(xAxisName:'Date',yAxisName:'Hits', yAxisMaxValue: maxCount  ,caption:'Target pages breakdown', subCaption:'20 days', decimalPrecision:'0', rotateNames:'1',
        		numDivLines:'3', showValues:'0', formatNumberScale:'0')
        {
        	xml.categories(font:'Arial', fontSize:'11', fontColor:'000000' )
        	{
        		sessionCountByDate.each()
        		{date, num ->
        			xml.category(name:date) 
        		}
        	}
        	
        	xml.dataset(seriesname:"Session Count", color:"FDC12E") 
        	{
        	
	        	sessionCountByDate.each()
	        		{date, num ->
	        			xml.set(value:num)
	        		}
        	}

        	xml.dataset(seriesname:"Target Session Count", color:"56B9F9") 
        	{
	        	sessionCountByDate.each()
	        		{date, num ->
	        			xml.set(value: (targetSessionCountByDate[date] == null) ? 0 : targetSessionCountByDate[date])
	        		}
        	}
        }
		
		//TODO: log debug string 
		
		println writer.toString()
		graphFile.write(writer.toString())
	
        
	}	
	


	
}
