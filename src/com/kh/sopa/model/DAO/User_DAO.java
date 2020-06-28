package com.kh.sopa.model.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;

import com.kh.sopa.controller.LoginController;
import com.kh.sopa.controller.ObjectIO;
import com.kh.sopa.model.vo.User_VO;
import com.kh.sopa.view.Find_Id;
import com.kh.sopa.view.Find_Pwd;
import com.kh.sopa.view.Sign_Up;

//유저관련 데이터 액세스 오브젝트 
public class User_DAO {

	private Sign_Up su;
	private User_VO uu;
	private LoginController lc;
	private Find_Id fi;

	// 회원 가입용 데이터 저장
	public void userOutput(User_VO uv) {

		ObjectOutputStream oos = null;

		// 게터에서 받아서 저장할 문자열
		String nid;
		String npw;
		String npn;

		// 문자열 변수 저장
		nid = uv.getUser_id();
		npw = uv.getUser_pw();
		npn = uv.getUser_phone_number();

		// 받아온 값, user-vo에 넣음
		ArrayList<User_VO> write = new ArrayList();
		write.add(new User_VO(nid, npw, npn, 0, 0, 0, 0, 0, 0, 0, 0, 0));

		System.out.println(write + "값 재확인");

		try {

			// 값 저장
			oos = new ObjectOutputStream(new FileOutputStream("user.txt"));

			oos.writeObject(write);

			for (int i = 0; i < write.size(); i++) {
				// 값 확인
				System.out.println(write.get(i).getUser_id() + "  " + write.get(i).getUser_pw() + "  "
						+ write.get(i).getUser_phone_number() + "  " + write.size() + "확인");
				System.out.println("다음줄");
			}
			oos.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 종료
			if (oos != null) {
				try {
					oos.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 파일 읽기
	public ArrayList<User_VO> userInput() {

		ObjectInputStream ois = null;
		ArrayList<User_VO> read = null;

		try {

			// 파일 읽기
			ois = new ObjectInputStream(new FileInputStream("./user.txt"));

			read = (ArrayList<User_VO>) ois.readObject();

			// 값 체크용 구문
			int result = 0;
			for (int i = 0; i < read.size(); i++) {
				System.out.println(read.get(i).getUser_id() + read.get(i).getUser_pw()
						+ read.get(i).getUser_phone_number() + "확인");
				result++;
				System.out.println("누적 : " + result);
				System.out.println("다음줄");
			}

		} catch (FileNotFoundException e) {
			System.out.println("파일 없음");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 종료
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return read;
	}

	// 아이디 찾기
	public void findId() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ArrayList<User_VO> read = null;

		String fineNum; // 받아온 번호 값

		try {
			// 값 불러오기
			fis = new FileInputStream("user.txt");
			ois = new ObjectInputStream(fis);

//			read = (ArrayList<User_VO>) ois.readObject();
			read = new ObjectIO().UserReadToFile();

			// 받아온 값
			String inputPhoneNumber;
			inputPhoneNumber = fi.getText();
			System.out.println(inputPhoneNumber + "입력값");
			
			String find;
			String fid;

			User_VO nuv = new User_VO();
			boolean existUser = false;

			for (int i = 0; i < read.size(); i++) {
				// 기존 회원의 아이디, 전화번호와 비교 구문
				if (read.get(i).getUser_phone_number().equals(inputPhoneNumber)) {
					existUser = true;
					nuv.setUser_id(read.get(i).getUser_id());
					break;
				}
			}
			
			if (existUser) {
				// 아이디가 있을 때
				JOptionPane.showMessageDialog(null, "회원님의 아이디는 " + nuv.getUser_id() + " 입니다", "ID를 찾았습니다.",
						JOptionPane.INFORMATION_MESSAGE);
				System.out.println("회원님" + nuv.getUser_id());
			}
			else {
				// 아이디가 없을 때
				System.out.println("없어요");
				JOptionPane.showMessageDialog(null, "가입하신 아이디가 없습니다.", "ID가 없습니다.", JOptionPane.ERROR_MESSAGE);
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 종료
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 비밀번호 찾기

	public void findPw() {
		FileInputStream fis;
		ObjectInputStream ois;
		ArrayList<User_VO> read;

		try {
			// 값 불러오기
			fis = new FileInputStream("user.txt");
			ois = new ObjectInputStream(fis);

			read = new ObjectIO().UserReadToFile();

			String getId = null;
			String getPhone = null;
			String findpw = null;
			
			String dataId = null;
			String dataPhone = null;
			String dataPw = null;
			boolean existUser = false;
			
			User_VO tmp = new User_VO();
			for (int i = 0; i < read.size(); i++) {
				// 체크 구문
				System.out.println(read.get(i).getUser_id() + " " + read.get(i).getUser_pw() + " "
						+ read.get(i).getUser_phone_number());

				// 받아올 값 선언
				Find_Pwd fp = null;
				getId = fp.getId();
				getPhone = fp.getPhone();

				// 회원 정보에서 비교할 변수 선언
				dataId = read.get(i).getUser_id();
				dataPhone = read.get(i).getUser_phone_number();
				dataPw = read.get(i).getUser_pw();

				if (getId.equals(dataId)) {
					existUser = true;
					tmp.setUser_id(read.get(i).getUser_id());
					tmp.setUser_pw(read.get(i).getUser_pw());
					tmp.setUser_phone_number(read.get(i).getUser_phone_number());
					break;
				}
			}
			if (existUser) {
				if (getPhone.equals(tmp.getUser_phone_number())) {
					// 전화번호, 아이디 모두 일치할 시, 비밀번호 수정
					String change = JOptionPane.showInputDialog(null, "새 비밀번호를 입력하세요", "비밀번호 수정",
							JOptionPane.OK_OPTION);
					dataPw = change;
					System.out.println("입력값 : " + dataPw);
					tmp.setUser_pw(dataPw);
					ArrayList<User_VO> userList = new ArrayList<User_VO>();
					userList = new ObjectIO().UserReadToFile();
					
					// compare and arraylist change;
					for (int i = 0; i < userList.size(); i++) {
						if (userList.get(i).getUser_id().equals(tmp.getUser_id())) {
							System.out.println("h");
							userList.get(i).setUser_pw(tmp.getUser_pw());
						}
					}
					// save to file
					new ObjectIO().UserWriteToFile(userList);
					
					JOptionPane.showMessageDialog(null, "변경되었습니다.");
				} else {
					// 전화번호가 다른 경우
					JOptionPane.showMessageDialog(null, "입력하신 전화번호가 정보가 없습니다.", "비밀번호 오류",
							JOptionPane.ERROR_MESSAGE);
					System.out.println("전번이 다름");
				}
			}
			else {
				// 아이디가 다른 경우
				JOptionPane.showMessageDialog(null, "입력하신 아이디가 없습니다", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);
				System.out.println("아이디가 다름");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}