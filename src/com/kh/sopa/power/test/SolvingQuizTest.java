package com.kh.sopa.power.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kh.sopa.controller.Client_Controller;
import com.kh.sopa.makingQuiz.model.dao.Quiz_DAO;
import com.kh.sopa.makingQuiz.view.CookieFont;
import com.kh.sopa.model.vo.Quiz_VO;
import com.kh.sopa.model.vo.User_VO;
import com.kh.sopa.view.JdialogAuto1;
import com.kh.sopa.view.ResultPage;

public class SolvingQuizTest extends JPanel implements ActionListener {

   // 게임 시작시간 측정 (문제푸는 데 걸린 총 시간 구하기 위해)
   long start = System.currentTimeMillis();

   // 시작전에 SolvingQuizDao(파일 입출력 클래스) 한 번 실행 시킨 후 실행
   private Client_Controller client = null;
   private User_VO uv = new User_VO();
   private Quiz_DAO qd = new Quiz_DAO();
   
   //수정 부분
   private ArrayList<Quiz_VO> quizList = null;
   
   private ArrayList<Quiz_VO> set = new ArrayList<Quiz_VO>();
  
   
   private JButton btn_quiz_answer_1;
   private JButton btn_quiz_answer_2;
   private JButton btn_quiz_answer_3;
   private JButton btn_quiz_answer_4;
   private JFrame mf = null;
   // 한 세트에서 푼 문제 수
   private int solved_qnumInSet = 0;
   // 한 세트에서 맞춘 문제 수
   private int correct_qnumInSet = 0;
   // 문제 별 정답 여부 (맞으면 +1)
   private int correct_num = 0;
   // 문제 정답시 획득한 쿠키수
   private int cookie_num = 0;
   // 한 세트에서 얻은 총 쿠키수
   private int got_cookie_InSet = 0;
   // 한 세트에서 문제푸는데 걸린 총 시간의 합
   private long amountOfSecInSet = 0;
   // 문제가 담겨진 배열의 인덱스
   private int cnt;
   // 플레이어가 서버로 보낼 모든 문제 푼 시간 시간
   private long result_user_gaming_time = 0;
   // 유저 id
   private String user_id = "";
   private JPanel thispage;
   // 난이도별 초를 담아줄 배열
   
   // 수정부분
   private int[] sec = null;
   private boolean ishead_hear = true;
   // 누르는 시간 마다 측정
   private long clickTime = 0;

   //쿠키런 폰트 선언
   private CookieFont fm = new CookieFont();
   
   
   private String roomTitle = null;
   
   public SolvingQuizTest() {
      this.setBackground(Color.RED);
      this.setBounds(0, 0, 1024, 300);

   }

