package com.kh.sopa.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.kh.sopa.model.vo.User_VO;
import com.kh.sopa.view.Sever_view;

public class Sever_Controller {
	private String[] split_msg;
	private ServerSocket serverSocket;
	private Socket socket;
	private Sever_view gui;
	// 유저가 플레이할 게임방
	private ArrayList<User_VO> room1 = new ArrayList<User_VO>();
	private ArrayList<User_VO> room2 = new ArrayList<User_VO>();
	private ArrayList<User_VO> room3 = new ArrayList<User_VO>();
	private ArrayList<User_VO> room4 = new ArrayList<User_VO>();
	private ArrayList<User_VO> room5 = new ArrayList<User_VO>();
	// 유저가 플레이한 게임방의 결과모음
	private ArrayList room1_result = new ArrayList();
	private ArrayList room2_result = new ArrayList();
	private ArrayList room3_result = new ArrayList();
	private ArrayList room4_result = new ArrayList();
	private ArrayList room5_result = new ArrayList();

	private Map<String, DataOutputStream> room1Map = new HashMap<String, DataOutputStream>();
	private Map<String, DataOutputStream> room2Map = new HashMap<String, DataOutputStream>();
	private Map<String, DataOutputStream> room3Map = new HashMap<String, DataOutputStream>();
	private Map<String, DataOutputStream> room4Map = new HashMap<String, DataOutputStream>();
	private Map<String, DataOutputStream> room5Map = new HashMap<String, DataOutputStream>();

	int count_user_info = 0;
	private Map<String, DataOutputStream> clientMap = new HashMap<String, DataOutputStream>();

	public void setGui(Sever_view gui) {
		this.gui = gui;
	}

	private int cnt1 = 0;
	private int cnt2 = 0;
	private int cnt3 = 0;
	private int cnt4 = 0;
	private int cnt5 = 0;

