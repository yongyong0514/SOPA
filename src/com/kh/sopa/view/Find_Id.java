package com.kh.sopa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
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
import com.kh.sopa.model.DAO.User_DAO;
import com.kh.sopa.model.vo.User_VO;

public class Find_Id extends JFrame {
	private JPanel contentPane, find_id;
	private JLabel sopa, text;
	private static JTextField phone;
	private JButton back, check;
	private Login_Panel lp;

	JFrame mainFrame;
	private User_VO uv;
	private LoginController lc;
	private User_DAO ud;

	// 아이디 찾기
	public Find_Id() {

		mainFrame = this;
		JPanel contentPane;
		setTitle("아이디찾기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		;
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// 아이디 찾기 패널
		find_id = new JPanel();
		find_id.setBounds(0, 0, 1024, 768);
		find_id.setBackground(new Color(252, 228, 167));
		find_id.setLayout(null);
		super.add(find_id);

		// sopa 라벨
		ImageIcon imi1;
		imi1 = new ImageIcon("image/mainLabel.PNG");
		sopa = new JLabel(imi1);
		sopa.setBounds(280, 80, 400, 300);
		find_id.add(sopa);

		// 아이디를 찾고 싶어요 라벨
		ImageIcon im = new ImageIcon("image/findlabel.PNG");
		text = new JLabel(im);
		text.setBounds(340, 210, 300, 200);
		find_id.add(text);

		// 전화번호 입력창
		// 실행시, 초기 입력창 출력
		Image im3 = new ImageIcon("image/text300.PNG").getImage();
		phone = new JTextField() {
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
		phone.setText("  전화번호를 입력하세요");
		phone.setBounds(340, 380, 350, 50);
		phone.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		phone.setForeground(Color.GRAY);
		phone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 클릭시 초기화
				phone.setText("  ");
				phone.requestFocus();
				phone.setForeground(Color.black);

			}
		});
		find_id.add(phone);

		// 뒤로가요 버튼
		ImageIcon im4 = new ImageIcon("image/back.PNG");
		back = new JButton(im4);
		// 위치고정
		back.setRolloverIcon(im4);
		back.setBorderPainted(false);
//				// 투명 버튼 구문

		back.setContentAreaFilled(false);
		back.setFocusable(false);
		back.setBounds(340, 480, 120, 60);
		find_id.add(back);
		// 클릭
		back.addMouseListener(new MouseAdapter() {
			// 1.클릭 -> 로그인화면
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 버튼 클릭시 재확인
				int ba = JOptionPane.showConfirmDialog(null, "로그인 화면으로 돌아가시겠습니까?");
				if (ba == 0) {
					JOptionPane.showMessageDialog(null, "로그인 화면으로 돌아갑니다");
					dispose();
				}
			}
		});

		// 확인 버튼
		ImageIcon im5 = new ImageIcon("image/ok.PNG");
		check = new JButton(im5);
		check.setBounds(530, 480, 120, 60);
		check.setBorderPainted(false);
		check.setContentAreaFilled(false);
		check.setFocusable(false);
		find_id.add(check);
		check.addMouseListener(new MouseAdapter() {

			// 1. 전화번호 동일 여부 체크
			// 1-1. 없음 : 전번 없다 띄움
			// 1-2. 있음 : 아이디 띠움
			// 2. 로그인으로 돌아가기
			@Override
			public void mouseClicked(MouseEvent arg0) {

				System.out.println(phone.getText() + "입력값 버튼");

				// 로그인컨트롤러로 이동
				LoginController ld = new LoginController();
				ld.fine_user();

				// 버튼 클릭시 재확인
				int ba = JOptionPane.showConfirmDialog(null, "로그인 화면으로 돌아가시겠습니까?");
				if (ba == 0) {
					JOptionPane.showMessageDialog(null, "로그인 화면으로 돌아갑니다");
					mainFrame.dispose();
				}
				super.mouseClicked(arg0);

			}
		});

		this.setVisible(true);
	}

	public static String getText() {
		// 받은 전화번호 값을 dao에 보냄
		return phone.getText();
	}
}