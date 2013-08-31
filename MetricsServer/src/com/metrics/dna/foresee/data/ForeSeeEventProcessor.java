package com.metrics.dna.foresee.data;
import javax.servlet.http.HttpServletRequest;

public class ForeSeeEventProcessor {
	/**
	 * 
	 * @param request
	 * @return
	 */
	public boolean authenticateRequest(HttpServletRequest request)
	{
		return true;
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	public boolean processEvent(HttpServletRequest request)
	{
		boolean flag = false;
		
		try
		{
			ForeSeeEventHandler.storeAllRequestData(request);
			
			flag = true;			
		}
		catch(Exception ex)
		{
			System.err.print(ex);
			flag = false;
		}
		
		return flag;
	}
	
	
}
