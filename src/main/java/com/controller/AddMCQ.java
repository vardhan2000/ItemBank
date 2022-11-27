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
import com.dao.StaticVariablesDAO;
import com.model.MultipleCQ;
import com.model.Question;
import com.model.QuestionVersion;

public class AddMCQ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qdomain = request.getParameter("domain");
		String qtext = request.getParameter("text");
		String qanswer = request.getParameter("answer");
		String opt1 = request.getParameter("opt1");
		String opt2 = request.getParameter("opt2");
		String opt3 = request.getParameter("opt3");
		String opt4 = request.getParameter("opt4");
		
		// Tables to update:
		// 1. staticvariables
		// 2. questionbank
		// 3. qvtable
		// 4. MCQ
		
HttpSession session = request.getSession();
		
		DAO_Factory daoFactory = new DAO_Factory();
		
		try {
			daoFactory.activateConnection();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		// update staticvariables (table)
		
		StaticVariablesDAO sdao = null;
		try {
			sdao = daoFactory.getStaticVariables();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int id = sdao.getStaticVariable("Question","IDCount")+1;
		sdao.setStaticVariable("Question", "IDCount", id);
		
		String qid = id+"";
		
		// update questionbank (table)
		
		QuestionDAO qdao = null;
		try {
			qdao = daoFactory.getQuestionDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String aid = (String) session.getAttribute("authorId");
		Question ques = new Question(qid, qdomain, aid, "ACTIVE");
		qdao.addQuestion(ques);
		
		// update questionversion (table)
		
		QuestionVersionDAO qv_dao = null;
		try {
			qv_dao = daoFactory.getQuestionVersionDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		QuestionVersion qv = new QuestionVersion(qid, "1", 1); // version 1 type 1 (MCQ)
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
			mcq.setVersion("1");
			
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
		
		daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		
		PrintWriter out = response.getWriter();
		out.print("Question added successfully!");
		
	}

}
