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
			oos = new ObjectOutputStream(new FileOutputStream("User.txt"));

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
			ois = new ObjectInputStream(new FileInputStream("./User.txt"));

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
			fis = new FileInputStream("User.txt");
			ois = new ObjectInputStream(fis);

			read = (ArrayList<User_VO>) ois.readObject();

			// 받아온 값
			String get;
			get = fi.getText();
			System.out.println(get + "입력값");
			String find;
			String fid;

			User_VO nuv = null;

			for (int i = 0; i < read.size(); i++) {
				// 기존 회원의 아이디, 전화번호와 비교 구문
				find = read.get(i).getUser_phone_number();
				fid = read.get(i).getUser_id();
				if (get.equals(find)) {
					// 아이디가 있을 때
					JOptionPane.showMessageDialog(null, "회원님의 아이디는 " + fid + " 입니다", "ID를 찾았습니다.",
							JOptionPane.INFORMATION_MESSAGE);
					System.out.println("회원님" + fid);
				} else if (!get.equals(find)) {
					// 아이디가 없을 때
					System.out.println("없어요");
					JOptionPane.showMessageDialog(null, "가입하신 아이디가 없습니다.", "ID가 없습니다.", JOptionPane.ERROR_MESSAGE);
				}

			}

		} catch (FileNotFoundException e) {

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
	}

	// 비밀번호 찾기

	public void findPw() {
		FileInputStream fis;
		ObjectInputStream ois;
		ArrayList<User_VO> read;

		try {
			// 값 불러오기
			fis = new FileInputStream("User.txt");
			ois = new ObjectInputStream(fis);

			try {
				read = (ArrayList<User_VO>) ois.readObject();

				for (int i = 0; i < read.size(); i++) {
					// 체크 구문
					System.out.println(read.get(i).getUser_id() + " " + read.get(i).getUser_pw() + " "
							+ read.get(i).getUser_phone_number());

					// 받아올 값 선언
					String getId;
					String getPhone;
					String findPw;
					Find_Pwd fp = null;
					getId = fp.getId();
					getPhone = fp.getPhone();

					// 회원 정보에서 비교할 변수 선언
					String dataId;
					dataId = read.get(i).getUser_id();
					String dataPhone;
					dataPhone = read.get(i).getUser_phone_number();
					String dataPw;
					dataPw = read.get(i).getUser_pw();

					if (getId.equals(dataId)) {
						if (getPhone.equals(dataPhone)) {
							// 전화번호, 아이디 모두 일치할 시, 비밀번호 수정
							String change = JOptionPane.showInputDialog(null, "새 비밀번호를 입력하세요", "비밀번호 수정",
									JOptionPane.OK_OPTION);
							dataPw = change;
							read.get(i).setUser_pw(change);

						} else {
							// 전화번호가 다른 경우
							JOptionPane.showMessageDialog(null, "입력하신 전화번호가 정보가 없습니다.", "비밀번호 오류",
									JOptionPane.ERROR_MESSAGE);
							System.out.println("전번이 다름");
						}
					} else {
						// 아이디가 다른 경우
						JOptionPane.showMessageDialog(null, "입력하신 아이디가 없습니다", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);
						System.out.println("아이디가 다름");
					}

				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}