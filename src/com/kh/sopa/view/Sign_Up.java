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
import com.kh.sopa.controller.ObjectIO;
import com.kh.sopa.model.vo.User_VO;



public class Sign_Up extends JFrame {

	// 회원 가입

	private Login_Panel lp;
	private JPanel contentPane, sign;
	private JButton login_button, back;
	private JLabel sopa, text;
	private JTextField sign_phone, check_pw, sign_pw, sign_id;

	private User_VO uv = new User_VO();
	private LoginController lc;
	private Properties prop;
	private ArrayList al;
	JFrame mainFrame;

	public Sign_Up() {

		mainFrame = this;
		JPanel contentPane;
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// 가입하기 패널
		sign = new JPanel();
		sign.setBounds(0, 0, 1024, 768);
		sign.setBackground(new Color(252, 228, 167));
		sign.setLayout(null);
		super.add(sign);

		// sopa 라벨
		sopa = new JLabel("S.O.P.A");
		sopa.setBounds(370, 200, 250, 100);
		sopa.setFont(new Font("바탕", Font.ITALIC, 60));
		sign.add(sopa);

		// sopa 계정을 만들어요 라벨
		text = new JLabel("계정을 만들어요");
		text.setBounds(390, 255, 250, 100);
		text.setFont(new Font("바탕", Font.ITALIC, 25));
		sign.add(text);

		// 아이디 입력창
		sign_id = new JTextField(10);
		sign_id.setBounds(385, 340, 200, 30);
		sign_id.setText("아이디를 입력하세요"); // 입력창 기본 출력
		sign_id.setFont(new Font("바탕", Font.ITALIC, 14));
		sign.add(sign_id);
		sign_id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 클릭시 초기화
				sign_id.setText("");
				sign_id.requestFocus();
			}
		});

		// 비밀번호 입력창
		sign_pw = new JTextField(10);
		sign_pw.setBounds(385, 380, 200, 30);
		// 입력창 기본 출력
		sign_pw.setText("비밀 번호를 입력하세요");
		sign_pw.setFont(new Font("바탕", Font.ITALIC, 14));
		sign.add(sign_pw);
		// 클릭시 출력 공백
		sign_pw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 클릭시 초기화
				sign_pw.setText("");
				sign_pw.requestFocus();
			}
		});

		// 비밀번호 확인 입력창
		check_pw = new JTextField(10);
		check_pw.setBounds(385, 420, 200, 30);
		check_pw.setFont(new Font("바탕", Font.ITALIC, 14));
		// 입력창 기본 출력
		check_pw.setText("비밀번호를 다시 입력해주세요");
		sign.add(check_pw);
		// 클릭시 출력 공백
		check_pw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				check_pw.setText("");
				check_pw.requestFocus();
			}
		});

		// 전화번호 입력창
		sign_phone = new JTextField(10);
		sign_phone.setBounds(385, 460, 200, 30);
		// 입력창 기본 출력
		sign_phone.setText("전화번호를 입력하세요");
		sign_phone.setFont(new Font("바탕", Font.ITALIC, 14));
		sign.add(sign_phone);
		// 클릭시 출력 공백
		sign_phone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sign_phone.setText("");
				sign_phone.requestFocus();
			}
		});

		// 뒤로가기 버튼
		back = new JButton("뒤로가기");
		back.setBounds(325, 500, 120, 30);
		back.setFont(new Font("바탕", Font.ITALIC, 14));
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setFocusable(false);
		sign.add(back);
		back.addMouseListener(new MouseAdapter() {

			// 클릭 -> 로그인 화면
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int ba = JOptionPane.showConfirmDialog(null, "로그인 화면으로 돌아가시겠습니까?");
				if (ba == 0) {
					JOptionPane.showMessageDialog(null, "로그인 화면으로 돌아갑니다");
					mainFrame.dispose();
				}
				super.mouseClicked(arg0);
			}
		});

		// 회원가입 버튼
		login_button = new JButton("회원가입");
		login_button.setBounds(525, 500, 120, 30);
		login_button.setFont(new Font("바탕", Font.ITALIC, 14));
		// 버튼 투명화
		login_button.setBorderPainted(false);
		login_button.setContentAreaFilled(false);
		login_button.setFocusable(false);
		sign.add(login_button);
		login_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean[] checkCondition = new boolean[3];
				// 1. 아이디 중복 여부 검사
				ArrayList<User_VO> userList = new ArrayList<User_VO>();
				userList = new ObjectIO().UserReadToFile();
				System.out.println(userList.size());
				
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getUser_id().equals(sign_id.getText())) {
						checkCondition[0] = true;
						break;
					}
				}
				
				// 2. 비밀번호 일치 확인
				if(sign_pw.getText().equals(check_pw.getText())) {
					checkCondition[1] = true;
				}
				
				// 3. 전화번호 null 인지 확인
				if (sign_phone.getText().equals("") || ("전화번호를 입력하세요").equals(sign_phone.getText())) {
					checkCondition[2] = true;
				}
				
				int checkCount = 0;
				for (int i = 0; i < checkCondition.length; i++) {
					if (i == 0) {
						if (checkCondition[i] == true) {
							JOptionPane.showMessageDialog(null, "이미 가입한 아이디입니다.");
							System.out.println("중복된 아이디 존재");
							checkCount++;
							break;
						}
					}
					else if (i == 1) {
						if (checkCondition[i] == false) {
							JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
							System.out.println("비밀번호 불일치");
							checkCount++;
							break;
						}
					}
					else {
						if (checkCondition[i] == true) {
							JOptionPane.showMessageDialog(null, "전화번호가 입력되지 않았습니다.");
							System.out.println("전화번호 null값");
							checkCount++;
							break;
						}
					}
				}
				
				if (checkCount == 0) {
					uv.setUser_id(sign_id.getText());
					uv.setUser_pw(sign_pw.getText());
					uv.setUser_phone_number(sign_phone.getText());
					
					userList.add(uv);
					
					// 파일에 저장.
					new ObjectIO().UserWriteToFile(userList);
					
					JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.");
					new ObjectIO().UserReadToFile();
				}
