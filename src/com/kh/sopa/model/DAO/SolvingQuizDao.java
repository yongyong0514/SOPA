package com.kh.sopa.model.DAO;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.kh.sopa.model.vo.Quiz_VO;


//문제 풀기 용 입출력 파일 클래스
public class SolvingQuizDao {
	
	//문제, 정답 파일 읽어오기 클래스
	public ArrayList<Quiz_VO> readQuizList() {

		InputStream in = null;
		BufferedInputStream bin = null;
		ObjectInputStream ois = null;
		
		//읽어온 파일을 다시 담을 배열 생성
		ArrayList<Quiz_VO> quizList2 = null;
		
		try {

		/*	in = new FileInputStream("quiz.dat");
			bin = new BufferedInputStream(in);*/
			
			ois = new ObjectInputStream(new FileInputStream("solvingquiz.txt"));

			quizList2 = (ArrayList<Quiz_VO>) ois.readObject();

			//파일의 내용이 배열에 잘 저장됐는지 확인용 for문
			for (Quiz_VO obj : quizList2) {
				System.out.println(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				ois.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return quizList2;
	}
	
	//문제 풀기 확인용 파일 저장 클래스
	public void fileSave() {

		//문제와 정답을 입력할  ArrayList<Quiz_VO> 생성
		Quiz_VO qv = new Quiz_VO();
		ArrayList<Quiz_VO> quizList = new ArrayList<Quiz_VO>();



		quizList.add(new Quiz_VO("", "H2O란?", "상식", "주스", "물", "커피", "콜라", "물", 10, 15, "", 5));
		quizList.add(new Quiz_VO("","객체지향 프로그램이 아닌 것은?", "프로그래밍", "자바", "파이썬", "c#", "c언어", "c언어", 15, 20, "", 3));
		quizList.add(new Quiz_VO("","다음 중 과일은?", "상식", "사과", "당근", "스마트폰", "감자", "사과", 5, 10, "", 4));

	
		
		System.out.println(quizList);
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream("solvingquiz.txt");
			oos = new ObjectOutputStream(new FileOutputStream("solvingquiz.txt"));

			oos.writeObject(quizList);
			oos.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		SolvingQuizDao qd = new SolvingQuizDao();
		qd.fileSave();
		qd.readQuizList();
 
	}
	
	
}
