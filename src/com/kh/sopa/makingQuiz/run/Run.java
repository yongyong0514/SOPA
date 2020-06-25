package com.kh.sopa.makingQuiz.run;

import java.util.ArrayList;

import com.kh.sopa.makingQuiz.model.dao.Quiz_DAO;
import com.kh.sopa.makingQuiz.view.MakingQuiz;
import com.kh.sopa.model.vo.Quiz_VO;

public class Run {
	public static void main(String[] args) {
		MakingQuiz mq = new MakingQuiz();
		Quiz_DAO qd = new Quiz_DAO();
		Quiz_VO q = new Quiz_VO();

		ArrayList<Quiz_VO> list = qd.readQuizList();
		if(list != null) {
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}else {
			System.out.println("문제 파일을 생성해야 합니다");
		}
		
		System.out.println("경꼐선");
		
		ArrayList<Quiz_VO> set = qd.readQs();
		if(set != null) {
			for(int i = 0; i < set.size(); i++) {
				System.out.println(set.get(i));
			}
		}else {
			System.out.println("세트 파일을 생성해야 합니다.");
		}
	
	}
	
}
