package com.haichao.protogame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.haichao.protogame.entity.mob.Player;
import com.haichao.protogame.graphics.Screen;
import com.haichao.protogame.input.Keyboard;
import com.haichao.protogame.level.Level;
import com.haichao.protogame.level.SpawnLevel;

/**
 * Game
 * 
 * @author hmiao87@gmail.com (Haichao Miao)
 */
public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	// Canvas represents a blank rectangle are on the screen, that we can
	// manipulate
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3; // make the window 3 times larger (for a kind
									// of old school game)
	public static String title = "Protogame";
	
	private Thread thread; // explanation: thread is a process within a process
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false; // indicator that the program is running

	private Screen screen; 
	
	private BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		// holds a size/dimension
		Dimension size = new Dimension(width * scale, height * scale);

		// method is in Canvas
		setPreferredSize(size);
		
		screen = new Screen(width, height);

		frame = new JFrame();
		key = new Keyboard();
		level = new SpawnLevel("/textures/level.png");
		player = new Player(key);
		addKeyListener(key);
	}

	/**
	 * synchronized: this is essentially to prevent memory inconsistencies and
	 * thread interferencies. this thread is visible to other threads (e.g. the
	 * Game thread)
	 * 
	 * 
	 */
	public synchronized void start() {
		running = true;
		// new game is able to contain the Game Thread
		// new thread is called Display
		thread = new Thread(this, "Display");
		thread.start(); // new thread is created
		// calls automatically run()
	}

	public synchronized void stop() {
		running = false;
		// wait for the thread to die
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run() lets keep our program up (Game loop)
	 * otherwise the program will be closed after the jobs are finished
	 */
	@Override
	public void run() {
		
		long lastTime = System.nanoTime(); //nano is much preciser
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0; //how many time to run the update methode
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		// keep on doing whats in here
		while (running) {
			long now = System.nanoTime(); // very small time passed from lastTime and now
			delta += (now-lastTime) / ns;
			lastTime = now;
			//calculate the time that takes to get from the beginning of the loop til the end.
			while (delta >= 1) {
				update(); // logic (keep the fps manageable)
				updates++;
				delta--;
			}
			render(); // display
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		
		stop();
	}
	
	
	public void update() {
		key.update();
		player.update();
		
 	}

	public void render() {
		BufferStrategy bs = getBufferStrategy(); // here the new "frame" ist
													// calculated. temporarily
													// storage
		if (bs == null) {
			createBufferStrategy(3); // multiple buffering. for speed
										// improvement.
			// one frame is already calculated in the memory. the cpu can start
			// to work on the third one. -> 3 buffers
			return;
		}
		
		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		// apply buffer to graphics
		Graphics g = bs.getDrawGraphics();

		// graphics needs to be done here

		g.fillRect(0, 0, getWidth(), getHeight());

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 50));
		//g.drawString("X: " + player.x + ", Y:" + player.y, 520, 470);
		// disposes the current graphics (release system resources)
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false); // always make sure that this is set
										// false, because resizable windows
										// causes tons of errors
		game.frame.add(game);
		game.frame.pack(); // size up the frame to be same size as out
							// components
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}

}
