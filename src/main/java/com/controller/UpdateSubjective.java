package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DAO_Factory;
import com.dao.QuestionDAO;
import com.dao.QuestionVersionDAO;
import com.dao.SubjectiveQDAO;
import com.model.Question;
import com.model.QuestionVersion;
import com.model.SubjectiveQ;

public class UpdateSubjective extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String qid = request.getParameter("id");
		String qdomain = request.getParameter("domain");
		String qtext = request.getParameter("text");
		String qanswer = request.getParameter("answer");
		
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
				// update questionbank (table)
				ques.setDomain(qdomain);
				qdao.updateQuestion(ques);
				
				// update questionversion (table)
				
				QuestionVersionDAO qv_dao = null;
				try {
					qv_dao = daoFactory.getQuestionVersionDAO();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ArrayList<QuestionVersion> qvs = qv_dao.getQuestionVersion(qid);
				int ver_num = qvs.size()+1;
				
				QuestionVersion qv = new QuestionVersion(qid, ver_num+"", 2); // version 1 type 2 (Subj)
				qv_dao.addQuestionVersion(qv);
				
				// update subjective (table)
				
				SubjectiveQ sub_ques = new SubjectiveQ();
				sub_ques.setVersion(ver_num+"");
				sub_ques.setId(qid);
				sub_ques.setQuestion_text(qtext);
				sub_ques.setSubjective_answer_points(qanswer);
				
				SubjectiveQDAO sqdao=null;
				try {
					sqdao = daoFactory.getSubDAO();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				sqdao.addSubjective(sub_ques);
				
				out.print("Question updated successfully!");
			}
			
			else 
			{
				out.print("You are trying to update a deleted question.");
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
