package com.SubState;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


import com.Entity.Player;
import com.Room.Room;

import state.PlayState;

public class EventState extends SubState{

	BufferedImage[] image=new BufferedImage[1];
	BufferedImage[] trueEnd=new BufferedImage[1];
	BufferedImage[] falseEnd=new BufferedImage[2];
	private int currentimage=0;
	private int FalseEnd=0;

	
	public EventState(Player player ,int next) {
		// TODO Auto-generated constructor stub
		if(player.getXposition()<240 &&
				player.getXposition()>0 &&
				player.getYposition()>50 &&
				player.getYposition()<130 &&
				player.getLook()==Player.BACK) {
			
			System.out.println("if "+next);
			try {
				image[0]=ImageIO.read(EventState.class.getResource("/item/event_photo.png"));
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			
			
		}
		else  {
			
			try {
				switch(next) {
				case Room.DOOR:
					System.out.println("else "+next);
					if(PlayState.hasAX && PlayState.hasMirror) {
						trueEnd[0]=ImageIO.read(EventState.class.getResource("/Ending/True_success.png"));
						System.out.println("trueeeee"+next);
					}
					else {
						falseEnd[0]=ImageIO.read(EventState.class.getResource("/Ending/False_success_1.png"));
						falseEnd[1]=ImageIO.read(EventState.class.getResource("/Ending/False_success_2.png"));
					}
					break;
				
				case Room.CLOSET:
					System.out.println("switch "+next);
					image[0]=ImageIO.read(EventState.class.getResource("/item/diary_open.png"));
					break;
					
				case Room.LOCK:
					image[0]=ImageIO.read(EventState.class.getResource("/item/event_password.png"));
					break;
			}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
			currentimage++;
	}
	
	public void update(int a) {
		FalseEnd++;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
			g.drawImage(image[currentimage],0,0,null);
			g.drawImage(trueEnd[currentimage],0,0,null);
			g.drawImage(falseEnd[FalseEnd],0,0,null);
			
		}
		
	
	
	
}
