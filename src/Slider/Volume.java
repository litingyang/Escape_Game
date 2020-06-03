package Slider;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.net.ssl.SSLException;
import javax.sound.sampled.*;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.Main.Main;

public class Volume extends JPanel{
	static JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 20);
	static FloatControl[] gainControl = new FloatControl[5];
	Clip music, open, page, step, strange; 
	boolean changed = false;
	int next = 2;
	float SOUND = 3.0f;
	public Volume() {
		// TODO Auto-generated constructor stub
		try {
			
			AudioInputStream musicwav = AudioSystem.getAudioInputStream(new File("Resources/music.wav"));
			
			
			
			AudioInputStream openwav = AudioSystem.getAudioInputStream(new File("Resources/open.wav"));
			AudioInputStream pagewav = AudioSystem.getAudioInputStream(new File("Resources/page.wav"));
			AudioInputStream stepwav = AudioSystem.getAudioInputStream(new File("Resources/step.wav"));
			AudioInputStream strangewav = AudioSystem.getAudioInputStream(new File("Resources/item_get.wav"));
			
			music = AudioSystem.getClip();
			open = AudioSystem.getClip();
			page = AudioSystem.getClip();
			step = AudioSystem.getClip();
			strange = AudioSystem.getClip();
			
			music.open(musicwav);
			open.open(openwav);
			page.open(pagewav);
			step.open(stepwav);
			strange.open(strangewav);

			gainControl[0] = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl[1] = (FloatControl) open.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl[2] = (FloatControl) page.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl[3] = (FloatControl) step.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl[4] = (FloatControl) strange.getControl(FloatControl.Type.MASTER_GAIN);
			
			gainControl[0].setValue(-40);
		//	gainControl[0].setValue(changeVolume(slider.getValue()));
			gainControl[1].setValue(SOUND);
			gainControl[2].setValue(SOUND);
			gainControl[3].setValue(SOUND);
			gainControl[4].setValue(SOUND);
			
			music.start();
			music.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		slider.setMinorTickSpacing(5);  
		slider.setMajorTickSpacing(10);  
		slider.setBounds(0,0,150,30);
		slider.setBackground(new Color(56,56,56));
		slider.setVisible(true);
				
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(slider.getValue());
				gainControl[0].setValue(changeVolume(slider.getValue()));
				gainControl[1].setValue(changeVolume(slider.getValue()));
				gainControl[2].setValue(changeVolume(slider.getValue()));
				gainControl[3].setValue(changeVolume(slider.getValue()));
				gainControl[4].setValue(changeVolume(slider.getValue()));
			}
		});
		
		setLayout(null);
		setSize(150,30);
		setLocation(400,350);
		add(slider);
		setVisible(false);
		setLayout(null);
		playMusic();
		
	}
	
	public float changeVolume(int input) {
		float result = ((slider.getValue())/100.0f*86 - 80);
		result = (float) (Math.log10((slider.getValue()+1)/100.0)*40);
		
		return result;
	}
	public void playMusic() {
		// AudioInputStream audioInputStream =
		// AudioSystem.getAudioInputStream(new File("C:/Users/user/Music/The
		// Gummy Bear Song - Long English Version.mp3").getAbsoluteFile());

	}
	public void playSound(int sound) {
		switch (sound) {
		case 1:
			open.start();
			try {
				AudioInputStream openwav = AudioSystem.getAudioInputStream(new File("Resources/open.wav"));
				open = AudioSystem.getClip();
				open.open(openwav);
				gainControl[sound] = (FloatControl) open.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl[sound].setValue(SOUND);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			page.start();
			try {
				AudioInputStream pagewav = AudioSystem.getAudioInputStream(new File("Resources/page.wav"));
				page = AudioSystem.getClip();
				page.open(pagewav);
				gainControl[sound] = (FloatControl) page.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl[sound].setValue(SOUND);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			step.start();
			try {
				AudioInputStream stepwav = AudioSystem.getAudioInputStream(new File("Resources/step.wav"));
				step = AudioSystem.getClip();
				step.open(stepwav);
				gainControl[sound] = (FloatControl) step.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl[sound].setValue(SOUND);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			strange.start();
			try {
				AudioInputStream strangewav = AudioSystem.getAudioInputStream(new File("Resources/item_get.wav"));
				strange = AudioSystem.getClip();
				strange.open(strangewav);
				gainControl[sound] = (FloatControl) strange.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl[sound].setValue(SOUND);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}
	
	public void getFocus() {
		Main.main.remove(this);
		setVisible(false);
		removeAll();
		add(slider);
		Main.main.add(this);
		setVisible(true);
		slider.requestFocus();
		
		changed = false;
	}
	public int leave() {
		next = 2;
		while (true) {
			if(changed)break;
			slider.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESC");
			slider.getActionMap().put("ESC", new AbstractAction() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				//	Main.main.requestFocus();
				//	Main.mainPanel.requestFocus();
					changed =true; 
					next = 5;
					
				}
			});
			slider.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
			slider.getActionMap().put("Enter", new AbstractAction() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				//	Main.main.requestFocus();
				//	Main.mainPanel.requestFocus();
					changed =true; 
					next = 2;
					
				}
			});
			slider.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
			slider.getActionMap().put("UP", new AbstractAction() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				//	Main.main.requestFocus();
				//	Main.mainPanel.requestFocus();
					changed =true; 
					next = 1;
				}
			});
			slider.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
			slider.getActionMap().put("DOWN", new AbstractAction() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				//	Main.main.requestFocus();
				//	Main.mainPanel.requestFocus();
					changed = true;
					next = 3;
					
				}
			});
			//System.out.println(changed);
			
		}
		return next;
		
	}
}