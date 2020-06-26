package com.kh.sopa.makingQuiz.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

import com.kh.sopa.makingQuiz.model.controller.MakingQuizManager;
import com.kh.sopa.makingQuiz.model.dao.Quiz_DAO;
import com.kh.sopa.model.vo.Quiz_VO;

public class MakingQuiz extends JPanel implements ActionListener, ItemListener, MouseListener, FocusListener {
	MakingQuizManager mqm = new MakingQuizManager();
	Quiz_DAO qd = new Quiz_DAO();
	Quiz_VO qv = new Quiz_VO();

	private JPanel lPanel;
	private JPanel qPanel;
	private JPanel aPanel;
	private JPanel aPanel_1;
	private JPanel aPanel_2;
	private JPanel aPanel_3;
	private JPanel aPanel_4;

	private JPanel qListPanel;
	private JPanel qSetPanel;

	private JTextField set_info;
	private JTextField title;
	private JTextField subject;
	private JTextField answer_1;
	private JTextField answer_2;
	private JTextField answer_3;
	private JTextField answer_4;
	private String final_answer = null;
	private int difficulty_result;
	private JTextField cookie;
	private JTextField image;
	private JTextField people;
	private JTable tableList;

	private JRadioButton ansRadio_1;
	private JRadioButton ansRadio_2;
	private JRadioButton ansRadio_3;
	private JRadioButton ansRadio_4;
	private JRadioButton ansRadio_5; // ansRadio_5는 선택 해제용

	// 2차 스위치
	// private ToggleSwitch ansSwitch_1;
	// private ToggleSwitch ansSwitch_2;
	// private ToggleSwitch ansSwitch_3;
	// private ToggleSwitch ansSwitch_4;

	private JRadioButton level_1;
	private JRadioButton level_2;
	private JRadioButton level_3;
	private JRadioButton level_4; // level_4는 선택 해제용

	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
//	private JButton button_5;

	private JTable listQuiz;
	private JScrollPane scrollList;
	private JScrollPane scrollSet;

	private DefaultTableModel modelList;
	private DefaultTableModel modelSet;

	
	
