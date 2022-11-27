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
import com.dao.QuestionVersionDAO;
import com.dao.StaticVariablesDAO;
import com.dao.SubjectiveQDAO;
import com.model.Question;
import com.model.QuestionVersion;
import com.model.SubjectiveQ;

public class AddSubjective extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qdomain = request.getParameter("domain");
		String qtext = request.getParameter("text");
		String qanswer = request.getParameter("answer");
		
		// Tables to update:
		// 1. staticvariables
		// 2. questionbank
		// 3. qvtable
		// 4. subjective
		
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
		
		QuestionVersion qv = new QuestionVersion(qid, "1", 2); // version 1 type 2 (Subj)
		qv_dao.addQuestionVersion(qv);
		
		// update subjective (table)
		
		SubjectiveQ sub_ques = new SubjectiveQ();
		sub_ques.setVersion("1");
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
		
		daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		
		PrintWriter out = response.getWriter();
		out.print("Question added successfully!");
	}

}
