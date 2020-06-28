package com.kh.sopa.power.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kh.sopa.model.vo.User_VO;
import com.kh.sopa.view.ChangePage;
import com.kh.sopa.view.Mypage;

/*import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;*/

public class MyPageTest extends JPanel {
	private JPanel contentPane;
	
	User_VO tmp = new User_VO();
	
	public MyPageTest(String user_id) {
		setVisible(true);
		setBounds(0, 0, 1024, 768);
		setLayout(null);

		System.out.println(user_id);
		// 마이페이지 패널
		JPanel myPage = new JPanel();
		myPage.setLayout(null);
		myPage.setBounds(0, 0, 1024, 430);
		myPage.setBackground(Color.YELLOW);

		// 123랭크 누적 패널
		JPanel totalRank = new JPanel();
		totalRank.setLayout(null);
		totalRank.setBounds(10, 10, 380, 380);
		totalRank.setBackground(Color.WHITE);

		// 누적패널
		JPanel totalRankP = new JPanel();
		totalRankP.setBounds(5, 5, 370, 50);
		totalRankP.setBackground(Color.YELLOW);
		totalRankP.setLayout(new BorderLayout());

		// 누적패널위라벨
		JLabel total_Label = new JLabel("그 동안 이만큼 이겼어요 :)");
		total_Label.setSize(150, 40);
		total_Label.setFont(new Font("바탕", Font.BOLD, 20));
		total_Label.setHorizontalAlignment(JLabel.CENTER);

		totalRankP.add(total_Label);

		// 123랭크 누적패널 _ 이미지
		// 랭크
		Image totalRankIma = new ImageIcon("image/rank.PNG").getImage().getScaledInstance(300, 300, 0);
		;
		JLabel imaLabel = new JLabel(new ImageIcon(totalRankIma));
		imaLabel.setSize(300, 300);
		imaLabel.setLocation(40, 100);

		totalRank.add(totalRankP);
		totalRank.add(imaLabel);

		// // 1등 이모티콘 이미지
		Image firstMedal = new ImageIcon("image/medal.PNG").getImage().getScaledInstance(50, 50, 0);
		JLabel firstMedalLab = new JLabel(new ImageIcon(firstMedal));
		firstMedalLab.setSize(60, 60);
		firstMedalLab.setLocation(30, 50);

		totalRank.add(firstMedalLab);

		//// 1등 횟수 라벨
		JLabel firstL = new JLabel();
		firstL.setSize(60, 10);
		firstL.setLocation(60, 110);
		firstL.setText(" ");

		totalRank.add(firstL);

		// // 2등 이모티콘 이미지
		Image secondMedal = new ImageIcon("image/medal.PNG").getImage().getScaledInstance(40, 40, 0);
		JLabel secondMedalLab = new JLabel(new ImageIcon(secondMedal));
		secondMedalLab.setSize(30, 30);
		secondMedalLab.setLocation(270, 100);

		totalRank.add(secondMedalLab);

		//
		//// 2등 횟수 라벨
		JLabel secondL = new JLabel();
		secondL.setSize(60, 10);
		secondL.setLocation(60, 110);
		secondL.setText("2등횟수");

		totalRank.add(firstL);

		//
		// // 3등 이모티콘 이미지 및 아이디
		Image thirdMedal = new ImageIcon("image/medal.PNG").getImage().getScaledInstance(40, 40, 0);
		JLabel thirdMedalLab = new JLabel(new ImageIcon(thirdMedal));
		thirdMedalLab.setSize(30, 30);
		thirdMedalLab.setLocation(270, 100);
		// // FirstEmojiLab.setText(quizRoom1.get(0).getUser_id());
		totalRank.add(thirdMedalLab);
		//

		//// 3등 횟수 라벨
		JLabel thirdL = new JLabel();
		thirdL.setSize(60, 10);
		thirdL.setLocation(60, 110);
		thirdL.setText("3등횟수");

		totalRank.add(thirdL);

		// 정답율 패널
		JPanel percentage = new JPanel();
		percentage.setLayout(null);
		percentage.setBounds(400, 10, 380, 380);
		percentage.setBackground(Color.WHITE);

		JButton pp = new JButton();
/*
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("O", 23.3);
		dataset.setValue("X", 56.5);

		JFreeChart chart1 = ChartFactory.createPieChart("", dataset, false, false, false);

		percentage.add(new ChartPanel(chart1));

		percentage.setPreferredSize(new Dimension(380, 200));
*/
		// 정답율 라벨패널g
		JPanel percentP = new JPanel();
		percentP.setBounds(5, 5, 370, 50);
		percentP.setBackground(Color.YELLOW);
		percentP.setLayout(new BorderLayout());

		// 정답율 라벨패널 위 라벨
		JLabel percent_Label = new JLabel("그 동안 내가 선택한 답이에요!)");
		percent_Label.setSize(150, 40);
		percent_Label.setFont(new Font("바탕", Font.BOLD, 20));
		percent_Label.setHorizontalAlignment(JLabel.CENTER);

		percentP.add(percent_Label);

		// 정답율 출력 패널
		JPanel percentout = new JPanel();
		percentout.setBounds(10, 320, 360, 50);

		// 정답율 출력 패널위 라벨
		JLabel percentLabel = new JLabel();
		percentLabel.setSize(100, 50);
		percentLabel.setText("정답율" /* + 정답율 */);
		percentLabel.setFont(new Font("바탕", Font.BOLD, 35));

		percentout.add(percentLabel, "Center");

		percentage.add(percentP);
		percentage.add(percentout);

		// 정보를 수정해요 버튼
		JButton change = new JButton("정보를 수정해요");
		change.setBounds(780, 10, 150, 100);
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
		back.setBounds(840, 0, 100, 50);
		back.setBackground(Color.yellow);
		
		
		
		change.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				super.mouseClicked(arg0);
		}
	});
		
		
		myPage.add(back);
		myPage.add(totalRank);
		myPage.add(percentage);
		myPage.add(change);

		this.add(myPage);

	}

//	public static void main(String[] args) {
//		JFrame jf = new JFrame();
//		jf.add(new Mypage("powerman"));
//		jf.setVisible(true);
//		jf.setSize(1024, 768);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
}
