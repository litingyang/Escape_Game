package com.control;

import java.awt.event.KeyEvent;

public class Keys {
	private static final int NUM=7;
	
	public static final int ENTER=0;
	public static final int ESC=1;
	public static final int SPACE=2;
	public static final int UP=3;
	public static final int DOWN=4;
	public static final int LEFT=5;
	public static final int RIGHT=6;
	
	
	

	
	private static boolean[] key_state=new boolean[NUM];
	private static boolean[] key_last=new boolean[NUM];
	
	public static void setKey(int key,boolean state) {
		switch(key) {
		case KeyEvent.VK_ENTER :
			key_state[ENTER]=state;
			break;
		case KeyEvent.VK_UP :
			key_state[UP]=state;
			break;
		case KeyEvent.VK_DOWN:
			key_state[DOWN]=state;
			break;
		case KeyEvent.VK_LEFT:
			key_state[LEFT]=state;
			break;
		case KeyEvent.VK_RIGHT:
			key_state[RIGHT]=state;
			break;
		case KeyEvent.VK_ESCAPE:
			key_state[ESC]=state;
			break;
		case KeyEvent.VK_SPACE:
			key_state[SPACE]=state;
			break;
		}
		
	}
	public static void update() {
		for(int i=0;i<key_state.length;i++) {
			key_last[i]=key_state[i];
		}
	}
	public static boolean isPressed(int key) {
		if(key_last[key]==true&&key_state[key]==true)return true;
		else{return false;}
	}
	public static boolean getState(int key) {return key_state[key];}
	
	
}