	public MakingQuiz(JFrame jf) {
		this.setLayout(null);
		this.setBounds(0, 0, 1024, 768);

		//최하단 패널 여기에 모든 패널이 올라옵니다
		JPanel mqPanel = new JPanel();
		mqPanel.setBounds(0, 0, 1024, 768);
		mqPanel.setBackground(new Color(254, 228, 167));
		mqPanel.setLayout(null);

		// 좌측 리스트 패널, 세트리스트와 문제리스트가 위치합니다
		lPanel = new JPanel();
		lPanel.setBounds(7, 60, 340, 440);
		lPanel.setBackground(new Color(255, 234, 185));
		lPanel.setLayout(null);

		// 좌측 리스트 패널, 세트 리스트가 올라옵니다
		qSetPanel = new JPanel();
		qSetPanel.setBounds(7, 7, 160, 340);
		qSetPanel.setBackground(new Color(254, 228, 167));
		

		try {

			// 세트 리스트에서 세트 이름을 불러와서 정렬 후 표시합니다
			ArrayList<Quiz_VO> set = qd.readQuizSet();
			String[] setTitle = { "추가된 세트" };
//			Object[][] setData = new Object[set.size()][12];
//			for (int i = 0; i < set.size(); i++) {
//				setData[i][0] = set.get(i).getQuiz_set_info();
//				setData[i][1] = set.get(i).getQuiz_title();
//				setData[i][2] = set.get(i).getQuiz_subject();
//				setData[i][3] = set.get(i).getQuiz_answer_1();
//				setData[i][4] = set.get(i).getQuiz_answer_2();
//				setData[i][5] = set.get(i).getQuiz_answer_3();
//				setData[i][6] = set.get(i).getQuiz_answer_4();
//				setData[i][7] = set.get(i).getQuiz_final_answer();
//				setData[i][8] = set.get(i).getQuiz_difficulty();
//				setData[i][9] = set.get(i).getQuiz_cookie();
//				setData[i][10] = set.get(i).getQuiz_image();
//				setData[i][11] = set.get(i).getQuiz_people();
//			}
			String[][] setData = new String[set.size()][1];
			String[][] setData2 = new String[set.size()][1];
			for (int i = 0; i < set.size(); i++) {
				setData[i][0] = set.get(i).getQuiz_set_info();
			}
			
//			ArrayList<Quiz_VO> set 을  setData로 getQuiz_set_info만 받아와서 출력
			List<String[]> temp = new ArrayList<String[]>(Arrays.asList(setData));
			for (int i = setData.length - 1; i >= 1; i--) {
				if (Arrays.equals(setData[i], setData[i - 1])) {
					temp.remove(i);
				}
			}
			setData2 = temp.toArray(new String[][] {});


			modelSet = new DefaultTableModel(setData2, setTitle);
			JTable tableSet = new JTable(modelSet);
			tableSet.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			tableSet.addMouseListener(this);
			tableSet.setRowHeight(30);
			scrollSet = new JScrollPane(tableSet);
			scrollSet.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollSet.setBounds(7, 7, 160, 340);
			
			lPanel.add(scrollSet);

		} catch (NullPointerException e) {
			// e.printStackTrace();
			System.out.println("파일 없어");
		}

		// 문제 리스트 패널, 문제 리스트가 올라옵니다
		qListPanel = new JPanel();
		qListPanel.setBounds(170, 7, 160, 340);
		qListPanel.setBackground(new Color(254, 228, 167));


		try {
			// 문제 리스트에서 문제를 불러와 정렬 후 출력합니다
			ArrayList<Quiz_VO> list = qd.readQuizList();
			String[] atitle = { "추가한 문제" };
			Object[][] panelList = new Object[list.size()][11];
			for (int i = 0; i < list.size(); i++) {
//				panelList[i][0] = list.get(i).getQuiz_set_info();
				panelList[i][0] = list.get(i).getQuiz_title();
				panelList[i][1] = list.get(i).getQuiz_subject();
				panelList[i][2] = list.get(i).getQuiz_answer_1();
				panelList[i][3] = list.get(i).getQuiz_answer_2();
				panelList[i][4] = list.get(i).getQuiz_answer_3();
				panelList[i][5] = list.get(i).getQuiz_answer_4();
				panelList[i][6] = list.get(i).getQuiz_final_answer();
				panelList[i][7] = list.get(i).getQuiz_difficulty();
				panelList[i][8] = list.get(i).getQuiz_cookie();
				panelList[i][9] = list.get(i).getQuiz_image();
				panelList[i][10] = list.get(i).getQuiz_people();
			}

			modelList = new DefaultTableModel(panelList, atitle);
			tableList = new JTable(modelList);
			tableList.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			tableList.setRowHeight(30);
			tableList.addMouseListener(new MouseListener(){
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = tableList.getSelectedRow();
					System.out.println(index);
					for(int i = 0 ; i < list.size(); i++) {
						if(index == i) {
							title.setText(list.get(i).getQuiz_title());
							answer_1.setText(list.get(i).getQuiz_answer_1());
							answer_2.setText(list.get(i).getQuiz_answer_2());
							answer_3.setText(list.get(i).getQuiz_answer_3());
							answer_4.setText(list.get(i).getQuiz_answer_4());
							ansRadio_5.isSelected();	//최종답안 라디오버튼
							
							
					}
	
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
		
				});

		
		

			scrollList = new JScrollPane(tableList);
			scrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollList.setBounds(170, 7, 160, 340);
			lPanel.add(scrollList);

		} catch (NullPointerException e) {
			 e.printStackTrace();
			
		}

		// 문제 리스트에서 문제를 클릭하면 해당 필드 값을 표시합니다

		// 버튼, 추가, 문제 리스트에 작성된 문제를 추가합니다
		button_3 = new JButton("리스트에 추가"){
			@Override
			public void setBorder(Border border) {
				}
			};
		button_3.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		button_3.setBounds(170, 350, 160, 40);
		button_3.addActionListener(this);
		button_3.setBackground(new Color(255, 179, 0));

		// 버튼, 삭제, 문제 리스트에서 선택한 문제를 삭제합니다
		button_4 = new JButton("리스트에서 삭제"){
			@Override
			public void setBorder(Border border) {
				}
			};
		button_4.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		button_4.setBounds(170, 395, 160, 40);
		button_4.setBackground(new Color(255, 179, 0));

//		// 버튼, 수정, 문제 리스트에서 선택한 문제를 수정합니다
//		button_5 = new JButton("\uC218\uC815"){
//			@Override
//			public void setBorder(Border border) {
//				}
//			};
//		button_5.setFont(new Font("맑은 고딕", Font.BOLD, 18));
//		button_5.setBounds(170, 395, 80, 40);
//		button_5.setBackground(new Color(255, 179, 0));

		// 버튼, 세트로 만듬, 문제 리스트의 문제들을 세트로 만듭니다
		button_2 = new JButton("세트에 추가"){
			@Override
			public void setBorder(Border border) {
				}
			};
		button_2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		button_2.setBounds(7, 351, 160, 40);
		button_2.addActionListener(this);
		button_2.setBackground(new Color(255, 179, 0));

		// 문제 패널, 문제를 만드는 패널 입니다
		qPanel = new JPanel();
		qPanel.setBounds(354, 60, 658, 440);
		qPanel.setLayout(null);
		qPanel.setBackground(new Color(255, 234, 185));

		// 버튼, 방을 만듬, 세트를 선택한 상태에서 버튼을 누릅니다
		button_1 = new JButton("방을 만들어요"){
			@Override
			public void setBorder(Border border) {
				}
			};
		button_1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		button_1.setBounds(498, 6, 153, 78);
		button_1.setBackground(new Color(255, 179, 0));

		// 텍스트 필드, 1세트 세트 제목을 입력받습니다
		set_info = new JTextField(){
			@Override
			public void setBorder(Border border) {
				}
			};
		set_info.setBounds(70, 7, 420, 35);
		set_info.setColumns(10);

		// 텍스트, 문제 패널에 위치하고, 방제목이라고 적혀있습니다
		JLabel qTitleSet = new JLabel("세트명");
		qTitleSet.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		qTitleSet.setBounds(7, 7, 58, 30);
		qTitleSet.setBackground(Color.WHITE);

		// 텍스트 필드, 1문제 문제를 입력받습니다
		title = new JTextField(){
			@Override
			public void setBorder(Border border) {
				}
			};
		title.setBounds(70, 49, 420, 35);
		title.setColumns(10);
		title.addFocusListener(this);

		// 텍스트, 문제 패널에 위치하고, 문제라고 적혀있습니다
		JLabel qTitle = new JLabel("문제");
		qTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		qTitle.setBounds(7, 49, 43, 30);
		qTitle.setBackground(new Color(252, 209, 108));

		// 문제 패널의 주제 패널, 주제를 입력합니다
		JPanel sPanel = new JPanel();
		sPanel.setBounds(498, 90, 153, 78);
		sPanel.setLayout(null);
		sPanel.setBackground(new Color(252, 209, 108));
		

		// 타이틀, 주제라고 적혀있습니다
		JLabel sTitle = new JLabel("주제");
		sTitle.setBounds(60, 0, 43, 30);
		sTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		sTitle.setBackground(new Color(252, 209, 108));

		// 텍스트필드, 1세트 주제를 입력받는 텍스트 필드 입니다
		subject = new JTextField(){
			@Override
			public void setBorder(Border border) {
				}
			};
		subject.setBounds(7, 35, 139, 35);
		subject.setColumns(10);

		// 문제 패널의 참여 인원 패널, 참여인원 패널입니다
		JPanel pPanel = new JPanel();
		pPanel.setBounds(498, 174, 153, 50);
		pPanel.setLayout(null);
		pPanel.setBackground(new Color(252, 209, 108));

		// 타이틀, 명이라고 적혀있습니다.
		JLabel pTitle = new JLabel("명");
		pTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		pTitle.setBounds(105, 10, 43, 30);


		// 텍스트필드, 1세트의 참여인원 수를 입력받는 텍스트 필드 입니다
		people = new JTextField(){
			@Override
			public void setBorder(Border border) {
				}
			};
		people.setColumns(10);
		people.setBounds(7, 7, 90, 35);


		// 문제 패널의 쿠키 패널, 쿠키패널 입니다
		JPanel cPanel = new JPanel();
		cPanel.setBounds(498, 228, 153, 50);
		cPanel.setLayout(null);
		cPanel.setBackground(new Color(252, 209, 108));

		// 타이틀, 쿠키라고 적혀있습니다
		JLabel cTitle = new JLabel("쿠키");
		cTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		cTitle.setBounds(105, 10, 43, 30);
		cTitle.setBackground(new Color(252, 209, 108));

		// 텍스트필드, 1문제 쿠키 개수를 입력받습니다
		cookie = new JTextField(){
			@Override
			public void setBorder(Border border) {
				}
			};
		cookie.setColumns(10);
		cookie.setBounds(7, 7, 90, 35);

		// 문제 패널의 난이도 패널, 난이도 패널 입니다
		JPanel lvPanel = new JPanel();
		lvPanel.setBounds(498, 285, 153, 150);
		lvPanel.setLayout(null);
		lvPanel.setBackground(new Color(252, 209, 108));

		// 타이틀, 난이도라고 적혀있습니다
		JLabel lvTitle = new JLabel("난이도 (초)");
		lvTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lvTitle.setBounds(30, 2, 90, 30);

		// 라디오 버튼, 상급, 난이도 패널에 추가
		level_1 = new JRadioButton(" 상급 (5)");
		level_1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		level_1.setBounds(15, 35, 120, 30);
		level_1.addItemListener(this);
		level_1.setBackground(new Color(252, 209, 108));

		// 라디오 버튼, 중급, 난이도 패널에 추가
		level_2 = new JRadioButton(" 중급 (10)");
		level_2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		level_2.setBounds(15, 75, 120, 30);
		level_2.addItemListener(this);
		level_2.setBackground(new Color(252, 209, 108));

		// 라디오 버튼, 하급,
		level_3 = new JRadioButton(" 하급 (20)");
		level_3.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		level_3.setBounds(15, 115, 120, 30);
		level_3.addItemListener(this);
		level_3.setBackground(new Color(252, 209, 108));

		// 라디오 버튼 선택 해제용
		level_4 = new JRadioButton();

		// 난이도, 그룹화 라디오 버튼
		ButtonGroup lvGroup = new ButtonGroup();
		lvGroup.add(level_1);
		lvGroup.add(level_2);
		lvGroup.add(level_3);
		lvGroup.add(level_4);
		mqPanel.add(level_4);

		// 답 패널, 답이 위치합니다
		aPanel = new JPanel();
		aPanel.setBounds(7, 504, 1005, 210);
		aPanel.setLayout(null);
		aPanel.setBackground(new Color(255, 234, 185));

		// 1번 패널 입니다
		aPanel_1 = new JPanel();
		aPanel_1.setLayout(null);
		aPanel_1.setBackground(new Color(226, 91 ,69));
		aPanel_1.setBounds(10, 7, 485, 98);

		// 텍스트필드, 1번답, 1번 패널에 들어갑니다
		answer_1 = new JTextField(){
			@Override
			public void setBorder(Border border) {
				}
			};
		answer_1.setColumns(10);
		answer_1.setBackground(new Color(255, 158, 142));
		answer_1.setBounds(73, 38, 400, 50);

		//// 2차 스위치, 1번 답의 정답 여부를 체크합니다
		// ansSwitch_1 = new ToggleSwitch();
		// ansSwitch_1.setBounds(432, 7, 41, 21);

		ansRadio_1 = new JRadioButton("정답");
		ansRadio_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		ansRadio_1.setBounds(412, 5, 60, 30);
		ansRadio_1.setBackground(new Color(226, 91 ,69));
		ansRadio_1.setForeground(Color.BLACK);
		ansRadio_1.addItemListener(this);

		// 2번 패널 입니다
		aPanel_2 = new JPanel();
		aPanel_2.setBounds(510, 7, 485, 98);
		aPanel_2.setBackground(new Color(255, 137, 81));
		aPanel_2.setLayout(null);

		// 텍스트 필드, 2번답, 2번 패널에 들어갑니다
		answer_2 = new JTextField(){
			@Override
			public void setBorder(Border border) {
				}
			};
		answer_2.setBackground(new Color(255, 189, 166));
		answer_2.setColumns(10);
		answer_2.setBounds(73, 38, 400, 50);

		//// 2차 스위치, 2번 답의 정답 여부를 체크합니다
		// ansSwitch_2 = new ToggleSwitch();
		// ansSwitch_2.setBounds(432, 7, 41, 21);

		ansRadio_2 = new JRadioButton("정답");
		ansRadio_2.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		ansRadio_2.setBounds(412, 5, 60, 30);
		ansRadio_2.setBackground(new Color(255, 137, 81));
		ansRadio_2.setForeground(Color.BLACK);
		ansRadio_2.addItemListener(this);

		// 3번 패널 입니다
		aPanel_3 = new JPanel();
		aPanel_3.setLayout(null);
		aPanel_3.setBackground(new Color(137, 213, 201));
		aPanel_3.setBounds(10, 107, 485, 98);

		// 텍스트 필드, 3번답, 3번 패널에 들어갑니다
		answer_3 = new JTextField(){
			@Override
			public void setBorder(Border border) {
				}
			};
		answer_3.setColumns(10);// 텍스트 필드, 3번답, 3번 패널에 들어갑니다
		answer_3.setBackground(new Color(193, 238, 231));
		answer_3.setColumns(10);
		answer_3.setBounds(73, 38, 400, 50);

		//// 2차 스위치, 3번 답의 정답 여부를 체크합니다
		// ansSwitch_3 = new ToggleSwitch();
		// ansSwitch_3.setBounds(432, 7, 41, 21);

		ansRadio_3 = new JRadioButton("정답");
		ansRadio_3.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		ansRadio_3.setBounds(412, 5, 60, 30);
		ansRadio_3.setBackground(new Color(137, 213, 201));
		ansRadio_3.setForeground(Color.BLACK);
		ansRadio_3.addItemListener(this);

		// 4번 패널 입니다
		aPanel_4 = new JPanel();
		aPanel_4.setLayout(null);
		aPanel_4.setBackground(new Color(173, 201, 101));
		aPanel_4.setBounds(510, 107, 485, 98);

		// 텍스트 필드, 4번답, 4번 패널에 들어갑니다
		answer_4 = new JTextField(){
			@Override
			public void setBorder(Border border) {
				}
			};
		answer_4.setColumns(10);
		answer_4.setBackground(new Color(213, 239, 144));
		answer_4.setBounds(73, 38, 400, 50);

		//// 2차 스위치, 4번 답의 정답 여부를 체크합니다
		// ansSwitch_4 = new ToggleSwitch();
		// ansSwitch_4.setBounds(432, 7, 41, 21);

		ansRadio_4 = new JRadioButton("정답");
		ansRadio_4.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		ansRadio_4.setBounds(412, 5, 60, 30);
		ansRadio_4.setBackground(new Color(173, 201, 101));
		ansRadio_4.addItemListener(this);

		// 라디오 버튼 선택 해제용
		ansRadio_5 = new JRadioButton();
		ansRadio_5.setSelected(true);

		// 정답, 그룹화 라디오 버튼
		ButtonGroup aRGroup = new ButtonGroup();
		aRGroup.add(ansRadio_1);
		aRGroup.add(ansRadio_2);
		aRGroup.add(ansRadio_3);
		aRGroup.add(ansRadio_4);
		aRGroup.add(ansRadio_5);
		mqPanel.add(ansRadio_5);

		// 이미지 패널
		JPanel iPanel = new JPanel();
		iPanel.setBounds(7, 90, 483, 345);
		iPanel.setBackground(new Color(252, 209, 108));
		
		// 이미지를 추가하는 패널
		JPanel image = new JPanel();
		

		// 메인 패널에 올라가는 개별 패널입니다
		mqPanel.add(lPanel);
		mqPanel.add(qPanel);
		mqPanel.add(aPanel);

		// 리스트 패널에 올라가는 버튼과 리스트입니다
		// lPanel.add(comp);
		// listPanel.add(listQuiz);
		lPanel.add(button_2);
		lPanel.add(button_3);
		lPanel.add(button_4);
//		lPanel.add(button_5);

		// 문제 패널에 올라가는 버튼과 리스트 입니다 패널내 하위패널은 들여쓰기되어 있습니다
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

		// 답 패널에 올라가는 패널과 텍스트 필드 입니다
		// 스위치 2차 구현
		aPanel.add(aPanel_1);
		aPanel_1.add(answer_1);
		// aPanel_1.add(ansSwitch_1);
		aPanel_1.add(ansRadio_1);
		aPanel.add(aPanel_2);
		aPanel_2.add(answer_2);
		// aPanel_2.add(ansSwitch_2);
		aPanel_2.add(ansRadio_2);
		aPanel.add(aPanel_3);
		aPanel_3.add(answer_3);
		// aPanel_3.add(ansSwitch_3);
		aPanel_3.add(ansRadio_3);
		aPanel.add(aPanel_4);
		aPanel_4.add(answer_4);
		// aPanel_4.add(ansSwitch_4);
		aPanel_4.add(ansRadio_4);

		this.add(mqPanel);

	}

	//
	public static void main(String[] args) {
		Quiz_DAO qd = new Quiz_DAO();
		JFrame jf = new JFrame();
		jf.setSize(1024, 768);
		jf.setLayout(null);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.add(new MakingQuiz(jf));

		jf.setVisible(true);
		
		ArrayList<Quiz_VO> list = qd.readQuizList();
		if(list != null) {
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}else {
			System.out.println("문제 파일을 생성해야 합니다");
		}
		
		System.out.println("경꼐선");
		
		ArrayList<Quiz_VO> set = qd.readQuizSet();
		if(set != null) {
			for(int i = 0; i < set.size(); i++) {
				System.out.println(set.get(i));
			}
		}else {
			System.out.println("세트 파일을 생성해야 합니다.");
		}
	}

	// 난이도, 선택한 라디오 버튼에 따라 변수에 값 저장
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == level_1) {
			difficulty_result = 5;
		} else if (e.getSource() == level_2) {
			difficulty_result = 10;
		} else if (e.getSource() == level_3) {
			difficulty_result = 20;
		}

