package com.kh.sopa.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.kh.sopa.controller.Client_Controller;
import com.kh.sopa.controller.ObjectIO;
import com.kh.sopa.makingQuiz.model.dao.Quiz_DAO;
import com.kh.sopa.model.vo.Quiz_VO;
import com.kh.sopa.view_Song.Gaming_stand_room;

public class StandRoomPanelTest extends JPanel {
	Client_Controller client = null;
	String user = "";
	JFrame mainFrame = null;
	JPanel sub_panel;
	int button_clicked_num;
	JPanel thisPage;

	public StandRoomPanelTest() {
	}

	public StandRoomPanelTest(JFrame mf, String user) {
		this.user = user;
		this.mainFrame = mf;
		this.thisPage = this;
		
//		this.setBackground(Color.YELLOW);
		this.setBackground(new Color(252, 209, 108));
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);

		// room Panel == top panel(main Panel)
		// size = 1024, 430
		JPanel roomPanel = new JPanel();
		roomPanel.setBackground(new Color(252, 228, 167));
		roomPanel.setBounds(0, 0, 1024, 430);
		roomPanel.setLayout(null);

		// SubPanel change to class(for reuse)
		SubPanel sp = new SubPanel(this.mainFrame, this, roomPanel, this.user);
		this.add(sp);
		this.client = sp.client;

		JButton[] rooms = new JButton[5];
		int x = 0, y = 40;
		for (int i = 0; i < rooms.length; i++) {
			rooms[i] = new JButton();

			rooms[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					int cnt = 0;
					System.out.println(((JButton) e.getSource()).getText());
					System.out.println("버튼 액션 리스너에서 서버로 정보를 보내야함");
					ArrayList<Quiz_VO> quizList = new ObjectIO().QuizReadTest();
					ArrayList<Quiz_VO> quizList2 = new Quiz_DAO().readQuizSet();
					HashSet<String> title = new HashSet<String>();
					
					for (int i = 0; i < quizList2.size(); i++) {
						title.add(quizList2.get(i).getQuiz_set_info());
					}
					
					String t = ((JButton) e.getSource()).getText();
					Iterator quizTitle = title.iterator();
					while(quizTitle.hasNext()) {
						cnt++;
						if (t.equals(quizTitle.next() + "")) {
							break;
						}
					}
					
					System.out.println("대기방으로 이동합니다.");
					thisPage.remove(roomPanel);
					thisPage.add(new Gaming_stand_room(thisPage, mainFrame, "", client, cnt,t));
					System.out.println("EORLTLF");
					mainFrame.repaint();

					System.out.println("Cnt : " + cnt);
					String msg = "room_inter" + "/" + (cnt + "");
					client.sendSystemMessage(msg);
				}
			});
			if (i % 2 == 0) {
				rooms[i].setBackground(new Color(171, 201, 101));
			} else
				rooms[i].setBackground(new Color(213, 239, 144));
			rooms[i].setOpaque(true);
			rooms[i].setFont(new Font("맑은 고딕", Font.BOLD, 30));
			rooms[i].setBorderPainted(false);
			rooms[i].setBounds(x, y, 1024, 75);
			y += 85;
			rooms[i].setVisible(false);
			roomPanel.add(rooms[i]);
		}
		
		JButton reFresh = new JButton();
		reFresh.setText("새로고침");
		roomPanel.add(reFresh);
		reFresh.setBounds(0, 0, 100, 40);
		reFresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Quiz_VO> quizList = new Quiz_DAO().readQuizSet();
				
				
				HashSet<String> quizSet = new HashSet<String>();

				for (int i = 0; i < quizList.size(); i++) {
//					System.out.println(quizList.get(i));
					quizSet.add(quizList.get(i).getQuiz_set_info());
				}
				
				// check quiz's count,
				System.out.println("갯수 : " + quizSet.size());
				
				Iterator roomTitle = quizSet.iterator();
				int roomCount = 0;
				while (roomTitle.hasNext()) {
					rooms[roomCount].setText(roomTitle.next() + "");
					rooms[roomCount++].setVisible(true);
				}
			}
		});
		this.add(roomPanel);

		// show room's info
		ArrayList<Quiz_VO> quizList = new Quiz_DAO().readQuizSet();
		
		
		HashSet<String> quizSet = new HashSet<String>();

		for (int i = 0; i < quizList.size(); i++) {
//			System.out.println(quizList.get(i));
			quizSet.add(quizList.get(i).getQuiz_set_info());
		}
		
		// check quiz's count,
		System.out.println("갯수 : " + quizSet.size());
		
		Iterator roomTitle = quizSet.iterator();
		int roomCount = 0;
		while (roomTitle.hasNext()) {
			rooms[roomCount].setText(roomTitle.next() + "");
			rooms[roomCount++].setVisible(true);
		}
	}
	
	public void i_setter(int button_clicked_num) {
		this.button_clicked_num = button_clicked_num;
	}
}
