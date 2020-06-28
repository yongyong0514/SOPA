package com.kh.sopa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
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
		ImageIcon imi1;
		imi1 = new ImageIcon("image/mainLabel.PNG");
		sopa = new JLabel(imi1);
		sopa.setBounds(280, 20, 400, 300);
		sign.add(sopa);

		// sopa 계정을 만들어요 라벨
		ImageIcon imi2;
		imi1 = new ImageIcon("image/createlabel.PNG");
		sopa = new JLabel(imi1);
		sopa.setBounds(320, 200, 350, 100);
		sign.add(sopa);

		// 아이디 입력창
		sign_id = new JTextField(20);
		Image im3 = new ImageIcon("image/text300.PNG").getImage();
		sign_id = new JTextField() {
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
		sign_id.setText("  아이디를 입력하세요");
		sign_id.setBounds(340, 300, 350, 50);
		sign_id.setForeground(Color.GRAY);
		sign_id.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		sign_id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 클릭시 초기화
				sign_id.setText("  ");
				sign_id.requestFocus();
				sign_id.setForeground(Color.BLACK);
			}
		});
		sign.add(sign_id);

		// 전화번호 입력창
		sign_phone = new JTextField(20);
		Image im4 = new ImageIcon("image/text300.PNG").getImage();
		sign_phone = new JTextField() {
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
		sign_phone.setText("  전화번호를 입력하세요");
		sign_phone.setBounds(340, 360, 350, 50);
		sign_phone.setForeground(Color.GRAY);
		sign_phone.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		sign_phone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 클릭시 초기화
				sign_phone.setText("  ");
				sign_phone.requestFocus();
				sign_phone.setForeground(Color.BLACK);
			}
		});
		sign.add(sign_phone);
		// 클릭시 출력 공백
		sign_phone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sign_phone.setText("");
				sign_phone.requestFocus();
			}
		});

		// 비밀번호 입력창
				Image im6 = new ImageIcon("image/text300.PNG").getImage();
				sign_pw =  new JTextField() {
					public void setBorder(Border border) {
					}
					{ setOpaque(false);} 
					public void paintComponent(Graphics g) {
						g.drawImage(im3, 0, 0, null);
						super.paintComponent(g);
					}
				};
				//기본 입력
				sign_pw.setText("  비밀번호를 입력하세요");
				sign_pw.setBounds(340, 420, 350, 50);
				sign_pw.setFont(new Font("맑은 고딕", Font.BOLD, 25));
				sign_pw.setForeground(Color.GRAY);
				sign_pw.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						// 클릭시 초기화
						sign_pw.setText("  ");
						sign_pw.requestFocus();
						sign_pw.setForeground(Color.BLACK);
					}
				});
				sign.add(sign_pw);
				// 클릭시 출력 공백
				sign_pw.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						sign_pw.setText("  ");
						sign_pw.requestFocus();
					}
				});


				// 비밀번호 확인 입력창
				Image im7 = new ImageIcon("image/text300.PNG").getImage();
				check_pw = new JTextField() {
					public void setBorder(Border border) {
					}
					{ setOpaque(false);} 
					public void paintComponent(Graphics g) {
						g.drawImage(im3, 0, 0, null);
						super.paintComponent(g);
					}
				};
				//기본 입력
				check_pw.setText(" 비밀번호를 재입력하세요");
				check_pw.setBounds(340, 480, 350, 50);
				check_pw.setForeground(Color.GRAY);
				check_pw.setFont(new Font("맑은 고딕", Font.BOLD, 25));
				check_pw.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						// 클릭시 초기화
						check_pw.setText("  ");
						check_pw.requestFocus();
					}
				});
				sign.add(check_pw);
				// 클릭시 출력 공백
				check_pw.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						check_pw.setText("  ");
						check_pw.requestFocus();
						check_pw.setForeground(Color.BLACK);
					}
				});


		// 뒤로가기 버튼
		ImageIcon im8 = new ImageIcon("image/back.PNG");
		back = new JButton(im8);
		// 위치고정
		back.setRolloverIcon(im8);
		back.setBorderPainted(false);
//		// 투명 버튼 구문
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setFocusable(false);
		back.setBounds(340, 550, 120, 60);
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
		ImageIcon im9 = new ImageIcon("image/signup.PNG");
		login_button = new JButton(im9);
		// 위치고정
		login_button.setRolloverIcon(im9);
		login_button.setBorderPainted(false);
//		// 투명 버튼 구문
		login_button.setBorderPainted(false);
		login_button.setContentAreaFilled(false);
		login_button.setFocusable(false);
		login_button.setBounds(530, 550, 120, 60);
		sign.add(login_button);
		login_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int ba = JOptionPane.showConfirmDialog(null, "제대로 입력하셨습니까?");
				if (ba == 0) {
					// 가입시 화면 메세지
					if (sign_pw.getText().equals(check_pw.getText())) {
						// 비밀번호와 비밀 번호 확인이 일치할 시
						JOptionPane.showMessageDialog(null, "가입을 축하합니다", "정상입력", JOptionPane.INFORMATION_MESSAGE);
					} else {
						// 비밀번호와 비밀번호확인이 불일치 시
						JOptionPane.showMessageDialog(null, "입력값이 다릅니다", "입력오류", JOptionPane.WARNING_MESSAGE);
						return;
					}
					// 재확인 구문
					JOptionPane.showMessageDialog(null, "로그인 화면으로 돌아갑니다");
					mainFrame.dispose();
				}

				// 값을 받고 이동
				uv.setUser_id(sign_id.getText());
				System.out.println(uv.getUser_id());
				uv.setUser_pw(sign_pw.getText());
				System.out.println(uv.getUser_pw());
				uv.setUser_phone_number(sign_phone.getText());
				System.out.println(uv.getUser_phone_number());

				// 확인 구문
				System.out.println(uv.getUser_phone_number() + "  " + uv.getUser_pw() + "  " + uv.getUser_id() + "버튼");

				// 로그인 컨트롤러로 정보 이동
				lc = new LoginController();
				lc.user_make(uv);
				// 버튼 클릭시 재확인
				int ba1 = JOptionPane.showConfirmDialog(null, "로그인 화면으로 돌아가시겠습니까?");
				if (ba1 == 0) {
					JOptionPane.showMessageDialog(null, "로그인 화면으로 돌아갑니다");
					mainFrame.dispose();
				}

			}
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}