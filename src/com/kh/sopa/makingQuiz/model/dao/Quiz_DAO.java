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
	
	// 세트파일 점검용 메소드
	public ArrayList<Quiz_VO> readQs() {
		ObjectInputStream ois = null;
		ArrayList<Quiz_VO> list = null;

		try {
			ois = new ObjectInputStream(new FileInputStream("quiz.dat"));

			list = (ArrayList<Quiz_VO>) ois.readObject();

		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("세트 파일이 없으므로 파일생성해야 합니다.");
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

	// 세트로 합치기 위해 퀴즈 리스트를 불러오는 메소드
	public ArrayList<Quiz_VO> readQuizSet() {
		ObjectInputStream ois = null;
		ArrayList<Quiz_VO> set = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("quizList.dat"));

			set = (ArrayList<Quiz_VO>) ois.readObject();

		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("문제 파일이 없으므로 파일생성해야 합니다.");
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

	// 새로운 문제를 추가하기 위해서 퀴즈 리스트를 불러오는 메소드
	public ArrayList<Quiz_VO> readQuizList() {
		ObjectInputStream ois = null;
		ArrayList<Quiz_VO> list = null;

		try {
			ois = new ObjectInputStream(new FileInputStream("quizList.dat"));

			list = (ArrayList<Quiz_VO>) ois.readObject();

		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("문제 파일이 없으므로 파일생성");
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

	// 새로운 세트명을 포함해서 퀴즈 세트를 만드는 메소드
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

	// 퀴즈 리스트에 퀴즈 1개를 등록하는 메소드
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
