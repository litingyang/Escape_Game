package com.Entity;

import java.awt.Graphics;


import com.control.Animation;

public  abstract class Entity {
	
	
	protected Animation animation;
	
	protected int x_position;
	protected int y_position;
	
	protected  int x_size;
	protected  int y_size;
	protected  int z_size;
	
	public abstract void draw(Graphics g) ;
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
	
}
