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

   private SolvingQuiz sq = new SolvingQuiz();
   private static final int TIME_START = 1;
   private int count = TIME_START;
   private Timer timer = new Timer(500, this);
   private JDialog dialog = new JDialog();
   private JOptionPane optPane = new JOptionPane();
   private int num;
   private final JLabel label = new JLabel(message());
   private JLabel label1 = new JLabel(message1());
   
    public static void dialog() {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                new JdialogAuto().createDialog();
            }
        });
    }
    
    public static void dialog1() {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                new JdialogAuto().falseDialog();
            }
        });
    }
    
    public void falseDialog() {
       //Image icon = new ImageIcon("images/user.PNG").getImage().getScaledInstance(150, 150, 0);
             timer.setCoalesce(false);
             optPane.setMessage(label1);
             label1.setHorizontalAlignment(JLabel.CENTER);
             label1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
             label1.setForeground(Color.red);
             dialog.add(optPane);
             dialog.pack();
             dialog.setVisible(true);
             dialog.setBounds(380, 320 , 260, 180);
             timer.start();
    }
      
    
   
   public void createDialog( ) {
      //Image icon = new ImageIcon("images/user.PNG").getImage().getScaledInstance(150, 150, 0);
      timer.setCoalesce(false);
      optPane.setMessage(label);
      label.setHorizontalAlignment(JLabel.CENTER);
      label.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      label.setForeground(Color.red);
      dialog.add(optPane);
      dialog.pack();
      dialog.setVisible(true);
      dialog.setBounds(380, 320 , 260, 180);
      timer.start();
   }
   

   private String message() {
      return "정답!!!";
   
   }
   
   private String message1() {
      return "오답!!!";
   
   
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      count--;
        label.setText(message());
        if (count == 0) {
           thatsAllFolks();
        }
        timer.restart();
    }
   

   
   private void thatsAllFolks() {
       dialog.setVisible(false);
       dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    }
   
   
}