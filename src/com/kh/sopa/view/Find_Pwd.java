package com.kh.sopa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.kh.sopa.controller.LoginController;

public class Find_Pwd extends JFrame {
	private JPanel contentPane, find_pwd;
	private JLabel sopa, text;
	private static JTextField user_id;
	private static JTextField user_phone;
	private JButton back, check;
	private Login_Panel lp;
	JFrame mainFrame;

	public Find_Pwd() { // 수정 안되는 저주를 받음.

		mainFrame = this;
		JPanel contentPane;
		setTitle("비밀번호 찾기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// 비밀번호 찾기 패널
		find_pwd = new JPanel();
		find_pwd.setBounds(0, 0, 1024, 768);
		find_pwd.setBackground(new Color(252, 228, 167));
		find_pwd.setLayout(null);
		this.add(find_pwd);

		// sopa 라벨
		sopa = new JLabel("S.O.P.A");
		sopa.setBounds(370, 200, 250, 100);
		sopa.setFont(new Font("바탕", Font.ITALIC, 60));
		find_pwd.add(sopa);

		// 비밀번호를 바꾸고 싶어요 라벨
		text = new JLabel("비밀번호를 바꾸고 싶어요");
		text.setBounds(340, 255, 450, 100);
		text.setFont(new Font("바탕", Font.ITALIC, 25));
		find_pwd.add(text);

		// 아이디 입력창
		user_id = new JTextField(10);
		user_id.setBounds(390, 400, 200, 30);
		// 입력창 기본 출력화면
		user_id.setText("아이디를 입력하세요");
		user_id.setFont(new Font("바탕", Font.ITALIC, 14));
		find_pwd.add(user_id);
		// 클릭시 출력창 초기화
		user_id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				user_id.setText("");
				user_id.requestFocus();
			}
		});

		// 전화번호 입력창
		user_phone = new JTextField(10);
		user_phone.setBounds(390, 450, 200, 30);
		// 입력창 기본 출력화면
		user_phone.setText("전화번호를 입력하세요");
		user_phone.setFont(new Font("바탕", Font.ITALIC, 14));
		find_pwd.add(user_phone);
		// 클릭시 출력창 공백
		user_phone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				user_phone.setText("");
				user_phone.requestFocus();
			}
		});

		// 뒤로가요 버튼
		back = new JButton("뒤로가기");
		back.setBounds(325, 500, 120, 30);
		back.setFont(new Font("바탕", Font.ITALIC, 14));
		// 버튼 투명화
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setFocusable(false);
		find_pwd.add(back);
		back.addMouseListener(new MouseAdapter() {

			// 1.클릭 -> 로그인 화면
			@Override
			public void mouseClicked(MouseEvent arg0) {

				// 재확인
				int ba = JOptionPane.showConfirmDialog(null, "로그인 화면으로 돌아가시겠습니까?");
				if (ba == 0) {
					JOptionPane.showMessageDialog(null, "로그인 화면으로 돌아갑니다");
					dispose();
				}
				super.mouseClicked(arg0);

			}
		});

		// 확인 버튼
		check = new JButton("확인");
		check.setBounds(520, 500, 120, 30);
		check.setFont(new Font("바탕", Font.ITALIC, 14));
		check.setBorderPainted(false);
		check.setContentAreaFilled(false);
		check.setFocusable(false);
		find_pwd.add(check);
		check.addMouseListener(new MouseAdapter() {

			// 1. 아이디 동일 여부 체크
			// 1-1. 없음 : 아이디 없다 띄움
			// 1-2. 있음 : 전화번호 체크
			// 2. 전화번호 동일 여부 체크
			// 2-1. 없음 : 전화번호 다르다 띄움
			// 2-2. 있음 : 비번 수정 혹은 새로운 비번 띄우기
			// 3. 로그인으로 돌아가기

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(user_id.getText());
				System.out.println(user_phone.getText());

				// 컨트롤러로 이동
				LoginController lc = new LoginController();
				lc.findPw();

				// 재확인
				int ba = JOptionPane.showConfirmDialog(null, "로그인 화면으로 돌아가시겠습니까?");
				if (ba == 0) {
					JOptionPane.showMessageDialog(null, "로그인 화면으로 돌아갑니다");
					mainFrame.dispose();
				}
				super.mouseClicked(arg0);
			}
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static String getId() {

		// 아이디 값을 보내서 dao에 보냄

		return user_id.getText();

	}

	public static String getPhone() {

		// 전화번호 값을 받아서 dao에 보냄
		return user_phone.getText();
	}

	public static void main(String[] args) {
		Find_Pwd fp = new Find_Pwd();
	}
}
