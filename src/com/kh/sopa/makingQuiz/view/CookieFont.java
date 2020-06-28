package com.kh.sopa.makingQuiz.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class CookieFont {

	public void fontChange(GraphicsEnvironment ge) {
		String path = "CookieRunRegular.ttf";

		try {
			Font recipe = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(12f);

			ge.registerFont(recipe);

		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
