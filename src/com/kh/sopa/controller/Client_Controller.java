package com.kh.sopa.controller;

import java.awt.Container;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.kh.sopa.model.vo.User_VO;
import com.kh.sopa.test.SubPanel;
import com.kh.sopa.view.ResultPage;
import com.kh.sopa.view.SolvingQuiz;
import com.kh.sopa.view_Song.Gaming_stand_room;


public class Client_Controller {
	private String room_title;
	private Client_Controller client;
	private int room_number;
	private JFrame main_frame;
	private JPanel StandRoomPanel;
	private JPanel solving;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private SubPanel gui;
	private String msg;
	private String client_id;
	private String[] split_msg;
	private boolean game_stable = false;
	private boolean ishead_hear = true;

	private class Clth extends Thread {

		public void run() {
			while (in != null) {
				try {
					System.out.println("클라이언트의 스레드가 작동을 시작했습니다.");
					msg = in.readUTF();
					split_msg = msg.split("/");
					int head = Integer.valueOf(split_msg[0]);
					switch (Integer.valueOf(split_msg[0])) {
					case 0:
						System.out.println("서버로 부터의 게임인원수 꽉참");

						break;
					case 1:
						System.out.println("클라이언트에서 받아 : msg " + split_msg[1]);
						if (split_msg[2].equals("true")) {
							stable_chage();
							
						} else {

						}
						gui.appendMsg(split_msg[1]);
						break;
					case 2:
						break;
					case 3:
						switch(Integer.valueOf(split_msg[1])) {
						case 1:
							String[] result_1 = new String[5];
							result_1[0] = split_msg[2];
							result_1[1] = "";
							result_1[2] = "";
							result_1[3] = "";
							result_1[4] = "";
							change_resultpage(main_frame, result_1);
							break;
						case 2:
							String[] result_2 = new String[5];
							result_2[0] = split_msg[2];
							result_2[1] = split_msg[3];
							result_2[2] = "";
							result_2[3] = "";
							result_2[4] = "";
							change_resultpage(main_frame, result_2);
							break;
						case 3:
							String[] result_3 = new String[5];
							result_3[0] = split_msg[2];
							result_3[1] = split_msg[3];
							result_3[2] = split_msg[4];
							result_3[3] = "";
							result_3[4] = "";
							change_resultpage(main_frame, result_3);
							break;
						case 4:
							String[] result_4 = new String[5];
							result_4[0] = split_msg[2];
							result_4[1] = split_msg[3];
							result_4[2] = split_msg[4];
							result_4[3] = split_msg[5];
							result_4[4] = "";
							change_resultpage(main_frame, result_4);
							break;
						case 5:
							String[] result_5 = new String[5];
							result_5[0] = split_msg[2];
							result_5[1] = split_msg[3];
							result_5[2] = split_msg[4];
							result_5[3] = split_msg[5];
							result_5[4] = split_msg[6];
							change_resultpage(main_frame, result_5);
							break;
						}
						ishead_hear = false;
						break;
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	public void solvingquiz_remove_info(JPanel solving) {
		this.solving = solving;
	}
	public void solvingquiz_frame_info(JFrame main,String title) {
		this.main_frame = main;
		this.room_title = title;
	}
	
	public void change_resultpage(JFrame mainframe, String[] result) {
		main_frame.remove(solving);
		ResultPage resultpage = new ResultPage(mainframe, result);
		main_frame.add(resultpage);
		main_frame.repaint();
		main_frame.setVisible(true);
	}

	public boolean get_ishead_hear() {
		return ishead_hear;
	}

	public String[] get_split_result() {
		return split_msg;
	}

	public void setGui(SubPanel subPanel) {
		this.gui = subPanel;
	}

	// Client_id = user_id
	public void connect(User_VO vo) {
		String ip = "192.168.219.107";

		int port = 8080;
		try {
			socket = new Socket(ip, port);
			System.out.println("서버에 연결  ");

			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());

			out.writeUTF(vo.getUser_id());
			out.writeUTF(vo.getUser_pw());
			out.writeUTF(vo.getUser_phone_number());
			out.writeInt(vo.getUser_cookie());
			out.writeInt(vo.getUser_1st());
			out.writeInt(vo.getUser_2nd());
			out.writeInt(vo.getUser_3rd());
			out.writeInt(vo.getUser_all_quiz());
			out.writeInt(vo.getUser_correct_quiz());
			out.writeInt(vo.getUser_gaming_cookie());
			out.writeInt(vo.getUser_gaming_correct_quiz());
			out.writeDouble(vo.getUser_gaming_time());
			System.out.println("Client : ID 전송 완료");
//			gui.label_userid(vo.getUser_id());

			// thread start
			new Clth().start();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("서버에 연결할 수 없습니다.");
		}
	}

	public void sendMessage(String user_id) {
		try {
			out.writeUTF("1/" + user_id + "/false");
			System.out.println("일반 채팅 메시지 서버로 전송 : " + msg);
//			out.flush();
		} catch (IOException e) {
			System.out.println("메시지 전송 오류");
			e.printStackTrace();
		}
	}

	public void result_game_sendMessage(String msg) {
		try {
			out.writeUTF("2/" + msg);
			System.out.println("게임결과 메시지 서버로 전송 : " + msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("게임 결과 전송 오류");
		}
	}

	public void sendSystemMessage(String msg) {
		try {
			out.writeUTF("0/" + msg);
			System.out.println("시스템 메시지 서버로 전송 : " + msg);
//			out.flush();
		} catch (IOException e) {
			System.out.println("메시지 전송 오류");
			e.printStackTrace();
		}
	}

	// 닉네임 setter
	public void setNicknames(String client_id) {
		this.client_id = client_id;
	}

	public String getNicknames() {
		return client_id;
	}

	public void gaming_stable(JPanel StandRoomPanel, JFrame mainFrame , Client_Controller client, int room_number,String room_title) {
		this.StandRoomPanel = StandRoomPanel;
		this.main_frame = mainFrame;
		this.client = client;
		this.room_number = room_number;
		this.room_title = room_title;
	}
	
	public void stable_chage() {
		main_frame.remove(StandRoomPanel);
		SolvingQuiz solvingquiz = new SolvingQuiz(main_frame,client,room_number,room_title);
		main_frame.add(solvingquiz);
		main_frame.repaint();
		main_frame.setVisible(true);
	}

}
