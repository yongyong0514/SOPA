package com.kh.sopa.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.kh.sopa.model.vo.User_VO;
import com.kh.sopa.view.Sever_view;

public class Sever_Controller {

	private ServerSocket serverSocket;
	private Socket socket;
	private Sever_view gui;
	
	private Map<String, DataOutputStream> clientMap = new HashMap<String, DataOutputStream>() ;
	
	public void setGui(Sever_view gui) {
		this.gui = gui;
	}
	
	// 소켓에 접속하는 메소드
	public void setting() {
		try {
			Collections.synchronizedMap(clientMap);
			
			serverSocket = new ServerSocket(8080);
			
			while(true) {
				System.out.println("접속자 대기중");
				
				socket = serverSocket.accept();
				
				System.out.println(socket.getInetAddress()+ "에서 접속했습니다.");
				
				Receiver receiver = new Receiver(socket);
				receiver.start();
			}
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("서버 소켓 에러");
		}
	}
	
	public void sendMessage(String msg) {
		Iterator<String> itearator = clientMap.keySet().iterator();
		String key = "";
		
		
		while (itearator.hasNext()) {
			key = itearator.next();
			try {
				System.out.println("서버에서 클라이언트로 sendMessage " + msg);
				clientMap.get(key).writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	// nic은 ID ,
	public void addClient(String user_id, DataOutputStream out) throws IOException{
		String message = user_id + "님이 접속하셨습니다.\n";
		
		clientMap.put(user_id, out);
		sendMessage(message);
	}
	
	
	class Receiver extends Thread {
		User_VO vo = new User_VO();
		private DataInputStream in;
		private DataOutputStream out;
		private String user_id;
		// DataOutputStream을 사용해서 객체의 순서가 중요합니다.
		// vo객체에 User_VO의 정보를 모두 담았다.
		public Receiver(Socket socket) {
			try {
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
				user_id = in.readUTF();
				vo.setUser_id(user_id);
				vo.setUser_pw(in.readUTF());
				vo.setUser_phone_number(in.readUTF());
				vo.setUser_cookie(in.readInt());
				vo.setUser_1st(in.readInt());
				vo.setUser_2nd(in.readInt());
				vo.setUser_3rd(in.readInt());
				vo.setUser_all_quiz(in.readInt());
				vo.setUser_correct_quiz(in.readInt());
				System.out.println(vo.getUser_id() + " " + vo.getUser_pw() + " " 
				+ vo.getUser_all_quiz() + " 새로 생된 객체의 정보");
				
				System.out.println("Receiver : " + user_id);
				addClient(user_id, out);
				System.out.println("접속자수 : " + clientMap.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// 클라이언트에서 받은 정보를 다른 클라이언트에 뿌려주는 메소드
		
		@Override
		public void run() {
			String msg = "";
			try {
				while (in != null) {
					msg = in.readUTF();
					String[] msg_parse = msg.split("/");
					
					System.out.println("run : "+msg_parse[0]);
					System.out.println("run : "+msg_parse[1]);
					if ("1".equals(msg_parse[0])) {
						sendMessage(msg_parse[1]);
					}
					else {
						switch(msg_parse[1]) {
						case "logout":
							removeClient(msg_parse[2]);
							break;
						}
					}
				}
			} catch (Exception e) {
				removeClient(user_id);
			}
		}
	}
	
	public void removeClient(String user_id) {
		String message= user_id + "님이 나가셨습니다. \n";
		sendMessage(message);
		clientMap.remove(user_id);
		System.out.println("접속자수 : " + clientMap.size());
	}
	
}
