package com.kh.sopa.test;

import java.util.ArrayList;

import com.kh.sopa.controller.ObjectIO;
import com.kh.sopa.model.vo.User_VO;

public class TestRun {

	public static void main(String[] args) {
		ObjectIO oio = new ObjectIO();
		ArrayList<User_VO> userList = new ArrayList<User_VO>();
		userList.add(new User_VO("aa", "123", "010-111-1111", 5000, 3, 2, 5, 100, 80, 0, 0, 0));
//		oio.UserWriteToFile(userList);
		oio.UserWriteToFile();
		oio.UserReadToFile();
		
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
		
	}

}
