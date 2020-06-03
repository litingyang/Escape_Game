package com.SubState;
import java.awt.*;

import javax.imageio.ImageIO;
import com.Entity.Player;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class DialogState extends SubState {
	
	private ArrayList<String> script=new ArrayList<String>();
	private int count=0;
	
    public void draw(Graphics g)
    {
    	if(!isSpace()) {
	    	try {
				g.drawImage(ImageIO.read(Player.class.getResource("/item/Dialog.png")), 0, 395, 800, 200, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	g.setColor(Color.BLACK);
			Font f=new Font("Times New Roman",Font.BOLD/Font.ITALIC,25);
			g.setFont(f);
			if(count>0)
				g.drawString(script.get(count), 110, 480);
    	}
    }
    
    public boolean isSpace() {
    	if(script.get(count).equals(""))
    		return true;
    	else
    		return false;
    }
    
    public void init() {
		setScript();
    }
    
    public void update(boolean dialog) {
		if(dialog) {
			count++;
		}
	}
    
    public void update() {
    	
    }

    private void setScript() {
		try {
    		Scanner scanner=new Scanner(new FileInputStream("script.txt"));
    		while(scanner.hasNextLine()) {
    			script.add(scanner.nextLine());
    		}
    		scanner.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public String getScript(int count){
    	this.count=count;
    	return script.get(count);
    }
}
