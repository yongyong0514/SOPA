package com.kh.sopa.test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.kh.sopa.controller.Client_Controller;
import com.kh.sopa.controller.ObjectIO;
import com.kh.sopa.model.vo.Quiz_VO;
import com.kh.sopa.view_Song.Gaming_stand_room;

public class StandRoomPanelTest extends JPanel{
	Client_Controller client = null;
	String user = "";
	JFrame mainFrame = null;
	int button_clicked_num;
	JPanel thisPage;

	public StandRoomPanelTest() { }
	
	public StandRoomPanelTest(JFrame mf, String user) {
		this.user = user;
		this.mainFrame = mf;
		this.thisPage = this;
		
		this.setBackground(Color.YELLOW);
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		
		// room Panel == top panel(main Panel)
		// size = 1024, 430
		JPanel roomPanel = new JPanel();
		roomPanel.setBackground(Color.BLUE);
		roomPanel.setBounds(0, 0, 1024, 430);
		roomPanel.setLayout(null);
		
		JButton[] rooms = new JButton[5];
		int x = 0, y = 0;
		for (int i = 0; i < rooms.length; i++) {
			rooms[i] = new JButton();
			
			
			rooms[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int cnt = 0;
					System.out.println(((JButton) e.getSource()).getText());
					System.out.println("버튼 액션 리스너에서 서버로 정보를 보내야함");
					ArrayList<Quiz_VO> quizList = new ObjectIO().QuizReadTest();
					String t = ((JButton) e.getSource()).getText();
					System.out.println("title : " + t);
					for (int j = 0; j < quizList.size(); j++) {
						Quiz_VO tmp = quizList.get(j);
						cnt++;
						if (t.equals(tmp.getQuiz_title())) {
							break;
						}
					}
					
					System.out.println("대기방으로 이동합니다.");
					thisPage.remove(roomPanel);
					thisPage.add(new Gaming_stand_room(mainFrame, "",client,cnt));
					System.out.println("EORLTLF");
					mainFrame.repaint();
			
					System.out.println("Cnt : " + cnt);
					String msg = "room_inter"+"/"+ (cnt + "");
					client.sendSystemMessage(msg);
				}
			});
			if (i % 2 == 0) {
				rooms[i].setBackground(Color.MAGENTA);
			}
			else rooms[i].setBackground(Color.PINK);
			rooms[i].setOpaque(true);
			rooms[i].setBorderPainted(false);
			rooms[i].setBounds(x, y, 1024, 75);
			y += 85;
			rooms[i].setVisible(false);
			roomPanel.add(rooms[i]);
		}
		this.add(roomPanel);
		
		// SubPanel change to class(for reuse)
		SubPanel sp = new SubPanel(this.mainFrame, this, this.user);
		this.add(sp);
		this.client = sp.client;
		
		//show room's info
		ArrayList<Quiz_VO> quizList = new ObjectIO().QuizReadTest();
		
		// check quiz's count,
		System.out.println("quiz 갯수 : " + quizList.size());
		
		for (int i = 0; i < quizList.size(); i++) {
			rooms[i].setText(quizList.get(i).getQuiz_title());
			rooms[i].setVisible(true);
		}
	}
	
	
	public void i_setter(int button_clicked_num) {
		this.button_clicked_num = button_clicked_num;
	}
}
