package com.kh.sopa.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

// 문제 풀 때 정답시 정답 알려주는 다이얼로그 호출

public class JdialogAuto implements ActionListener {

	private static final int TIME_START = 1;
	private int count = TIME_START;
	private Timer timer = new Timer(500, this);
	private JDialog dialog = new JDialog();
	
	 public static void dialogCorrect() {
	        EventQueue.invokeLater(new Runnable() {

	            public void run() {
	                new JdialogAuto().createCorrectDialog();
	            }
	        });
	    }
		
	    public static void dialogWrong() {
	        EventQueue.invokeLater(new Runnable() {

               public void run() {
                   new JdialogAuto().createWrongDialog();
               }
           });
       }
    
    public void createWrongDialog() {

    	timer.setCoalesce(false);
		JLabel imageLabel = new JLabel();
		Image icon = new ImageIcon("image/miniwrong.PNG").getImage().getScaledInstance(180, 180, 0);
		imageLabel.setIcon(new ImageIcon(icon));
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		dialog.add(imageLabel);
		dialog.pack();
		dialog.setVisible(true);
		dialog.setBounds(410, 300 , 220, 240);
		timer.start();
    }
      	
	public void createCorrectDialog( ) {
		timer.setCoalesce(false);
		JLabel imageLabel = new JLabel();
		Image icon = new ImageIcon("image/minicorrect.PNG").getImage().getScaledInstance(180, 180, 0);
		imageLabel.setIcon(new ImageIcon(icon));
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		dialog.add(imageLabel);
		dialog.pack();
		dialog.setVisible(true);
		dialog.setBounds(410, 300 , 220, 240);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		count--;
        if (count == 0) {
        	disappearDialog();
        }
        timer.restart();
    }
		
	private void disappearDialog() {
 	   dialog.setVisible(false);
 	   dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    }
   
   
}