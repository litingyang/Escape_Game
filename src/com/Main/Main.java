package com.Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static JFrame main =new JFrame();
	public static MainPanel mainPanel = new MainPanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		main.setTitle("Escape Now");
		main.add(mainPanel);
		
		main.setResizable(false);
		main.pack();
		main.setLayout(null);
		
		main.setVisible(true);
		main.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		main.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		       
		            System.exit(0);
		            }
		});
	}
	
}