		// 정답, 선택한 라디오 버튼에 따라 변수에 값 저장
		if (e.getSource() == ansRadio_1) {
			final_answer = answer_1.getText();
		} else if (e.getSource() == ansRadio_2) {
			final_answer = answer_2.getText();
		} else if (e.getSource() == ansRadio_3) {
			final_answer = answer_3.getText();
		} else if (e.getSource() == ansRadio_4) {
			final_answer = answer_4.getText();
		}

	}

	// 리스트에 추가해요 버튼을 누르면 Quiz_added_VO에 setter값으로 저장
	@Override
	public void actionPerformed(ActionEvent a) {
		Quiz_VO qv = new Quiz_VO();
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new ColorUIResource(252, 209, 108));
		UI.put("Panel.background", new ColorUIResource(254, 228, 67));

//		int row = tableList.getSelectedRow();
		try {
			if (a.getSource() == button_2) {
				if((set_info.getText().equals(""))|| (subject.getText().equals(""))||(people.getText().equals(""))){
					JButton button = new JButton("OK");
					button.setBackground(new Color(255, 179, 0));
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							JOptionPane.getRootFrame().dispose();
						}
					});
					JButton[] buttons = { button };
					JOptionPane.showOptionDialog(null, "세트를 만드려면, 방제목(세트제목), 주제, 참여인원 수를 입력해주세요 :)   ", "입력해주세요",
					JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(), buttons,
							buttons[0]);
					return;

				} else {	
				qv.setQuiz_set_info(set_info.getText());
				// qv.setQuiz_title(title.getText());
				qv.setQuiz_subject(subject.getText());
				// qv.setQuiz_answer_1(answer_1.getText());
				// qv.setQuiz_answer_2(answer_2.getText());
				// qv.setQuiz_answer_3(answer_3.getText());
				// qv.setQuiz_answer_4(answer_4.getText());
				// qv.setQuiz_final_answer(final_answer);
				// qv.setQuiz_cookie(Integer.parseInt(cookie.getText()));
				// qv.setQuiz_difficulty(difficulty_result);
				// qv.setQuiz_image(null);
				qv.setQuiz_people(Integer.parseInt(people.getText()));
				mqm.insertSet(set_info.getText(), subject.getText(), Integer.parseInt(people.getText()));
//				mqm.insertSet(qv);

				// 하나의 문제 등록 후 필드 초기화
				set_info.setText(null); // 세트 이름 초기화는 필요 없음
				title.setText(null); // 문제 이름 초기화
				subject.setText(null);
				answer_1.setText(null);
				answer_2.setText(null);
				answer_3.setText(null);
				answer_4.setText(null);
				ansRadio_5.setSelected(true); // 정답 라디오 버튼 초기화
				final_answer = null;
				level_4.setSelected(true); // 난이도 라디오 버튼 초기화
				cookie.setText(null); // 쿠키 개수 초기화
				image.setText(null);
				people.setText(null);
				// aResult.clear(); //스위치형 정답 (2차구현)
				
				
				}
			}

			else if (a.getSource() == button_3) {
				if ((title.getText().equals("")) || (answer_1.getText().equals("")) || (answer_2.getText().equals(""))
						|| (answer_3.getText().equals("")) || (answer_4.getText().equals(""))
//						|| (cookie.getText().equals("")) || (difficulty_result == 0) || (final_answer.equals(null))) {
						|| (cookie.getText().equals("")) || difficulty_result == 0 || ansRadio_5.isSelected()) {
					JButton button = new JButton("OK");
					button.setBackground(new Color(255, 179, 0));
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							JOptionPane.getRootFrame().dispose();
						}
					});
					JButton[] buttons = { button };
					JOptionPane.showOptionDialog(null, "문제를 만드려면, 문제, 쿠키, 난이도 체크, 답과 함께 정답 체크를 해주세요 :)   ", "입력해주세요",
					JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(), buttons,
							buttons[0]);
					return;

				} else {
					qv.setQuiz_set_info(null);
					qv.setQuiz_title(title.getText());
					qv.setQuiz_answer_1(answer_1.getText());
					qv.setQuiz_answer_2(answer_2.getText());
					qv.setQuiz_answer_3(answer_3.getText());
					qv.setQuiz_answer_4(answer_4.getText());
					qv.setQuiz_final_answer(final_answer);
					qv.setQuiz_cookie(Integer.parseInt(cookie.getText()));
					qv.setQuiz_difficulty(difficulty_result);
					qv.setQuiz_subject(null);
					qv.setQuiz_image(null);
					qv.setQuiz_people(0);
					
	
				}

				// qav.setAdded_subject(null);
				// qav.setAdded_people(0);
				// 2차 스위치
				// if(ansSwitch_1.isActivated()) {
				// aResult.add("ans1");
				// }
				// if(ansSwitch_2.isActivated()) {
				// aResult.add("ans2");
				// }
				// if(ansSwitch_3.isActivated()) {
				// aResult.add("ans3");
				// }
				// if(ansSwitch_4.isActivated()) {
				// aResult.add("ans4");
				// }
				// String listString = String.join(", ", aResult);
				mqm.insertQuiz(qv);

				// set_info.setText(null); //세트 이름 초기화는 필요 없음
				title.setText(null); // 문제 이름 초기화
				// subject.setText(null);
				answer_1.setText(null);
				answer_2.setText(null);
				answer_3.setText(null);
				answer_4.setText(null);
				ansRadio_5.setSelected(true); // 정답 라디오 버튼 초기화
				final_answer = null;
				level_4.setSelected(true); // 난이도 라디오 버튼 초기화
				cookie.setText(null); // 쿠키 개수 초기화
				image.setText(null);
				// people.setText(null);
				
			}

		} catch (NullPointerException e) {
			System.out.println("문제에 필요한 입력 값은 문제, 난이도, 쿠키, 답1, 답2, 답3, 답4, 정답 라디오 버튼입니다.");
			System.out.println("세트에 필요한 입력 값은 방제목과 주제, 참여인원입니다. ");
			// e.printStackTrace();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}

}
