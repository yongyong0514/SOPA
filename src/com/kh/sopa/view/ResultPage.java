package com.kh.sopa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//결과페이지  클래스
public class ResultPage extends JFrame {
	private JPanel contentPane;

	/*Test_QuizResultDao qd = new Test_QuizResultDao();
	ArrayList<Test_QuizResult> quizRoom1 = qd.fileOpen();*/

	public ResultPage() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// 상단바 패널
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setLocation(40, 10);
		panel.setSize(940, 50);
		panel.setBackground(Color.WHITE);

		// 상단 오른쪽의 뒤로가기 버튼
		JButton panel_stopBtn = new JButton("뒤로가기");
		panel_stopBtn.setBounds(840, 0, 100, 50);
		panel_stopBtn.setBackground(Color.yellow);

		panel.add(panel_stopBtn);

		// 123 순위 결과(rank패널)출력 패널
		JPanel rankPanel = new JPanel();
		rankPanel.setLayout(null);
		rankPanel.setBounds(40, 70, 940, 460);
		rankPanel.setBackground(Color.WHITE);

		// rank패널안_이미지

		Image rank_ima = new ImageIcon("image/rank.PNG").getImage().getScaledInstance(400, 400, 0);
		JLabel rank_ImaL = new JLabel(new ImageIcon(rank_ima));
		rank_ImaL.setSize(400, 400);
		rank_ImaL.setLocation(270, 100);

		rankPanel.add(rank_ImaL);

		// 총문제에서 몇문제를 맞췄습니다.라벨

		// 1등 이모티콘 이미지 및 아이디
		Image FirstEmoji = new ImageIcon("Emoji/emoji_19.GIF").getImage().getScaledInstance(100, 100, 0);
		JLabel FirstEmojiLab = new JLabel(new ImageIcon(FirstEmoji));
		FirstEmojiLab.setSize(150, 150);
		FirstEmojiLab.setLocation(270,100 );
		//FirstEmojiLab.setText(quizRoom1.get(0).getUser_id());
		rankPanel.add(FirstEmojiLab);
		
		// 2등 이모티콘 이미지 및 아이디
		Image secondEmoji = new ImageIcon("Emoji/emoji_19.GIF").getImage().getScaledInstance(100, 100, 0);
		JLabel secondEmojiLab = new JLabel(new ImageIcon(FirstEmoji));
		secondEmojiLab.setSize(150, 150);
		secondEmojiLab.setLocation(450, 100);
		//secondEmojiLab.setText(quizRoom1.get(1).getUser_id());

		rankPanel.add(secondEmojiLab);

		

		//3등 이모티콘 이미지 및 아이디
		Image thirdLEmoji = new ImageIcon("Emoji/emoji_19.GIF").getImage().getScaledInstance(100, 100, 0);
		JLabel thirdEmojiLab = new JLabel(new ImageIcon(FirstEmoji));
		thirdEmojiLab.setSize(150, 150);
		thirdEmojiLab.setLocation(600, 100);
		//thirdEmojiLab.setText(quizRoom1.get(1).getUser_id());

		rankPanel.add(thirdEmojiLab);


		// rank패널_ 등수패널
		JPanel rank_RPanel = new JPanel();
		rank_RPanel.setLayout(null);
		rank_RPanel.setBounds(50, 40, 150, 90);
		rank_RPanel.setBackground(Color.BLUE);

		// 자신의 등수 라벨

		// rank패널_ 쿠키패널
		JPanel rank_CPanel = new JPanel();
		rank_CPanel.setLayout(null);
		rank_CPanel.setBounds(50, 150, 150, 90);
		rank_CPanel.setBackground(Color.RED);

		// 자신이 얻을 쿠키라벨

		rankPanel.add(rank_CPanel);
		rankPanel.add(rank_RPanel);

		this.add(rankPanel);
		this.add(panel);

		// 4등rank 패널
		JPanel four = new JPanel();
		four.setBounds(40, 540, 940, 50);
		four.setBackground(Color.WHITE);
		four.setLayout(new BorderLayout());

		JLabel four_Label = new JLabel(" 4 등");
		four_Label.setSize(80, 50);
		four_Label.setFont(new Font("바탕", Font.BOLD, 28));
		four_Label.setHorizontalAlignment(JLabel.LEFT);

		// 4등 아이디 라벨
		JLabel fourL = new JLabel();
		fourL.setSize(50, 50);
		//fourL.setText(quizRoom1.get(3).getUser_id());
		fourL.setFont(new Font("바탕", Font.BOLD, 28));
		fourL.setHorizontalAlignment(JLabel.CENTER);

		four.add(four_Label);
		four.add(fourL);
		this.add(four);

		// 5등rank 패널
		JPanel five = new JPanel();
		five.setLayout(new BorderLayout());
		five.setBounds(40, 600, 940, 50);
		five.setBackground(Color.WHITE);

		JLabel five_Label = new JLabel(" 5 등");
		five_Label.setSize(80, 50);
		five_Label.setFont(new Font("바탕", Font.BOLD, 28));
		five_Label.setHorizontalAlignment(JLabel.LEFT);

		// 5등 아이디라벨
		JLabel fiveL = new JLabel();
		fiveL.setSize(50, 50);
		//fiveL.setText(quizRoom1.get(4).getUser_id());
		fiveL.setFont(new Font("바탕", Font.BOLD, 28));
		fiveL.setHorizontalAlignment(JLabel.CENTER);

		five.add(five_Label);
		five.add(fiveL);
		this.add(five);

	}

	public static void main(String[] args) {
		new ResultPage();

	}

}
