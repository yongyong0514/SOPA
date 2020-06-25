package com.kh.sopa.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kh.sopa.model.DAO.SolvingQuizDao;
import com.kh.sopa.model.vo.Quiz_VO;

public class SolvingQuiz extends JFrame implements ActionListener {

	long start = System.currentTimeMillis();
	// 시작전에 SolvingQuizDao(파일 입출력 클래스) 한 번 실행 시킨 후 실행

	private SolvingQuizDao qd = new SolvingQuizDao();
	private ArrayList<Quiz_VO> quizList2 = qd.readQuizList();
	
	private JPanel contentPane;
	private JButton btn_quiz_answer_1;
	private JButton btn_quiz_answer_2;
	private JButton btn_quiz_answer_3;
	private JButton btn_quiz_answer_4;

	// 세트에서 푼 문제 수
	private int solved_qnumInSet = 0;
	// 세트에서 맞춘 문제 수
	private int correct_qnumInSet = 0;

	// 난이도별 초를 담아줄 배열
	private int[] sec = new int[quizList2.size()];
	private int cnt;
	private int correct_num = 0;

	// 푼 문제까지 초의 합
	private double sum = 0;
	
	public SolvingQuiz() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBounds(100, 100, 1024, 768);
		contentPane.setBackground(new Color(254, 228, 167));
		setContentPane(contentPane);

		// 상단바
		JPanel timePanel = new JPanel();
		timePanel.setLayout(null);
		timePanel.setLocation(40, 10);
		timePanel.setSize(940, 50);
		timePanel.setBackground(Color.WHITE);

