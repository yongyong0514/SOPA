package com.kh.sopa.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.kh.sopa.makingQuiz.model.dao.Quiz_DAO;
import com.kh.sopa.model.vo.Quiz_VO;

public class TestRun {

	public static void main(String[] args) {
//		ObjectIO oio = new ObjectIO();
//		ArrayList<User_VO> userList = new ArrayList<User_VO>();
//		userList.add(new User_VO("aa", "123", "010-111-1111", 5000, 3, 2, 5, 100, 80, 0, 0, 0));
//		oio.UserWriteToFile(userList);
//		oio.UserWriteToFile();
//		oio.UserReadToFile();
		
//		oio.UserWriteToFile();
//		oio.UserReadToFile();
		
//		oio.QuizWriteTest();
//		oio.QuizReadTest();
		
//		StandRoomPanelTest srpt = new StandRoomPanelTest("powerman");
//		srpt.panelTest();
		
//		JFrame f = new JFrame();
//		f.setSize(1024, 768);
//		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
//		f.add(new Login_Panel(f));
//		f.setVisible(true);
		
		Quiz_DAO qd = new Quiz_DAO();
		ArrayList<Quiz_VO> quizList = new ArrayList<Quiz_VO>();
		quizList = qd.readQuizSet();
		HashSet<String> quizSet = new HashSet<String>();
		
		for (int i = 0; i < quizList.size(); i++) {
//			System.out.println(quizList.get(i));
			quizSet.add(quizList.get(i).getQuiz_set_info());
		}
		
		Iterator iter = quizSet.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
//		qd.readQuizList();
//		qd.readQuizSet();
	}

}
