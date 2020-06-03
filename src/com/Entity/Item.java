package com.Entity;

import java.awt.Graphics;
import com.control.Animation;

public  abstract class Item {
	
	
	protected Animation animation;
	
	protected  int itemCode;
	
	protected int x_position;
	protected int y_position;
	
	protected  int x_size;
	protected  int y_size;
	protected  int z_size;
	
	public abstract void draw(Graphics g) ;
	public abstract void init();
	public  void update() {
		animation.update();
	};
	public int getYposition() {
		return y_position;
	}
	public int getXposition() {
		return x_position;
	}
	public int getYsize() {
		return y_size;
	}
	public int getXsize() {
		return x_size;
	}
	public int getZsize() {
		return z_size;
	}
	
	public void setRight(int movespeed) {
		x_size+=movespeed;
	}
	public void setLeft(int movespeed) {
		x_size-=movespeed;
	}
	public void setUp() {
		y_size++;
	}
	public int getItemCode() {
		return itemCode;
	}
	public void setMove(Player player) {
		int look=player.getLook();
		int movespeed=player.movespeed;
		
			switch(look){
			case Player.BACK:
				if(y_position>250-z_size+movespeed)
					y_position-=movespeed;	
			break;
			case Player.FRONT:
				if(y_position<600-z_size-y_size)
					y_position+=movespeed;	
			break;
			case Player.RIGHT:
				
				if(x_position<800-x_size-movespeed)
					System.out.println("a");
					x_position+=movespeed;
			
				break;
			
			case Player.LEFT:
				if(x_position>0)
				x_position-=movespeed;
				break;
			}
		
		
	}
	public void remove() {
		x_size = 0;
		y_size = 0;
		
	}
	
}
