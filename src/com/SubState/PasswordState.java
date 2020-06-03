package com.SubState;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.control.Keys;

import state.PlayState;

public class PasswordState extends SubState {

	BufferedImage[] image = new BufferedImage[1];
	private int[] password = new int[4];
	private int i;
	private int flagx;
	private int flagy;

	public PasswordState() {
		i=0;
		flagx=294;
		flagy=240;
		System.out.println("enter");
		for (int i = 0; i < password.length; i++) {

			password[i] = 0;
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {


		// TODO Auto-generated method stub
		if (Keys.getState(Keys.UP) && !Keys.isPressed(Keys.UP)) {
			password[i]++;
			if (password[i] > 9) {
				password[i] = 9;
			}
		} else if (Keys.getState(Keys.DOWN) && !Keys.isPressed(Keys.DOWN)) {
			password[i]--;
			if (password[i] < 0) {
				password[i] = 0;
			}
		} else if (Keys.getState(Keys.RIGHT) && !Keys.isPressed(Keys.RIGHT)) {
			i++;
			flagx+=58;
			if (i > 3) {
				flagx=471;
				i = 3;
			}
		} else if (Keys.getState(Keys.LEFT) && !Keys.isPressed(Keys.LEFT)) {
			i--;
			flagx-=58;
			if (i < 0) {
				flagx=290;
				i = 0;
			}
		}
	}
	
	public String getPassword(){
		String a = String.valueOf(password[0]);
		String b = String.valueOf(password[1]);
		String c = String.valueOf(password[2]);
		String d = String.valueOf(password[3]);
		return a+b+c+d;
	}

	@Override
	public void draw(java.awt.Graphics g) {
		// TODO Auto-generated method stub

		
		g.setColor(Color.BLACK);
		Font f=new Font("Times New Roman",Font.BOLD/Font.ITALIC,60);
		g.setFont(f);
		try {
			g.drawImage(ImageIO.read(PasswordState.class.getResource("/item/numberp-35.png")),flagx, flagy, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawString(String.valueOf(password[0]), 287, 315);
		g.drawString(String.valueOf(password[1]), 346, 315);
		g.drawString(String.valueOf(password[2]), 404, 315);
		g.drawString(String.valueOf(password[3]), 463, 315);
	}

}
