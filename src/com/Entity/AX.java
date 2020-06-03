package com.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.control.Animation;

public class AX extends Item{
	private BufferedImage[] ax;
	private boolean moving;
	
	public AX() {
		init();
		
		x_position=200;
		y_position=340;
		x_size=75;
		y_size=75;
		z_size=0;
		
		moving=false;
		animation.setFrame(ax,moving);
		
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
		
		ax=new BufferedImage[1];
		try {
				ax[0]=ImageIO.read(AX.class.getResource("/item/ax.png"));
		} catch (IOException e) {
	
		e.printStackTrace();
		}
	}

}
