package com.kh.sopa.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.kh.sopa.controller.Client_Controller;
import com.kh.sopa.controller.ObjectIO;
import com.kh.sopa.model.vo.User_VO;
import com.kh.sopa.view.Login_Panel;

public class SubPanel extends JPanel {
	JFrame mainFrame = null;
	JPanel mainPanel = null;
	JPanel thisPage = null;
	
	JTextArea chatArea = null;
	JTextField messageArea = null;
	// socket, system message
	Client_Controller client = new Client_Controller();
	
	
	
	
	
	public SubPanel() {}
	// user in constructor parameter
	// reason --> user panel : show user info
	public SubPanel(JFrame mf, JPanel mp, String user) {
		this.mainFrame = mf;
		this.mainPanel = mp;
		this.thisPage = this;
		
		this.setBackground(Color.ORANGE);
		this.setBounds(0, 430, 1024, 338);
		this.setLayout(null);
		
		
		JPanel userPanel = new JPanel();
		userPanel.setBackground(Color.GREEN);
		userPanel.setBounds(724, 0, 300, 338);
		userPanel.setLayout(null);
		
		JPanel userIdPanel = new JPanel();
		JLabel userIdLabel = new JLabel();
		userIdPanel.setBackground(Color.RED);
		userIdPanel.add(userIdLabel);
		
		userPanel.add(userIdPanel);
		userIdPanel.setBounds(0, 50, 150, 30);
		
		
		JPanel userCookiePanel = new JPanel();
		JLabel userCookieLabel = new JLabel();
		userCookiePanel.setBackground(Color.BLUE);
		userCookiePanel.add(userCookieLabel);
		
		userPanel.add(userCookiePanel);
		userCookiePanel.setBounds(0, 100, 150, 30);
		
		JPanel userEmoPanel = new JPanel();
		userEmoPanel.setBackground(Color.DARK_GRAY);
		
		userPanel.add(userEmoPanel);
		userEmoPanel.setBounds(150, 0, 150, 130);
		
		ArrayList<User_VO> userList = new ObjectIO().UserReadToFile();
	
		User_VO tmp = new User_VO();
		
		// user info copy to tmp;
		for (int i = 0; i < userList.size(); i++) {
			if(user.equals(userList.get(i).getUser_id())) {
				tmp.setUser_id(userList.get(i).getUser_id());
				tmp.setUser_pw(userList.get(i).getUser_pw());
				tmp.setUser_phone_number(userList.get(i).getUser_phone_number());
				tmp.setUser_cookie(userList.get(i).getUser_cookie());
				tmp.setUser_1st(userList.get(i).getUser_1st());
				tmp.setUser_2nd(userList.get(i).getUser_1st());
				tmp.setUser_3rd(userList.get(i).getUser_1st());
				tmp.setUser_all_quiz(userList.get(i).getUser_all_quiz());
				tmp.setUser_correct_quiz(userList.get(i).getUser_correct_quiz());
			}
		}
		
		userIdLabel.setText(tmp.getUser_id());
		userCookieLabel.setText(tmp.getUser_cookie() + "");
		
		// menuPanel
		JButton myInfo = new JButton();
		myInfo.setText("내 정보를 봐요");
		myInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("label : " + userIdLabel.getText());
				System.out.println("cookie : " + userCookieLabel.getText());
				System.out.println(((JButton) e.getSource()).getText());
			}
		});
		userPanel.add(myInfo);
		myInfo.setBounds(0, 150, 150, 40);
		
		JButton logOut = new JButton();
		logOut.setText("로그아웃 해요");
		logOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃", 
							JOptionPane.YES_NO_CANCEL_OPTION);
				if (resp == 0) {
					client.sendSystemMessage("logout/" + tmp.getUser_id());
					JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.");
					mainFrame.remove(mainPanel);
					mainFrame.remove(thisPage);
					mainFrame.add(new Login_Panel(mainFrame));
					mainFrame.repaint();
//					mainFrame.setVisible(true);
				}				
			}
		});
		userPanel.add(logOut);
		logOut.setBounds(0, 200, 150, 40);
		
		JButton makeRoom = new JButton();
		makeRoom.setText("방을 만들어요");
		makeRoom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// change MakeRoom Panel;
				System.out.println(((JButton) e.getSource()).getText());
			}
		});
		userPanel.add(makeRoom);
		makeRoom.setBounds(150, 150, 150, 40);
		
		JButton quit = new JButton();
		quit.setText("종료해요");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// frame close;
				int resp = JOptionPane.showConfirmDialog(null,  "종료 하시겠습니까?", "종료", 
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (resp == 0) {
					client.sendSystemMessage("exit/" + tmp.getUser_id());
					JOptionPane.showMessageDialog(null, "종료합니다.");
					mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
		userPanel.add(quit);
		quit.setBounds(150, 200, 150, 40);
		
		this.add(userPanel);
		
		// chatting
		chatArea = new JTextArea(50, 50);
		this.add(chatArea);
		chatArea.setEditable(false);
		chatArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		chatArea.setBounds(270, 0, 380, 250);
		
		messageArea = new JTextField();
		this.add(messageArea);
		messageArea.setBounds(265, 250, 390, 50);
		messageArea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = tmp.getUser_id() + ": " + messageArea.getText() + "\n";
				System.out.println(msg);
				// sendMessage -> 서버에 전송
				client.sendMessage(msg);
				
				messageArea.setText("");
			}
		});
		
		client.setGui(this);
		client.setNicknames(tmp.getUser_id());
		client.connect(tmp);
		
		// friend list
		JPanel friendList = new JPanel();
		JLabel tmpLabel = new JLabel("friend list(tmp)");
		friendList.add(tmpLabel);
		friendList.setBackground(Color.GRAY);
		this.add(friendList);
		friendList.setBounds(50, 0, 180, 338);
	}
	
	public void appendMsg(String msg) {
		chatArea.append(msg);
	}
	
	public void label_userid(String user) {
		// 서버 나중
	}
}
