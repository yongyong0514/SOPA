package com.kh.sopa.makingQuiz.model.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.sopa.makingQuiz.model.dao.Quiz_DAO;
import com.kh.sopa.model.vo.Quiz_VO;

public class MakingQuizManager {

	public void insertQuiz(Quiz_VO q) {
		Quiz_DAO qd = new Quiz_DAO();
		ArrayList<Quiz_VO> list = qd.readQuizList();

		if (list == null) {
			list = new ArrayList<Quiz_VO>();
		}
		list.add(q);
		qd.writeQuiz(list);
	}

	public void insertSet(String quiz_set_info, String quiz_subject, int quiz_people) {
		Quiz_DAO qd = new Quiz_DAO();
		ArrayList<Quiz_VO> set = qd.readQuizSet();

		for (int i = 0; i < set.size(); i++) {
			if (set.get(i).getQuiz_set_info() != null) {
				System.out.println("추가할 문제 없음");
				break;
			} else if (set.get(i).getQuiz_set_info() == null) {
				set.get(i).setQuiz_set_info(quiz_set_info);
				set.get(i).setQuiz_subject(quiz_subject);
				set.get(i).setQuiz_people(quiz_people);
			}
			qd.writeSet(set);
		}
	}
	
	public String streamQList() {
		Quiz_DAO qd = new Quiz_DAO();
		ArrayList<Quiz_VO> list = qd.readQuizList();
		String qlist = null;
		
		for(int i = 0; i < list.size(); i++) {
			
			qlist += list.get(i).getQuiz_title();
		}
		return qlist;
	}
}
