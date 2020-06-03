package com.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.control.Animation;

public class Lock extends Item{

	private BufferedImage[] lock;
	public Lock() {
		 init() ;
			x_position=70;
			y_position=350;
			x_size=90;
			y_size=96;
			z_size=2;
		
			animation.setFrame(lock,false);

	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(animation.getImage(), x_position, y_position, null);
	}
	public void setMove(Player player) {
		
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		animation=new Animation(10);
		
		lock=new BufferedImage[1];
		try {
				lock[0]=ImageIO.read(Door.class.getResource("/item/lock.png"));
		} catch (IOException e) {
	
		e.printStackTrace();
		}
	}
	public void update() {
		super.update();
		
	}
}
