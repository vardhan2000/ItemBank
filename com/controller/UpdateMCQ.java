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
import com.dao.MultipleCQDAO;
import com.dao.QuestionDAO;
import com.dao.QuestionVersionDAO;
import com.dao.SubjectiveQDAO;
import com.model.MultipleCQ;
import com.model.Question;
import com.model.QuestionVersion;
import com.model.SubjectiveQ;

public class UpdateMCQ extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String qid = request.getParameter("id");
		String qdomain = request.getParameter("domain");
		String qtext = request.getParameter("text");
		String qanswer = request.getParameter("answer");
		String opt1 = request.getParameter("opt1");
		String opt2 = request.getParameter("opt2");
		String opt3 = request.getParameter("opt3");
		String opt4 = request.getParameter("opt4");
		
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
				
				QuestionVersion qv = new QuestionVersion(qid, ver_num+"", 1); // version 1 type 1 (MCQ)
				qv_dao.addQuestionVersion(qv);
				
				// update mcq (table)
				
				ArrayList<String> options=new ArrayList<String>();
				options.add(opt1);
				options.add(opt2);
				options.add(opt3);
				options.add(opt4);
				
				MultipleCQDAO mcq_dao = null;
				try {
					mcq_dao = daoFactory.getMCQDAO();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(int i=1; i<=4; i++) {
					MultipleCQ mcq = new MultipleCQ();
					mcq.setId(qid);
					mcq.setQuestion_text(qtext);
					mcq.setChoice(i+"");
					mcq.setOption(options.get(i-1));
					mcq.setVersion(ver_num+"");
					
					if(qanswer.equals(options.get(i-1)))
					{
						mcq.setIs_correct("YES");
					}
					else 
					{
						mcq.setIs_correct("NO");
					}
					
					mcq_dao.addMCQ(mcq);
				}
				
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
