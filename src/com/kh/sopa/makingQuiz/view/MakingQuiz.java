package com.kh.sopa.makingQuiz.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kh.sopa.makingQuiz.model.controller.MakingQuizManager;
import com.kh.sopa.makingQuiz.model.dao.Quiz_DAO;
import com.kh.sopa.model.vo.Quiz_VO;

public class MakingQuiz extends JFrame implements ActionListener, ItemListener {
	MakingQuizManager mqm = new MakingQuizManager();
	Quiz_DAO qd = new Quiz_DAO();
	Quiz_VO qv = new Quiz_VO();
	
	private JPanel	mqPanel;
	private JPanel	aPanel_1;
	private JPanel	aPanel_2;
	private JPanel	aPanel_3;
	private JPanel	aPanel_4;
	
	private JTextField set_info;
	private JTextField title;
	private JTextField subject;
	private JTextField answer_1;
	private JTextField answer_2;
	private JTextField answer_3;
	private JTextField answer_4;
	private String final_answer;
	private int difficulty_result;
	private JTextField cookie;
	private JTextField image;
	private JTextField people;

	
	
	private JRadioButton ansRadio_1;
	private JRadioButton ansRadio_2;
	private JRadioButton ansRadio_3;
	private JRadioButton ansRadio_4;
	private JRadioButton ansRadio_5; //ansRadio_5는 선택 해제용
	
//	2차 스위치
//	private ToggleSwitch ansSwitch_1;
//	private ToggleSwitch ansSwitch_2;
//	private ToggleSwitch ansSwitch_3;
//	private ToggleSwitch ansSwitch_4;
	
	private JRadioButton level_1;
	private JRadioButton level_2;
	private JRadioButton level_3;
	private JRadioButton level_4; //level_4는 선택 해제용
	
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	
//	ArrayList<String> aResult = new ArrayList<>();
//	private String [] answerResult = new String [4];

	private JTable listQuiz;
	private JScrollPane scrollList;

	
	public String streamQList() {
		Quiz_DAO qd = new Quiz_DAO();
		ArrayList<Quiz_VO> list = qd.readQuizList();
		String qlist = null;
		
		for(int i = 0; i < list.size(); i++) {
			qlist += list.get(i).getQuiz_title();
		}
		return qlist;
	}
	

