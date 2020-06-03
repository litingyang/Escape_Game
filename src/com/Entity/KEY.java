package com.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.control.Animation;

public class KEY extends Entity {
	private BufferedImage[] key;
	
	public void update() {
		super.update();
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(animation.getImage(), x_position, y_position, null);
	}
	
	public void init() {
		animation=new Animation(10);
		
		key=new BufferedImage[1];
		try {
				key[0]=ImageIO.read(AX.class.getResource("/item/toolboxkey.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
