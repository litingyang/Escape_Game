package com.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.control.Animation;

public class Door extends Item{

	private BufferedImage[] door;
	private boolean moving;
	
	public Door() {
		init();
		
		x_position=575;
		y_position=49;
		x_size=90;
		y_size=20;
		z_size=150;
		
		moving=false;
		animation.setFrame(door,moving);
		
	}
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
		
		door=new BufferedImage[1];
		try {
				door[0]=ImageIO.read(Door.class.getResource("/item/door.png"));
		} catch (IOException e) {
	
		e.printStackTrace();
		}
	}

}
