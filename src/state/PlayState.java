package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;
import com.Entity.Player;
import com.Entity.ToolBox;
import com.Entity.Closet;
import com.Main.Main;
import com.Room.Room;
import com.SubState.DialogState;
import com.SubState.EventState;
import com.SubState.PasswordState;
import com.control.Keys;
import com.control.StateManager;

public class PlayState extends MainState {
	// play
	private Room room;
	private static Player player;
	private static ToolBox toolbox;

	// paused option
	public static boolean change = true;
	public static final int CONTINUE = 0;
	public static final int NEWGAME = 1;
	public static final int VOLUME = 2;
	public static final int EXIT = 3;

	private static final int KEY = 1;
	private static final int MIRROR = 2;
	private static final int AX = 3;

	public static boolean saved = false;
	public static boolean ToolBoxOpen = false;
	public static boolean ClosetOpen = false;

	public static boolean hasAX = false;
	public static boolean hasMirror = false;
	public static boolean hasKey = false;
	public static boolean DoorUnlocked;
	private static int currentOption;
	public static boolean reStart = false;
	// paused image

	private BufferedImage[] pausedMenu = new BufferedImage[1];
	private BufferedImage[] pausedFlag = new BufferedImage[1];
	private int flag_y;
	private final int flag_x = 200;

	Slider.Volume volume = new Slider.Volume();
	//

	private boolean password;
	private boolean dialog;
	public static boolean event;

	DialogState dls;
	EventState evs;
	PasswordState pws;

	public PlayState(StateManager sm, boolean s) {

		this.sm = sm;
		init();
		saved = s;

	}

	// start new game

