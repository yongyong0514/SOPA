package com.kh.sopa.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.kh.sopa.model.vo.User_VO;
import com.kh.sopa.test.SubPanel;

public class Client_Controller {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private SubPanel gui;
	private String msg;
	private String client_id;
	private String[] split_msg;
	private boolean ishead_hear =true;
	private class Clth extends Thread {
		
		public void run() {
			while (in != null) {
				try {
					System.out.println("클라이언트의 스레드가 작동을 시작했습니다.");
					msg = in.readUTF();
					split_msg = msg.split("/");
					int head = Integer.valueOf(split_msg[0]);
					switch(Integer.valueOf(split_msg[0])) {
						case 0:
							break;
						case 1:							
							System.out.println("클라이언트에서 받아 : msg " + split_msg[1]);
							gui.appendMsg(split_msg[1]);
							break;
						case 2:
							break;
						case 3:
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
			out.writeUTF("1/" + user_id);
			System.out.println("일반 채팅 메시지 서버로 전송 : " +msg);
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
}
