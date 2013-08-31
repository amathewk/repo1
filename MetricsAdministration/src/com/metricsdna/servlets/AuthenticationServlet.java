package com.metricsdna.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AuthenticationServlet
 */
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthenticationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String domain = request.getParameter("domainname");
			String username = request.getParameter("username");
			String userpassword = request.getParameter("userpassword");

			Context ctx = new InitialContext();

			DataSource ds = (DataSource) ctx.lookup("jdbc/Foresee");

			Connection con = ds.getConnection();
			
			String sql = "SELECT * FROM FORESEE.CUSTOMERS WHERE DOMAIN = '" + domain
					+ "'";
			
			Statement stmt = con.createStatement();
			
			ResultSet res = stmt.executeQuery(sql);
			
			int count = 0;
			
			while(res.next())
			{
				count++;
				if(count > 0)
					break;
			}
			
			if(count > 0)
			{
				response.sendRedirect("reports.jsp");
			}
			else
			{
				response.sendRedirect("login.jsp");
			}
			
			
		} catch (Exception ex) {
			response.sendRedirect("login.jsp?e=" + ex.getMessage());
		}
	}

}
