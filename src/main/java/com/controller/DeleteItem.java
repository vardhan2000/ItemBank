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
		
		DAO_Factory daoFactory = new DAO_Factory();
		
		try {
			daoFactory.activateConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		QuestionDAO qdao=null;
		try {
			qdao = daoFactory.getQuestionDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Question ques = qdao.getQuestion(qid);
		
		String aid = (String) session.getAttribute("authorId");
		PrintWriter out = response.getWriter();
		
		if(ques.getAuthorId().equals(aid)) 
		{
			if(ques.getStatus().equals("ACTIVE")) 
			{
				qdao.deleteQuestion(ques);
				out.print("Question deleted successfully");
			}
			
			else 
			{
				out.print("You are trying to delete an already deleted question.");
			}
			
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		}
		
		else 
		{
			out.print("You are not the author of this question!");
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		}
	}

}
