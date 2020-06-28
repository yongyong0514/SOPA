package com.kh.sopa.makingQuiz.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.sopa.makingQuiz.model.dao.Quiz_DAO;
import com.kh.sopa.model.vo.Quiz_VO;

public class MakingQuizManager {

	public void insertQuiz(Quiz_VO qv) {
		Quiz_DAO qd = new Quiz_DAO();
		ArrayList<Quiz_VO> list = qd.readQuizList();
		// System.out.println("mqm qd.size() : " + list.size());
		if (list == null) {
			list = new ArrayList<Quiz_VO>();
		}
		list.add(qv);
		qd.writeQuiz(list);
	}

	public void insertSet(Quiz_VO qv) {
		Quiz_DAO qd = new Quiz_DAO();
		ArrayList<Quiz_VO> list = qd.readQuizList();
		ArrayList<Quiz_VO> setList = qd.readQuizSet();

		if (setList == null) {
			setList = new ArrayList<Quiz_VO>();
		}
		for (int i = 0; i < list.size(); i++) {
			Quiz_VO temp = new Quiz_VO();
			temp.setQuiz_set_info(qv.getQuiz_set_info());
			temp.setQuiz_title(list.get(i).getQuiz_title());
			temp.setQuiz_subject(qv.getQuiz_subject());
			temp.setQuiz_answer_1(list.get(i).getQuiz_answer_1());
			temp.setQuiz_answer_2(list.get(i).getQuiz_answer_2());
			temp.setQuiz_answer_3(list.get(i).getQuiz_answer_3());
			temp.setQuiz_answer_4(list.get(i).getQuiz_answer_4());
			temp.setQuiz_final_answer(list.get(i).getQuiz_final_answer());
			temp.setQuiz_difficulty(list.get(i).getQuiz_difficulty());
			temp.setQuiz_cookie(list.get(i).getQuiz_cookie());
			temp.setQuiz_image(null);
			temp.setQuiz_people(qv.getQuiz_people());

			setList.add(temp);
		}
		qd.writeSet(setList);
	}

	public void deleteQuiz(int index) {
		Quiz_DAO qd = new Quiz_DAO();
		ArrayList<Quiz_VO> list = qd.readQuizList();
		for (int i = list.size() - 1; i >= 0; i--) {
			if (i == index) {
				list.remove(index);
			}
		}
		qd.writeQuiz(list);
	}

	public void deleteQuizSet(int index) {
		Quiz_DAO qd = new Quiz_DAO();
		ArrayList<Quiz_VO> set = qd.readQuizList();
		// for (int i = 0 ; i < set.size() ; i++) {
		for (int i = set.size() - 1; i >= 0; i--) {
			if (i == index) {
				set.remove(index);
			}
		}
		qd.writeSet(set);
	}

	public void deleteListWhenInsertSet() {
		Quiz_DAO qd = new Quiz_DAO();
		ArrayList<Quiz_VO> list = qd.readQuizList();
		list.clear();

		qd.writeQuiz(list);
	}

}