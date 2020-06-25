package com.kh.sopa.run;


import java.io.IOException;

import com.kh.sopa.view.Sever_view;

public class Server_run {

	public static void main(String[] args) {
		try {
			new Sever_view();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
