package com.kh.sopa.test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientTest {
	private BufferedReader in;
	private static PrintWriter out;
	private static JTextField j1 = new JTextField(50);
	private static JTextArea a1 = new JTextArea(10, 50);
	private static Socket socket;
	private JScrollPane s = new JScrollPane(a1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	public Date cd;
	private int myId = 0;
	
	public ClientTest() {
//		JFrame f = new JFrame("클라이언트")
	}
}
