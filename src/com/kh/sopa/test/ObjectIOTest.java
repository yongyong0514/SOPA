//package com.kh.sopa.test;
//
//import java.io.EOFException;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.util.ArrayList;
//
//import com.kh.sopa.model.vo.Quiz_VO;
//import com.kh.sopa.model.vo.User_VO;
//
//public class ObjectIOTest {
//	
//	public void UserWriteToFile() {
//		User_VO tmp1 = new User_VO("powerman", "1234", "010-1123-1234", 
//				5000, 3, 2, 5, 100, 80);
//		User_VO tmp2 = new User_VO("weakman", "5678", "010-2245-6621", 
//				2000, 1, 2, 3, 120, 30);
//		
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		try {
//			fos = new FileOutputStream("user.txt");
//			oos = new ObjectOutputStream(fos);
//			
//			oos.writeObject(tmp1);
//			oos.writeObject(tmp2);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (oos != null) {
//					oos.close();
//				}
//				if (fos != null) {
//					fos.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	public ArrayList<User_VO> UserReadToFile() {
//		FileInputStream fis = null;
//		ObjectInputStream ois = null;
//		
//		ArrayList<User_VO> userList = new ArrayList<User_VO>();
//		try {
//			fis = new FileInputStream("user.txt");
//			ois = new ObjectInputStream(fis);
//			
//			while(true) {
//				try {
//					User_VO tmp = (User_VO) ois.readObject();
//					
//					if(tmp instanceof User_VO)
//						userList.add((User_VO) tmp);
//				} catch (EOFException e) {
//					break;
//				}
//			}
//		} 
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (ois != null) {
//					ois.close();
//				}
//				if (fis != null) {
//					fis.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		for (int i = 0; i < userList.size(); i++) {
//			System.out.println(userList.get(i));
//		}
//		
//		return userList;
//	}
//	
//	public void QuizWriteTest() {
//		Quiz_VO tmp1 = new Quiz_VO("java", "친구가 델리만쥬를 달라고 한다.", "상식", "1개", "2개", "3개", "4개",
//				"2", 30, 20, "", 4);
//		Quiz_VO tmp2 = new Quiz_VO("C++", "사용 가능한 템플릿", "수학", "1개", "2개", "3개", "4개", 
//				"2", 10, 15, "", 4);
//		Quiz_VO tmp3 = new Quiz_VO("Python", "객체지향 프로그래밍", "과학", "1개", "2개", "3개", "4개", 
//				"2", 20, 10, "", 4);
//		
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		
//		try {
//			fos = new FileOutputStream("quiz.txt");
//			oos = new ObjectOutputStream(fos);
//			
//			oos.writeObject(tmp1);
//			oos.writeObject(tmp2);
//			oos.writeObject(tmp3);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(oos != null) {
//					oos.close();
//				}
//				if(fos != null) {
//					fos.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	
//	public ArrayList<Quiz_VO> QuizReadTest() {
//		FileInputStream fis = null;
//		ObjectInputStream ois = null;
//		ArrayList<Quiz_VO> quizList = new ArrayList<Quiz_VO>();
//		
//		try {
//			fis = new FileInputStream("quiz.txt");
//			ois = new ObjectInputStream(fis);
//			
//			while(true) {
//				try {
//					Quiz_VO tmp = (Quiz_VO) ois.readObject();
//					
//					if (tmp instanceof Quiz_VO) {
//						quizList.add(tmp);
//					}
//					
//				} catch (EOFException e) {
//					break;
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(ois != null) {
//					ois.close();
//				}
//				if(fis != null) {
//					fis.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		for (int i = 0; i < quizList.size(); i++) {
//			System.out.println(quizList.get(i));
//		}
//		
//		return quizList;
//	}
//}
