package com.kh.sopa.test;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerTest {
	private ArrayList<PrintWriter> out = new ArrayList<PrintWriter>();
	private JTextField j1 = new JTextField(50);
	private JTextArea a1 = new JTextArea(10, 50);
	private JScrollPane s = new JScrollPane(a1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private int connectCount = 0;
	public static Date cd;
	
	public ServerTest() {
		JFrame f = new JFrame("서버");
		j1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				System.out.println("key press test");
			}
		});
		a1.setEditable(false);
		f.setLayout(new BorderLayout());
		f.add(j1, BorderLayout.SOUTH);
		f.add(s, BorderLayout.CENTER);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setVisible(true);
		s.setVisible(true);
	}
	
	// not yet 2
	class get_t extends Thread { 
		public void run() {
			
		}
	}
	
	private class Ssth extends Thread {
		private PrintWriter pw = null;
		private Socket socket;
		private int myId;
		
		public Ssth(Socket socket, int clientId) {
			connectCount++;
			this.socket = socket;
			this.myId = clientId;
			try {
				pw = new PrintWriter(socket.getOutputStream());
				out.add(pw);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pw.println(myId);
				String ib = "사용자" + out.size() + " 님이 입장하셨습니다.";
				a1.append("\n" + ib + "\n");
				a1.setCaretPosition(a1.getDocument().getLength());
				for (int i = 0; i < out.size()-1; i++) {
					PrintWriter a = out.get(i);
					a.println("0+" + ib);
				}
				while (true) {
					String id = null, mes = null, res = null;
					res = br.readLine();
					id = res.split("\\+")[0];
					mes = res.split("\\+")[1];
					// not add cd (realtime date)
					a1.append("사용자" + id + " : " + mes + "   -" + cd + "\n");
					a1.setCaretPosition(a1.getDocument().getLength());
					for (int i = 0; i < out.size(); i++) {
						if (Integer.parseInt(id) -1 != i) {
							PrintWriter a = out.get(i);
							a.println(res);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
