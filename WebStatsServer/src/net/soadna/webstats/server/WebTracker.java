package net.soadna.webstats.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.soadna.webstat.core.StatProcessor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/track.dna")
public class WebTracker {

//	Logger LOG = Logger.getLogger(WebTracker.class.getName()); 
	
	
	private String clientId;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return "start.jsp";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String action(@RequestParam("clientId") String clientId ,HttpServletRequest request) throws IOException {

		System.out.println("Tracking...");
		
		try {
		
			// TODO: get the client id
			if (clientId == null || "".equals(clientId.trim())) {
				clientId = "10001";
			}
			
			
			File jdbcPropsFile = new File(request.getRealPath("/")+ "jdbc.properties");
			
			StatProcessor processor = new StatProcessor(jdbcPropsFile);
			
			URL url = new URL("http://localhost/WebStats/clickInfo.txt");
			if (!processor.collectClickStats(url)) 
			{
				// TODO: file not found. handle
				System.out.println("Remote Click Stat File not found.");
			}
			
			String outputFileName = request.getRealPath("/")+ "data/clickData.xml";
			File graphFile = new File(outputFileName);
			processor.processClickStats(graphFile);
			
	
			url = new URL("http://localhost/WebStats/urlInfo.txt");
			if (!processor.collectURLStats(url)) {
				// TODO: file not found. handle
				System.out.println("Remote URL Stat File not found.");
			}
			
			outputFileName = request.getRealPath("/")+ "data/urlData.xml";
			graphFile = new File(outputFileName);
			processor.processURLStatsUniqueVisitors(graphFile);
			
			outputFileName = request.getRealPath("/")+ "data/urlHitPieData.xml";
			graphFile = new File(outputFileName);
			processor.processURLStatsPageNavigationTotal(graphFile, clientId);
	
			outputFileName = request.getRealPath("/")+ "data/hitsMissesClmn3D.xml";
			graphFile = new File(outputFileName);
			processor.processURLStatsPageNavigationTargetBreak(graphFile, clientId);
			
			
			
			// DELETE the file after consuming
			
			url = new URL("http://localhost/WebStats/delete.php");
	
			InputStream is = url.openStream();
			System.out.println("deleting");
			is.close();
		
		} catch (Exception ex) {
			// TODO: log
			String errorID = UUID.randomUUID().toString();
			System.out.println(errorID + "\n" +ex);
			// TODO: pass error id to display to customer for support
			return "error.html";
		}
		
		return "success.html";
		
	}

}
