package com.kh.sopa.model.vo;

public class User_VO implements java.io.Serializable {

	private String user_id;				//������ ���̵�
	private String user_pw;				//������ ��й�ȣ
	private String user_phone_number;	//������ �ڵ��� ��ȣ
	private int user_cookie;			//������ ��ȭ ����
	private int user_1st;				//������ 1�� Ƚ��
	private int user_2nd;				//������ 2�� Ƚ��
	private int user_3rd;				//������ 3�� Ƚ��
	private int user_all_quiz;			//������Ǭ ��ü ���� ����
	private int user_correct_quiz;		//������ ���� ���� ����
	
	public User_VO() {
		
	}
	
	public User_VO(String user_id, String user_pw, String user_phone_number, int user_cookie, int user_1st,
			int user_2nd, int user_3rd, int user_all_quiz, int user_correct_quiz) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_phone_number = user_phone_number;
		this.user_cookie = user_cookie;
		this.user_1st = user_1st;
		this.user_2nd = user_2nd;
		this.user_3rd = user_3rd;
		this.user_all_quiz = user_all_quiz;
		this.user_correct_quiz = user_correct_quiz;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_phone_number() {
		return user_phone_number;
	}

	public void setUser_phone_number(String user_phone_number) {
		this.user_phone_number = user_phone_number;
	}

	public int getUser_cookie() {
		return user_cookie;
	}

	public void setUser_cookie(int user_cookie) {
		this.user_cookie = user_cookie;
	}

	public int getUser_1st() {
		return user_1st;
	}

	public void setUser_1st(int user_1st) {
		this.user_1st = user_1st;
	}

	public int getUser_2nd() {
		return user_2nd;
	}

	public void setUser_2nd(int user_2nd) {
		this.user_2nd = user_2nd;
	}

	public int getUser_3rd() {
		return user_3rd;
	}

	public void setUser_3rd(int user_3rd) {
		this.user_3rd = user_3rd;
	}

	public int getUser_all_quiz() {
		return user_all_quiz;
	}

	public void setUser_all_quiz(int user_all_quiz) {
		this.user_all_quiz = user_all_quiz;
	}

	public int getUser_correct_quiz() {
		return user_correct_quiz;
	}

	public void setUser_correct_quiz(int user_correct_quiz) {
		this.user_correct_quiz = user_correct_quiz;
	}

	@Override
	public String toString() {
		return "User_VO [user_id=" + user_id + ", user_pw=" + user_pw + ", user_phone_number=" + user_phone_number
				+ ", user_cookie=" + user_cookie + ", user_1st=" + user_1st + ", user_2nd=" + user_2nd + ", user_3rd="
				+ user_3rd + ", user_all_quiz=" + user_all_quiz + ", user_correct_quiz=" + user_correct_quiz + "]";
	}
	
	
	
}
