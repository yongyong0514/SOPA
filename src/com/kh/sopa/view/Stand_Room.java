//package com.kh.sopa.view;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//
//import com.kh.sopa.controller.Client_Controller;
//import com.kh.sopa.model.vo.User_VO;
//
//public class Stand_Room extends JFrame implements ActionListener{
//
//	private JPanel contentPane;
//	private JTextArea jta = new JTextArea(14, 25);
//	private JTextField jtf = new JTextField(25);
//	private Client_Controller client = new  Client_Controller();
//	private User_VO vo;
//	private JLabel user_label = new JLabel(); 
//	private String user_id;
//	
//	//panel_stand_room -> 전체 패널
//	//panel_south_menu -> 밑에 패널 회색
//	//panel_south_menu_chat ->  채팅패널(TextArea 랑 TextFiel랑 두개만 add되어있어서 색은 보이지 않습니다.)
//	//panel_north_userhi -> 유저가 접속하면 인사가 보이는 라벨
//	public Stand_Room(User_VO vo) {
//		this.user_id= vo.getUser_id();
//		
//
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1024, 768);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
//
//		
//		setTitle("대기방");
//		JPanel panel_stand_room = new JPanel();
//		panel_stand_room.setLayout(null);
//		panel_stand_room.setBounds(0, 0, 1024, 768);
//		panel_stand_room.setBackground(Color.white);
//
//		user_label.setFont(new Font("고딕체", Font.PLAIN, 30));
//		user_label.setBackground(Color.YELLOW);
//		user_label.setBounds(0, 0, 1024, 100);
//		
//		panel_stand_room.add(user_label,BorderLayout.NORTH);
//		
//		JPanel panel_south_menu = new JPanel();			//諛묒뿉 硫붾돱 �뙣�꼸
//		
//		panel_south_menu.setBackground(Color.gray);
//		panel_south_menu.setBounds(96, 261, 800, 400);
//		panel_south_menu.setLocation(100, 400);
//		panel_south_menu.setLayout(null);
//		
//		getContentPane().add(panel_stand_room);
//		panel_stand_room.add(panel_south_menu);
//		
//		
//		JPanel panel_south_menu_chat = new JPanel();
//		panel_south_menu_chat.setBounds(0, 0, 337, 340);
//		panel_south_menu.add(panel_south_menu_chat);
//		panel_south_menu_chat.setLayout(null);
//		jta.setBounds(14, 12, 304, 284);
//		panel_south_menu_chat.add(jta);
//		
//		jta.setEditable(false);
//		jta.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
//		jta.setBackground(new Color(230, 255, 230));
//		jtf.setBounds(14, 293, 304, 24);
//		panel_south_menu_chat.add(jtf);
//		jtf.addActionListener(this);
//		
//		
//		this.setResizable(false);
//		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		// client_Controller -> client
//		client.setGui(this);
//		client.setNicknames(user_id);	
//		//cennect에서  실제로 소켓연결하는 메소드
//		client.connect(vo);
//	}
//	
//	// 텍스트 필드에서 엔터를 누르는 이벤트
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		System.out.println("액션 리스스너 실행.");
//		String msg = user_id + ": " + jtf.getText() +"\n";
//		System.out.println(msg);
//		//sendMessage -> 서버에 전송하는 메소드
//		client.sendMessage(msg);
//		
//		jtf.setText("");
//	}
//
//
//	//텍스트 에리어에 추가하는 메소드 
//	public void appendMsg(String msg) {
//		jta.append(msg);
//		
//	}
//	//유저의 라벨을 바꾸는 메소드
//	public void label_userid(String user_id) {
//		user_label.setText(user_id+"님 환영합니다..!!!");
//	}
//
//}
