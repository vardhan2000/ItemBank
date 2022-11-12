package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DAO_Factory;

public class Logout extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession session = request.getSession();
    	System.out.println(session);
//    	DAO_Factory daoFactory = (DAO_Factory) session.getAttribute("daoFactory");
    	String uname = (String) session.getAttribute("username");
    	
//    	System.out.println(daoFactory);
//    	System.out.println(uname);
    	
//    	daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		
    	session.removeAttribute("username");
//		session.removeAttribute("daoFactory");
		session.invalidate();
		response.sendRedirect("home.jsp");
	
    }

}
