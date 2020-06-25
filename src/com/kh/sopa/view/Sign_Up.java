package com.kh.sopa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.kh.sopa.controller.LoginController;
import com.kh.sopa.model.vo.User_VO;









public class Sign_Up extends JFrame{
	
	//회원 가입
	
	private Login_Panel lp;
	private JPanel contentPane, sign;
	private JButton login_button, back;
	private JLabel sopa, text;
	private JTextField sign_phone, check_pw, sign_pw, sign_id;
	private User_VO uv = new User_VO();
	private LoginController lc;
	private Properties prop;
	private ArrayList al;
	
	
	
	
	public Sign_Up() {
		super();
		JPanel contentPane;
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		
		// 가입하기 패널
		sign = new JPanel();
		sign.setBounds(0, 0, 1024, 768);
		sign.setBackground(Color.white);
		sign.setLayout(null);
		super.add(sign);
		
				
		//sopa 라벨
		sopa = new JLabel("S.O.P.A");
		sopa.setBounds(430, 100, 300, 200);
		sopa.setFont(new Font("바탕", Font.ITALIC, 50));
		sign.add(sopa);

		
		
		//sopa 계정을 만들어요 라벨
		text = new JLabel("계정을 만들어요");
		text.setBounds(430, 200, 200, 100);
		sopa.setFont(new Font("바탕", Font.ITALIC, 20));
		sign.add(text);

		
		
		//아이디 입력창
		sign_id = new JTextField(10);
		sign_id.setBounds(390, 340, 200, 40);
		sign_id.setFont(new Font("바탕", Font.ITALIC, 12));
		sign.add(sign_id);
		
		
		
		
		//비밀번호 입력창
		sign_pw = new JTextField(10);
		sign_pw.setBounds(390, 380, 200, 40);
		sign_pw.setFont(new Font("바탕", Font.ITALIC, 12));
		sign.add(sign_pw);
		
		
		
		//비밀번호 확인 입력창
		check_pw = new JTextField(10);
		check_pw.setBounds(390, 420, 200, 40);
		check_pw.setFont(new Font("바탕", Font.ITALIC, 12));
		sign.add(check_pw);
		
		
		//전화번호 입력창
		sign_phone = new JTextField(10);
		sign_phone.setBounds(390, 460, 200, 40);
		sign_phone.setFont(new Font("바탕", Font.ITALIC, 12));
		sign.add(sign_phone);
		
		
		
		//뒤로가기 버튼
		back = new JButton("뒤로가기");
		back.setBounds(390, 500, 110, 20);
		back.setFont(new Font("바탕", Font.ITALIC, 12));
		sign.add(back);
		back.addMouseListener(new MouseAdapter() {
			
			// 1.클릭 -> 로그인 화면
			@Override 
			public void mouseClicked(MouseEvent arg0) {

				lp = new Login_Panel();
				dispose();
				super.mouseClicked(arg0);
			}
		});
		

		
		//회원가입 버튼
		login_button = new JButton("회원가입");
		login_button.setBounds(500, 500, 110, 20);
		sopa.setFont(new Font("바탕", Font.ITALIC, 20));
		sign.add(login_button);
		login_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				if(sign_pw.getText().equals(check_pw.getText())) {
				JOptionPane.showMessageDialog(null, "가입을 축하합니다", "정상입력", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("입력값이 다릅니다");
			} else {
				JOptionPane.showMessageDialog(null, "입력값이 다릅니다", "입력오류", JOptionPane.WARNING_MESSAGE);
				return;
			}
				
				//값을 받고 이동
				uv.setUser_id(sign_id.getText());
				System.out.println(uv.getUser_id());
				uv.setUser_pw(sign_pw.getText());
				System.out.println(uv.getUser_pw());
				uv.setUser_phone_number(sign_phone.getText());
				System.out.println(uv.getUser_phone_number());
					
				System.out.println(uv.getUser_phone_number() + "  " + uv.getUser_pw()  + "  " + uv.getUser_id()  + "버튼");
		
				lc = new LoginController();
				lc.user_make(uv);
				new Login_Panel();
				dispose();
			}
		});
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
