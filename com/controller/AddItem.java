package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DAO_Factory;

public class AddItem extends HttpServlet {
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String itemType = request.getParameter("type"); 
//		System.out.println(daoFactory);
		
		if(itemType.equals("sub")) {
			response.sendRedirect("addSubjective.jsp");
		}
		
		else {
			response.sendRedirect("addMCQ.jsp");
		}
	}

}
