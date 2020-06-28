package com.kh.sopa.test;

import javax.swing.JFrame;

import com.kh.sopa.controller.ObjectIO;
import com.kh.sopa.view.Login_Panel;

public class TestMainFrame {
	public static void main(String[] args) {
		ObjectIO io = new ObjectIO();
		io.QuizWriteTest();
		io.UserWriteToFile();
		
		JFrame f = new JFrame("realtest");
		f.setSize(1024, 768);
		f.setLayout(null);
//		f.add(new Login_Panel(f));
		
		f.add(new StandRoomPanelTest(f, "powerman"));
	//	f.add(new StandRoomPanelTest(f, "weakman"));

		
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
