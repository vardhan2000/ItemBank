package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AuthorDataDAO;
import com.dao.DAO_Factory;
import com.dao.QuestionDAO;
import com.model.AuthorData;
import com.model.Question;

public class DeleteItem extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qid = request.getParameter("id");
		
		HttpSession session = request.getSession();
		DAO_Factory daoFactory = (DAO_Factory)session.getAttribute("daoFactory");
		
		System.out.println("daofac: " + daoFactory);
		
		QuestionDAO qdao=null;
		try {
			qdao = daoFactory.getQuestionDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("qdao: " + qdao);
		
		Question ques = qdao.getQuestion(qid);
		
		System.out.println("ques: " + ques); // print
		
		String aid = ques.getAuthorId();
		
		AuthorDataDAO adao=null;
		try {
			adao = daoFactory.getAuthorDataDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AuthorData author = adao.getAuthorById(aid);
		
		System.out.println("author: " + author); // print
		
		String uname = (String) session.getAttribute("username");
		
		PrintWriter out = response.getWriter();
		
		if(author.getUsername().equals(uname)) 
		{
			qdao.deleteQuestion(ques);
			session.setAttribute("daoFactory", daoFactory);
			response.sendRedirect("authorhome.jsp");
		}
		
		else 
		{
			session.setAttribute("daoFactory", daoFactory);
			out.print("You are not the author of this question!");
		}
	}

}
