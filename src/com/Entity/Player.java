package com.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Room.Room;
import com.control.Animation;

public class Player extends Item{
	
	//position state
	private  int look;
	private boolean walking;
	public int movespeed;
	private Room room;
	
	public static final int FRONT=0;
	public static final int BACK=1;
	public static final int RIGHT=2;
	public static final int LEFT=3;
	
	private static BufferedImage[] front;
	private static BufferedImage[] back;
	private static BufferedImage[] right;
	private static BufferedImage[] left;
	private static BufferedImage [] front_push;
	private static BufferedImage [] back_push;
	
	public boolean movingthing;
	
	//play state 
	//private boolean hasKey;
	
	
	public Player(Room room) {

		init();
		movingthing=false;
		this.room=room;
		//position
		x_position=300;
		y_position=200;
		//body
		
		x_size=63;
		y_size=183;
		z_size=160;
		
		walking=false;
		movespeed=5;
		setLook(FRONT);
		
		animation.setFrame(front,walking);

	}


	public void setLook(int look) {
		this.look=look;
	}
	public int getLook() {
		return look;
	}
	
	public void setMovespeed(int movespeed) {
		this.movespeed=movespeed;
	}
	
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(animation.getImage(), x_position, y_position, null);		
	}
	
	public void down() {
		setLook(FRONT);
		if(y_position+z_size<=600&room.isMovable(x_position, y_position+z_size+movespeed)&room.isMovable(x_position+x_size, y_position+z_size+movespeed)) {
			y_position+=movespeed;
			walking=true;
			
			
			animation.setFrame(front,walking);
		}
	}
	public void up() {
		setLook(BACK);
		if(y_position+z_size>220&room.isMovable(x_position, y_position+z_size-movespeed)&room.isMovable(x_position+x_size, y_position+z_size-movespeed)) {
			y_position-=movespeed;
			walking=true;
			
			animation.setFrame(back,walking);
		}
	}
	public void left() {
		setLook(LEFT);
		if(x_position>0&room.isMovable(x_position-movespeed, y_position+z_size)) {
			x_position-=movespeed;
			walking=true;
			
			animation.setFrame(left,walking);
		}
	}
	public void right() {
		setLook(RIGHT);
		if(x_position+x_size<800&room.isMovable(x_position+x_size+movespeed, y_position+z_size)) {
			x_position+=movespeed;
			walking=true;
			
			animation.setFrame(right,walking);
		}
	}
	@Override
	public void update() {
		super.update();
		walking=false;
		
		switch(look) {
		case FRONT:
			
			animation.setFrame(front,walking);
			
			break;
		case BACK:
			animation.setFrame(back,walking);
			
			break;
		case RIGHT:
			animation.setFrame(right,walking);
			break;
		case LEFT:
			animation.setFrame(left,walking);
			break;
		}
		
	}

	public void init() {
		// TODO Auto-generated method stub
		animation=new Animation(10);
		
		front=new BufferedImage[2];
		back=new BufferedImage[2];
		right=new BufferedImage[2];
		left=new BufferedImage[2];
		front_push=new BufferedImage[2];
		back_push=new BufferedImage[2];
		
		try {
			front[0]=ImageIO.read(new File("Resources/player/front1.png"));
			front[1]=ImageIO.read(new File("Resources/player/front2.png"));
			back[0]=ImageIO.read(new File("Resources/player/back1.png"));
			back[1]=ImageIO.read(new File("Resources/player/back2.png"));
			right[0]=ImageIO.read(new File("Resources/player/right1.png"));
			right[1]=ImageIO.read(new File("Resources/player/right2.png"));
			left[0]=ImageIO.read(new File("Resources/player/left1.png"));
			left[1]=ImageIO.read(new File("Resources/player/left2.png"));
			front_push[0]=ImageIO.read(new File("Resources/player/push front1.png"));
			front_push[1]=ImageIO.read(new File("Resources/player/push front2.png"));
			back_push[0]=ImageIO.read(new File("Resources/player/push back1.png"));
			back_push[1]=ImageIO.read(new File("Resources/player/push back2.png"));
		} catch (IOException e) {e.printStackTrace();}
	}
	
}
