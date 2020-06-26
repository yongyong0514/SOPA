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
import javax.swing.JOptionPane;
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
		sopa = new JLabel("S.O.P.A");

		sopa.setBounds(370, 200, 250, 100);
		sopa.setFont(new Font("바탕", Font.ITALIC, 60));
		find_id.add(sopa);

		// 아이디를 찾고 싶어요 라벨
		text = new JLabel("아이디를 찾고 싶어요");

		text.setBounds(360, 255, 250, 100);
		text.setFont(new Font("바탕", Font.ITALIC, 25));
		find_id.add(text);

		// 전화번호 입력창
		phone = new JTextField(20);
		phone.setBounds(380, 400, 200, 30);
		// 실행시, 초기 입력창 출력
		phone.setText("전화번호를 입력하세요");
		phone.setFont(new Font("바탕", Font.ITALIC, 14));
		phone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 클릭시 초기화
				phone.setText("");
				phone.requestFocus();
			}
		});

		find_id.add(phone);

		// 뒤로가요 버튼
		back = new JButton("뒤로가요");
		back.setBounds(325, 500, 120, 30);
		back.setFont(new Font("바탕", Font.ITALIC, 14));
		find_id.add(back);
		// 투명 버튼 구문
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setFocusable(false);
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
		check = new JButton("확인");
		check.setBounds(520, 500, 120, 30);
		check.setFont(new Font("바탕", Font.ITALIC, 14));
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

	public static void main(String[] args) {
		Find_Id fi = new Find_Id();
	}
}
