package com.kh.sopa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.kh.sopa.controller.Sever_Controller;

public class Sever_view extends JFrame {
	private JTextArea jta = new JTextArea(40, 25);
	private JTextField jtf = new JTextField(25);
	private Sever_Controller server = new Sever_Controller();

	public Sever_view() throws IOException {
		setBounds(200, 100, 400, 600);
		setTitle("서버");

		jta.setEditable(false);
		jta.setFont(new Font("고딕체", Font.PLAIN, 18));
		jta.setBackground(new Color(230, 255, 230));

		add(jta, BorderLayout.CENTER);
		add(jtf, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		server.setGui(this);
		server.setting();
	}

}
