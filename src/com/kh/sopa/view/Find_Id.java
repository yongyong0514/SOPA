package com.kh.sopa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
	JPanel thisPage;
	private User_VO uv;
	private LoginController lc;
	private User_DAO ud;

	// 아이디 찾기
	public Find_Id() {
	}

	public Find_Id(JFrame jf) {

		super();
		JPanel contentPane;
		setTitle("아이디찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		sopa = new JLabel("S.O.P.A");
		sopa.setBounds(390, 250, 124, 78);
		sopa.setFont(new Font("바탕", Font.ITALIC, 10));
		find_id.add(sopa);

		// 아이디를 찾고 싶어요 라벨
		text = new JLabel("아이디를 찾고 싶어요");
		text.setBounds(390, 400, 124, 78);
		text.setFont(new Font("바탕", Font.ITALIC, 10));
		find_id.add(text);

		// 전화번호 입력창
		phone = new JTextField(20);
		phone.setBounds(390, 480, 200, 40);
		phone.setFont(new Font("바탕", Font.ITALIC, 10));
		find_id.add(phone);

		// 뒤로가요 버튼
		back = new JButton("뒤로가요");
		back.setBounds(390, 550, 110, 20);
		back.setFont(new Font("바탕", Font.ITALIC, 10));
		find_id.add(back);
		back.addMouseListener(new MouseAdapter() {

			// 1.클릭 -> 로그인화면
			@Override
			public void mouseClicked(MouseEvent arg0) {

				lp = new Login_Panel();
				dispose();
				super.mouseClicked(arg0);
			}
		});

		// 확인 버튼
		check = new JButton("확인");
		check.setBounds(600, 550, 110, 20);
		check.setFont(new Font("바탕", Font.ITALIC, 10));
		find_id.add(check);
		check.addMouseListener(new MouseAdapter() {

			// 1. 전화번호 동일 여부 체크
			// 1-1. 없음 : 전번 없다 띄움
			// 1-2. 있음 : 아이디 띠움
			// 2. 로그인으로 돌아가기
			@Override
			public void mouseClicked(MouseEvent arg0) {

				System.out.println(phone.getText() + "입력값 버튼");

				LoginController ld = new LoginController();
				ld.fine_user();

				lp = new Login_Panel();

				dispose();
				super.mouseClicked(arg0);
			}
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static String getText() {
		System.out.println(phone.getText() + "값테스트");
		return phone.getText();
	}

}