//				int ba = JOptionPane.showConfirmDialog(null, "제대로 입력하셨습니까?");
//				if (ba == 0) {
//					// 가입시 화면 메세지
//					if (sign_pw.getText().equals(check_pw.getText())) {
//						// 비밀번호와 비밀 번호 확인이 일치할 시
//						JOptionPane.showMessageDialog(null, "가입을 축하합니다", "정상입력", JOptionPane.INFORMATION_MESSAGE);
//					} else {
//						// 비밀번호와 비밀번호확인이 불일치 시
//						JOptionPane.showMessageDialog(null, "입력값이 다릅니다", "입력오류", JOptionPane.WARNING_MESSAGE);
//						return;
//					}
//					// 재확인 구문
//					JOptionPane.showMessageDialog(null, "로그인 화면으로 돌아갑니다");
//					mainFrame.dispose();
//				}
//
//				// 값을 받고 이동
//				uv.setUser_id(sign_id.getText());
//				System.out.println(uv.getUser_id());
//				uv.setUser_pw(sign_pw.getText());
//				System.out.println(uv.getUser_pw());
//				uv.setUser_phone_number(sign_phone.getText());
//				System.out.println(uv.getUser_phone_number());
//
//				// 확인 구문
//				System.out.println(uv.getUser_phone_number() + "  " + uv.getUser_pw() + "  " + uv.getUser_id() + "버튼");
//
//				// 로그인 컨트롤러로 정보 이동
//				lc = new LoginController();
//				lc.user_make(uv);
//				// 버튼 클릭시 재확인
//				int ba1 = JOptionPane.showConfirmDialog(null, "로그인 화면으로 돌아가시겠습니까?");
//				if (ba1 == 0) {
//					JOptionPane.showMessageDialog(null, "로그인 화면으로 돌아갑니다");
//					mainFrame.dispose();
//				}

			}
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Sign_Up si = new Sign_Up();
	}

}
