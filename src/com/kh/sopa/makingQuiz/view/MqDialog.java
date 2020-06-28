package com.kh.sopa.makingQuiz.view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.Timer;

public class MqDialog implements ActionListener {

	private MakingQuiz mq = new MakingQuiz();
	private static final int TIME_START = 1;
	private int count = TIME_START;
	private Timer timer = new Timer(1500, this);
	private JDialog dialog = new JDialog();

	public static void nullListDialog() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MqDialog().createNullListDialog();
			}
		});
	}
	
	public static void nullSetDialog() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MqDialog().createNullSetDialog();
			}
		});
	}

	public static void notSelect() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MqDialog().createNoSelectDialog();
			}
		});
	}

	public void createNullSetDialog() {
		timer.setCoalesce(false);
		JLabel imageLabel = new JLabel();
		Image icon = new ImageIcon("image/nullCheckSet.JPG").getImage().getScaledInstance(600, 100, 0);
		imageLabel.setIcon(new ImageIcon(icon));
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		dialog.add(imageLabel);
		dialog.pack();
		dialog.setVisible(true);
		dialog.setBounds(410, 300, 600, 170);
		timer.start();
	}
	
	public void createNullListDialog() {
		timer.setCoalesce(false);
		JLabel imageLabel = new JLabel();
		Image icon = new ImageIcon("image/nullCheckList.JPG").getImage().getScaledInstance(600, 100, 0);
		imageLabel.setIcon(new ImageIcon(icon));
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		dialog.add(imageLabel);
		dialog.pack();
		dialog.setVisible(true);
		dialog.setBounds(410, 300, 600, 170);
		timer.start();
	}

	public void createNoSelectDialog() {
		timer.setCoalesce(false);
		JLabel imageLabel = new JLabel();
		Image icon = new ImageIcon("").getImage().getScaledInstance(180, 180, 0);
		imageLabel.setIcon(new ImageIcon(icon));
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		dialog.add(imageLabel);
		dialog.pack();
		dialog.setVisible(true);
		dialog.setBounds(410, 300, 220, 240);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		count--;
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
