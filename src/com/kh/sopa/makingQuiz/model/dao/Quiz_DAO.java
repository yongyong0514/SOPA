package com.kh.sopa.makingQuiz.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.kh.sopa.model.vo.Quiz_VO;

public class Quiz_DAO {

	public Quiz_DAO() {}
	
	// ��Ʈ���� ���˿� �޼ҵ�
	public ArrayList<Quiz_VO> readQs() {
		ObjectInputStream ois = null;
		ArrayList<Quiz_VO> list = null;

		try {
			ois = new ObjectInputStream(new FileInputStream("quiz.dat"));

			list = (ArrayList<Quiz_VO>) ois.readObject();

		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("��Ʈ ������ �����Ƿ� ���ϻ����ؾ� �մϴ�.");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	// ��Ʈ�� ��ġ�� ���� ���� ����Ʈ�� �ҷ����� �޼ҵ�
	public ArrayList<Quiz_VO> readQuizSet() {
		ObjectInputStream ois = null;
		ArrayList<Quiz_VO> set = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("quiz.dat"));

			set = (ArrayList<Quiz_VO>) ois.readObject();

		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("���� ������ �����Ƿ� ���ϻ����ؾ� �մϴ�.");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return set;
	}

	// ���ο� ������ �߰��ϱ� ���ؼ� ���� ����Ʈ�� �ҷ����� �޼ҵ�
	public ArrayList<Quiz_VO> readQuizList() {
		ObjectInputStream ois = null;
		ArrayList<Quiz_VO> list = null;

		try {
			ois = new ObjectInputStream(new FileInputStream("quizList.dat"));

			list = (ArrayList<Quiz_VO>) ois.readObject();

		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("���� ������ �����Ƿ� ���ϻ���");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	// ���ο� ��Ʈ���� �����ؼ� ���� ��Ʈ�� ����� �޼ҵ�
	public static void writeSet(ArrayList<Quiz_VO> set) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("quiz.dat"));

			oos.writeObject(set);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// ���� ����Ʈ�� ���� 1���� ����ϴ� �޼ҵ�
	public static void writeQuiz(ArrayList<Quiz_VO> list) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("quizList.dat"));

			oos.writeObject(list);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
