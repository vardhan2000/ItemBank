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
    	String uname = (String) session.getAttribute("username");
    			
    	session.removeAttribute("username");
		session.removeAttribute("authorId");
		session.invalidate();
		response.sendRedirect("home.jsp");
	
    }

}
