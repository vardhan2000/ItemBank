package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AuthorDataDAO;
import com.dao.DAO_Factory;
import com.model.AuthorData;


public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname"); 
		String pass = request.getParameter("pass");
		
		HttpSession session = request.getSession();
		DAO_Factory daoFactory = new DAO_Factory();
		
		PrintWriter out = response.getWriter();
		
		try {
			if(is_authentic(uname, pass, daoFactory)) 
			{
				session.setAttribute("username", uname);
				AuthorDataDAO dao = daoFactory.getAuthorDataDAO();
				AuthorData author = dao.getAuthor(uname);
				String aid = author.getId();
				session.setAttribute("authorId", aid);
				
				daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
				response.sendRedirect("authorhome.jsp");
			}
			
			else 
			{
				daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
				out.print("Invalid credentials");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	protected boolean is_authentic(String uname, String pass, DAO_Factory daoFactory) throws Exception 
	{
		daoFactory.activateConnection();
		AuthorDataDAO dao = daoFactory.getAuthorDataDAO();
		AuthorData author = dao.getAuthor(uname);
		
		return (author.getUsername().equals(uname) && author.getPassword().equals(pass));
	}

	

}
