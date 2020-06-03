package state;

import java.awt.Graphics;

import com.control.StateManager;

public abstract class MainState {
	public boolean paused;
	protected static StateManager sm;

	public abstract void update();
	public abstract void init();
	public abstract void draw(Graphics g);
	public void setPaused() {
		
	}
}
