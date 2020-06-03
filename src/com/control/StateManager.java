package com.control;

import java.awt.Graphics;

import state.MainState;
import state.MenuState;
import state.PlayState;

public class StateManager {
	private MainState[] mainState=new MainState[4];
	public static final int PLAY=0;
	public static final int MENU=3;
	public static final int SAVE=1;
	public static final int INTRO=2;
	private int currentstate;
	
	public StateManager() {
		mainState[MENU] = new MenuState(this);
		mainState[PLAY] = new PlayState(this,false);
		
		setState(MENU);
		
		
	}
	
	public void init() {
		
	}
	
	public void update() {
		// TODO Auto-generated method stub
		mainState[currentstate].update();
	}
	public void draw(Graphics g) {
		mainState[currentstate].draw(g);
	}
	public void setState(int target_state) {
		currentstate = target_state;
		if(target_state==SAVE){
			mainState[SAVE] = new PlayState(this,true);
		}
		mainState[target_state].init();
	}
	
}
