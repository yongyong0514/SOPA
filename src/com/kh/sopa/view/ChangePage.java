package com.kh.sopa.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.kh.sopa.controller.ObjectIO;
import com.kh.sopa.model.DAO.User_DAO;
import com.kh.sopa.model.vo.User_VO;

public class ChangePage extends JFrame {
	String user_id;
	
	public ChangePage(String user_id) {
	
		//정보수정 프레임
		JFrame change = new JFrame("정보를 수정해요");
		change.setBounds(300,300, 400,300);
		change.setLayout(null);
		change.setVisible(true);
		change.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	    //sopa 라벨
	     JLabel sopa = new JLabel("S.O.P.A");
	     sopa.setBounds(115, 20, 200, 40);
	     sopa.setFont(new Font("바탕", Font.ITALIC, 40));
	     change.add(sopa);

		
	     
	     
	    //sopa 계정을 수정해요
	    JLabel text = new JLabel("계정을 수정해요");
	     text.setBounds(115, 65, 300, 30);
	     text.setFont(new Font("바탕", Font.ITALIC, 20));
	     change.add(text);

	     
	     
	     
	   //비밀번호 수정입력창
	     JTextField pw = new JTextField(10);
	      pw.setBounds(100, 105, 200, 40);
	      pw.setFont(new Font("바탕", Font.ITALIC, 12));
	     
	      
	      
	    
	      change.add(pw);

		
	      //전화번호 입력창
	      JTextField phone = new JTextField(10);
	      phone.setBounds(100, 155, 200, 40);
	      phone.setFont(new Font("바탕", Font.ITALIC, 12));
	      
	      change.add(phone);
	      
	      
	      //확인 버튼
	      JButton back = new JButton("확인");
	      back.setBounds(120, 205, 110, 30);
	      back.setFont(new Font("바탕", Font.ITALIC, 13));
	      change.add(back);
	   
	      back.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String pwc =  pw.getText();
					String phonec = phone.getText();
					
					ArrayList<User_VO> userList = new ArrayList<User_VO>();
					userList = new User_DAO().userInput();
					
					System.out.println();
					
					for(int i = 0; i < userList.size(); i++) {
						if(user_id.equals(userList.get(i).getUser_id())) {
							userList.get(i).setUser_pw(pwc);
							userList.get(i).setUser_phone_number(phonec);
							
						}
						
					}
				
				}

	       });
	      
	      // 1.클릭 -> Mypage 화면   
	      back.addMouseListener(new MouseAdapter() {
	      @Override 
	      public void mouseClicked(MouseEvent arg0) {
	    	  
	    	  change.dispose();
	    	  super.mouseClicked(arg0);
	      }
	 });
	 }
		
		

		
		

		
		
	
		
		
		

		
	
	/*
	public static void main(String[] args) {
		 ChangePage cp = new ChangePage(user_id);
		 
		
	}*/

}
