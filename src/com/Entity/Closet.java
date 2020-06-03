package com.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.control.Animation;

public class Closet extends Item {
	private BufferedImage[] closet_close;
	private BufferedImage[] closet_open;
	private boolean moving;
	private int look = 0;
	public static final int CLOSE = 0;
	public static final int OPEN = 1;
	
	public Closet() {
		init();
		
		x_position=300;
		y_position=40;
		x_size=133;
		y_size=40;
		z_size=180;
		
		moving=false;
		animation.setFrame(closet_close,moving);
		
	}
	private void setLook(int look) {
		this.look = look;
	}
	public void update() {
		super.update();
		
		switch (look) {
		case CLOSE:
			x_position=300;
			x_size=140;
			animation.setFrame(closet_close, moving);
			break;
			
		case OPEN:
			x_position=272;
			x_size=190;
			animation.setFrame(closet_open, moving);
			break;
		}
	}
	public void setOpen() {
		setLook(OPEN);
		animation.setFrame(closet_open, moving);
	}

	public void setClose() {
		setLook(CLOSE);
		animation.setFrame(closet_close, moving);
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if (look == CLOSE) {
			x_position=300;
			animation.setFrame(closet_close, moving);
			g.drawImage(animation.getImage(), x_position, y_position, null);
		}else{
			x_position=272;
			animation.setFrame(closet_open, moving);		
			g.drawImage(animation.getImage(), x_position, y_position, null);
		}

	}
	public void init() {
		animation=new Animation(10);
		
		closet_close=new BufferedImage[1];
		closet_open=new BufferedImage[1];
		try {
				closet_close[0]=ImageIO.read(Bed.class.getResource("/item/closet.png"));
				closet_open[0]=ImageIO.read(Bed.class.getResource("/item/closet_open.png"));
		} catch (IOException e) {
	
		e.printStackTrace();
		}
	}

}
