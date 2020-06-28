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

	public Quiz_DAO() {
	}

	// 퀴즈 세트 파일을 읽어옵니다.
	public ArrayList<Quiz_VO> readQuizSet() {
		ObjectInputStream ois = null;
		ArrayList<Quiz_VO> set = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("quiz.dat"));

			set = (ArrayList<Quiz_VO>) ois.readObject();

		} catch (FileNotFoundException e) {
			// e.printStackTrace();

			System.out.println("퀴즈 세트 파일 없음");
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

	// 퀴즈 리스트의 퀴즈를 읽어옵니다.
	public ArrayList<Quiz_VO> readQuizList() {
		ObjectInputStream ois = null;
		ArrayList<Quiz_VO> list = null;

		try {
			ois = new ObjectInputStream(new FileInputStream("quizList.dat"));

			list = (ArrayList<Quiz_VO>) ois.readObject();

		} catch (FileNotFoundException e) {
			// e.printStackTrace();

			System.out.println("퀴즈 리스트 파일 없음");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
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

	// 퀴즈 리스트의 문제들을 세트로 만듭니다
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

	// 추가한 문제를 퀴즈 리스트에 추가합니다
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
