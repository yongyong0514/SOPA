package com.kh.sopa.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.kh.sopa.model.DAO.User_DAO;
import com.kh.sopa.model.vo.User_VO;
import com.kh.sopa.view.Find_Id;
import com.kh.sopa.view.Find_Pwd;
import com.kh.sopa.view.Login_Panel;
import com.kh.sopa.view.Sign_Up;



public class LoginController {
	private User_VO uv;
	private User_DAO ud;
	private Login_Panel lp;
	private Sign_Up su;
	private Find_Id fi;
	private Find_Pwd fp;

	// 회원 가입 객체 생성
	public void user_make(User_VO uv) {
		ud = new User_DAO();
		ud.userOutput(uv);

	}

	// 회원 아이디 찾기
	public void fine_user() {
		User_VO uv = null;
		ud = new User_DAO();
		ud.findId();

	}

	//비밀번호 수정
	public void findPw() {

		User_DAO ud;
		ud = new User_DAO();
		ud.findPw();
	}
	
}