   // 수정 부분 생성자 매개변수 추가
   public SolvingQuizTest(JFrame jf, Client_Controller client, int roomnumber, String title) {
      //쿠키런 폰트 받기 위해
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      fm.fontChange(ge);
      
      // 수정
      this.roomTitle = title;
      quizList = new Quiz_DAO().readQuizSet();
      System.out.println("HHH : " + quizList.size());
      for (int i = 0; i < quizList.size(); i++) {
    	  if (roomTitle.equals(quizList.get(i).getQuiz_set_info())) {
    		  set.add(quizList.get(i));
    	  }
      }
      
      //수정
      sec = new int[set.size()];
      
      this.client = client;
      this.mf = jf;
      this.thispage = this;

      this.setLayout(null);
      this.setBounds(0, 0, 1024, 768);
      // this.setBackground(Color.RED);

      JPanel bigPanel = new JPanel();
      bigPanel.setLayout(null);
      bigPanel.setBounds(0, 0, 1024, 768);
      bigPanel.setBackground(new Color(254, 228, 167));

      // 상단바
      JPanel timePanel = new JPanel();
      timePanel.setLayout(null);

      timePanel.setLocation(40, 10);
      timePanel.setSize(940, 50);
      timePanel.setBackground(Color.WHITE);

      // 상단바 위 시간 흐르는 라벨
      JLabel timeLabel = new JLabel();
      timeLabel.setHorizontalAlignment(JLabel.CENTER);
      timeLabel.setBounds(360, 5, 250, 40);
      timeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
      timePanel.add(timeLabel, "Center");
      Image icon = new ImageIcon("image/minitimer.PNG").getImage().getScaledInstance(40, 40, 0);
      JLabel timerLabel = new JLabel();
      timerLabel.setIcon(new ImageIcon(icon));
      timerLabel.setBounds(372, 6, 40, 40);
      timePanel.add(timerLabel);
      bigPanel.add(timePanel);

      // 상단 오른쪽의 그만해요 버튼
      JButton stopBtn = new JButton("그만해요");
      stopBtn.setBounds(840, 0, 100, 50);
      stopBtn.setBorderPainted(false);
      stopBtn.setBackground(new Color(255, 179, 0));

      timePanel.add(stopBtn);

      // 문제 수 표기 라벨
      JLabel quiz_num_lb = new JLabel();
      quiz_num_lb.setHorizontalAlignment(JLabel.CENTER);
      quiz_num_lb.setBounds(440, 410, 150, 45);
      quiz_num_lb.setOpaque(true);
      quiz_num_lb.setBackground(new Color(252, 209, 108));
      quiz_num_lb.setFont(new Font("맑은 고딕", Font.BOLD, 25));

      bigPanel.add(quiz_num_lb);

      // 문제출제 패널
      JPanel quizPanel = new JPanel();
      quizPanel.setLayout(null);
      quizPanel.setBounds(40, 70, 940, 330);
      quizPanel.setBackground(Color.WHITE);

      bigPanel.add(quizPanel);

      // 문제가 적혀질 라벨
      JLabel quizLabel = new JLabel();
      quizLabel.setHorizontalAlignment(JLabel.CENTER);
      quizLabel.setBounds(80, 10, 800, 300);
      quizLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));

      quizPanel.add(quizLabel);

      // 문제버튼

      // 문제버튼 1
      btn_quiz_answer_1 = new JButton();
      btn_quiz_answer_1.setBorderPainted(false);
      // 보기 버튼 왼쪽 작은 표시
      Image minicircle = new ImageIcon("image/circle.PNG").getImage().getScaledInstance(50, 50, 0);
      JLabel circleLabel = new JLabel();
      circleLabel.setIcon(new ImageIcon(minicircle));
      btn_quiz_answer_1.setBounds(40, 475, 455, 110);
      btn_quiz_answer_1.setBackground(new Color(226, 91, 69));
      btn_quiz_answer_1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      btn_quiz_answer_1.add(circleLabel);
      
      // 문제버튼 2
      btn_quiz_answer_2 = new JButton();
      btn_quiz_answer_2.setBorderPainted(false);
      // 보기 버튼 왼쪽 작은 표시
      Image minix = new ImageIcon("image/x.PNG").getImage().getScaledInstance(50, 50, 0);
      JLabel minixLabel = new JLabel();
      minixLabel.setIcon(new ImageIcon(minix));
      btn_quiz_answer_2.setBounds(525, 475, 455, 110);
      btn_quiz_answer_2.setBackground(new Color(225, 131, 87));
      btn_quiz_answer_2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      btn_quiz_answer_2.add(minixLabel);
      
      // 문제버튼 3
      btn_quiz_answer_3 = new JButton();
      btn_quiz_answer_3.setBorderPainted(false);
      // 보기 버튼 왼쪽 작은 표시
      Image minitriangle = new ImageIcon("image/triangle.PNG").getImage().getScaledInstance(50, 50, 0);
      JLabel minitriangleLabel = new JLabel();
      minitriangleLabel.setIcon(new ImageIcon(minitriangle));
      btn_quiz_answer_3.setBounds(40, 600, 455, 110);
      btn_quiz_answer_3.setBackground(new Color(137, 213, 201));
      btn_quiz_answer_3.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      btn_quiz_answer_3.add(minitriangleLabel);
      
      // 문제버튼 4
      btn_quiz_answer_4 = new JButton();
      btn_quiz_answer_4.setBorderPainted(false);
      // 보기 버튼 왼쪽 작은 표시
      Image minirectangle = new ImageIcon("image/rectangle.PNG").getImage().getScaledInstance(50, 50, 0);
      JLabel minirectangleLabel = new JLabel();
      minirectangleLabel.setIcon(new ImageIcon(minirectangle));
      btn_quiz_answer_4.setBounds(525, 600, 455, 110);
      btn_quiz_answer_4.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      btn_quiz_answer_4.add(minirectangleLabel);
      btn_quiz_answer_4.setBackground(new Color(172, 201, 101));
      
      bigPanel.add(btn_quiz_answer_1);
      bigPanel.add(btn_quiz_answer_2);
      bigPanel.add(btn_quiz_answer_3);
      bigPanel.add(btn_quiz_answer_4);

      this.add(bigPanel);

      // int timesec = 0;
      int quiz_num_ing = 1;

      // 문제 난이도별 초가 담기는 배열 생성
      for (int i = 0; i < set.size(); i++) {
         sec[i] = set.get(i).getQuiz_difficulty();
      }

      // 보기 버튼별 액션 설정 여부 입력
      btn_quiz_answer_1.addActionListener(this);
      btn_quiz_answer_2.addActionListener(this);
      btn_quiz_answer_3.addActionListener(this);
      btn_quiz_answer_4.addActionListener(this);

      class quiz_thread extends Thread {

         public void run() {
            // 담긴 문제를 반복문 통해서 돌림
            for (cnt = 0; cnt < set.size(); cnt++) {
               // 난이도별 시간을 변수에 담음
               int timesec = sec[cnt];
               // 진행한 문제 수 / 전체 문제수
               quiz_num_lb.setText((1 + cnt) + " / " + set.size());
               // 문제출제 라벨에 문제 넣어주기
               quizLabel.setText("[" + set.get(cnt).getQuiz_subject() + "] " + set.get(cnt).getQuiz_title());
               // 보기 버튼에 보기 넣어주기
               btn_quiz_answer_1.setText(set.get(cnt).getQuiz_answer_1());
               btn_quiz_answer_2.setText(set.get(cnt).getQuiz_answer_2());
               btn_quiz_answer_3.setText(set.get(cnt).getQuiz_answer_3());
               btn_quiz_answer_4.setText(set.get(cnt).getQuiz_answer_4());

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
               // 다음 문제 넘어갈 때 다시 버튼 활성화
               btn_quiz_answer_1.setEnabled(true);
               btn_quiz_answer_2.setEnabled(true);
               btn_quiz_answer_3.setEnabled(true);
               btn_quiz_answer_4.setEnabled(true);

            }
            correct_qnumInSet += correct_num;
            System.out.println("맞춘 문제 수 : " + correct_qnumInSet);
            System.out.println("얻은 총 쿠키 수 : " + got_cookie_InSet);
            System.out.println("지금까지 푼 문제들의 초 합 : " + amountOfSecInSet);

            uv.setUser_all_quiz(uv.getUser_all_quiz() + set.size());
            uv.setUser_correct_quiz(uv.getUser_correct_quiz() + correct_qnumInSet);
            uv.setUser_cookie(uv.getUser_cookie() + got_cookie_InSet);
            uv.setUser_gaming_cookie(got_cookie_InSet);
            uv.setUser_gaming_correct_quiz(correct_qnumInSet);
            user_id = client.getNicknames();

            String msg = uv.getUser_gaming_cookie() + "/" + uv.getUser_gaming_correct_quiz() + "/"
                  + result_user_gaming_time + "/" + cnt + "/" + roomnumber + "/" + user_id;

            client.result_game_sendMessage(msg);

            while (ishead_hear) {
               boolean head_hear = client.get_ishead_hear();
               if (head_hear == false) {
                  mf.remove(thispage);
                  String[] msg_result = client.get_split_result();
                  System.out.println("넘어가는 값 확인" + msg_result[1]);
                  ResultPage resultpage = new ResultPage(mf, msg_result);
                  mf.add(resultpage);
                  mf.repaint();
                  mf.setVisible(true);

                  ishead_hear = false;
               }
            }

         }
      }
      new quiz_thread().start();

      // 맞춘 문제수 확인

      // 이번 세트에서 푼 문제수와 맞춘 문제수를 유저의 정보에 합산하여 넘겨준다.

   }

   public static void main(String[] args) {

      JFrame jf = new JFrame();
      jf.setSize(1024, 768);
      jf.setLayout(null);
      jf.setResizable(false);
      jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
      //jf.add(new SolvingQuiz(jf));

      jf.setVisible(true);
   }

   // 보기 버튼 별 액션
   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == btn_quiz_answer_1 || e.getSource() == btn_quiz_answer_2
            || e.getSource() == btn_quiz_answer_3 || e.getSource() == btn_quiz_answer_4) {

         // 보기가 선택되면 더이상 선택할 수 없도록 함
         btn_quiz_answer_1.setEnabled(false);
         btn_quiz_answer_2.setEnabled(false);
         btn_quiz_answer_3.setEnabled(false);
         btn_quiz_answer_4.setEnabled(false);
         JdialogAuto1 ja = new JdialogAuto1();
         if (e.getSource() == btn_quiz_answer_1) {
            if (btn_quiz_answer_1.getText().equals(set.get(cnt).getQuiz_final_answer())) {
               System.out.println("정답");
               ja.dialogCorrect();
               correct_num++;
               cookie_num += set.get(cnt).getQuiz_cookie();
               System.out.println("쿠키 획득 : " + set.get(cnt).getQuiz_cookie());
               got_cookie_InSet += set.get(cnt).getQuiz_cookie();
               calSec();

            } else {
               System.out.println("오답");
               cookie_num = 0;
               got_cookie_InSet += 0;
               ja.dialogWrong();
            }
         } else if (e.getSource() == btn_quiz_answer_2) {
            if (btn_quiz_answer_2.getText().equals(set.get(cnt).getQuiz_final_answer())) {
               System.out.println("정답");
               ja.dialogCorrect();
               correct_num++;
               cookie_num += set.get(cnt).getQuiz_cookie();
               System.out.println("쿠키 획득 : " + set.get(cnt).getQuiz_cookie());
               got_cookie_InSet += set.get(cnt).getQuiz_cookie();
               calSec();

            } else {
               System.out.println("오답");
               cookie_num = 0;
               got_cookie_InSet += 0;
               ja.dialogWrong();
            }
         } else if (e.getSource() == btn_quiz_answer_3) {
            if (btn_quiz_answer_3.getText().equals(set.get(cnt).getQuiz_final_answer())) {
               System.out.println("정답");
               ja.dialogCorrect();
               correct_num++;
               cookie_num += set.get(cnt).getQuiz_cookie();
               System.out.println("쿠키 획득 : " + set.get(cnt).getQuiz_cookie());
               got_cookie_InSet += set.get(cnt).getQuiz_cookie();
               calSec();

            } else {
               System.out.println("오답");
               cookie_num = 0;
               got_cookie_InSet += 0;
               ja.dialogWrong();
            }

         } else if (e.getSource() == btn_quiz_answer_4) {
            if (btn_quiz_answer_4.getText().equals(set.get(cnt).getQuiz_final_answer())) {
               System.out.println("정답");
               ja.dialogCorrect();
               correct_num++;
               cookie_num += set.get(cnt).getQuiz_cookie();
               System.out.println("쿠키 획득 : " + set.get(cnt).getQuiz_cookie());
               got_cookie_InSet += set.get(cnt).getQuiz_cookie();
               got_cookie_InSet += 0;
               calSec();

            } else {
               System.out.println("오답");
               cookie_num = 0;
               got_cookie_InSet += 0;
               ja.dialogWrong();
            }
         }

      }
   }

   // 정답일 때 초 계산하는 메소드
   void calSec() {
      if (cnt != 0) {
         // 지금 푸는 문제 이전의 문제들에 할당된 초의 합
         // sec[] 배열 : 문제 난이도별 초의 배열
         long pre_sec = 0;

         for (int i = 0; i < cnt; i++) {
            pre_sec += sec[i];
         }

         long Click = System.currentTimeMillis();
         // 두번째 문제부터의 초값 변수 선언 및 초기화
         clickTime = (long) (((Click - start) / 1000) - pre_sec);
         System.out.println((cnt + 1) + "번째 문제 마우스 클릭한 시간 : " + clickTime);

      } else {
         long Click = System.currentTimeMillis();
         // 첫번째 문제의 초값 변수 선언 및 초기화
         clickTime = (long) ((Click - start) / 1000);
         System.out.println((cnt + 1) + "번째 문제 마우스 클릭한 시간 : " + clickTime);

      }
      uv.setUser_gaming_time((long) amountOfSecInSet);
      result_user_gaming_time = uv.getUser_gaming_time();
   }
}