package com.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.control.Animation;

public class Bed extends Item {
	private BufferedImage[] bed;
	private boolean moving;
	
	public Bed() {
		init();
		
		x_position=0;
		y_position=275;
		x_size=263;
		y_size=150;
		z_size=75;
		
		moving=false;
		animation.setFrame(bed,moving);
		
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
		
		bed=new BufferedImage[1];
		try {
				bed[0]=ImageIO.read(Bed.class.getResource("/item/bed.png"));
		} catch (IOException e) {
	
		e.printStackTrace();
		}
	}

}
