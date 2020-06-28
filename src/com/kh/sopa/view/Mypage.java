package com.kh.sopa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kh.sopa.controller.ObjectIO;
import com.kh.sopa.model.DAO.User_DAO;
import com.kh.sopa.model.vo.User_VO;
/*
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
*/
public class Mypage extends JPanel {
	private JPanel contentPane;
	
	
	JPanel mainPanel = null;
	JPanel thisPage = null;
	JPanel roomPanel = null;
	JFrame mainFrame = null;

	
	User_VO tmp = new User_VO();
	
	private String firstnum, secondnum, thirdnum, totalcookie;

	public Mypage(String user_id, JPanel mp, JPanel rp, JFrame mf) {
		
		this.mainPanel = mp;
		this.thisPage = this;
		this.roomPanel = rp;
		this.mainFrame = mf;

		ArrayList<User_VO> userList = new ObjectIO().UserReadToFile();
	
		
		for(int i = 0; i < userList.size(); i++) {
		if(user_id.equals(userList.get(i).getUser_id())) {
			firstnum = Integer.valueOf(userList.get(i).getUser_1st()).toString();
			secondnum = Integer.valueOf(userList.get(i).getUser_2nd()).toString();
			thirdnum = Integer.valueOf(userList.get(i).getUser_3rd()).toString();
			totalcookie = Integer.valueOf(userList.get(i).getUser_cookie()).toString();
			
		}
			
		}
		
		setVisible(true);
		setBounds(0, 0, 1024, 768);
		setLayout(null);

		System.out.println(user_id);
		// 마이페이지 패널
		JPanel myPage = new JPanel();
		myPage.setLayout(null);
		myPage.setBounds(0, 0, 1024, 430);
		myPage.setBackground(new Color(252,209,108));

		// 123랭크 누적 패널
		JPanel totalRank = new JPanel();
		totalRank.setLayout(null);
		totalRank.setBounds(10, 10, 430, 410);
		totalRank.setBackground(Color.WHITE);

		//그동안 이만큼 이겼어요 패널
		JPanel totalRankP = new JPanel();
		totalRankP.setBounds(5, 5, 420, 50);
		totalRankP.setBackground(new Color(255,179,0));
		totalRankP.setLayout(new BorderLayout());

		// 머리글 패널위라벨
		JLabel total_Label = new JLabel("그 동안 이만큼 이겼어요 :)");
		total_Label.setSize(140, 40);
		total_Label.setFont(new Font("바탕", Font.BOLD, 20));
		total_Label.setHorizontalAlignment(JLabel.CENTER);

		totalRankP.add(total_Label);

		// 123랭크 누적패널 _ 이미지
		// 랭크
		Image totalRankIma = new ImageIcon("image/rank.PNG").getImage().getScaledInstance(400, 400, 0);
		;
		JLabel imaLabel = new JLabel(new ImageIcon(totalRankIma));
		imaLabel.setSize(400, 400);
		imaLabel.setLocation(10, 100);

		totalRank.add(totalRankP);
		totalRank.add(imaLabel);
		
		// // 1등 이모티콘 이미지
		Image firstEmoji = new ImageIcon("image/medal.PNG").getImage().getScaledInstance(80, 80, 0);
		JLabel firstEmojiLab = new JLabel(new ImageIcon(firstEmoji));
		firstEmojiLab.setSize(80, 80);
		firstEmojiLab.setLocation(170, 100);

		
		JLabel firstId = new JLabel();
		firstId.setSize(80, 25);
		firstId.setLocation(170, 180);
		firstId.setFont(new Font("바탕", Font.BOLD, 15));
		firstId.setHorizontalAlignment(JLabel.CENTER);
		firstId.setText(firstnum); //1등아이디...ㅎㅎ
		

		totalRank.add(firstEmojiLab);
		totalRank.add(firstId);

		// // 2등 이모티콘 이미지 및 아이디
		Image secondEmoji = new ImageIcon("image/medal.PNG").getImage().getScaledInstance(80, 80, 0);
		JLabel secondEmojiLab = new JLabel(new ImageIcon(secondEmoji));
		secondEmojiLab.setSize(80, 80);
		secondEmojiLab.setLocation(50, 165);
		
		JLabel secondId = new JLabel();
		secondId.setSize(80, 25);
		secondId.setLocation(50, 240);
		secondId.setFont(new Font("바탕", Font.BOLD, 15));
		secondId.setHorizontalAlignment(JLabel.CENTER);
		secondId.setText(secondnum); //2등아이디...ㅎㅎ

		totalRank.add(secondEmojiLab);
		totalRank.add(secondId);
		
		
		
		// // 3등 이모티콘 이미지 및 아이디
		Image thirdEmoji = new ImageIcon("image/medal.PNG").getImage().getScaledInstance(80, 80, 0);
		JLabel thirdEmojiLab = new JLabel(new ImageIcon(thirdEmoji));
		thirdEmojiLab.setSize(80, 80);
		thirdEmojiLab.setLocation(290, 165);
		

		 JLabel thirdId = new JLabel();
		 thirdId.setSize(80, 25);
		 thirdId.setLocation(290, 240);
		 thirdId.setFont(new Font("바탕", Font.BOLD, 15));
		 thirdId.setHorizontalAlignment(JLabel.CENTER);
		 thirdId.setText(thirdnum); //3등아이디...ㅎㅎ

		totalRank.add(thirdId);
		totalRank.add(thirdEmojiLab);


		// 정답율 패널
		JPanel percentage = new JPanel();
		percentage.setLayout(null);
		percentage.setBounds(460, 10, 430, 410);
		percentage.setBackground(Color.WHITE);
		
	
		JButton button = new JButton();
/*
		button.setSize(420,280);
		button.setLocation(5,60);
		button.addMouseListener(new MouseAdapter() {

			// 클릭 -> 정보수정패널
			@Override
			public void mouseClicked(MouseEvent arg0) {

				CharView2 cv = new CharView2(user_id);

				super.mouseClicked(arg0);
			}
		});
		*/

		percentage.add(button);
		


		// 정답율 라벨패널g
		JPanel percentP = new JPanel();
		percentP.setBounds(5, 5, 420, 50);
		percentP.setBackground(new Color(255,179,0));
		percentP.setLayout(new BorderLayout());

		// 정답율 라벨패널 위 라벨
		JLabel percent_Label = new JLabel("그 동안 내가 선택한 답이에요!)");
		percent_Label.setSize(150, 40);
		percent_Label.setFont(new Font("바탕", Font.BOLD, 20));
		percent_Label.setHorizontalAlignment(JLabel.CENTER);

		percentP.add(percent_Label);

		// 정답율 출력 패널
		JPanel percentout = new JPanel();
		percentout.setBounds(5, 365, 420, 40);

		// 정답율 출력 패널위 라벨
		JLabel percentLabel = new JLabel();
		percentLabel.setSize(100, 40);
		percentLabel.setText("정답율" /* + 정답율 */);
		percentLabel.setFont(new Font("바탕", Font.BOLD, 25));

		percentout.add(percentLabel, "Center");

		percentage.add(percentP);
		percentage.add(percentout);

		// 정보를 수정해요 버튼
		JButton change = new JButton();
		change.setBounds(900, 20, 100, 50);
		change.setBackground(Color.BLUE);

		change.addMouseListener(new MouseAdapter() {

			// 클릭 -> 정보수정패널
			@Override
			public void mouseClicked(MouseEvent arg0) {

				ChangePage cp = new ChangePage(user_id);

				super.mouseClicked(arg0);
			}
		});
		
		
		//뒤로 가기 버튼
		JButton back = new JButton("뒤로가기");
		back.setBounds(900, 80, 100, 50);
		back.setBackground(Color.yellow);
		
		
		
		// 수정부분
				back.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						mp.remove(thisPage);
						mp.add(rp);
						mainFrame.repaint();
					}
				});

		
		
		myPage.add(back);
		myPage.add(totalRank);
		myPage.add(percentage);
		myPage.add(change);

		this.add(myPage);

	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
//		jf.add(new Mypage("user_id"));
		jf.setVisible(true);
		jf.setSize(1024, 768);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
