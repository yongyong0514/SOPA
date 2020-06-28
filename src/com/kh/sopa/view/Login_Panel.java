package com.kh.sopa.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.kh.sopa.controller.ObjectIO;
import com.kh.sopa.model.vo.User_VO;
import com.kh.sopa.test.StandRoomPanelTest;

public class Login_Panel extends JPanel {
	private JPanel contentPane;
	private JLabel sopa, id_label, pw_label;
	private JTextField login_id;
	private JTextField login_pw;
	private JButton login_quiz, sign_up, find_id, find_pw;
	JFrame mainFrame;
	JPanel thisPage;

	public Login_Panel() {
	}

	public Login_Panel(JFrame mf) {
		System.out.println("Log");
		this.mainFrame = mf;
//		mainFrame.setVisible(true);
		this.thisPage = this;

		// 로그인 페이지 패널
		this.setBounds(0, 0, 1024, 768);
		this.setBackground(new Color(252, 228, 167));
		this.setLayout(null);

		JPanel bigPanel = new JPanel();
		bigPanel.setBackground(new Color(252, 228, 167));
		bigPanel.setLayout(null);
		bigPanel.setBounds(0, 0, 1024, 768);
		this.add(bigPanel);

		// 로그인 창 sopa 라벨
		ImageIcon imi1;
		imi1 = new ImageIcon("image/mainLabel.PNG");
		sopa = new JLabel(imi1);
		sopa.setBounds(280, 80, 400, 300);
		bigPanel.add(sopa);

		// 아이디 라벨
		ImageIcon imi14 = new ImageIcon("image/idlabel.PNG");
		id_label = new JLabel(imi14);
		id_label.setBounds(160, 350, 120, 30);
		bigPanel.add(id_label);

		// 비밀번호 라벨
		ImageIcon imi15 = new ImageIcon("image/pwLabel.PNG");
		pw_label = new JLabel(imi15);
		pw_label.setBounds(150, 430, 120, 30);
		bigPanel.add(pw_label);

		// 로그인 창 아이디 입력 패널
		Image im2 = new ImageIcon("image/text300.PNG").getImage();

		login_id = new JTextField() {
			public void setBorder(Border border) {
			}

			{
				setOpaque(false);
			}

			public void paintComponent(Graphics g) {
				g.drawImage(im2, 0, 0, null);
				super.paintComponent(g);
			}
		};
		// 기본 입력
		login_id.setText("  아이디를 입력하세요");
		login_id.setBounds(280, 350, 350, 50);
		login_id.setForeground(Color.GRAY);
		login_id.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		login_id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 클릭시 초기화
				login_id.setText("  ");
				login_id.requestFocus();
				login_pw.setForeground(Color.BLACK);
			}
		});
		bigPanel.add(login_id);

		// 로그인 창 비밀번호 입력 패널
		login_pw = new JPasswordField(20);
		Image im6 = new ImageIcon("image/text300.PNG").getImage();
		login_pw = new JPasswordField() {
			public void setBorder(Border border) {
			}

			{
				setOpaque(false);
			}

			public void paintComponent(Graphics g) {
				g.drawImage(im6, 0, 0, null);
				super.paintComponent(g);
			}
		};
		// 기본 입력
		login_pw.setText("비밀번호 입력");
		login_pw.setBounds(280, 420, 350, 50);
		login_pw.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		login_pw.setForeground(Color.GRAY);
		login_pw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 클릭시 초기화
				login_pw.setText("");
				login_pw.requestFocus();
				login_pw.setForeground(Color.BLACK);
			}
		});
		bigPanel.add(login_pw);

		// 로그인 창 로그인 버튼
		ImageIcon im4 = new ImageIcon("image/loginbutton.PNG");
		login_quiz = new JButton(im4);
		// 위치고정
		login_quiz.setRolloverIcon(im4);
		login_quiz.setBorderPainted(false);
		login_quiz.setContentAreaFilled(false);
		login_quiz.setFocusable(false);
		login_quiz.setBounds(590, 350, 80, 120);
		bigPanel.add(login_quiz);

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
						} else {
							JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.", "비밀번호 불일치",
									JOptionPane.WARNING_MESSAGE);
						}
						break;
					}
				}
				// 로그인 성공
				System.out.println("before login check");
				if (checkUser) {
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

		// 회원가입 버튼
		ImageIcon im5 = new ImageIcon("image/sign.PNG");
		sign_up = new JButton(im5);
		// 위치고정
		sign_up.setRolloverIcon(im5);
		sign_up.setBorderPainted(false);
		sign_up.setContentAreaFilled(false);
		sign_up.setFocusable(false);
		sign_up.setBounds(250, 500, 150, 60);
		bigPanel.add(sign_up);
		sign_up.addMouseListener(new MouseAdapter() {

			// 클릭 -> 회원가입 패널
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Sign_Up su = new Sign_Up();

				super.mouseClicked(arg0);
			}

		});

		// 로그인 창 아이디 찾기 버튼
		ImageIcon im8 = new ImageIcon("image/findid.PNG");
		find_id = new JButton(im8);
		// 위치고정
		find_id.setRolloverIcon(im8);
		find_id.setBorderPainted(false);
		find_id.setContentAreaFilled(false);
		find_id.setFocusable(false);
		find_id.setBounds(373, 500, 160, 60);

		bigPanel.add(find_id);
		find_id.addMouseListener(new MouseAdapter() {

			// 클릭 -> 아이디 찾기 패널
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Find_Id fi = new Find_Id();

				super.mouseClicked(arg0);
			}

		});

		// 로그인 창 비밀번호 찾기
		ImageIcon im7 = new ImageIcon("image/findpw.PNG");
		find_pw = new JButton(im7);
		// 위치고정
		find_pw.setRolloverIcon(im7);
		find_pw.setBorderPainted(false);
		find_pw.setContentAreaFilled(false);
		find_pw.setFocusable(false);
		find_pw.setBounds(515, 500, 180, 60);

		bigPanel.add(find_pw);
		find_pw.addMouseListener(new MouseAdapter() {

			// 클릭 -> 비밀번호 찾기 패널
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Find_Pwd fp = new Find_Pwd();

				super.mouseClicked(arg0);
			}
		});
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(1024, 768);
		f.setTitle("test");
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.add(new Login_Panel(f));
		f.setVisible(true);
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
