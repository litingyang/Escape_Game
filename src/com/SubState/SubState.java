package com.SubState;

import java.awt.Graphics;

public abstract class SubState {
	
	public abstract void init();
	public abstract void draw(Graphics g);
	public abstract void update();

}
