package state;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.control.Animation;
import com.control.Keys;
import com.control.StateManager;

public class MenuState extends MainState {

	

	private Animation animation;
	private BufferedImage[] back;
	private BufferedImage[] menu;
	private BufferedImage[] flag;
	private BufferedImage[] instruction;
	
	private  final int flag_x=200;
	private int flag_y;
	
	public static final int START=0;
	public static final int CONTINUE=1;
	public static final int ISTRUCT=2;
	public static final int EXIT=3;
	private boolean isInstruction = false;
	private static int currentOption;
	
	public MenuState(StateManager sm) {
		init();
		this.sm=sm;
		
		currentOption=0;
		
		flag_y=265;
	}
	
	
	public void init() {
		animation=new Animation(10);
		back=new BufferedImage[2];
		menu=new BufferedImage[1];
		flag=new BufferedImage[1];
		currentOption=0;
		flag_y=265;
		instruction = new BufferedImage[1];
		
		try {
			
			back[0]=ImageIO.read(MenuState.class.getResource("/Menu/back13.png"));
			back[1]=ImageIO.read(MenuState.class.getResource("/Menu/back14.png"));
			instruction[0]=ImageIO.read(MenuState.class.getResource("/Menu/instruction.png"));;
			menu[0]=ImageIO.read(MenuState.class.getResource("/Menu/option15.png"));
			flag[0]=ImageIO.read(MenuState.class.getResource("/Menu/flag.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		animation.setFrame(back, true);

	}
	public void draw(Graphics g) {
		g.drawImage(animation.getImage(),0,0,null);
		g.drawImage(menu[0], 0, 0, null);
		g.drawImage(flag[0],flag_x,flag_y,null);
		if(isInstruction)g.drawImage(instruction[0],0,0,null);
	}
	public void update() {
		animation.update();
		if(Keys.getState(Keys.UP)&&Keys.isPressed(Keys.UP)==false&&!isInstruction) {
			currentOption--;
			flag_y-=43;
			if(currentOption<0) {
				currentOption=0;
				flag_y+=43;
			}
		}
		else if(Keys.getState(Keys.DOWN)&&Keys.isPressed(Keys.DOWN)==false&&!isInstruction) {
			currentOption++;
			flag_y+=43;
			if(currentOption>3) {
				currentOption=3;
				flag_y-=43;
			}
		}
		else if(Keys.getState(Keys.ENTER)&&!Keys.isPressed(Keys.ENTER)) {
			if(currentOption==3) System.exit(0);
			if(currentOption==2) {
				isInstruction = isInstruction?false:true;
			}
			else {
				sm.setState(currentOption);
			}
			
		}else if(Keys.getState(Keys.ESC)&&!Keys.isPressed(Keys.ESC)) {
			if(currentOption==2) {
				isInstruction = isInstruction?false:true;
			}
			
		}
	}
}
