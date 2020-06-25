package com.kh.sopa.model.vo;


public class Quiz_added_VO implements java.io.Serializable {

	private String added_title;			//문제이름
//	private String added_subject;		//주제는 세트에서 추가됨
	private String added_answer_1;		//1번답
	private String added_answer_2;		//2번답
	private String added_answer_3;		//3번답
	private String added_answer_4;		//4번답
	private String added_final_answer;	//최종 정답은 String으로 비교
	private int added_difficulty;		//난이도 상급10 중급20 하급30
	private int added_cookie;			//문제당 지급 쿠키
	private String added_image;			//문제에 추가할 이미지
//	private int added_people;			//참여인원은 세트에서 추가됨
 
	
	public Quiz_added_VO() {
		// TODO Auto-generated constructor stub
	}


	public Quiz_added_VO(String added_title, String added_answer_1, String added_answer_2, String added_answer_3,
			String added_answer_4, String added_final_answer, int added_difficulty, int added_cookie,
			String added_image) {
		super();
		this.added_title = added_title;
		this.added_answer_1 = added_answer_1;
		this.added_answer_2 = added_answer_2;
		this.added_answer_3 = added_answer_3;
		this.added_answer_4 = added_answer_4;
		this.added_final_answer = added_final_answer;
		this.added_difficulty = added_difficulty;
		this.added_cookie = added_cookie;
		this.added_image = added_image;
	}


	public String getAdded_title() {
		return added_title;
	}


	public void setAdded_title(String added_title) {
		this.added_title = added_title;
	}


	public String getAdded_answer_1() {
		return added_answer_1;
	}


	public void setAdded_answer_1(String added_answer_1) {
		this.added_answer_1 = added_answer_1;
	}


	public String getAdded_answer_2() {
		return added_answer_2;
	}


	public void setAdded_answer_2(String added_answer_2) {
		this.added_answer_2 = added_answer_2;
	}


	public String getAdded_answer_3() {
		return added_answer_3;
	}


	public void setAdded_answer_3(String added_answer_3) {
		this.added_answer_3 = added_answer_3;
	}


	public String getAdded_answer_4() {
		return added_answer_4;
	}


	public void setAdded_answer_4(String added_answer_4) {
		this.added_answer_4 = added_answer_4;
	}


	public String getAdded_final_answer() {
		return added_final_answer;
	}


	public void setAdded_final_answer(String added_final_answer) {
		this.added_final_answer = added_final_answer;
	}


	public int getAdded_difficulty() {
		return added_difficulty;
	}


	public void setAdded_difficulty(int added_difficulty) {
		this.added_difficulty = added_difficulty;
	}


	public int getAdded_cookie() {
		return added_cookie;
	}


	public void setAdded_cookie(int added_cookie) {
		this.added_cookie = added_cookie;
	}


	public String getAdded_image() {
		return added_image;
	}


	public void setAdded_image(String added_image) {
		this.added_image = added_image;
	}


	@Override
	public String toString() {
		return "Quiz_added_VO [added_title=" + added_title + ", added_answer_1=" + added_answer_1 + ", added_answer_2="
				+ added_answer_2 + ", added_answer_3=" + added_answer_3 + ", added_answer_4=" + added_answer_4
				+ ", added_final_answer=" + added_final_answer + ", added_difficulty=" + added_difficulty
				+ ", added_cookie=" + added_cookie + ", added_image=" + added_image + "]";
	}
	
	
	
}
