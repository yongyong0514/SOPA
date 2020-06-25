package com.kh.sopa.test;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameRoomTest extends JPanel{
	public GameRoomTest() {
		JLabel label = new JLabel("대기방");
		this.add(label);
		label.setBounds(100, 100, 100, 100);
		this.setBackground(Color.RED);
		this.setBounds(0, 0, 1024, 430);
//		this.setLayout(null);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(1024, 768);
		f.add(new GameRoomTest());
		f.setLayout(null);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}