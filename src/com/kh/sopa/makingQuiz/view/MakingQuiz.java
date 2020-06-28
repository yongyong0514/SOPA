package com.kh.sopa.makingQuiz.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.kh.sopa.makingQuiz.controller.MakingQuizManager;
import com.kh.sopa.makingQuiz.model.dao.Quiz_DAO;
import com.kh.sopa.model.vo.Quiz_VO;

public class MakingQuiz extends JPanel implements ActionListener, ItemListener, MouseListener {
	ArrayList<Quiz_VO> selectSet = new ArrayList<Quiz_VO>(); // 시작버튼 클릭시 가져갈 ArrayList
	MakingQuizManager mqm = new MakingQuizManager();
	Quiz_DAO qd = new Quiz_DAO();
	Quiz_VO qv = new Quiz_VO();

	// 텍스트필드로 입력받는 기본 생성자 초기화
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
	private JTable tableSet;

	private JRadioButton answerRadio_1;
	private JRadioButton answerRadio_2;
	private JRadioButton answerRadio_3;
	private JRadioButton answerRadio_4;
	private JRadioButton answerRadio_5; // answerRadio_5는 선택 해제용

	private JRadioButton difficulty_1;
	private JRadioButton difficulty_2;
	private JRadioButton difficulty_3;
	private JRadioButton difficulty_4; // difficulty_4는 선택 해제용

	private JButton startButton;
	private JButton addSetListBtn;
	private JButton delSetListBtn;
	private JButton addListBtn;
	private JButton delListBtn;
	// private JButton upDate;

	private JScrollPane scrollList;
	private JScrollPane scrollSet;

	private DefaultTableModel modelList;
	private DefaultTableModel modelSet;

	private CookieFont fm = new CookieFont();
	private JPanel showImgPanel;
	private JPanel iSubPanel;
	private String sname;
	
	private JPanel mainPanel = null;
	private JPanel thisPage = null;

	private void insertList() {

	}

