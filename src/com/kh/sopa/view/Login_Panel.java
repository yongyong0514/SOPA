package com.kh.sopa.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.sopa.controller.ObjectIO;
import com.kh.sopa.model.vo.User_VO;
import com.kh.sopa.test.StandRoomPanelTest;

public class Login_Panel extends JPanel{
	private JPanel contentPane;
	private JLabel sopa, id_label, pw_label;
	private JTextField login_id;
	private JPasswordField login_pw;
	private JButton login_quiz, sign_up, find_id, find_pw;
	JFrame mainFrame;
	JPanel thisPage;
	public Login_Panel() {}
	
	public Login_Panel(JFrame mf) {
		System.out.println("Log");
		this.mainFrame = mf;
//		mainFrame.setVisible(true);
		this.thisPage = this;
		
		//로그인 페이지 패널
		this.setBounds(0, 0, 1024, 768);
		this.setBackground(new Color(252, 228, 167));  
		this.setLayout(null);
		
		//로그인 창 sopa 라벨
		sopa = new JLabel("S.O.P.A");
		sopa.setBounds(430, 100, 300, 200);
		sopa.setFont(new Font("바탕", Font.ITALIC, 50));
		this.add(sopa);
		
		// 로그인 창 아이디 입력 라벨
		id_label = new JLabel("아이디 입력");
		id_label.setBounds(280, 350, 100, 50);
		id_label.setFont(new Font("바탕", Font.ITALIC, 12));
		this.add(id_label);
		
		//로그인 창 아이디 입력 패널
		login_id = new JTextField(20);
		login_id.setBounds(380, 350, 200, 40);
		login_id.setFont(new Font("바탕", Font.ITALIC, 12));
		this.add(login_id);
		
		
		//로그인 창 비밀번호 입력 라벨
		pw_label = new JLabel("비밀번호 입력");
		pw_label.setBounds(280, 400, 100, 50);
		pw_label.setFont(new Font("바탕", Font.ITALIC, 12));
		this.add(pw_label);
		
		
		//로그인 창 비밀번호 입력 패널
		login_pw = new JPasswordField(20);
		login_pw.setBounds(380, 400, 200, 40);
		login_pw.setFont(new Font("바탕", Font.ITALIC, 12));
		this.add(login_pw);
		
	
		
		//로그인 창 로그인 버튼
		login_quiz = new JButton("로그인");
		login_quiz.setBounds(640, 340, 80, 120);
		this.add(login_quiz);
		login_quiz.addMouseListener(new MouseAdapter() {
			
			// 1.클릭 -> 대기
			// 2.클릭 -> 아이디, 비번 체크 -> 객체 소환?
			@Override 
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("로그인 버튼 클릭");
				
				// 아이디 검증 추가해야함. 아이디는 파일에서 찾아서.
				
				ArrayList<User_VO> userList = new ObjectIO().UserReadToFile();
//				System.out.println(login_id.getText());
				
				String loginUser = login_id.getText();
				String userPw = login_pw.getText();
				
				boolean checkUser = false;
				for (int i = 0; i < userList.size(); i++) {
					if (loginUser.equals(userList.get(i).getUser_id())) {
						// 비밀번호 체크 추가해야함 그 후에 true
						if (userPw.equals(userList.get(i).getUser_pw())) {
							JOptionPane.showMessageDialog(null, loginUser + "님 환영합니다!");
							checkUser = true;
						}
						else {
							JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.", "비밀번호 불일치", 
									JOptionPane.WARNING_MESSAGE);
						}
						break;
					}
				}
				// 로그인 성공 
				System.out.println("before login check");
				if(checkUser) {
					System.out.println("로그인 성공");
					mainFrame.remove(thisPage);
					StandRoomPanelTest srpt = new StandRoomPanelTest(mainFrame, loginUser);
					mainFrame.add(srpt);
//					srpt.setVisible(true);
					mainFrame.repaint();
					System.out.println("DWR");
					mainFrame.setVisible(true);
				}
				
				super.mouseClicked(arg0);
			}
		});
		
		//로그인 창 아이디 찾기 버튼
		sign_up = new JButton("아이디 찾기");
		sign_up.setBounds(350, 500, 100, 20);
		sign_up.setFont(new Font("바탕", Font.ITALIC, 10));
		this.add(sign_up);
		sign_up.addMouseListener(new MouseAdapter() { 
			
			//클릭 -> 회원가입 패널
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Sign_Up su = new Sign_Up();

				super.mouseClicked(arg0);
			}
			
		});

		
		//로그인 창 아이디 찾기 버튼
		find_id = new JButton("아이디 찾기");
		find_id.setBounds(450, 500, 100, 20);
		find_id.setFont(new Font("바탕", Font.ITALIC, 10));
		this.add(find_id);
		find_id.addMouseListener(new MouseAdapter() { 
			
			//클릭 -> 아이디 찾기 패널
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Find_Id fi = new Find_Id();
				
				super.mouseClicked(arg0);
			}
			
		});
	
		//로그인 창 비밀번호 찾기
		find_pw = new JButton("비밀번호 찾기");
		find_pw.setBounds(550, 500, 100, 20);
		find_pw.setFont(new Font("바탕", Font.ITALIC, 10));
		this.add(find_pw);
		find_pw.addMouseListener(new MouseAdapter() { 
			
			//클릭 -> 비밀번호 찾기 패널
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Find_Pwd fp = new Find_Pwd();

				super.mouseClicked(arg0);
			}
		});
	}
	
	
//	public static void main(String[] args) {
//		JFrame f = new JFrame();
//		f.setTitle("로그인 화면 테스트");
//		f.setSize(1024, 768);
//		f.add(new Login_Panel(f));
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setVisible(true);
//	}
}
