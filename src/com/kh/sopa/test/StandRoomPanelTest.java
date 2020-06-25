package com.kh.sopa.test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.kh.sopa.controller.ObjectIO;
import com.kh.sopa.model.vo.Quiz_VO;

public class StandRoomPanelTest extends JPanel{
	String user = "";
	JFrame mainFrame = null;
	
	public StandRoomPanelTest() { }
	public StandRoomPanelTest(JFrame mf, String user) {
		this.user = user;
		this.mainFrame = mf;
		
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
					System.out.println(((JButton) e.getSource()).getText());
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
		this.add(new SubPanel(this.mainFrame, this, this.user));
		
		//show room's info
		ArrayList<Quiz_VO> quizList = new ObjectIO().QuizReadTest();
		
		// check quiz's count,
		System.out.println("quiz 갯수 : " + quizList.size());
		
		for (int i = 0; i < quizList.size(); i++) {
			rooms[i].setText(quizList.get(i).getQuiz_title());
			rooms[i].setVisible(true);
		}
	}
}
