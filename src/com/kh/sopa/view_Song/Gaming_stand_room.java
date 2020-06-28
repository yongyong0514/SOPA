package com.kh.sopa.view_Song;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kh.sopa.controller.Client_Controller;
import com.kh.sopa.power.test.SolvingQuizTest;
import com.kh.sopa.view.SolvingQuiz;

public class Gaming_stand_room extends JPanel{
	JFrame mainFrame = null;
	JPanel thispanel;
	JPanel StandRoomPanel;
	Client_Controller client = null;
	String roomTitle = null;
	String user = "";
	public Gaming_stand_room() {}
	
	public Gaming_stand_room(JPanel StandRoomPanel,JFrame mf, String user,Client_Controller client ,int cnt, String title) {
		this.user = user;
		this.mainFrame = mf;
		this.roomTitle = title;
		this.setBackground(Color.RED);
		this.setBounds(0, 0, 1024, 430);
		this.thispanel = this;
		this.StandRoomPanel = StandRoomPanel;
		int first_number = 0;
		String GuBunJa = "/";
		int second_number = 0;
		
		String player = "0/0";
		
		
		
		// 게임 대기방
		JPanel roomPanel_stand = new JPanel();
		roomPanel_stand.setBackground(Color.RED);
//		roomPanel_stand.setBounds(0, 0, 1024, 430);


		
//		JLabel stand_label = new JLabel("게임을 기다리고 있어요");
//		stand_label.setSize(900, 200);
//		stand_label.setLocation(200, 200);
//		stand_label.setFont(new Font("맑은 고딕",Font.BOLD,40));
//		
		JLabel stand_label = new JLabel("게임을 기다리고 있어요\n" + player);
		stand_label.setSize(443, 82);
	//	stand_label.setLocation(265, 146);
		stand_label.setFont(new Font("맑은 고딕",Font.BOLD,40));
//		roomPanel_stand.setLayout(null);
		
		JLabel player_label = new JLabel("");
		player_label.setText(player);
		player_label.setBounds(460, 200, 1024, 203);
		player_label.setFont(new Font("맑은 고딕",Font.BOLD,30));
		
		
		JButton j_ready = new JButton("Ready");
		roomPanel_stand.add(j_ready);
		j_ready.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String msg = "game/ready/"+cnt;
				client.sendSystemMessage(msg);
//				mainFrame.remove(thispanel);
//				mainFrame.remove(sub_penel);
				mainFrame.remove(StandRoomPanel);
//				mainFrame.setSize(1200,800);
//				SolvingQuiz solvingquiz = new SolvingQuiz(mf,client,cnt);
				SolvingQuizTest solvingquiz = new SolvingQuizTest(mf,client,cnt, roomTitle);
				mainFrame.add(solvingquiz);
				mainFrame.repaint();
				mainFrame.setVisible(true);
			}
		});
		roomPanel_stand.add(player_label);
		roomPanel_stand.add(stand_label);
//		roomPanel_stand.setVisible(true);
		
		
		this.add(roomPanel_stand);
//		this.setBounds(0, 0, 1024, 430);
//		
//		SubPanel sp = new SubPanel(this.mainFrame, this, this.user);
//		this.add(sp);
//		this.add(roomPanel_stand);
//		this.client = sp.client;
		
//		sp.setVisible(true);
	
		
		
	}
	
//	public static void main(String[] args) {
//		JFrame f = new JFrame();
//		f.setSize(1024, 768);
//		f.setLayout(null);
//		f.add(new Gaming_stand_room(f, ""));
//		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
//		f.setVisible(true);
//	}
	
}
