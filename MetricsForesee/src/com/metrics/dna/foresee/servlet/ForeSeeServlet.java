package com.metrics.dna.foresee.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ForeSeeEventProcessor processor = new ForeSeeEventProcessor();
		
		if(processor.authenticateRequest(request))
		{
			processor.processEvent(request);
		}
		response.setContentType("image/gif");
		
		response.setHeader("Pragma", "Cache-Control");
		response.setHeader("Cache-Control","private, no-cache, no-cache='Set-Cookie', proxy-revalidate");
		response.setHeader("Expires","Fri, 04 Aug 1978 12:00:00 GMT");
		
		BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);  
		Graphics g = bufferedImage.getGraphics();  
		g.setColor(Color.blue);  
		g.fillOval(0, 0, 199,199);  
		g.dispose();  
		ImageIO.write(bufferedImage, "gif", response.getOutputStream());  

		
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
