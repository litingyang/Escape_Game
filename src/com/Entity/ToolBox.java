package com.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.control.Animation;

public class ToolBox extends Entity {

	private BufferedImage[] close;
	private BufferedImage[] open;
//	private BufferedImage[] diary;
	private BufferedImage[] key;
	private BufferedImage[] mirror;
	private BufferedImage[] ax;
	private boolean moving;
	private int look = 0;
	public static final int CLOSE = 0;
	public static final int OPEN = 1;
//	private static final int DIARY = 1;
	private static final int KEY = 1;
	private static final int MIRROR = 2;
	private static final int AX = 3;
	private int box[];

	public ToolBox() {
		init();

		x_position = 600;
		y_position = 500;
		x_size = 50;
		y_size = 50;
		z_size = 50;

		moving = false;
		setLook(CLOSE);
		animation.setFrame(close, moving);

	}

	private void setLook(int look) {
		this.look = look;
	}

	public void update() {
		super.update();

		switch (look) {
		case CLOSE:
			animation.setFrame(close, moving);
			break;
		case OPEN:
			animation.setFrame(open, moving);
			break;
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		if (look == OPEN) {
			x_position = 231;
			y_position = 500;
			g.drawImage(animation.getImage(), x_position, y_position, null);
			for (int i = 0; i < 3; i++) {
				if (box[i] != 0) {
					x_position = 50 + i*120 + 232;
					y_position = 505;
					/*
					if (box[i] == DIARY) {
						y_position = 510;
						animation.setFrame(diary, moving);
						g.drawImage(animation.getImage(), x_position, y_position, null);
					}
					*/
					if (box[i] == KEY) {
						y_position = 510;
						animation.setFrame(key, moving);
						g.drawImage(animation.getImage(), x_position, y_position, null);
					}
					if (box[i] == MIRROR) {
						x_size = 50;
						y_size = 60;
						animation.setFrame(mirror, moving);
						g.drawImage(animation.getImage(), x_position, y_position, x_size ,y_size ,null);
					}
					if (box[i] == AX) {
						x_size = 60;
						y_size = 60;
						animation.setFrame(ax, moving);
						g.drawImage(animation.getImage(), x_position, y_position, x_size ,y_size ,null);
					}
					//x_position = i*70 + 200;
				}
			}
		}else{
			x_position = 600;
			y_position = 500;
			g.drawImage(animation.getImage(), x_position, y_position, null);
		}
	}

	public void setOpen() {
		setLook(OPEN);
		animation.setFrame(open, moving);
	}

	public void setClose() {
		setLook(CLOSE);
		animation.setFrame(close, moving);
	}

	public void add(int item) {
		for (int i = 0; i < 3; i++) {
			if(box[i] == item)break;
			if (box[i] == 0) {
				box[i] = item;
				break;
			}
		}
	}

	public void init() {
		animation = new Animation(10);
		box = new int[3];
		for (int i = 0; i < 3; i++) {
			box[i] = 0;
		}
		//box[0] = MIRROR;

		close = new BufferedImage[1];
		open = new BufferedImage[1];
	//	diary = new BufferedImage[1];
		key = new BufferedImage[1];
		mirror = new BufferedImage[1];
		ax = new BufferedImage[1];
		try {
			close[0] = ImageIO.read(ToolBox.class.getResource("/item/toolbox-close.png"));
			open[0] = ImageIO.read(ToolBox.class.getResource("/item/toolbox-open.png"));
			//diary[0] = ImageIO.read(ToolBox.class.getResource("/item/diary.png"));
			key[0] = ImageIO.read(ToolBox.class.getResource("/item/toolboxkey.png"));
			mirror[0] = ImageIO.read(ToolBox.class.getResource("/item/mirror.png"));
			ax[0] = ImageIO.read(ToolBox.class.getResource("/item/ax.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public String saveBox(){
		String result = "";
		for (int i = 0; i < 3; i++) {
			switch(box[i]){
		/*	
			case DIARY:
				result = result + "DIARY\r\n";
				break;
		*/
			case KEY:
				result = result + "KEY\r\n";
				break;
			case MIRROR:
				result = result + "MIRROR\r\n";
				break;
			case AX:
				result = result + "AX\r\n";
				break;
			}
		}
		return result;
	}

}