	public void init() {
		System.out.println("save=" + saved);
		ToolBoxOpen = false;
		ClosetOpen = false;
		hasAX = false;
		hasMirror = false;
		hasKey = false;
		DoorUnlocked = false;

		Main.main.add(volume);

		room = new Room(player);
		player = new Player(room);
		toolbox = new ToolBox();

		dls = new DialogState();
		dls.init();

		paused = false;
		dialog = true;
		password = false;
		event = false;

		if (saved) {
			resumeGame();
			dialog = false;
		}

		saved = false;

		try {
			pausedFlag[0] = ImageIO.read(PlayState.class.getResource("/paused/flag2.png"));
			pausedMenu[0] = ImageIO.read(PlayState.class.getResource("/paused/back.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// update image

	public void draw(Graphics g) {

		if (paused) {
			if (change) {
				g.drawImage(pausedMenu[0], 0, 0, null);
				g.drawImage(pausedFlag[0], flag_x, flag_y, null);
			}

		} else {
			room.draw(g);

			if (player.getYposition() + player.getZsize() < room.item[1].getYposition() + room.item[1].getZsize()) {

				if (!hasMirror)
					room.item[Room.MIR].draw(g);
				if (!hasAX)
					room.item[Room.AX].draw(g);
				room.getItem(Room.DOOR).draw(g);
				room.getItem(Room.CLOSET).draw(g);

				room.getItem(Room.LOCK).draw(g);
				player.draw(g);
				room.getItem(Room.BED).draw(g);

			} else {
				if (!hasAX)
					room.item[Room.AX].draw(g);
				if (!hasMirror)
					room.item[Room.MIR].draw(g);

				room.getItem(Room.DOOR).draw(g);
				room.getItem(Room.CLOSET).draw(g);
				room.getItem(Room.LOCK).draw(g);
				room.getItem(Room.BED).draw(g);

				player.draw(g);

			}
			if (!dialog && !ClosetOpen)
				toolbox.draw(g);

		}

		if (event) {
			try {
				evs.draw(g);
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
				event = false;
			}
		}
		if (dialog)
			dls.draw(g);
		if (password)
			pws.draw(g);

	}

	// resume game
	public void resumeGame() {

		Scanner scanner;
		try {
			scanner = new Scanner(new FileInputStream("save_data.txt"));
			while (scanner.hasNextLine()) {
				String aline = scanner.nextLine();
				System.out.println(aline);
				switch (aline) {
				case "KEY":
					hasKey = true;
					toolbox.add(KEY);
					break;
				case "MIRROR":
					hasMirror = true;
					room.item[Room.MIR].remove();
					toolbox.add(MIRROR);
					break;
				case "AX":
					hasAX = true;
					room.item[Room.AX].remove();
					toolbox.add(AX);
					break;

				default:
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void pausedMethod(int currentOption) {
		switch (currentOption) {
		case CONTINUE:
			paused = false;

			break;
		case NEWGAME:
			init();
			break;
		case VOLUME:
			change = false;
			volume.getFocus();
			int next = volume.leave();
			change = true;
			Keys.setKey(KeyEvent.VK_ENTER, false);
			if (next == 1) {
				Keys.setKey(KeyEvent.VK_UP, false);
				Keys.setKey(KeyEvent.VK_UP, true);
				flag_y -= 43;
				PlayState.currentOption = next;
			} else if (next == 3) {
				Keys.setKey(KeyEvent.VK_DOWN, false);
				Keys.setKey(KeyEvent.VK_DOWN, true);
				flag_y += 43;
				PlayState.currentOption = next;
			} else if (next == 5) {
				PlayState.currentOption = 0;
				paused = false;
			}
			break;
		case EXIT:
			sm.setState(StateManager.MENU);
			try {
				PrintWriter writer = new PrintWriter(new FileOutputStream("save_data.txt"));
				writer.print(toolbox.saveBox());
				writer.flush();
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	public void update() {
		// TODO Auto-generated method stub
		player.update();
		room.update();
		toolbox.update();

		// play
		if (!paused && !dialog && !ToolBoxOpen && !password) {
			walk();

			// when paused
		} else if (paused) {

			if (Keys.getState(Keys.UP) && !Keys.isPressed(Keys.UP)) {
				currentOption--;
				flag_y -= 43;
				if (currentOption < 0) {
					currentOption = 0;
					flag_y += 43;
				}
			} else if (Keys.getState(Keys.DOWN) && !Keys.isPressed(Keys.DOWN)) {
				currentOption++;
				flag_y += 43;
				if (currentOption > 3) {
					currentOption = 3;
					flag_y -= 43;
				}
			} else if (Keys.getState(Keys.ENTER) && !Keys.isPressed(Keys.ENTER)) {
				System.out.print("Enter");
				if (currentOption != VOLUME)
					paused = false;
				pausedMethod(currentOption);

			} else if (Keys.getState(Keys.ESC) && !Keys.isPressed(Keys.ESC)) {
				System.out.print("Esc");
				paused = false;

			}
		} else if (dialog) {
			if (Keys.getState(Keys.ENTER) && !Keys.isPressed(Keys.ENTER)) {
				System.out.println("dialog");
				dls.update(dialog);
				int next = Room.checkNext(player);
				if (ClosetOpen || DoorUnlocked) {
					event = true;
					evs = new EventState(player, next);
					if(ClosetOpen)volume.playSound(2);
				}
				if (password) {
					event = false;
					password = false;
				}
				if (dls.isSpace())
					dialog = false;
			}

			// System.out.println(currentOption);
		} else if (ToolBoxOpen) {
			if (Keys.getState(Keys.SPACE) && !Keys.isPressed(Keys.SPACE)) {
				System.out.println("Space");
				// paused = false;
				ToolBoxOpen = false;
				toolbox.setClose();
			}
		} else if (password) {
			pws.update();
			if (Keys.getState(Keys.ENTER) && !Keys.isPressed(Keys.ENTER)) {
				// password=false;
				System.out.println(pws.getPassword());
				if (pws.getPassword().equals("8496")) {
					toolbox.add(KEY);
					volume.playSound(4);
					hasKey = true;
					dialog = true;
					dls.getScript(36);
				} else {
					dialog = true;
					dls.getScript(46);
				}
			}
		}

	}

	private void walk() {
		if (Keys.getState(Keys.UP)) {
			player.up();
			player.setMovespeed(5);
			if (Keys.getState(Keys.ENTER)) {
				player.movingthing=true;
				int next = Room.checkNext(player);
				if (next != Room.OCCUPIED) {
					player.setMovespeed(1);
					room.getItem(next).setMove(player);
					;
				}
			}
		} else if (Keys.getState(Keys.DOWN)) {
			player.down();
			player.setMovespeed(5);
			if (Keys.getState(Keys.ENTER)) {
				player.movingthing=true;
				int next = Room.checkNext(player);

				if (next != Room.OCCUPIED) {

					player.setMovespeed(1);

					room.getItem(next).setMove(player);
				}
			}
		} else if (Keys.getState(Keys.RIGHT)) {
			player.right();
			player.setMovespeed(5);

			if (Keys.getState(Keys.ENTER)) {
				int next = Room.checkNext(player);
				if (next != Room.OCCUPIED) {
					player.setMovespeed(1);

					room.getItem(next).setMove(player);
				}
			}
		} else if (Keys.getState(Keys.LEFT)) {
			player.left();
			player.setMovespeed(5);
			if (Keys.getState(Keys.ENTER)) {
				int next = Room.checkNext(player);
				if (next != Room.OCCUPIED) {
					player.setMovespeed(1);
					room.getItem(next).setMove(player);
				}
			}
		} else if (Keys.getState(Keys.ESC) && !Keys.isPressed(Keys.ESC)) {
			System.out.println("esc");
			paused = true;
			flag_y = 265;
			currentOption = 0;

		} else if (Keys.getState(Keys.SPACE) && !Keys.isPressed(Keys.SPACE)) {
			System.out.println("space");
			if (!ToolBoxOpen) {
				// paused = true;
				ToolBoxOpen = true;
				toolbox.setOpen();
			}
		} else if (Keys.getState(Keys.ENTER) && !Keys.isPressed(Keys.ENTER)) {
			System.out.println("enter");
			int next = Room.checkNext(player);
			switch (next) {
			case Room.CLOSET:
				if (!ClosetOpen && player.getLook() == Player.BACK) {
					dialog = true;
					dls.getScript(16);
					ClosetOpen = true;
					((Closet) room.item[0]).setOpen();
					volume.playSound(1);
				} else {
					try {
						evs.update();
					} catch (java.lang.NullPointerException e) {
						event = false;
					}
					ClosetOpen = false;
					((Closet) room.item[0]).setClose();
				}
				break;
			case Room.AX:
				if (!hasAX) {
					dialog = true;
					dls.getScript(21);
					toolbox.add(AX);
					room.item[Room.AX].remove();
					hasAX = true;
					evs = new EventState(player, next);
					volume.playSound(4);
				}
				break;
			case Room.MIR:
				if (!hasMirror) {
					dialog = true;
					dls.getScript(31);
					toolbox.add(MIRROR);
					room.item[Room.MIR].remove();
					hasMirror = true;
					volume.playSound(4);
				}
				break;
			case Room.OCCUPIED:
				if (player.getXposition() < 240 && player.getXposition() > 0 && player.getYposition() > 50
						&& player.getYposition() < 130 && player.getLook() == Player.BACK) {
					System.out.println(event);
					if (!event) {
						event = true;
						evs = new EventState(player, next);
					} else {
						evs.update();
						event = false;
					}
				} else {
					dialog = true;
					dls.getScript(26);
				}
				break;
			case Room.DOOR:
				dialog = true;
				if (hasKey && !DoorUnlocked) {
					dls.getScript(41);
					DoorUnlocked = true;
				} else if (hasKey && DoorUnlocked) {
					if (reStart) {
						event = false;
						System.out.println("tomenu");
						reStart=false;
						sm.setState(StateManager.MENU);
					} else {
						try {
							evs.update(1);
							reStart = true;
							dialog = false;
							System.out.println("updates");
						} catch (java.lang.ArrayIndexOutOfBoundsException e) {

						}
					}
				} else if (!hasKey)
					dls.getScript(11);

				break;
			case Room.LOCK:
				if (!event) {
					event = true;
					evs = new EventState(player, next);
					password = true;
					pws = new PasswordState();
				} else {
					// evs.update();
					// event=false;
				}

				break;
			}

		}
	}
}
