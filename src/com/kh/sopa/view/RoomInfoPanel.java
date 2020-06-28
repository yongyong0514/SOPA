package com.kh.sopa.view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kh.sopa.makingQuiz.model.dao.Quiz_DAO;
import com.kh.sopa.model.vo.Quiz_VO;


// 대기방 들어갔을 때 방 정보 뜨는 패널
public class RoomInfoPanel extends JPanel {
	JFrame mainFrame = null;
	JPanel mainPanel = null;
	JPanel thisPage = null;

	
	public RoomInfoPanel() {}
	
	public RoomInfoPanel(JFrame mf, JPanel mp, String setName) {
		this.mainFrame = mf;
		this.mainPanel = mp;
		this.thisPage = this;
		
	/*	JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setBounds(0,0,1024,768);*/
		
		//방 정보 패널
		JPanel roomInfoPanel = new JPanel();
		roomInfoPanel.setLayout(null);
		roomInfoPanel.setBounds(724, 515, 250, 180);
		roomInfoPanel.setBackground(new Color(252, 209, 108));

		//"지금 입장한 방은요"가 적힐 패널
		JPanel roomNowPanel = new JPanel();
		roomNowPanel.setLayout(null);
		roomNowPanel.setBounds(10, 10, 220, 30);
			
		
		//"지금 입장한 방은요" 라벨
		JLabel roomNowLabel = new JLabel();
		roomNowLabel.setText("지금 입장한 방은요");
		roomNowLabel.setHorizontalAlignment(JLabel.CENTER);
		roomNowLabel.setBounds(10, 10, 230, 30 );
		roomNowLabel.setOpaque(true);
		roomNowLabel.setBackground(new Color(252, 209, 108));
		
		roomInfoPanel.add(roomNowLabel);

		
		//방제목라벨
		JLabel roomTitleLabel = new JLabel();
		roomTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		roomTitleLabel.setBounds(10, 50, 230, 30);
		roomTitleLabel.setOpaque(true);
		roomTitleLabel.setBackground(new Color(254, 228, 167));
		
		
		//문항수라벨
		JLabel numInSetLabel = new JLabel("문항 수");
		numInSetLabel.setHorizontalAlignment(JLabel.CENTER);
		numInSetLabel.setBounds(10, 95, 110, 30);
		numInSetLabel.setOpaque(true);
		numInSetLabel.setBackground(new Color(254, 228, 167));
		
		roomInfoPanel.add(numInSetLabel);

		//문항수표기라벨
		JLabel numInSetDisplayLabel = new JLabel();
		numInSetDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
		numInSetDisplayLabel.setBounds(130, 95, 110, 30);
		numInSetDisplayLabel.setOpaque(true);
		numInSetDisplayLabel.setBackground(new Color(254, 228, 167));
		
		roomInfoPanel.add(numInSetDisplayLabel);

		
		//주제라벨
		JLabel setSubjectLabel = new JLabel("주제");
		setSubjectLabel.setHorizontalAlignment(JLabel.CENTER);
		setSubjectLabel.setBounds(10, 135, 110, 30);
		setSubjectLabel.setOpaque(true);
		setSubjectLabel.setBackground(new Color(254, 228, 167));
		
		roomInfoPanel.add(setSubjectLabel);

		//세트주제표기라벨
		JLabel setSubjectDisplayLabel = new JLabel("출제자");
		setSubjectDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
		setSubjectDisplayLabel.setBounds(130, 135, 110, 30);
		setSubjectDisplayLabel.setOpaque(true);
		setSubjectDisplayLabel.setBackground(new Color(254, 228, 167));
		
		roomInfoPanel.add(setSubjectDisplayLabel);
		
		
		
		
		//방제목( 세트제목 ), 문제수, 문제 주제 담기
		
		Quiz_DAO qd = new Quiz_DAO();
		ArrayList<Quiz_VO> set = qd.readQuizSet();
		
		for(int i = 0; i < set.size(); i++) {
			if(setName.equals(set.get(i).getQuiz_set_info())) {
				
	
			}
		}
		
		roomTitleLabel.setText("");
		numInSetDisplayLabel.setText("");
		
		roomInfoPanel.add(roomTitleLabel);
		
/*		
		jf.add(roomInfoPanel);
		
		jf.setVisible(true);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		*/
		
	
	}
	
	
	
	
	public static void main(String[] args) {
		RoomInfoPanel rif = new RoomInfoPanel();
		

	}

}
