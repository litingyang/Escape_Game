package com.Room;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Entity.Bed;
import com.Entity.Closet;
import com.Entity.Door;
import com.Entity.Item;
import com.Entity.Lock;
import com.Entity.Mirror;
import com.Entity.Player;
import com.Entity.AX;

public class Room {
	
	public   static Player player;
	
	//ITEM CODE
	public static final int CLOSET=0;
	public static final int BED=1;
	public static final int DOOR=4;
	public static final int LOCK=5;
	public static final int OCCUPIED=6;
	public static final int MIR=2;
	public static final int AX=3;
	
	
	public static final int NUM=6;
	public Item[] item =new Item[NUM];
	
	
	//ROOM SIZE
	private  boolean[][] movable;
	private static int [][] occupied;
	public final static int Y_SIZE=600;
	public final static int X_SIZE=800;
	public Room(Player player) {
	// 
		movable=new boolean[X_SIZE][Y_SIZE];
		occupied=new int [X_SIZE][Y_SIZE];
		
		item[CLOSET]=new Closet();
		item[BED]=new Bed();
		item[DOOR]=new Door();
		item[MIR]=new Mirror();
		item[LOCK]=new Lock();
		item[AX]=new AX();
	}
	//draw just background
	public void draw(Graphics g) {
		
		
			try {
				g.drawImage(ImageIO.read(Player.class.getResource("/back/background.png")), 0, 0, 800, 600,null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
	}
	//not implement
	public void init() {
		movable=new boolean[X_SIZE][Y_SIZE];
		item[CLOSET]=new Closet();
		item[BED]=new Bed();
		item[DOOR]=new Door();
		item[LOCK]=new Lock();
	}
	//SET ROOM TO OCUPPIED METHOD
	public void setMovable(Item e,int i) {
		
		for(int x=e.getXposition();x<e.getXposition()+e.getXsize();x++) {
			
			for(int y=e.getYposition()+e.getZsize();
					y<e.getYposition()+e.getZsize()+e.getYsize();
					y++) {
				
				movable[x][y]=false;
				occupied[x][y]=i;
			}
		
		}
		
	}
	//check current item position 
	public void update() {
		
		for(int x=0;x<movable.length;x++) {
			for(int y=0;y<movable[0].length;y++) {
				//SET TO NOT OCCUPIED
				movable[x][y]=true;
				occupied[x][y]=OCCUPIED;
			}
		}
		// to check each item's occupied
		for(int i=0;i<item.length;i++)	{
			
			setMovable(item[i],i);
			
		}
			
		
	}
	//CHECK IF movable
	public boolean isMovable(int x,int y) {
		try {
			return movable[x][y];
			
		}catch(java.lang.ArrayIndexOutOfBoundsException e) {
			return false;
		}
		
		
	}
	public Item getItem(int Item) {
		return item[Item];
	}
	public static int checkNext(Item player) {
		int look=((Player) player).getLook();
		int movespeed=((Player) player).movespeed;
		int x_position=player.getXposition();
		int y_position=player.getYposition();
		int x_size=player.getXsize();
		int z_size=player.getZsize();
		
		switch(look) {
		case Player.FRONT:
			
			return occupied[x_position][y_position+z_size+movespeed];
		case Player.BACK:
			
			return occupied[x_position][y_position+z_size-movespeed];
		case Player.RIGHT:
			
			return occupied[x_position+x_size+movespeed][y_position+z_size];
		case Player.LEFT:
			
			return occupied[x_position-movespeed][y_position+z_size];
			
		}
		return occupied[x_position][y_position]=0;
	}
}
