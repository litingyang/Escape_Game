package com.Main;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import com.control.Keys;
import com.control.StateManager;



public class MainPanel extends JPanel implements Runnable , KeyListener  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int HEIGHT=600;
	private static final int WIDTH=800;
	
	private Thread thread;
	private static final  int FPS=30;
	private static final int FRAME=1000/FPS;
	private static boolean running;
	
	private BufferedImage image;
	private Graphics2D g;


	private static StateManager statemanager;
	
	public MainPanel() {
		
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();
		thread = new Thread(this);
		thread.start();
		addKeyListener(this);
		setLayout(null);
		

	}
	
	private void init() {
		running = true;

		image = new BufferedImage(800, 600, 1);
		g=(Graphics2D) image.getGraphics();
		
		statemanager=new StateManager();
		
	}
	

	
	@Override
	public void run() {
		
		
		init();
		
		long start;
		long elapsed;
		long wait;
	
		while(running) {
			start=System.nanoTime();
			update();
			draw(g);
			this.getGraphics().drawImage(image, 0, 0, null);
			
			
			elapsed=System.nanoTime()-start;
			wait=FRAME-elapsed;
			if(wait<0)wait=FRAME;
			try {
				Thread.sleep(wait);
			}catch(Exception e) {
				
			}
		}
	}
	
	private void draw(Graphics g) {
		statemanager.draw(g);
		requestFocus();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	//	System.out.println("press");
		Keys.setKey(e.getKeyCode(),true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		Keys.setKey(e.getKeyCode(),false);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

		
	}


	private static void update() {
		
		statemanager.update();
		Keys.update();
	}

	
}
