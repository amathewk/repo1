package com.metrics.dna.foresee.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ForeSeeEventHandler {

	/**
	 * 
	 * @param request
	 * @throws Exception
	 */
	public static void storeAllRequestData(HttpServletRequest request)
			throws Exception {
		Document xmldoc = new DocumentImpl();

		Element requestElement = xmldoc.createElement("REQUEST");

		Element generalElement = xmldoc.createElement("GENERAL");

		Element timeStampElement = xmldoc.createElement("TIMESTAMP");
		try
		{
		//Feb 3, 2009-02-03 07:10:40.806
		//2007-02-17T14:40:32.000Z
		
		Date d = new Date(System.currentTimeMillis());
		
		SimpleDateFormat sdf =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
		String date = sdf.format(d);
		
		  Node timeStampValue = xmldoc.createTextNode(date);
			
			timeStampElement.appendChild(timeStampValue);
			
			generalElement.appendChild(timeStampElement);
		}
		catch(Exception ex)
		{
			
		}
	  
			
		Element remoteHostElement = xmldoc.createElement("REMOTEHOST");

		try {
			String remoteHost = request.getRemoteHost();

			if (remoteHost != null) {
				Node remoteHostValue = xmldoc.createTextNode(remoteHost);
				remoteHostElement.appendChild(remoteHostValue);
			}
		} catch (Exception ex) {
		}

		generalElement.appendChild(remoteHostElement);

		Element remoteAddressElement = xmldoc.createElement("REMOTEADDRESS");

		try {
			String remoteAddress = request.getRemoteAddr();

			if (remoteAddress != null) {
				Node remoteAddressValue = xmldoc.createTextNode(remoteAddress);
				remoteAddressElement.appendChild(remoteAddressValue);
			}
		} catch (Exception ex) {
		}

		generalElement.appendChild(remoteAddressElement);

		requestElement.appendChild(generalElement);

		// Process all the request Attributes

		Enumeration attributeNames = request.getAttributeNames();

		Element attributes = xmldoc.createElement("ATTRIBUTES");

		try {
			while (attributeNames.hasMoreElements()) {
				String attributeName = (String) attributeNames.nextElement();

				if (attributeName != null) {
					Element attribute = xmldoc.createElement(attributeName);

					String attributeValue = (String) request
							.getAttribute(attributeName);

					if (attributeValue != null) {

						Node value = xmldoc.createTextNode(attributeValue);

						if (value != null)
							attribute.appendChild(value);
					}

					attributes.appendChild(attribute);
				}

			}
		} catch (Exception ex) {
		}

		requestElement.appendChild(attributes);

		// Now process the headers

		Enumeration headerNames = request.getHeaderNames();

		Element headers = xmldoc.createElement("HEADERS");

		try {
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();

				if (headerName != null) {
					Element header = xmldoc.createElement(headerName);

					String headerValue = (String) request.getHeader(headerName);

					if (headerValue != null) {
						Node value = xmldoc.createTextNode(headerValue);
						if (value != null)
							header.appendChild(value);
					}

					headers.appendChild(header);
				}
			}
		} catch (Exception ex) {
		}

		requestElement.appendChild(headers);

		// Process all the request parameters
		Enumeration parameterNames = request.getParameterNames();

		Element parameters = xmldoc.createElement("PARAMETERS");
		try {
			while (parameterNames.hasMoreElements()) {
				String parameterName = (String) parameterNames.nextElement();

				if (parameterName != null) {
					Element parameter = xmldoc.createElement(parameterName);
					String parameterValue = (String) request
							.getParameter(parameterName);

					if (parameterValue != null) {
						Node value = xmldoc.createTextNode(parameterValue);
						if (value != null)
							parameter.appendChild(value);
					}
					parameters.appendChild(parameter);
				}
			}
		} catch (Exception ex) {
		}
		requestElement.appendChild(parameters);

		// Process the cookies

		Cookie[] cookies = request.getCookies();

		Element cookiesElement = xmldoc.createElement("COOKIES");
		try {
			for (int i = 0; i < cookies.length; i++) {
				Element cookieElement = xmldoc.createElement("COOKIE");
				try {
					Cookie cookie = cookies[i];
					cookieElement
							.setAttributeNS(null, "NAME", cookie.getName());
					cookieElement.setAttributeNS(null, "DOMAIN", cookie
							.getDomain());
					cookieElement.setAttributeNS(null, "VALUE", cookie
							.getValue());
				} catch (Exception ex) {
				}
				cookiesElement.appendChild(cookieElement);

			}
		} catch (Exception ex) {
		}
		requestElement.appendChild(cookiesElement);

		xmldoc.appendChild(requestElement);

		OutputFormat of = new OutputFormat();

		File xmlFile = null;

		String tmpDir = System.getenv("java.io.tmpdir");

		if (tmpDir != null) {
			xmlFile = File.createTempFile(Long.toString(System
					.currentTimeMillis()), ".xml", new File(tmpDir));
		} else {
			xmlFile = File.createTempFile(Long.toString(System
					.currentTimeMillis()), ".xml");
		}

		FileOutputStream fos = new FileOutputStream(xmlFile);

		XMLSerializer serializer = new XMLSerializer(fos, of);

		if (serializer != null) {
			serializer.serialize(xmldoc);
		}

		if (fos != null)
			fos.close();
		
		Context ctx = new InitialContext();

		DataSource ds = (DataSource) ctx.lookup("jdbc/SageDB");

		Connection con = ds.getConnection();

		con.setAutoCommit(true);

		PreparedStatement pstmt = con
				.prepareStatement("INSERT INTO FORESEE.CAPTURE(XMLDATA) VALUES (?)");

		pstmt.setBinaryStream(1, new FileInputStream(xmlFile), (int) xmlFile
				.length());

		int i = pstmt.executeUpdate();

		if (i != 1) {
			System.out.println("No record inserted.");
		} else {
			System.out.println("Record inserted.");
		}

		pstmt.close();

		con.close();


	}

	/**
	 * 
	 * @param request
	 * @throws Exception
	 */
	public static void storeXMLData(HttpServletRequest request)
			throws Exception {

		ForeSeeEvent event = new ForeSeeEvent(request);

		System.out.println("Entering: storeXMLData");

		Document xmldoc = new DocumentImpl();

		System.out.println("Create Transaction");
		Element requestElement = xmldoc.createElement("TRANSACTION");
		requestElement.setAttributeNS(null, "TS", Long.toString(System
				.currentTimeMillis()));

		System.out.println("Create Event");
		Element xmlEvent = xmldoc.createElement("EVENT");

		if (event.getEventId() != null)
			xmlEvent.setAttributeNS(null, "ID", event.getEventId());

		if (event.getEventType() != null)
			xmlEvent.setAttributeNS(null, "TYPE", event.getEventType());

		if (event.getEventTimeStamp() != null)
			xmlEvent.setAttributeNS(null, "TIMESTAMP", event
					.getEventTimeStamp().toString());

		if (event.getEventSource() != null)
			xmlEvent.setAttributeNS(null, "SOURCE", event.getEventSource());
		System.out.println("Appending Event");
		requestElement.appendChild(xmlEvent);

		System.out.println("Create Client");
		Element xmlClient = xmldoc.createElement("CLIENT");
		if (event.getClientId() != null)
			xmlClient.setAttributeNS(null, "ID", event.getClientId());

		if (event.getClientHostName() != null)
			xmlClient.setAttributeNS(null, "HOSTNAME", event
					.getClientHostName());

		if (event.getClientIPAddress() != null)
			xmlClient.setAttributeNS(null, "IPADDRESS", event
					.getClientIPAddress());

		if (event.getClientSessionId() != null)
			xmlClient.setAttributeNS(null, "SESSIONID", event
					.getClientSessionId());
		System.out.println("Appending Client");
		requestElement.appendChild(xmlClient);

		xmldoc.appendChild(requestElement);

		OutputFormat of = new OutputFormat();

		File xmlFile = null;

		String tmpDir = System.getenv("java.io.tmpdir");

		if (tmpDir != null) {
			xmlFile = File.createTempFile(Long.toString(System
					.currentTimeMillis()), ".xml", new File(tmpDir));
		} else {
			xmlFile = File.createTempFile(Long.toString(System
					.currentTimeMillis()), ".xml");
		}

		FileOutputStream fos = new FileOutputStream(xmlFile);

		XMLSerializer serializer = new XMLSerializer(fos, of);

		if (serializer != null) {
			serializer.serialize(xmldoc);
		}

		if (fos != null)
			fos.close();

		Context ctx = new InitialContext();

		DataSource ds = (DataSource) ctx.lookup("jdbc/SageDB");

		Connection con = ds.getConnection();

		con.setAutoCommit(true);

		PreparedStatement pstmt = con
				.prepareStatement("INSERT INTO SAGE.CAPTURE(EVENT) VALUES (?)");

		pstmt.setBinaryStream(1, new FileInputStream(xmlFile), (int) xmlFile
				.length());

		int i = pstmt.executeUpdate();

		if (i != 1) {
			System.out.println("No record inserted.");
		} else {
			System.out.println("Record inserted.");
		}

		pstmt.close();

		con.close();

	}

}
