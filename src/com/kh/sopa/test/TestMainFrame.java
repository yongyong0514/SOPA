package com.kh.sopa.test;

import javax.swing.JFrame;

import com.kh.sopa.view.Login_Panel;

public class TestMainFrame {
	public static void main(String[] args) {
		JFrame f = new JFrame("realtest");
		f.setSize(1024, 768);
		f.setLayout(null);
		f.add(new Login_Panel(f));
//		f.add(new StandRoomPanelTest(f, "powerman"));
		
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
