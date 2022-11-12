package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AuthorDataDAO;
import com.dao.DAO_Factory;
import com.dao.StaticVariablesDAO;
import com.model.AuthorData;

public class Register extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("name"); 
		String mail = request.getParameter("email");
		String pass = request.getParameter("password");
		
		DAO_Factory daoFactory = new DAO_Factory();
		
//		System.out.println(daoFactory);
		
		try {
		daoFactory.activateConnection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		StaticVariablesDAO sdao = null;
		try {
			sdao = daoFactory.getStaticVariables();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int id = sdao.getStaticVariable("Author","IDCount")+1;
		String aid = id+"";
		
		AuthorData author = new AuthorData(aid, uname, pass, mail);
		
		System.out.println(author);
		
		AuthorDataDAO dao=null;
		try {
			dao = daoFactory.getAuthorDataDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dao.addAuthor(author);
		sdao.setStaticVariable("Author", "IDCount", id);
		daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		
//		PrintWriter out = response.getWriter();
//	
//		try {
//			out.print("Author registered successfully!");
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		response.sendRedirect("home.jsp");
	}

}
