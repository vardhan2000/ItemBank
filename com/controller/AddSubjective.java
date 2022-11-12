package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DAO_Factory;
import com.dao.QuestionDAO;
import com.dao.StaticVariablesDAO;
import com.dao.SubjectiveQDAO;
import com.model.SubjectiveQ;

public class AddSubjective extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qdomain = request.getParameter("domain");
		String qtext = request.getParameter("text");
		String qanswer = request.getParameter("answer");
		
		HttpSession session = request.getSession();
		
		DAO_Factory daoFactory = new DAO_Factory();
		
		try {
			daoFactory.activateConnection();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		StaticVariablesDAO sdao = null;
		try {
			sdao = daoFactory.getStaticVariables();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(sdao.getStaticVariable("Question","IDCount"));
		int id = sdao.getStaticVariable("Question","IDCount")+1;
		sdao.setStaticVariable("Question", "IDCount", id);
		
		String qid = id+"";
		System.out.println("qid: " + qid);
		System.out.println(sdao.getStaticVariable("Question","IDCount"));
		
		SubjectiveQ ques = new SubjectiveQ();
		ques.setVersion("1");
		ques.setId(qid);
		ques.setQuestion_text(qtext);
		ques.setSubjective_answer_points(qanswer);
		
		SubjectiveQDAO qdao=null;
		try {
			qdao = daoFactory.getSubDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		qdao.addSubjective(ques);
		
		daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		
//		session.setAttribute("daoFactory", daoFactory);
		
		PrintWriter out = response.getWriter();
		out.print("Question added successfully!");
	}

}
