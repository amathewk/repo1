package com.metrics.dna.foresee.data;

import javax.servlet.http.HttpServletRequest;

public class ForeSeeEvent {
	private String eventId;
	private Long eventTimeStamp;
	private String eventType;
	private String eventName;
	private String eventSource;
	private String clientId;
	private String clientIPAddress;
	private String clientHostName;
	private String clientSessionId;

	public ForeSeeEvent(HttpServletRequest request) {
		
		if (request.getParameter("id") != null) {
			this.eventId = request.getParameter("id");
		}
		this.eventTimeStamp = System.currentTimeMillis();
		
		this.clientIPAddress = request.getRemoteAddr();
		
		this.clientHostName = request.getRemoteHost();

		if (request.getParameter("clientId") != null) {
			this.clientId = request.getParameter("clientId");
		}

		if (request.getParameter("targetHit") != null) {
			this.eventName = request.getParameter("targetHit");
		}
		if (request.getParameter("url") != null) {
			this.eventSource = request.getParameter("url");
		}
		if (request.getParameter("type") != null) {
			this.eventType = request.getParameter("type");
		}
	}

	public String getClientHostName() {
		return clientHostName;
	}

	public void setClientHostName(String clientHostName) {
		this.clientHostName = clientHostName;
	}

	

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Long getEventTimeStamp() {
		return eventTimeStamp;
	}

	public void setEventTimeStamp(Long eventTimeStamp) {
		this.eventTimeStamp = eventTimeStamp;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventSource() {
		return eventSource;
	}

	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientIPAddress() {
		return clientIPAddress;
	}

	public void setClientIPAddress(String clientIPAddress) {
		this.clientIPAddress = clientIPAddress;
	}

	public String getClientSessionId() {
		return clientSessionId;
	}

	public void setClientSessionId(String clientSessionId) {
		this.clientSessionId = clientSessionId;
	}

}
