package com.kh.sopa.model.vo;

import java.io.Serializable;

//
public class Quiz_VO implements java.io.Serializable {
	private String quiz_set_info;		//세트 이름
	private String quiz_title;			//문제 이름
	private String quiz_subject;		//주제
	private String quiz_answer_1;		//답1
	private String quiz_answer_2;		//답2
	private String quiz_answer_3;		//답3
	private String quiz_answer_4;		//답4
	private String quiz_final_answer;	//선택한 정답
	private int quiz_difficulty;		//난이도 (10 20 30) 
	private int quiz_cookie;			//문제당 쿠키
	private String quiz_image;			//추가한 이미지
	private int quiz_people;			//참여 인원
	
	public Quiz_VO(){
		
	}
	
	public Quiz_VO(String quiz_title, String quiz_answer_1, String quiz_answer_2, String quiz_answer_3,
			String quiz_answer_4, String quiz_final_answer, int quiz_difficulty ) {
		super();
		this.quiz_title = quiz_title;
		this.quiz_answer_1 = quiz_answer_1;
		this.quiz_answer_2 = quiz_answer_2;
		this.quiz_answer_3 = quiz_answer_3;
		this.quiz_answer_4 = quiz_answer_4;
		this.quiz_final_answer = quiz_final_answer;
		this.quiz_difficulty = quiz_difficulty; 
	}
	
	
	public Quiz_VO(String quiz_set_info, String quiz_title, String quiz_subject, String quiz_answer_1,
			String quiz_answer_2, String quiz_answer_3, String quiz_answer_4, String quiz_final_answer,
			int quiz_difficulty, int quiz_cookie, String quiz_image, int quiz_people) {
		super();
		this.quiz_set_info = quiz_set_info;
		this.quiz_title = quiz_title;
		this.quiz_subject = quiz_subject;
		this.quiz_answer_1 = quiz_answer_1;
		this.quiz_answer_2 = quiz_answer_2;
		this.quiz_answer_3 = quiz_answer_3;
		this.quiz_answer_4 = quiz_answer_4;
		this.quiz_final_answer = quiz_final_answer;
		this.quiz_difficulty = quiz_difficulty;
		this.quiz_cookie = quiz_cookie;
		this.quiz_image = quiz_image;
		this.quiz_people = quiz_people;
	}

	
	
	public String getQuiz_set_info() {
		return quiz_set_info;
	}

	public void setQuiz_set_info(String quiz_set_info) {
		this.quiz_set_info = quiz_set_info;
	}

	public String getQuiz_title() {
		return quiz_title;
	}

	public void setQuiz_title(String quiz_title) {
		this.quiz_title = quiz_title;
	}

	public String getQuiz_subject() {
		return quiz_subject;
	}

	public void setQuiz_subject(String quiz_subject) {
		this.quiz_subject = quiz_subject;
	}

	public String getQuiz_answer_1() {
		return quiz_answer_1;
	}

	public void setQuiz_answer_1(String quiz_answer_1) {
		this.quiz_answer_1 = quiz_answer_1;
	}

	public String getQuiz_answer_2() {
		return quiz_answer_2;
	}

	public void setQuiz_answer_2(String quiz_answer_2) {
		this.quiz_answer_2 = quiz_answer_2;
	}

	public String getQuiz_answer_3() {
		return quiz_answer_3;
	}

	public void setQuiz_answer_3(String quiz_answer_3) {
		this.quiz_answer_3 = quiz_answer_3;
	}

	public String getQuiz_answer_4() {
		return quiz_answer_4;
	}

	public void setQuiz_answer_4(String quiz_answer_4) {
		this.quiz_answer_4 = quiz_answer_4;
	}

	public String getQuiz_final_answer() {
		return quiz_final_answer;
	}

	public void setQuiz_final_answer(String quiz_final_answer) {
		this.quiz_final_answer = quiz_final_answer;
	}

	public int getQuiz_difficulty() {
		return quiz_difficulty;
	}

	public void setQuiz_difficulty(int quiz_difficulty) {
		this.quiz_difficulty = quiz_difficulty;
	}

	public int getQuiz_cookie() {
		return quiz_cookie;
	}

	public void setQuiz_cookie(int quiz_cookie) {
		this.quiz_cookie = quiz_cookie;
	}

	public String getQuiz_image() {
		return quiz_image;
	}

	public void setQuiz_image(String quiz_image) {
		this.quiz_image = quiz_image;
	}

	public int getQuiz_people() {
		return quiz_people;
	}

	public void setQuiz_people(int quiz_people) {
		this.quiz_people = quiz_people;
	}
	
	
	//占쏙옙占쏙옙 + 占쏙옙占쏙옙 + 占쏙옙占쏙옙占� + 占쏙옙占쏙옙 占쏙옙占싱듸옙
	public String SolvingQuiz(String quiz_title, String quiz_answer_1, String quiz_answer_2, String quiz_answer_3,
			String quiz_answer_4, String quiz_final_answer, int quiz_difficulty) {
		return quiz_title + ", " + quiz_answer_1 + ", "
				+ quiz_answer_2 + ", " + quiz_answer_3 + ", " + quiz_answer_4
				+ ", " + quiz_final_answer + ", " + quiz_difficulty;
			
	}
	
	
	
	@Override
	public String toString() {
		return "Quiz_VO [quiz_set_info=" + quiz_set_info + ", quiz_title=" + quiz_title + ", quiz_subject="
				+ quiz_subject + ", quiz_answer_1=" + quiz_answer_1 + ", quiz_answer_2=" + quiz_answer_2
				+ ", quiz_answer_3=" + quiz_answer_3 + ", quiz_answer_4=" + quiz_answer_4 + ", quiz_final_answer="
				+ quiz_final_answer + ", quiz_difficulty=" + quiz_difficulty + ", quiz_cookie=" + quiz_cookie
				+ ", quiz_image=" + quiz_image + ", quiz_people=" + quiz_people + "]";
	}
	
	
}