		// 상단바 위 시간 흐르는 라벨
		JLabel timeLabel = new JLabel();
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		timeLabel.setBounds(50, 5, 850, 40);
		timeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));

		// 상단 오른쪽의 그만해요 버튼
		JButton stopBtn = new JButton();
		stopBtn.setBounds(840, 0, 100, 50);
		stopBtn.setBorderPainted(false);
		stopBtn.setBackground(new Color(255, 179, 0));

		// 그만해요 라벨
		JLabel stopLabel = new JLabel("그만해요<<");

		// 문제 수 표기 라벨
		JLabel quiz_num_lb = new JLabel();
		quiz_num_lb.setHorizontalAlignment(JLabel.CENTER);
		quiz_num_lb.setBounds(440, 410, 150, 45);
		quiz_num_lb.setOpaque(true);
		quiz_num_lb.setBackground(new Color(252, 209, 108));
		quiz_num_lb.setFont(new Font("맑은 고딕", Font.BOLD, 25));

		// 응답 수 표기 라벨
		/*
		 * JLabel answer_num_lb = new JLabel();
		 * answer_num_lb.setHorizontalAlignment(JLabel.CENTER);
		 * answer_num_lb.setText("응답 0 / 20"); answer_num_lb.setBounds(820, 410, 160,
		 * 45); answer_num_lb.setOpaque(true); answer_num_lb.setBackground(new
		 * Color(252, 209, 108)); answer_num_lb.setFont(new Font("맑은 고딕", Font.BOLD,
		 * 25));
		 */

		// 문제출제 패널
		JPanel quizPanel = new JPanel();
		quizPanel.setLayout(null);
		quizPanel.setBounds(40, 70, 940, 330);
		quizPanel.setBackground(Color.WHITE);

		// 문제가 적혀질 라벨
		JLabel quizLabel = new JLabel();
		quizLabel.setHorizontalAlignment(JLabel.CENTER);
		quizLabel.setBounds(80, 10, 800, 300);
		quizLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		/*
		 * quizLabel.setOpaque(true); //라벨크기 확인용 색깔넣어보기
		 * quizLabel.setBackground(Color.YELLOW);
		 */

		// 문제버튼

		// 문제버튼 1
		btn_quiz_answer_1 = new JButton();
		btn_quiz_answer_1.setBorderPainted(false);
		btn_quiz_answer_1.setBounds(40, 475, 455, 110);
		btn_quiz_answer_1.setBackground(new Color(226, 91, 69));
		btn_quiz_answer_1.setFont(new Font("맑은 고딕", Font.BOLD, 25));

		// 문제버튼 2
		btn_quiz_answer_2 = new JButton();
		btn_quiz_answer_2.setBorderPainted(false);
		btn_quiz_answer_2.setBounds(525, 475, 455, 110);
		btn_quiz_answer_2.setBackground(new Color(225, 131, 87));
		btn_quiz_answer_2.setFont(new Font("맑은 고딕", Font.BOLD, 25));

		// 문제버튼 3
		btn_quiz_answer_3 = new JButton();
		btn_quiz_answer_3.setBorderPainted(false);
		btn_quiz_answer_3.setBounds(40, 600, 455, 110);
		btn_quiz_answer_3.setBackground(new Color(137, 213, 201));
		btn_quiz_answer_3.setFont(new Font("맑은 고딕", Font.BOLD, 25));

		// 문제버튼 4
		btn_quiz_answer_4 = new JButton();
		btn_quiz_answer_4.setBorderPainted(false);
		btn_quiz_answer_4.setBounds(525, 600, 455, 110);
		btn_quiz_answer_4.setBackground(new Color(172, 201, 101));
		btn_quiz_answer_4.setFont(new Font("맑은 고딕", Font.BOLD, 25));

		// 패널, 라벨 , 버튼 등 추가

		timePanel.add(timeLabel);
		timePanel.add(stopBtn);
		stopBtn.add(stopLabel);
		contentPane.add(quiz_num_lb);
		/* contentPane.add(answer_num_lb); */
		quizPanel.add(quizLabel);

		this.add(btn_quiz_answer_1);
		this.add(btn_quiz_answer_2);
		this.add(btn_quiz_answer_3);
		this.add(btn_quiz_answer_4);
		this.add(timePanel);
		this.add(quizPanel);

		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 그만해요 버튼 누르면 화면 전환
		stopBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 임시 클래스 이동
				new ExtraPanel().setVisible(true);
				dispose();
			}

		});

		int timesec = 0;
		int quiz_num_ing = 1;

		// 문제 난이도별 초가 담기는 배열 생성
		for (int i = 0; i < quizList2.size(); i++) {
			sec[i] = quizList2.get(i).getQuiz_difficulty();
		}

		// 보기 버튼별 액션 설정 여부 입력
				btn_quiz_answer_1.addActionListener(this);
				btn_quiz_answer_2.addActionListener(this);
				btn_quiz_answer_3.addActionListener(this);
				btn_quiz_answer_4.addActionListener(this);

		
		int correct_num_sum = 0;
		// 담긴 문제를 반복문 통해서 돌림
		for (cnt = 0; cnt < quizList2.size(); cnt++) {

			// 난이도별 시간을 변수에 담음
			timesec = sec[cnt];

			// 진행한 문제 수 / 전체 문제수
			quiz_num_lb.setText((quiz_num_ing + cnt) + " / " + quizList2.size());

			// 문제출제 라벨에 문제 넣어주기
			quizLabel.setText(quizList2.get(cnt).getQuiz_title());

			// 보기 버튼에 보기 넣어주기
			btn_quiz_answer_1.setText(quizList2.get(cnt).getQuiz_answer_1());
			btn_quiz_answer_2.setText(quizList2.get(cnt).getQuiz_answer_2());
			btn_quiz_answer_3.setText(quizList2.get(cnt).getQuiz_answer_3());
			btn_quiz_answer_4.setText(quizList2.get(cnt).getQuiz_answer_4());

			while (true) {

				try {
					timeLabel.setText("" + timesec + "초 남았습니다");
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				timesec--;

				if (timesec == 0) {
					break;
				}
			}
			//다음 문제 넘어갈 때 다시 버튼 활성화
			btn_quiz_answer_1.setEnabled(true);
			btn_quiz_answer_2.setEnabled(true);
			btn_quiz_answer_3.setEnabled(true);
			btn_quiz_answer_4.setEnabled(true);	

			
						
		}
		
		//맞춘 문제수 확인 
		correct_num_sum += correct_num;
		System.out.println("맞춘 문제 수 : " + correct_num_sum);
		
	}

	// 보기 버튼 별 액션
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_quiz_answer_1 || e.getSource() == btn_quiz_answer_2
				|| e.getSource() == btn_quiz_answer_3 || e.getSource() == btn_quiz_answer_4) {
			
			//보기가 선택되면 더이상 선택할 수 없도록 함
				btn_quiz_answer_1.setEnabled(false);
				btn_quiz_answer_2.setEnabled(false);
				btn_quiz_answer_3.setEnabled(false);
				btn_quiz_answer_4.setEnabled(false);
				
			if (e.getSource() == btn_quiz_answer_1) {
				if (btn_quiz_answer_1.getText().equals(quizList2.get(cnt).getQuiz_final_answer())) {
					System.out.println("정답");
					correct_num++;
				} else {
					System.out.println("오답");
				}
			} else if (e.getSource() == btn_quiz_answer_2) {
				if (btn_quiz_answer_2.getText().equals(quizList2.get(cnt).getQuiz_final_answer())) {
					System.out.println("정답");
					correct_num++;
				} else {
					System.out.println("오답");
				}
			} else if (e.getSource() == btn_quiz_answer_3) {
				if (btn_quiz_answer_3.getText().equals(quizList2.get(cnt).getQuiz_final_answer())) {
					System.out.println("정답");
					correct_num++;
				} else {
					System.out.println("오답");
				}

			} else if (e.getSource() == btn_quiz_answer_4) {
				if (btn_quiz_answer_4.getText().equals(quizList2.get(cnt).getQuiz_final_answer())) {
					System.out.println("정답");
					correct_num++;
				} else {
					System.out.println("오답");
				}
			}
		}

	//총 푼 문제수 = 세트 사이즈 넘기기 , 총 맞춘 문제 수 = user  정보로 넘기기
		
	//문제별 초 더하기
		if (cnt != 0) {
			// 지금 푸는 문제 이전의 문제들에 할당된 초의 합
			// sec[] 배열 : 문제 난이도별 초의 배열
			double pre_sec = 0;

			for (int i = 0; i < cnt; i++) {
				pre_sec += sec[i];
			}

			long Click = System.currentTimeMillis();
			// 두번째 문제부터의 초값 변수 선언 및 초기화
			double clickTime = Double.parseDouble(String.format("%.2f", ((Click - start) / 1000.0) - pre_sec));
			sum += clickTime;
			System.out.println((cnt + 1) + "번째 문제 마우스 클릭한 시간 : " + clickTime);
			System.out.println("지금까지 푼 문제들의 초 합  : " + sum);

		} else {
			long Click = System.currentTimeMillis();
			// 첫번째 문제의 초값 변수 선언 및 초기화
			double clickTime = Double.parseDouble(String.format("%.2f", ((Click - start) / 1000.0)));
			System.out.println((cnt + 1) + "번째 문제 마우스 클릭한 시간 : " + clickTime);
			sum += clickTime;
			System.out.println("지금까지 푼 문제들의 초 합 : " + sum);

		}
		
	}

	public static void main(String[] args) {
		new SolvingQuiz();
	}

}