	public MakingQuiz(JFrame jf, JPanel mp) {
		// 폰트
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		fm.fontChange(ge);
		
		thisPage = this;
		mainPanel = mp;

		for (int i = 0; i < selectSet.size(); i++) {
			System.out.println(selectSet.get(i));
		}
		System.out.println("noline");

		this.setLayout(null);
		this.setBounds(0, 0, 1024, 768);

		//// 최하단 패널 여기에 모든 패널이 올라옵니다
		JPanel mqPanel = new JPanel();
		mqPanel.setBounds(0, 0, 1024, 768);
		mqPanel.setBackground(new Color(254, 228, 167));
		mqPanel.setLayout(null);

		//// 뒤로가기 버튼입니다
		JButton goBack = new JButton("뒤로가기");
		goBack.setBounds(852, 10, 153, 37);
		goBack.setBackground(new Color(255, 179, 0));
		goBack.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		goBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.remove(thisPage);
				jf.add(mp);
				jf.repaint();
			}
		});
		mqPanel.add(goBack);

		//// 좌측 리스트 패널, 세트리스트와 문제리스트가 위치합니다
		JPanel lPanel = new JPanel();
		lPanel.setBounds(7, 60, 340, 440);
		lPanel.setBackground(new Color(255, 234, 185));
		lPanel.setLayout(null);
		mqPanel.add(lPanel);

		//// 좌측 리스트 패널, 세트 리스트가 올라옵니다
		JPanel qSetPanel = new JPanel();
		qSetPanel.setBounds(7, 7, 160, 340);
		qSetPanel.setBackground(new Color(254, 228, 167));

		//// 좌측 리스트 패널, 세트를 불러와서 정렬 후 표시합니다
		try {
			ArrayList<Quiz_VO> set = qd.readQuizSet();
			Quiz_DAO qd = new Quiz_DAO();
			String[] set_info_head = { "추가된 세트" };
			String[][] set_info_name = new String[set.size()][1];
			for (int i = 0; i < set.size(); i++) {
				set_info_name[i][0] = set.get(i).getQuiz_set_info();
			}
			// 리스트 temp를 이용해서 저장된 세트이름 개수를 파악하고 set_info_name에 저장합니다
			List<String[]> temp = new ArrayList<String[]>(Arrays.asList(set_info_name));
			for (int i = set_info_name.length - 1; i >= 1; i--) {
				if (Arrays.equals(set_info_name[i], set_info_name[i - 1])) {
					temp.remove(i);
				}
			}
			set_info_name = temp.toArray(new String[][] {});
			// 테이블에 행열값을 넣습니다
			modelSet = new DefaultTableModel(set_info_name, set_info_head);
			tableSet = new JTable(modelSet);
			tableSet.setBackground(new Color(252, 241, 207));
			tableSet.setFont(new Font("CookieRun Regular", Font.PLAIN, 20));
			tableSet.setRowHeight(30);

			JTableHeader header = tableSet.getTableHeader();
			header.setFont(new Font("CookieRun Regular", Font.PLAIN, 20));
			header.setBackground(new Color(252, 209, 108));
			header.setForeground(Color.BLACK);

			tableSet.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					ArrayList<Quiz_VO> set = qd.readQuizSet();
					// Quiz_VO temp = new Quiz_VO();
					int index = tableSet.getSelectedRow();
					System.out.println(index); // 삭제예정

					for (int i = 0; i < set.size(); i++) {
						if (i == index) {
							Quiz_VO temp = new Quiz_VO();
							temp.setQuiz_set_info(set.get(index).getQuiz_set_info());
							temp.setQuiz_title(set.get(index).getQuiz_title());
							temp.setQuiz_subject(set.get(index).getQuiz_subject());
							temp.setQuiz_answer_1(set.get(index).getQuiz_answer_1());
							temp.setQuiz_answer_2(set.get(index).getQuiz_answer_2());
							temp.setQuiz_answer_3(set.get(index).getQuiz_answer_3());
							temp.setQuiz_answer_4(set.get(index).getQuiz_answer_4());
							temp.setQuiz_final_answer(set.get(index).getQuiz_final_answer());
							temp.setQuiz_difficulty(set.get(index).getQuiz_difficulty());
							temp.setQuiz_cookie(set.get(index).getQuiz_cookie());
							temp.setQuiz_image(set.get(index).getQuiz_image());
							temp.setQuiz_people(set.get(index).getQuiz_people());

							selectSet.add(temp);
						}
					}
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
			});

			scrollSet = new JScrollPane(tableSet);
			scrollSet.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollSet.setBounds(7, 7, 160, 340);

			lPanel.add(scrollSet);

		} catch (NullPointerException e) {
			// e.printStackTrace();
			System.out.println("파일 없어");
		}

		// 문제 리스트 패널, 문제 리스트가 올라옵니다
		JPanel qListPanel = new JPanel();
		qListPanel.setBounds(170, 7, 160, 340);
		qListPanel.setBackground(new Color(254, 228, 167));

		// 문제 리스트에서 문제를 불러와 정렬 후 출력합니다
		try {
			String[] list_title_head = { "추가한 문제" };
			ArrayList<Quiz_VO> list = qd.readQuizList();
			Object[][] list_title_name = new Object[list.size()][11];
			for (int i = 0; i < list.size(); i++) {
				list_title_name[i][0] = list.get(i).getQuiz_title();
			}

			// ArrayList<Quiz_VO> set 을 setData로 getQuiz_set_info만 받아와서 출력
			List<Object[]> temp = new ArrayList<Object[]>(Arrays.asList(list_title_name));
			for (int i = list_title_name.length - 1; i >= 1; i--) {
				if (Arrays.equals(list_title_name[i], list_title_name[i - 1])) {
					temp.remove(i);
				}
			}
			list_title_name = temp.toArray(new Object[][] {});

			modelList = new DefaultTableModel(list_title_name, list_title_head);
			tableList = new JTable(modelList);
			tableList.setBackground(new Color(252, 241, 207));
			tableList.setFont(new Font("CookieRun Regular", Font.PLAIN, 20));
			tableList.setRowHeight(30);

			JTableHeader header = tableList.getTableHeader();
			header.setFont(new Font("CookieRun Regular", Font.PLAIN, 20));
			header.setBackground(new Color(252, 209, 108));
			header.setForeground(Color.BLACK);

			tableList.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = tableList.getSelectedRow();
					System.out.println(index);
					// for (int i = 0; i < list.size(); i++) {
					// if (index == i) {
					// title.setText(list.get(i).getQuiz_title());
					// answer_1.setText(list.get(i).getQuiz_answer_1());
					// answer_2.setText(list.get(i).getQuiz_answer_2());
					// answer_3.setText(list.get(i).getQuiz_answer_3());
					// answer_4.setText(list.get(i).getQuiz_answer_4());
					// answerRadio_5.isSelected(); // 최종답안 라디오버튼
					// // image.setText(list.get(i).getQuiz_image());
					//
				}
				//
				// }
				// }

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

			});

			scrollList = new JScrollPane(tableList);
			scrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollList.setBounds(170, 7, 160, 340);
			lPanel.add(scrollList);

		} catch (NullPointerException e) {
			// e.printStackTrace();

		}

		// 문제 리스트에서 문제를 클릭하면 해당 필드 값을 표시합니다

		// 버튼, 추가, 문제 리스트에 작성된 문제를 추가합니다
		addListBtn = new JButton("리스트에 추가") {
			@Override
			public void setBorder(Border border) {
			}
		};
		addListBtn.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		addListBtn.setBounds(170, 350, 160, 40);
		addListBtn.setBackground(new Color(255, 179, 0));
		lPanel.add(addListBtn);
		addListBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					if ((title.getText().equals("")) || (answer_1.getText().equals(""))
							|| (answer_2.getText().equals("")) || (answer_3.getText().equals(""))
							|| (answer_4.getText().equals("")) || (cookie.getText().equals(""))
							|| difficulty_result == 0 || answerRadio_5.isSelected()) {

						System.out.println("empty");
						return;
					} else {
						qv.setQuiz_set_info(null);
						qv.setQuiz_title(title.getText());
						qv.setQuiz_subject(null);
						qv.setQuiz_answer_1(answer_1.getText());
						qv.setQuiz_answer_2(answer_2.getText());
						qv.setQuiz_answer_3(answer_3.getText());
						qv.setQuiz_answer_4(answer_4.getText());
						qv.setQuiz_final_answer(final_answer);
						qv.setQuiz_difficulty(difficulty_result);
						qv.setQuiz_cookie(Integer.parseInt(cookie.getText()));
						qv.setQuiz_image(sname);
						qv.setQuiz_people(0);

						modelList.addRow(new Object[] { qv.getQuiz_title() });

						mqm.insertQuiz(qv);

						Clear();
						// showImgPanel.revalidate();
						// showImgPanel.repaint();

					}

				} catch (NullPointerException e) {
					System.out.println("null point check");
				}

			}
		});

		delListBtn = new JButton("리스트에서 삭제") {
			@Override
			public void setBorder(Border border) {
			}
		};
		delListBtn.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		delListBtn.setBounds(170, 395, 160, 40);
		delListBtn.setBackground(new Color(255, 179, 0));
		lPanel.add(delListBtn);
		delListBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				int index = tableList.getSelectedRow();
				try {

					if (index >= 0 && index <= tableList.getRowCount()) {
						modelList.removeRow(index);
						mqm.deleteQuiz(index);

						// 모든 문제 삭제하고 새로운 문제 만들면 이전 문제 기록이 그대로 나옴
						// 225번 줄에 추가되는 정렬 후 출력을 다시 불러와야함
						// list_title_name을 비워야 함 전역변수?
					}

				} catch (NullPointerException e) {
					System.out.println("리스트에서 삭제시 null값 체크");
				}
			}
		});

		// // 버튼, 수정, 문제 리스트에서 선택한 문제를 수정합니다
		// upDate = new JButton("\uC218\uC815"){
		// @Override
		// public void setBorder(Border border) {
		// }
		// };
		// upDate.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		// upDate.setBounds(170, 395, 80, 40);
		// upDate.setBackground(new Color(255, 179, 0));
		// lPanel.add(upDate);

		// 버튼, 세트로 만듬, 문제 리스트의 문제들을 세트로 만듭니다
		addSetListBtn = new JButton("세트에 추가") {
			@Override
			public void setBorder(Border border) {
			}
		};
		addSetListBtn.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		addSetListBtn.setBounds(7, 351, 160, 40);
		addSetListBtn.setBackground(new Color(255, 179, 0));
		lPanel.add(addSetListBtn);
		addSetListBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					if ((set_info.getText().equals(null)) || (subject.getText().equals(null))
							|| (people.getText().equals(0))) {
						System.out.println("empty check");

						return;

					} else {
						// 등록할 리스트 정보를 manager로 보냅니다.
						qv.setQuiz_set_info(set_info.getText());
						qv.setQuiz_title(null);
						qv.setQuiz_subject(subject.getText());
						qv.setQuiz_answer_1(null);
						qv.setQuiz_answer_2(null);
						qv.setQuiz_answer_3(null);
						qv.setQuiz_answer_4(null);
						qv.setQuiz_final_answer(null);
						qv.setQuiz_difficulty(0);
						qv.setQuiz_cookie(0);
						qv.setQuiz_image(null);
						qv.setQuiz_people(Integer.parseInt(people.getText()));

						modelSet.addRow(new Object[] { qv.getQuiz_set_info() });

						mqm.insertSet(qv);

						// 하나의 세트 등록 후 필드 초기화
						set_info.setText(null); // 세트 이름 초기화는 필요 없음
						title.setText(null); // 문제 이름 초기화
						subject.setText(null);
						answer_1.setText(null);
						answer_2.setText(null);
						answer_3.setText(null);
						answer_4.setText(null);
						answerRadio_5.setSelected(true); // 정답 라디오 버튼 초기화
						final_answer = null;
						difficulty_4.setSelected(true); // 난이도 라디오 버튼 초기화
						cookie.setText(null); // 쿠키 개수 초기화
						image.setText(null);
						people.setText(null);

					}

				} catch (NullPointerException e) {
					System.out.println("세트추가시 입력값 필드의 null값 체크 오류");
				}
				modelList.setRowCount(0);
				mqm.deleteListWhenInsertSet();

			}
		});

		// 버튼, 삭제, 세트 리스트에서 선택한 문제를 삭제합니다
		delSetListBtn = new JButton("세트에서 삭제") {
			public void setBorder(Border border) {
			}
		};
		delSetListBtn.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		delSetListBtn.setBackground(new Color(255, 179, 0));
		delSetListBtn.setBounds(7, 395, 160, 40);
		lPanel.add(delSetListBtn);
		delSetListBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				int index = tableSet.getSelectedRow();
				try {

					if (index >= 0 && index <= tableSet.getRowCount()) {
						modelSet.removeRow(index);
						mqm.deleteQuizSet(index);

					}

				} catch (NullPointerException e) {
					System.out.println("세트에서 삭제시 null 값 체크");
				}
			}

		});

		// 우측 문제 패널, 문제를 만드는 패널 입니다
		JPanel qPanel = new JPanel();
		qPanel.setBounds(354, 60, 658, 440);
		qPanel.setLayout(null);
		qPanel.setBackground(new Color(255, 234, 185));
		mqPanel.add(qPanel);

		// 우측 문제 패널, 버튼, 방을 만듬, 세트를 선택한 상태에서 버튼을 누릅니다
		startButton = new JButton("방을 만들어요") {
			@Override
			public void setBorder(Border border) {
			}
		};
		startButton.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		startButton.setBounds(498, 6, 153, 78);
		startButton.setBackground(new Color(255, 179, 0));
		qPanel.add(startButton);
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				if (Character.isDigit(tableSet.getSelectedRow()) == true) {
					System.out.println("시작하려면, 세트를 선택해야해요.");
				} else {
					ArrayList<Quiz_VO> selectSet = qd.readQuizSet();
					for (int i = 0; i < selectSet.size(); i++) {
						System.out.println(selectSet.get(i));
					}

				}
			}

		});

		// 텍스트 필드, 1세트 세트 제목을 입력받습니다
		set_info = new JTextField() {
			@Override
			public void setBorder(Border border) {
			}
		};
		set_info.setBounds(70, 7, 420, 35);
		set_info.setColumns(10);
		qPanel.add(set_info);

		// 텍스트, 문제 패널에 위치하고, 방제목이라고 적혀있습니다
		JLabel qTitleSet = new JLabel("세트명");
		qTitleSet.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		qTitleSet.setBounds(7, 7, 58, 30);
		qTitleSet.setBackground(Color.WHITE);
		qPanel.add(qTitleSet);

		// 텍스트 필드, 1문제의 문제명을 입력받습니다
		title = new JTextField() {
			@Override
			public void setBorder(Border border) {
			}
		};
		title.setBounds(70, 49, 420, 35);
		title.setColumns(10);
		qPanel.add(title);

		// 텍스트, 문제 패널에 위치하고, 문제라고 적혀있습니다
		JLabel qTitle = new JLabel("문제");
		qTitle.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		qTitle.setBounds(7, 49, 43, 30);
		qTitle.setBackground(new Color(252, 209, 108));
		qPanel.add(qTitle);

		// 문제 패널의 주제 패널, 주제를 입력합니다
		JPanel sPanel = new JPanel();
		sPanel.setBounds(498, 90, 153, 78);
		sPanel.setLayout(null);
		sPanel.setBackground(new Color(252, 209, 108));
		qPanel.add(sPanel);

		// 타이틀, 주제라고 적혀있습니다
		JLabel sTitle = new JLabel("주제");
		sTitle.setBounds(60, 0, 43, 30);
		sTitle.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		sTitle.setBackground(new Color(252, 209, 108));
		sPanel.add(sTitle);

		// 텍스트필드, 1세트 주제를 입력받는 텍스트 필드 입니다
		subject = new JTextField() {
			@Override
			public void setBorder(Border border) {
			}
		};
		subject.setBounds(7, 35, 139, 35);
		subject.setColumns(10);
		sPanel.add(subject);

		// 문제 패널의 참여 인원 패널, 참여인원 패널입니다
		JPanel pPanel = new JPanel();
		pPanel.setBounds(498, 174, 153, 50);
		pPanel.setLayout(null);
		pPanel.setBackground(new Color(252, 209, 108));
		qPanel.add(pPanel);

		// 타이틀, 명이라고 적혀있습니다.
		JLabel pTitle = new JLabel("명");
		pTitle.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		pTitle.setBounds(105, 10, 43, 30);
		pPanel.add(pTitle);

		// 텍스트필드, 1세트의 참여인원 수를 입력받는 텍스트 필드 입니다
		people = new JTextField() {
			@Override
			public void setBorder(Border border) {
			}
		};
		people.setColumns(10);
		people.setBounds(7, 7, 90, 35);
		pPanel.add(people);

		// 문제 패널의 쿠키 패널, 쿠키패널 입니다
		JPanel cPanel = new JPanel();
		cPanel.setBounds(498, 228, 153, 50);
		cPanel.setLayout(null);
		cPanel.setBackground(new Color(252, 209, 108));
		qPanel.add(cPanel);

		// 타이틀, 쿠키라고 적혀있습니다
		JLabel cTitle = new JLabel("쿠키");
		cTitle.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		cTitle.setBounds(105, 10, 43, 30);
		cTitle.setBackground(new Color(252, 209, 108));
		cPanel.add(cTitle);
		// 텍스트필드, 1문제 쿠키 개수를 입력받습니다
		cookie = new JTextField() {
			@Override
			public void setBorder(Border border) {
			}
		};
		cookie.setColumns(10);
		cookie.setBounds(7, 7, 90, 35);
		cPanel.add(cookie);

		// 문제 패널의 난이도 패널, 난이도 패널 입니다
		JPanel lvPanel = new JPanel();
		lvPanel.setBounds(498, 285, 153, 150);
		lvPanel.setLayout(null);
		lvPanel.setBackground(new Color(252, 209, 108));
		qPanel.add(lvPanel);

		// 타이틀, 난이도라고 적혀있습니다
		JLabel lvTitle = new JLabel("난이도 (초)");
		lvTitle.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		lvTitle.setBounds(30, 2, 90, 30);
		lvPanel.add(lvTitle);

		// 라디오 버튼, 상급, 난이도 패널에 추가
		difficulty_1 = new JRadioButton(" 상급 (5)");
		difficulty_1.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		difficulty_1.setBounds(15, 35, 120, 30);
		difficulty_1.setBackground(new Color(252, 209, 108));
		lvPanel.add(difficulty_1);
		difficulty_1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				difficulty_result = 5;
			}
		});

		// 라디오 버튼, 중급, 난이도 패널에 추가
		difficulty_2 = new JRadioButton(" 중급 (10)");
		difficulty_2.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		difficulty_2.setBounds(15, 75, 120, 30);
		difficulty_2.setBackground(new Color(252, 209, 108));
		lvPanel.add(difficulty_2);
		difficulty_2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				difficulty_result = 10;
			}
		});

		// 라디오 버튼, 하급,
		difficulty_3 = new JRadioButton(" 하급 (20)");
		difficulty_3.setFont(new Font("CookieRun Regular", Font.PLAIN, 18));
		difficulty_3.setBounds(15, 115, 120, 30);
		difficulty_3.setBackground(new Color(252, 209, 108));
		lvPanel.add(difficulty_3);
		difficulty_3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				difficulty_result = 20;
			}
		});

		// 라디오 버튼 선택 해제용
		difficulty_4 = new JRadioButton();

		// 난이도, 그룹화 라디오 버튼
		ButtonGroup lvGroup = new ButtonGroup();
		lvGroup.add(difficulty_1);
		lvGroup.add(difficulty_2);
		lvGroup.add(difficulty_3);
		lvGroup.add(difficulty_4);
		mqPanel.add(difficulty_4);

		// 답 패널, 답들이 위치합니다
		JPanel aPanel = new JPanel();
		aPanel.setBounds(7, 504, 1005, 210);
		aPanel.setLayout(null);
		aPanel.setBackground(new Color(255, 234, 185));
		mqPanel.add(aPanel);

		// 1번 패널 입니다
		JPanel aPanel_1 = new JPanel();
		aPanel_1.setLayout(null);
		aPanel_1.setBackground(new Color(226, 91, 69));
		aPanel_1.setBounds(10, 7, 485, 98);
		aPanel.add(aPanel_1);

		// 텍스트필드, 1번답, 1번 패널에 들어갑니다
		answer_1 = new JTextField() {
			@Override
			public void setBorder(Border border) {
			}
		};
		answer_1.setColumns(10);
		answer_1.setBackground(new Color(255, 158, 142));
		answer_1.setBounds(73, 38, 400, 50);
		aPanel_1.add(answer_1);
		// 라디오 버튼, 1번 답의 정답 여부를 체크합니다
		answerRadio_1 = new JRadioButton("정답");
		answerRadio_1.setFont(new Font("CookieRun Regular", Font.PLAIN, 17));
		answerRadio_1.setBounds(392, 5, 80, 30);
		answerRadio_1.setBackground(new Color(226, 91, 69));
		answerRadio_1.setForeground(Color.BLACK);
		aPanel_1.add(answerRadio_1);
		answerRadio_1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				final_answer = answer_1.getText();
			}
		});

		// 2번 패널 입니다
		JPanel aPanel_2 = new JPanel();
		aPanel_2.setBounds(510, 7, 485, 98);
		aPanel_2.setBackground(new Color(255, 137, 81));
		aPanel_2.setLayout(null);
		aPanel.add(aPanel_2);
		// 텍스트 필드, 2번답, 2번 패널에 들어갑니다
		answer_2 = new JTextField() {
			@Override
			public void setBorder(Border border) {
			}
		};
		answer_2.setBackground(new Color(255, 189, 166));
		answer_2.setColumns(10);
		answer_2.setBounds(73, 38, 400, 50);
		aPanel_2.add(answer_2);
		//// 라디오 버튼, 2번 답의 정답 여부를 체크합니다
		answerRadio_2 = new JRadioButton("정답");
		answerRadio_2.setFont(new Font("CookieRun Regular", Font.PLAIN, 17));
		answerRadio_2.setBounds(392, 5, 80, 30);
		answerRadio_2.setBackground(new Color(255, 137, 81));
		answerRadio_2.setForeground(Color.BLACK);
		aPanel_2.add(answerRadio_2);
		answerRadio_2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				final_answer = answer_2.getText();
			}
		});

		// 3번 패널 입니다
		JPanel aPanel_3 = new JPanel();
		aPanel_3.setLayout(null);
		aPanel_3.setBackground(new Color(137, 213, 201));
		aPanel_3.setBounds(10, 107, 485, 98);
		aPanel.add(aPanel_3);
		// 텍스트 필드, 3번답, 3번 패널에 들어갑니다
		answer_3 = new JTextField() {
			@Override
			public void setBorder(Border border) {
			}
		};
		answer_3.setColumns(10);// 텍스트 필드, 3번답, 3번 패널에 들어갑니다
		answer_3.setBackground(new Color(193, 238, 231));
		answer_3.setColumns(10);
		answer_3.setBounds(73, 38, 400, 50);
		aPanel_3.add(answer_3);
		//// 라디오 버튼 3번 답의 정답 여부를 체크합니다
		answerRadio_3 = new JRadioButton("정답");
		answerRadio_3.setFont(new Font("CookieRun Regular", Font.PLAIN, 17));
		answerRadio_3.setBounds(392, 5, 80, 30);
		answerRadio_3.setBackground(new Color(137, 213, 201));
		answerRadio_3.setForeground(Color.BLACK);
		aPanel_3.add(answerRadio_3);
		answerRadio_3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				final_answer = answer_1.getText();
			}
		});

		// 4번 패널 입니다
		JPanel aPanel_4 = new JPanel();
		aPanel_4.setLayout(null);
		aPanel_4.setBackground(new Color(173, 201, 101));
		aPanel_4.setBounds(510, 107, 485, 98);
		aPanel.add(aPanel_4);
		// 텍스트 필드, 4번답, 4번 패널에 들어갑니다
		answer_4 = new JTextField() {
			@Override
			public void setBorder(Border border) {
			}
		};
		answer_4.setColumns(10);
		answer_4.setBackground(new Color(213, 239, 144));
		answer_4.setBounds(73, 38, 400, 50);
		aPanel_4.add(answer_4);
		// 라디오 버튼, 4번 답의 정답 여부를 체크합니다

		answerRadio_4 = new JRadioButton("정답");
		answerRadio_4.setFont(new Font("CookieRun Regular", Font.PLAIN, 17));
		answerRadio_4.setBounds(392, 5, 80, 30);
		answerRadio_4.setBackground(new Color(173, 201, 101));
		aPanel_4.add(answerRadio_4);
		answerRadio_4.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				final_answer = answer_1.getText();
			}
		});

		// 라디오 버튼 선택 해제용
		answerRadio_5 = new JRadioButton();
		answerRadio_5.setSelected(true);

		// 정답, 그룹화 라디오 버튼
		ButtonGroup aRGroup = new ButtonGroup();
		aRGroup.add(answerRadio_1);
		aRGroup.add(answerRadio_2);
		aRGroup.add(answerRadio_3);
		aRGroup.add(answerRadio_4);
		aRGroup.add(answerRadio_5);
		mqPanel.add(answerRadio_5);

		// 이미지 패널
		JPanel iPanel = new JPanel();
		iPanel.setBounds(7, 90, 483, 345);
		iPanel.setLayout(null);
		iPanel.setBackground(new Color(252, 209, 108));
		qPanel.add(iPanel);

		iSubPanel = new JPanel();
		iSubPanel.setBounds(5, 5, 472, 335);
		iSubPanel.setLayout(null);
		iSubPanel.setBackground(Color.WHITE);
		iPanel.add(iSubPanel);

		showImgPanel = new JPanel();
		showImgPanel.setSize(460, 260);
		showImgPanel.setLocation(5, 5);
		showImgPanel.setBackground(Color.WHITE);
