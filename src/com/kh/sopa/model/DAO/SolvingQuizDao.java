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

//占쏙옙占쏙옙 풀占쏙옙 占쏙옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙 클占쏙옙占쏙옙
public class SolvingQuizDao {
	
	
	//占쏙옙占쏙옙, 占쏙옙占쏙옙 占쏙옙占쏙옙 占싻억옙占쏙옙占� 클占쏙옙占쏙옙
	public ArrayList<Quiz_VO> readQuizList() {

		InputStream in = null;
		BufferedInputStream bin = null;
		ObjectInputStream ois = null;
		
		//占싻억옙占� 占쏙옙占쏙옙占쏙옙 占쌕쏙옙 占쏙옙占쏙옙 占썼열 占쏙옙占쏙옙
		ArrayList<Quiz_VO> quizList2 = null;
		
		try {

			in = new FileInputStream("solvingquiz.txt");
			bin = new BufferedInputStream(in);
			ois = new ObjectInputStream(bin);

			quizList2 = (ArrayList<Quiz_VO>) ois.readObject();

			//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占썼열占쏙옙 占쏙옙 占쏙옙占쏙옙틈占쏙옙占� 확占싸울옙 for占쏙옙
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
	
	//占쏙옙占쏙옙 풀占쏙옙 확占싸울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 클占쏙옙占쏙옙
	public void fileSave() {

		//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쌉뤄옙占쏙옙  ArrayList<Quiz> 占쏙옙占쏙옙
		ArrayList<Quiz_VO> quizList = new ArrayList<Quiz_VO>();

		quizList.add(new Quiz_VO("H2O란?", "주스", "물", "커피", "콜라", "물", 10));
		quizList.add(new Quiz_VO("객체지향 프로그램이 아닌 것은?", "자바", "파이썬", "c#", "c언어", "c언어", 20));
		quizList.add(new Quiz_VO("다음 중 과일은?", "사과", "당근", "스마트폰", "감자", "사과", 20));
		
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