	// 소켓에 접속하는 메소드
	public void setting() {
		try {
			Collections.synchronizedMap(clientMap);

			serverSocket = new ServerSocket(8080);

			while (true) {
				System.out.println("접속자 대기중");

				socket = serverSocket.accept();

				System.out.println(socket.getInetAddress() + "에서 접속했습니다.");

				Receiver receiver = new Receiver(socket);
				receiver.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("서버 소켓 에러");
		}
	}
//	public void game_person_sendMessage(String msg) {
//		
//	}

	public void sendMessage(String msg) {
		Iterator<String> itearator = clientMap.keySet().iterator();
		String key = "";

		while (itearator.hasNext()) {
			key = itearator.next();
			try {
				System.out.println("서버에서 클라이언트로 sendMessage " + msg);
				clientMap.get(key).writeUTF("1/" + msg + "/false");
				System.out.println("1/" + msg + "/false");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// nic은 ID ,
	public void addClient(String user_id, DataOutputStream out) throws IOException {
		String message = user_id + "님이 접속하셨습니다.\n";

		clientMap.put(user_id, out);
		sendMessage(message);
	}

	public void room_addClient(User_VO vo, DataOutputStream out, int room_number) {
		room1.add(vo);
		room1Map.put(vo.getUser_id(), out);

		for (int i = 0; i < room1.size(); i++) {
			System.out.println("getUser_id : " + room1.get(i).getUser_id() + "\n");
			System.out.println("getUser_pw : " + room1.get(i).getUser_pw() + "\n");
			System.out.println("getUser_phone_number : " + room1.get(i).getUser_phone_number() + "\n");
			System.out.println("getUser_cookie : " + room1.get(i).getUser_cookie() + "\n");
			System.out.println("getUser_1st : " + room1.get(i).getUser_1st() + "\n");
			System.out.println("getUser_2nd : " + room1.get(i).getUser_2nd() + "\n");
			System.out.println("getUser_3rd : " + room1.get(i).getUser_3rd() + "\n");
			System.out.println("getUser_all_quiz : " + room1.get(i).getUser_all_quiz() + "\n");
			System.out.println("getUser_correct_quiz : " + room1.get(i).getUser_correct_quiz() + "\n");
			System.out.println("getUser_gaming_cookie : " + room1.get(i).getUser_gaming_cookie() + "\n");
			System.out.println("getUser_gaming_correct_quiz : " + room1.get(i).getUser_gaming_correct_quiz() + "\n");
			System.out.println("getUser_gaming_time : " + room1.get(i).getUser_gaming_time() + "\n");
		}

		Iterator<String> iterator_room1 = room1Map.keySet().iterator();
		String key_room1 = "";
		String msg_room = "1/방에 입장하셨습니다./false";
		while (iterator_room1.hasNext()) {
			key_room1 = iterator_room1.next();
			try {
				System.out.println("유저 입장 완료");
				clientMap.get(key_room1).writeUTF(msg_room);
				System.out.println(clientMap.get(key_room1));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 받는 스레드
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
				vo.setUser_gaming_cookie(in.readInt());
				vo.setUser_gaming_correct_quiz(in.readInt());
				vo.setUser_gaming_time(in.readLong());
				System.out.println(
						vo.getUser_id() + " " + vo.getUser_pw() + " " + vo.getUser_all_quiz() + " 새로 생된 객체의 정보");

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
					String[] msg_split = msg.split("/");
					int head = Integer.valueOf(msg_split[0]);

					if (head == 0) {
						System.out.println("서버에서 시스템 메시지를 받음");
						switch (msg_split[1]) {

						case "logout":
							break;
						case "room_inter":
							room_addClient(vo, out, Integer.valueOf(msg_split[2]));
							break;
						case "game":
							if (msg_split[2].equals("ready")) {
								System.out.println("사용자가 게임 레디버튼을 눌렀습니다.");
								System.out.println("방번호 : " + msg_split[3]);
								room_number(Integer.valueOf(msg_split[3]));
							}

						}
					} else if (head == 1) {
						System.out.println("클라이언트로부터 메시지를 받음 : " + msg);
						sendMessage(msg_split[1]);

					} else if (head == 2) {
						System.out.println("클라이언트로부터 게임결과를 받음 : " + msg);
						count_user_info++;
						// ----2/getUser_all_quiz/getUser_correct_quiz/setUser_gaming_cookie/setUser_gaming_correct_quiz
						System.out.println("게임중 얻은 쿠키 : " + msg_split[1] + " 맞은 퀴즈: " + msg_split[2] + " 모든문제를 푼 시간 : "
								+ msg_split[3] + " 문제 개수 : " + msg_split[4] + " 방번호 : " + msg_split[5] + " 해당 유저 ID "
								+ msg_split[6]);

						switch (Integer.valueOf(msg_split[5])) {
						case 1:
							setUserInformation(room1, Integer.valueOf(msg_split[1]), Long.valueOf(msg_split[3]),
									Integer.valueOf(msg_split[2]), msg_split[6]);
							System.out.println("정렬전 ");
							if (count_user_info == room1.size()) {
								Game_reuslt_sort(room1);

								System.out.println("정렬후 ");
								result_send_msg(room1,out);
							} else {
								System.out.println("모두가 완료하지 못했다.");
							}
							break;
						case 2:
							setUserInformation(room2, Integer.valueOf(msg_split[1]), Long.valueOf(msg_split[3]),
									Integer.valueOf(msg_split[2]), msg_split[6]);
							break;
						case 3:
							setUserInformation(room3, Integer.valueOf(msg_split[1]), Long.valueOf(msg_split[3]),
									Integer.valueOf(msg_split[2]), msg_split[6]);
							break;
						case 4:
							setUserInformation(room4, Integer.valueOf(msg_split[1]), Long.valueOf(msg_split[3]),
									Integer.valueOf(msg_split[2]), msg_split[6]);
							break;
						case 5:
							setUserInformation(room5, Integer.valueOf(msg_split[1]), Long.valueOf(msg_split[3]),
									Integer.valueOf(msg_split[2]), msg_split[6]);
						}
						// 유저게임 결과 배열
//						String array[] = new String[4];
//						
//						array[0] = msg_split[1];
//						array[1] = msg_split[2];
//						array[2] = msg_split[3];
//						array[3] = msg_split[4];
//						
//						room1_result.add(array);
//						
					}

				}
			} catch (Exception e) {
				removeClient(user_id);
			}
		}
	}

	public void result_send_msg(ArrayList<User_VO> room,DataOutputStream out) {

		for (int i = 0; i < room.size(); i++) {
			switch (room.size()) {
			case 1:
				int size_1 =  room.size();
				String first_user = room.get(0).getUser_id();
				String all_result = "3/"+ size_1+"/" + first_user ;

				System.out.println(all_result);
				try {
					out.writeUTF(all_result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				int size_2 =  room.size();
				String first_user_2 = room.get(0).getUser_id();
				String second_user_2 = room.get(1).getUser_id();
				String all_result_2 = "3/"+ size_2+"/"  + first_user_2 + "/" + second_user_2;

				System.out.println(all_result_2);
				try {
					out.writeUTF(all_result_2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				int size_3 =  room.size();
				String first_user_3 = room.get(0).getUser_id();
				String second_user_3 = room.get(1).getUser_id();
				String third_user_3 = room.get(2).getUser_id();
				String all_result_3 = "3/"+ size_3+"/"  + first_user_3 + "/" + second_user_3 + "/" + third_user_3 ;

				System.out.println(all_result_3);
				try {
					out.writeUTF(all_result_3);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				int size_4 =  room.size();
				String first_user_4 = room.get(0).getUser_id();
				String second_user_4 = room.get(1).getUser_id();
				String third_user_4 = room.get(2).getUser_id();
				String fourth_user_4 = room.get(3).getUser_id();
				String all_result_4 = "3/"+ size_4+"/"  + first_user_4 + "/" + second_user_4 + "/" + third_user_4 + "/"
						+ fourth_user_4;

				System.out.println(all_result_4);
				try {
					out.writeUTF(all_result_4);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:
				int size_5 =  room.size();
				String first_user_5 = room.get(0).getUser_id();
				String second_user_5 = room.get(1).getUser_id();
				String third_user_5 = room.get(2).getUser_id();
				String fourth_user_5 = room.get(3).getUser_id();
				String fifth_user_5 = room.get(4).getUser_id();
				String all_result_5 = "3/"+ size_5+"/"  + first_user_5 + "/" + second_user_5 + "/" + third_user_5 + "/"
						+ fourth_user_5 + "/" + fifth_user_5;

				System.out.println(all_result_5);
				try {
					out.writeUTF(all_result_5);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	// 방이 생성될때 array_size가 정해저야한다.
	public void room_number(int roomNumber) {

		System.out.println("방인원수 세기 ");
		int room1_ready_size = room1.size();
		int room2_ready_size = room2.size();
		int room3_ready_size = room3.size();
		int room4_ready_size = room4.size();
		int room5_ready_size = room5.size();

		switch (roomNumber) {
		case 1:
			cnt1++;
			if (cnt1 == room1_ready_size) {
				System.out.println("현재 1번방에 들어온 인원수 : " + cnt1);
				System.out.println("최대 인원수 : " + room1_ready_size);
				String msg = "최대 인원이 레디를 눌러서 게임이 시작합니다./true";
				sendMessage(msg);
				System.out.println("어디서 팅긴거야");
				// SolvingQuiz 뷰로 이동 근데 문제에 대한 정보를 게임시작할때 보내주어야함
			}
			break;
		case 2:
			cnt2++;
			if (cnt2 == room2_ready_size) {
				System.out.println("현재 2번방에 들어온 인원수 : " + cnt2);

				// SolvingQuiz 뷰로 이동 근데 문제에 대한 정보를 게임시작할때 보내주어야함
			}
			break;
		case 3:
			cnt3++;
			if (cnt3 == room3_ready_size) {
				System.out.println("현재 3번방에 들어온 인원수 : " + cnt3);
				// SolvingQuiz 뷰로 이동 근데 문제에 대한 정보를 게임시작할때 보내주어야함
			}
			break;
		case 4:
			cnt4++;
			if (cnt4 == room4_ready_size) {
				System.out.println("현재 4번방에 들어온 인원수 : " + cnt4);
				// SolvingQuiz 뷰로 이동 근데 문제에 대한 정보를 게임시작할때 보내주어야함
			}
			break;
		case 5:

			cnt5++;
			if (cnt5 == room5_ready_size) {
				System.out.println("현재 5번방에 들어온 인원수 : " + cnt5);
				// SolvingQuiz 뷰로 이동 근데 문제에 대한 정보를 게임시작할때 보내주어야함
			}
			break;

		}
	}

	// 클라이언트 로그아웃
	public void removeClient(String user_id) {
		String message = "1/" + user_id + "님이 나가셨습니다. \n";
		sendMessage(message);
		clientMap.remove(user_id);
		System.out.println("접속자수 : " + clientMap.size());
	}

	// 해당하는 유저 정보를 방에 있는 유저 VO에 게임결과를 삽입하는 메소드
	public void setUserInformation(ArrayList<User_VO> room, int cookie, Long time, int correct_quiz, String user_id) {
		for (int i = 0; i < room.size(); i++) {
			if (room.get(i).getUser_id().equals(user_id)) {
				room.get(i).setUser_gaming_cookie(cookie);
				room.get(i).setUser_gaming_correct_quiz(correct_quiz);
				room.get(i).setUser_gaming_time(time);
			}
		}
	}
	// 유저 정리하는것

	public void Game_reuslt_sort(ArrayList<User_VO> vo) {
		vo.sort(new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				User_VO tmp1 = (User_VO) o1;
				User_VO tmp2 = (User_VO) o2;
				int res = 0;
				if (tmp1.getUser_correct_quiz() == tmp2.getUser_correct_quiz())
					if (tmp1.getUser_gaming_time() < tmp2.getUser_gaming_time()) {
						res = 1;
					} else {
						res = -1;
					}

				if (tmp1.getUser_correct_quiz() < tmp2.getUser_correct_quiz()) {
					res = 1;
				}
				if (tmp1.getUser_correct_quiz() > tmp2.getUser_correct_quiz()) {
					res = -1;
				}
				return res;
			}

		});
	}
}