	public MakingQuiz() {
//		Quiz_VO q = new Quiz_VO();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		mqPanel = new JPanel();
		mqPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mqPanel.setLayout(null);
		setContentPane(mqPanel);		
		
//		전체 리스트 패널, 세트리스트와 문제리스트가 위치합니다
		JPanel lPanel = new JPanel();
		lPanel.setBounds(7, 60, 340, 440);
		lPanel.setLayout(null);
		
//		리스트, 세트를 만들면 등록되는 리스트입니다
		JList lSet = new JList();
		lSet.setBounds(7, 7, 160, 340);
		lSet.setBackground(Color.LIGHT_GRAY);
		lSet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
//		리스트, 문제를 만들면 등록되는 리스트입니다
//		JList listQuiz = new JList(); //qad.readQuizList().toArray()
//		listQuiz.setBounds(170, 7, 160, 340);
//		listQuiz.setBackground(Color.LIGHT_GRAY);
//		listQuiz.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);	

		
		
//		문제 리스트에 담을 dat에서 읽어온 내용 중,
//		문제 제목과 난이도를 table값으로 가져와서
//		JTable에 값을 출력함
		
		
////	문제 리스트 패널, 문제 리스트가 올라옵니다
		JPanel qListPanel = new JPanel();
		qListPanel.setBounds(170, 7, 160, 340);
		qListPanel.setBackground(Color.GRAY);
//		lPanel.add(qListPanel);
		
		
		
//		문제리스트
		String [] atitle = {"추가한 문제"};
	    String alist = null;
	    String adif = null;
	    
	    ArrayList<Quiz_VO> list = qd.readQuizList();
	    
	    for(int i = 0; i < list.size(); i++) {
	    	alist += (list.get(i).getQuiz_title() + "#");
	    	adif += (list.get(i).getQuiz_difficulty() + "#");
	    }	
	    
	    String[] quiz_t = alist.split("#");
	    String[] quiz_d = adif.split("#");
//	    
//	    
//	    for(int i = 0; i < list.size(); i++) {
//	    	quiz_t = 
//	    }
	    //1차원 배열 두개로 차원배열 두개
	 
//		DefaultTableModel model =  new DefaultTableModel(, atitle);
		
//		JTable tableList = new JTable(model);
//		
//		scrollList = new JScrollPane(tableList);
//		scrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollList.setBounds(170, 7, 160, 340);
//		
//		lPanel.add(scrollList);


//		ArrayList<Quiz_VO> list = qd.readQuizList();
//		String quizTitle;
//		String quizDiffi;
//		Object [][] TD = list.add()
//		String [] col = {"문제","난이도"};
//		
//		for(int i = 0; i < list.size(); i++) {
//			quizTitle += list.get(i).getQuiz_title();
//			quizDiffi += list.get(i).getQuiz_difficulty();
//		}
//
////
//		model = new DefaultTableModel(quizTitle, col);
//		listQuiz = new JTable(model);
//		listQuiz.addMouseListener();
//		listQuiz.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//		scrollList = new JScrollPane(listQuiz);
//		
//		quizList.add(listQuiz);
//		listQuiz.add(scrollList);
//
//	
		
		
		
		

		
//		버튼, 추가, 문제 리스트에 작성된 문제를 추가합니다
		button_3 = new JButton("\uB9AC\uC2A4\uD2B8\uC5D0 \uCD94\uAC00!");
		button_3.setFont(new Font("굴림", Font.PLAIN, 18));
		button_3.setBounds(170, 350, 160, 40);
		button_3.addActionListener(this);
		
//		버튼, 삭제, 문제 리스트에서 선택한 문제를 삭제합니다
		button_4 = new JButton("\uC0AD\uC81C");
		button_4.setFont(new Font("굴림", Font.PLAIN, 18));
		button_4.setBounds(250, 395, 80, 40);
		
//		버튼, 수정, 문제 리스트에서 선택한 문제를 수정합니다
		button_5 = new JButton("\uC218\uC815");
		button_5.setFont(new Font("굴림", Font.PLAIN, 18));
		button_5.setBounds(170, 395, 80, 40);
		
//		버튼, 세트로 만듬, 문제 리스트의 문제들을 세트로 만듭니다
		button_2 = new JButton("\uC138\uD2B8\uB85C \uCD94\uAC00!");
		button_2.setFont(new Font("굴림", Font.PLAIN, 18));
		button_2.setBounds(7, 351, 160, 40);
		button_2.addActionListener(this);
		

		
		
//		문제 패널, 문제를 만드는 패널 입니다
		JPanel qPanel = new JPanel();
		qPanel.setBounds(354, 60, 658, 440);
		qPanel.setLayout(null);
		
//		버튼, 방을 만듬, 세트를 선택한 상태에서 버튼을 누릅니다
		button_1 = new JButton("\uBC29\uC744 \uB9CC\uB4E4\uC5B4\uC694");
		button_1.setFont(new Font("굴림", Font.PLAIN, 18));
		button_1.setBounds(498, 6, 153, 78);

		
//		텍스트 필드, 1세트 세트 제목을 입력받습니다
		set_info = new JTextField();
		set_info.setBounds(70, 7, 420, 35);
		set_info.setColumns(10);
		
//		텍스트, 문제 패널에 위치하고, 방제목이라고 적혀있습니다
		JLabel qTitleSet = new JLabel("\uBC29\uC81C\uBAA9");
		qTitleSet.setFont(new Font("굴림", Font.PLAIN, 18));
		qTitleSet.setBounds(7, 7, 58, 30);

		
//		텍스트 필드, 1문제 문제를 입력받습니다
		title = new JTextField();
		title.setBounds(70, 49, 420, 35);
		title.setColumns(10);
		
//		텍스트, 문제 패널에 위치하고, 문제라고 적혀있습니다
		JLabel qTitle = new JLabel("\uBB38\uC81C");
		qTitle.setFont(new Font("굴림", Font.PLAIN, 18));
		qTitle.setBounds(7, 49, 43, 30);

		
//		문제 패널의 주제 패널, 주제를 입력합니다
		JPanel sPanel = new JPanel();
		sPanel.setBounds(498, 90, 153, 78);
		sPanel.setLayout(null);
		
//		타이틀, 주제라고 적혀있습니다
		JLabel sTitle = new JLabel("\uC8FC\uC81C");
		sTitle.setBounds(60, 0, 43, 30);
		sTitle.setFont(new Font("굴림", Font.PLAIN, 18));
		
//		텍스트필드, 1세트 주제를 입력받는 텍스트 필드 입니다
		subject = new JTextField();
		subject.setBounds(7, 35, 139, 35);
		subject.setColumns(10);

		
		
		
//		문제 패널의 참여 인원 패널, 참여인원 패널입니다
		JPanel pPanel = new JPanel();
		pPanel.setBounds(498, 174, 153, 50);
		pPanel.setLayout(null);
		
//		타이틀, 명이라고 적혀있습니다.
		JLabel pTitle = new JLabel("\uBA85");
		pTitle.setFont(new Font("굴림", Font.PLAIN, 18));
		pTitle.setBounds(105, 10, 43, 30);
		
//		텍스트필드, 1세트의 참여인원 수를 입력받는 텍스트 필드 입니다
		people = new JTextField();
		people.setColumns(10);
		people.setBounds(7, 7, 90, 35);
		

		
		
//		문제 패널의 쿠키 패널, 쿠키패널 입니다
		JPanel cPanel = new JPanel();
		cPanel.setBounds(498, 228, 153, 50);
		cPanel.setLayout(null);
		
//		타이틀, 쿠키라고 적혀있습니다
		JLabel cTitle = new JLabel("\uCFE0\uD0A4");
		cTitle.setFont(new Font("굴림", Font.PLAIN, 18));
		cTitle.setBounds(105, 10, 43, 30);

//		텍스트필드, 1문제 쿠키 개수를 입력받습니다
		cookie = new JTextField();
		cookie.setColumns(10);
		cookie.setBounds(7, 7, 90, 35);
		
		
		
		
//		문제 패널의 난이도 패널, 난이도 패널 입니다
		JPanel lvPanel = new JPanel();
		lvPanel.setBounds(498, 285, 153, 150);
		lvPanel.setLayout(null);
	
//		타이틀, 난이도라고 적혀있습니다
		JLabel lvTitle = new JLabel("\uB09C\uC774\uB3C4 (\uCD08)");
		lvTitle.setFont(new Font("굴림", Font.PLAIN, 18));
		lvTitle.setBounds(30, 7, 90, 30);
		
//		라디오 버튼, 상급, 난이도 패널에 추가
		level_1 = new JRadioButton(" \uC0C1\uAE09 (10)");
		level_1.setFont(new Font("굴림", Font.PLAIN, 18));
		level_1.setBounds(15, 40, 120, 30);
		level_1.addItemListener(this);
		

//		라디오 버튼, 중급, 난이도 패널에 추가
		level_2 = new JRadioButton(" \uC911\uAE09 (20)");
		level_2.setFont(new Font("굴림", Font.PLAIN, 18));
		level_2.setBounds(15, 80, 120, 30);
		level_2.addItemListener(this);
		
//		라디오 버튼, 하급, 
		level_3 = new JRadioButton(" \uD558\uAE09 (30)");
		level_3.setFont(new Font("굴림", Font.PLAIN, 18));
		level_3.setBounds(15, 120, 120, 30);
		level_3.addItemListener(this);
		
//		라디오 버튼 선택 해제용
		level_4 = new JRadioButton();

//		난이도, 그룹화 라디오 버튼
		ButtonGroup lvGroup = new ButtonGroup();
		lvGroup.add(level_1);
		lvGroup.add(level_2);
		lvGroup.add(level_3);
		lvGroup.add(level_4);
		getContentPane().add(level_4);
		

		
//		답 패널, 답이 위치합니다
		JPanel aPanel = new JPanel();
		aPanel.setBounds(7, 504, 1005, 210);
		aPanel.setLayout(null);
		
//		1번 패널 입니다
		aPanel_1 = new JPanel();
		aPanel_1.setLayout(null);
		aPanel_1.setBackground(Color.RED);
		aPanel_1.setBounds(10, 7, 485, 98);

//		텍스트필드, 1번답, 1번 패널에 들어갑니다
		answer_1 = new JTextField();
		answer_1.setColumns(10);
		answer_1.setBounds(73, 38, 400, 50);
		
////	2차	스위치, 1번 답의 정답 여부를 체크합니다
//		ansSwitch_1 = new ToggleSwitch();
//		ansSwitch_1.setBounds(432, 7, 41, 21);
		
		ansRadio_1 = new JRadioButton("정답");
		ansRadio_1.setFont(new Font("굴림", Font.PLAIN, 17));
		ansRadio_1.setBounds(412, 5, 60, 30);
//		ansRadio_1.setBackground(Color.RED);
		ansRadio_1.addItemListener(this);

		
//		2번 패널 입니다
		aPanel_2 = new JPanel();
		aPanel_2.setBounds(510, 7, 485, 98);
		aPanel_2.setBackground(Color.ORANGE);
		aPanel_2.setLayout(null);
		
//		텍스트 필드, 2번답, 2번 패널에 들어갑니다
		answer_2 = new JTextField();
		answer_2.setColumns(10);
		answer_2.setBounds(73, 38, 400, 50);
		
////	2차	스위치, 2번 답의 정답 여부를 체크합니다
//		ansSwitch_2 = new ToggleSwitch();
//		ansSwitch_2.setBounds(432, 7, 41, 21);
		
		ansRadio_2 = new JRadioButton("정답");
		ansRadio_2.setFont(new Font("굴림", Font.PLAIN, 17));
		ansRadio_2.setBounds(412, 5, 60, 30);
//		ansRadio_2.setBackground(Color.ORANGE);
		ansRadio_2.addItemListener(this);

			
//		3번 패널 입니다
		aPanel_3 = new JPanel();
		aPanel_3.setLayout(null);
		aPanel_3.setBackground(Color.CYAN);
		aPanel_3.setBounds(10, 107, 485, 98);

//		텍스트 필드, 3번답, 3번 패널에 들어갑니다
		answer_3 = new JTextField();
		answer_3.setColumns(10);
		answer_3.setBounds(73, 38, 400, 50);
		
////	2차	스위치, 3번 답의 정답 여부를 체크합니다
//		ansSwitch_3 = new ToggleSwitch();
//		ansSwitch_3.setBounds(432, 7, 41, 21);
		
		ansRadio_3 = new JRadioButton("정답");
		ansRadio_3.setFont(new Font("굴림", Font.PLAIN, 17));
		ansRadio_3.setBounds(412, 5, 60, 30);
//		ansRadio_3.setBackground(Color.CYAN);
		ansRadio_3.addItemListener(this);
		
//		4번 패널 입니다
		aPanel_4 = new JPanel();
		aPanel_4.setLayout(null);
		aPanel_4.setBackground(Color.GREEN);
		aPanel_4.setBounds(510, 107, 485, 98);
		
//		텍스트 필드, 4번답, 4번 패널에 들어갑니다
		answer_4 = new JTextField();
		answer_4.setColumns(10);
		answer_4.setBounds(73, 38, 400, 50);
		
////	2차	스위치, 4번 답의 정답 여부를 체크합니다
//		ansSwitch_4 = new ToggleSwitch();
//		ansSwitch_4.setBounds(432, 7, 41, 21);
		
		ansRadio_4 = new JRadioButton("정답");
		ansRadio_4.setFont(new Font("굴림", Font.PLAIN, 17));
		ansRadio_4.setBounds(412, 5, 60, 30);
//		ansRadio_4.setBackground(Color.GREEN);
		ansRadio_4.addItemListener(this);
		
//		라디오 버튼 선택 해제용
		ansRadio_5 = new JRadioButton();
		
//		정답, 그룹화 라디오 버튼
		ButtonGroup aRGroup = new ButtonGroup();
		aRGroup.add(ansRadio_1);
		aRGroup.add(ansRadio_2);
		aRGroup.add(ansRadio_3);
		aRGroup.add(ansRadio_4);
		aRGroup.add(ansRadio_5);
		getContentPane().add(ansRadio_5);
		

//		이미지추가 패널
		JPanel iPanel = new JPanel();
		iPanel.setBackground(Color.LIGHT_GRAY);
		iPanel.setBounds(7, 90, 483, 345);
		
//		메인 패널에 올라가는 개별 패널입니다
		mqPanel.add(lPanel);
		mqPanel.add(qPanel);
		mqPanel.add(aPanel);
		
//		리스트 패널에 올라가는 버튼과 리스트입니다
		lPanel.add(lSet);
//		listPanel.add(listQuiz);
		lPanel.add(button_2);
		lPanel.add(button_3);
		lPanel.add(button_4);
		lPanel.add(button_5);
		
			
//		문제 패널에 올라가는 버튼과 리스트 입니다 패널내 하위패널은 들여쓰기되어 있습니다
		qPanel.add(set_info);
			qPanel.add(qTitleSet);
		
		qPanel.add(title);
			qPanel.add(qTitle);
		
		qPanel.add(button_1);
		
		qPanel.add(sPanel);
			sPanel.add(sTitle);
			sPanel.add(subject);	
		
		qPanel.add(pPanel);
			pPanel.add(pTitle);
			pPanel.add(people);
			
		qPanel.add(cPanel);		
			cPanel.add(cTitle);
			cPanel.add(cookie);
			
		qPanel.add(lvPanel);
			lvPanel.add(lvTitle);
			lvPanel.add(level_1);
			lvPanel.add(level_2);
			lvPanel.add(level_3);
			
		qPanel.add(iPanel);


//		답 패널에 올라가는 패널과 텍스트 필드 입니다
//		스위치 2차 구현
		aPanel.add(aPanel_1);
				aPanel_1.add(answer_1);
//				aPanel_1.add(ansSwitch_1);
				aPanel_1.add(ansRadio_1);
		aPanel.add(aPanel_2);
				aPanel_2.add(answer_2);
//				aPanel_2.add(ansSwitch_2);
				aPanel_2.add(ansRadio_2);
		aPanel.add(aPanel_3);
				aPanel_3.add(answer_3);
//				aPanel_3.add(ansSwitch_3);
				aPanel_3.add(ansRadio_3);
		aPanel.add(aPanel_4);
				aPanel_4.add(answer_4);
//				aPanel_4.add(ansSwitch_4);
				aPanel_4.add(ansRadio_4);
		
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}


