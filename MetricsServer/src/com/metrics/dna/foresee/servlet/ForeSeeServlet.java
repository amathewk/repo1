package com.metrics.dna.foresee.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.metrics.dna.foresee.data.ForeSeeEventProcessor;

/**
 * Servlet implementation class ForeSeeServlet
 */
public class ForeSeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForeSeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	   // This method is called by the servlet container to process a GET request.
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	
    	
	ForeSeeEventProcessor processor = new ForeSeeEventProcessor();
		
		if(processor.authenticateRequest(req))
		{
			processor.processEvent(req);
		}
    	
        // Get the absolute path of the image
        ServletContext sc = getServletContext();
        String filename = sc.getRealPath("images/MetricsDNA.gif");
    
        // Get the MIME type of the image
        String mimeType = sc.getMimeType(filename);
        if (mimeType == null) {
            sc.log("Could not get MIME type of "+filename);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
    
        // Set content type
        resp.setContentType(mimeType);
    
        // Set content size
        File file = new File(filename);
        resp.setContentLength((int)file.length());
    
        // Open the file and output streams
        FileInputStream in = new FileInputStream(file);
        OutputStream out = resp.getOutputStream();
    
        // Copy the contents of the file to the output stream
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        in.close();
        out.close();
    }

}