//		 showImgPanel.setLayout(null);
		iSubPanel.add(showImgPanel);

		JLabel addImgButtonLabel = new JLabel();
		addImgButtonLabel.setBounds(214, 270, 55, 55);
		addImgButtonLabel.setIcon(new ImageIcon("image/addImage.png"));
		iSubPanel.add(addImgButtonLabel);
		addImgButtonLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fc = new JFileChooser();
				int result = fc.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					sname = file.getAbsolutePath(); // THIS WAS THE PROBLEM
					JLabel im = new JLabel();
					im.setIcon(new ImageIcon(sname));

					showImgPanel.add(im);
					showImgPanel.revalidate(); // ADD THIS AS WELL
					showImgPanel.repaint(); // ADD THIS AS WELL
				}
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

		});

		//////////////////
		this.add(mqPanel);

	}

	// 메인
	public static void main(String[] args) {
		Quiz_DAO qd = new Quiz_DAO();
		JFrame jf = new JFrame();
		jf.setSize(1024, 768);
		jf.getContentPane().setLayout(null);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
//		jf.getContentPane().add(new MakingQuiz(jf, mp));

		jf.setVisible(true);

		ArrayList<Quiz_VO> list = qd.readQuizList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		} else {

			System.out.println("문제 파일을 생성해야 합니다");
		}

		System.out.println("경계선");

		ArrayList<Quiz_VO> set = qd.readQuizSet();
		if (set != null) {
			for (int i = 0; i < set.size(); i++) {
				System.out.println(set.get(i));
			}
		} else {
			System.out.println("세트 파일을 생성해야 합니다.");
		}
	}

	public void Clear() {
		// set_info.setText(null); //세트 정본
		title.setText(null);
		// subject.setText(null); //세트 정보
		answer_1.setText(null);
		answer_2.setText(null);
		answer_3.setText(null);
		answer_4.setText(null);
		answerRadio_5.setSelected(true); // 정답 라디오 버튼 초기화
		final_answer = null;
		difficulty_4.setSelected(true); // 난이도 라디오 버튼 초기화
		cookie.setText(null);
		image.setText(null);
		// people.setText(null); //세트 정보

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
