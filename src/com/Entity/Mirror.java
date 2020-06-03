package com.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.control.Animation;

public class Mirror extends Item{
	private BufferedImage[] mirror;
	private boolean moving;
	
	public Mirror() {
		init();
		
		x_position=400;
		y_position=400;
		x_size=130;
		y_size=60;
		z_size=0;
		
		moving=false;
		animation.setFrame(mirror,moving);
		
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
		
		mirror=new BufferedImage[1];
		try {
				mirror[0]=ImageIO.read(Mirror.class.getResource("/item/mirror.png"));
		} catch (IOException e) {
	
		e.printStackTrace();
		}
	}
}
