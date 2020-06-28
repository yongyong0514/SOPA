package com.kh.sopa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
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
		ImageIcon imi1;
		imi1 = new ImageIcon("image/mainLabel.PNG");
		sopa = new JLabel(imi1);
		sopa.setBounds(280, 80, 400, 300);
		find_pwd.add(sopa);

		// 비밀번호를 바꾸고 싶어요 라벨
		ImageIcon im = new ImageIcon("image/changepw.PNG");
		text = new JLabel(im);
		text.setBounds(310, 210, 350, 200);
		find_pwd.add(text);

		// 아이디 입력창
		user_id = new JTextField(20);
		Image im3 = new ImageIcon("image/text300.PNG").getImage();
		user_id = new JTextField() {
			public void setBorder(Border border) {
			}

			{
				setOpaque(false);
			}

			public void paintComponent(Graphics g) {
				g.drawImage(im3, 0, 0, null);
				super.paintComponent(g);
			}
		};
		find_pwd.add(user_id);
		// 기본 입력
		user_id.setText("  아이디를 입력하세요");
		user_id.setBounds(340, 350, 350, 50);
		user_id.setForeground(Color.GRAY);
		user_id.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		user_id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 클릭시 초기화
				user_id.setText("  ");
				user_id.requestFocus();
				user_id.setForeground(Color.BLACK);
			}
		});

		// 전화번호 입력창
		Image im4 = new ImageIcon("image/text300.PNG").getImage();
		user_phone = new JTextField() {
			public void setBorder(Border border) {
			}

			{
				setOpaque(false);
			}

			public void paintComponent(Graphics g) {
				g.drawImage(im3, 0, 0, null);
				super.paintComponent(g);
			}
		};
		// 기본 입력
		user_phone.setText("  전화번호를 입력하세요");
		user_phone.setBounds(340, 420, 350, 50);
		user_phone.setForeground(Color.GRAY);
		user_phone.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		user_phone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 클릭시 초기화
				user_phone.setText("  ");
				user_phone.requestFocus();
				user_phone.setForeground(Color.BLACK);
			}
		});
		find_pwd.add(user_phone);

		// 뒤로가요 버튼
		ImageIcon im5 = new ImageIcon("image/back.PNG");
		back = new JButton(im5);
		// 위치고정
		back.setRolloverIcon(im5);
		back.setBorderPainted(false);
//				// 투명 버튼 구문
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setFocusable(false);
		back.setBounds(340, 500, 120, 60);

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
		ImageIcon im6 = new ImageIcon("image/ok.PNG");
		check = new JButton(im6);
		// 위치고정
		check.setRolloverIcon(im6);
		check.setBorderPainted(false);
//				// 투명 버튼 구문
		check.setBorderPainted(false);
		check.setContentAreaFilled(false);
		check.setFocusable(false);
		check.setBounds(530, 500, 120, 60);
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
