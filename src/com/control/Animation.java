package com.control;

import java.awt.image.BufferedImage;



public class Animation {
	private BufferedImage[] frames;
	private  int  playedFrame;
	private int delay;
	private int currentFrame;
	
	private boolean loop;
	
	public Animation(int delay) {
		playedFrame=0;
		currentFrame=0;
		this.delay=delay;
		
	}

	public void setFrame(BufferedImage[] frames,boolean loop) {
		
		this.frames=frames;
		this.loop=loop;
	}
	public void update() {
		if(loop) {
		playedFrame++;
		
			
			if(playedFrame==delay) {
				playedFrame=0;
				currentFrame++;
				
				if (currentFrame==frames.length) {
					currentFrame=0;
					
				}
			}
		}

		

	}
	public BufferedImage getImage() {
		return frames[currentFrame];
	}
}
