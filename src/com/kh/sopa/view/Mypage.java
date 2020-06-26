package com.kh.sopa.view;
	
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
	import javax.swing.border.EmptyBorder;

	public class Mypage extends JFrame {
		private JPanel contentPane;

		public Mypage() {
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1024, 768);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(null);
			setContentPane(contentPane);

			// 마이페이지 패널
			JPanel myPage = new JPanel();
			myPage.setLayout(null);
			myPage.setBounds(40, 70, 940, 400);
			myPage.setBackground(Color.YELLOW);

			// 123랭크 누적 패널
			JPanel totalRank = new JPanel();
			totalRank.setLayout(null);
			totalRank.setBounds(10, 10, 380, 380);
			totalRank.setBackground(Color.WHITE);
			
			
			//누적패널 
			JPanel totalRankP = new JPanel();
			totalRankP.setBounds(5, 5, 370, 50);
			totalRankP.setBackground(Color.YELLOW);
			totalRankP.setLayout(new BorderLayout());
			
			
			//누적패널위라벨
			JLabel total_Label = new JLabel("그 동안 이만큼 이겼어요 :)");
			 total_Label.setSize(150,40);
	         total_Label.setFont(new Font("바탕", Font.BOLD, 20));
	         total_Label.setHorizontalAlignment(JLabel.CENTER); 

	         totalRankP.add(total_Label);
			
	       //123랭크 누적패널 _ 이미지 
		    //랭크
			Image totalRankIma = new ImageIcon("image/rank.PNG").getImage().getScaledInstance(300, 300, 0);;
			JLabel imaLabel = new JLabel(new ImageIcon (totalRankIma));
			imaLabel.setSize(300,300);
			imaLabel.setLocation(40, 100);
			
			
			totalRank.add(totalRankP);
			totalRank.add(imaLabel);
			
			

			// 정답율 패널
			JPanel percentage = new JPanel();
			percentage.setLayout(null);
			percentage.setBounds(400, 10, 380, 380);
			percentage.setBackground(Color.WHITE);
			
			//정답율 라벨패널 
			JPanel percentP = new JPanel();
			percentP.setBounds(5, 5, 370, 50);
			percentP.setBackground(Color.YELLOW);
			percentP.setLayout(new BorderLayout());
			
			//정답율 라벨패널 위 라벨
			JLabel percent_Label = new JLabel("그 동안 내가 선택한 답이에요!)");
			percent_Label.setSize(150,40);
			percent_Label.setFont(new Font("바탕", Font.BOLD, 20));
			percent_Label.setHorizontalAlignment(JLabel.CENTER); 

			percentP.add(percent_Label);
			
			
			//정답율 출력 패널
			JPanel percentout = new JPanel();
			percentout.setBounds(10, 320, 360, 50);
			
			//정답율 출력 패널위 라벨
			JLabel percentLabel = new JLabel();
			percentLabel.setSize(100,50);
			percentLabel.setText("정답율" /*+ 정답율*/);
			percentLabel.setFont(new Font("바탕", Font.BOLD, 35));
			
			percentout.add(percentLabel,"Center");

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

					ChangePage cp = new ChangePage();
					
					super.mouseClicked(arg0);
				}
			});

			
			
			myPage.add(totalRank);
			myPage.add(percentage);
			myPage.add(change);

			this.add(myPage);

		}

		public static void main(String[] args) {
			Mypage mp = new Mypage();

		}
	}