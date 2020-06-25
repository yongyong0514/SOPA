package com.kh.sopa.model.DAO;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
		//회원 가입용 데이터 저장
		public void userOutput(User_VO uv) {
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;
			
			String nid;
			String npw;
			String npn;
			
			nid = uv.getUser_id();
			npw = uv.getUser_pw();
			npn = uv.getUser_phone_number();
			ArrayList<User_VO> write = new ArrayList ();	
			write.add(new User_VO(nid, npw, npn, 0, 0, 0, 0, 0, 0));
			
			System.out.println(write + "값 재확인");
			
			try {
				
				fos = new FileOutputStream("User.txt", true);
				oos = new ObjectOutputStream(fos);
				
				oos.writeObject(write);
							
				for(int i = 0; i < write.size(); i++) {
					System.out.println(write.get(i).getUser_id() + "  " + write.get(i).getUser_pw() + "  " + write.get(i).getUser_phone_number() + "  " + write.size() + "확인");
					System.out.println("다음줄");
				}
				oos.flush();
				fos.flush();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(oos != null) {
					if(fos != null) {
					try {
						oos.close();
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
		//파일 읽기
		public void userInput(User_VO uv) {
			FileInputStream fis = null;
			ObjectInputStream ois = null;

			try {
				fis = new FileInputStream("User.txt");
				ois = new ObjectInputStream(fis);
				
				ArrayList<User_VO> read  = (ArrayList<User_VO>)ois.readObject();

					for(int i = 0; i < read.size(); i++) {
						System.out.println(read.get(i).getUser_id() + read.get(i).getUser_pw() + read.get(i).getUser_phone_number() + "확인");
						System.out.println("다음줄");
					}
			
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if(ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
			//아이디 찾기
			public void findId(User_VO uv) {
				FileInputStream fis = null;
				ObjectInputStream ois = null;
				ArrayList<User_VO> read = null;
				
				String fineNum; //받아온 번호 값
				
				
				try {
					fis = new FileInputStream("User.txt");
					ois = new ObjectInputStream(fis);
					
					read  = (ArrayList<User_VO>)ois.readObject();
					for(int i = 0; i < read.size(); i ++) {
					
						System.out.println(read.get(i).getUser_phone_number() + "/n" + "확인");
						System.out.println("다음줄");
					
						
						String get;
						get = fi.getText();
						String find;
						find = read.get(i).getUser_phone_number();
						String fid;
						fid = read.get(i).getUser_id();
						
						
						if(get.equals(find)) {
							JOptionPane.showMessageDialog(null, "아이디는 " + fid, "ID를 찾았습니다.", JOptionPane.INFORMATION_MESSAGE);
							System.out.println("있어여" + fid);
						}
						if(!get.equals(find)) {
							System.out.println("없어요");
							JOptionPane.showMessageDialog(null, "같은 아이디가 없습니다.", "ID가 없습니다.", JOptionPane.ERROR_MESSAGE);
						}
						}
					
						//중복 값 찾기
					
						
						
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} finally {
					if(ois != null) {
						try {
							ois.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
}
			
			//비밀번호 찾기
			
			public void findPw() {
				FileInputStream fis;
				ObjectInputStream ois;
				ArrayList<User_VO> read;
				
				try {
					fis = new FileInputStream("User.txt");
					ois = new ObjectInputStream(fis);
					
					try {
						 read = (ArrayList<User_VO>) ois.readObject();
						
						for(int i = 0; i < read.size(); i++) {
						System.out.println(read.get(i).getUser_id() + " " + read.get(i).getUser_pw() + " " + read.get(i).getUser_phone_number());
						
						String getId;
						String getPhone;
						String findPw;
						
					
						Find_Pwd fp = null;
						getId = fp.getId();
						getPhone = fp.getPhone();
						
						String dataId;
						dataId =  read.get(i).getUser_id();
						String dataPhone;
						dataPhone = read.get(i).getUser_phone_number();
						String dataPw;
						dataPw = read.get(i).getUser_pw();
						
						if(getId.equals(dataId)){
							if(getPhone.equals(dataPhone)) {
								JOptionPane.showMessageDialog(null, "찾으시는 비밀번호는 "+dataPw, "비밀번호 찾음", JOptionPane.INFORMATION_MESSAGE);
								System.out.println("다 같네" + dataPw);
							} else {
								JOptionPane.showMessageDialog(null, "전화번호가 다릅니다.", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);
								System.out.println("전번이 다름");
							}
						} else  {
							JOptionPane.showMessageDialog(null, "아이디가 다릅니다.", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);
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
				//
				
			}
}