	//난이도, 선택한 라디오 버튼에 따라 변수에 값 저장
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==level_1) {
			difficulty_result = 10;
		} else if(e.getSource()==level_2) {
			difficulty_result = 20;
		} else if(e.getSource()==level_3) {
			difficulty_result = 30;
		}
		
	//정답, 선택한 라디오 버튼에 따라 변수에 값 저장
		if(e.getSource()==ansRadio_1) {
			final_answer = answer_1.getText();
		} else if(e.getSource()==ansRadio_2) {
			final_answer = answer_2.getText();
		} else if(e.getSource()==ansRadio_3) {
			final_answer = answer_3.getText();
		} else if(e.getSource()==ansRadio_4) {
			final_answer = answer_4.getText();
		}

	}	
	
	
	//리스트에 추가해요 버튼을 누르면 Quiz_added_VO에 setter값으로 저장
	@Override	
	public void actionPerformed(ActionEvent a) {
		Quiz_VO qv = new Quiz_VO();
		try {
		if(a.getSource()==button_2) {
			qv.setQuiz_set_info(set_info.getText());
//			qv.setQuiz_title(title.getText());
			qv.setQuiz_subject(subject.getText());
//			qv.setQuiz_answer_1(answer_1.getText());
//			qv.setQuiz_answer_2(answer_2.getText());
//			qv.setQuiz_answer_3(answer_3.getText());
//			qv.setQuiz_answer_4(answer_4.getText());
//			qv.setQuiz_final_answer(final_answer);
//			qv.setQuiz_cookie(Integer.parseInt(cookie.getText()));
//			qv.setQuiz_difficulty(difficulty_result);
//			qv.setQuiz_image(null);
			qv.setQuiz_people(Integer.parseInt(people.getText()));
			
			
			
			mqm.insertSet(set_info.getText(), subject.getText(), Integer.parseInt(people.getText()));
//			mqm.insertSet(set_info.getText(), subject.getText(), Integer.parseInt(people.getText()));

//			하나의 문제 등록 후 필드 초기화
			set_info.setText(null);			//세트 이름 초기화는 필요 없음
			title.setText(null);			//문제 이름 초기화
			subject.setText(null);
			answer_1.setText(null);
			answer_2.setText(null);
			answer_3.setText(null);
			answer_4.setText(null);
			ansRadio_5.setSelected(true);	//정답 라디오 버튼 초기화
			final_answer = null;
			level_4.setSelected(true);		//난이도 라디오 버튼 초기화
			cookie.setText(null);			//쿠키 개수 초기화
			image.setText(null);
			people.setText(null);
//			aResult.clear();				//스위치형 정답 (2차구현)
		}
		
		else if(a.getSource()==button_3) {
			qv.setQuiz_set_info(null);
			qv.setQuiz_title(title.getText());
			qv.setQuiz_subject(null);
			qv.setQuiz_answer_1(answer_1.getText());
			qv.setQuiz_answer_2(answer_2.getText());
			qv.setQuiz_answer_3(answer_3.getText());
			qv.setQuiz_answer_4(answer_4.getText());
			qv.setQuiz_final_answer(final_answer);
			qv.setQuiz_cookie(Integer.parseInt(cookie.getText()));
			qv.setQuiz_difficulty(difficulty_result);
			qv.setQuiz_image(null);
			qv.setQuiz_people(0);	
//			qav.setAdded_subject(null);
//			qav.setAdded_people(0);
//			2차 스위치
//			if(ansSwitch_1.isActivated()) {
//				aResult.add("ans1");
//			}
//			if(ansSwitch_2.isActivated()) {
//				aResult.add("ans2");
//			}
//			if(ansSwitch_3.isActivated()) {
//				aResult.add("ans3");
//			}
//			if(ansSwitch_4.isActivated()) {
//				aResult.add("ans4");
//			}
//			String listString = String.join(", ", aResult);
			mqm.insertQuiz(qv);
			
//			set_info.setText(null);			//세트 이름 초기화는 필요 없음
			title.setText(null);			//문제 이름 초기화
//			subject.setText(null);
			answer_1.setText(null);
			answer_2.setText(null);
			answer_3.setText(null);
			answer_4.setText(null);
			ansRadio_5.setSelected(true);	//정답 라디오 버튼 초기화
			final_answer = null;
			level_4.setSelected(true);		//난이도 라디오 버튼 초기화
			cookie.setText(null);			//쿠키 개수 초기화
			image.setText(null);
//			people.setText(null);
		} 
		
	}catch(NullPointerException e) {
		System.out.println("문제에 필요한 입력 값은 문제, 난이도, 쿠키, 답1, 답2, 답3, 답4, 정답 라디오 버튼입니다.");
		System.out.println("세트에 필요한 입력 값은 방제목과 주제, 참여인원입니다. ");
//		e.printStackTrace();
		}
	}
}


