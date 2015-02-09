package com.thecherno.rain;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	// this attribute is to verify the integrity of the class when it's exchanged on a network
	private static final long serialVersionUID = 1L;
	
	// screen dimensions
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;

	// Game thread
	private Thread thread;

	// Game frame
	private JFrame frame;

	// Running game variable
	private boolean runnig = false;

	// Game default constructor
	public Game() {
		//creating a new dimension object 
		Dimension size = new Dimension(width * scale, height * scale);
		// this method is inherited from Canvas class it defines it's dimension
		setPreferredSize(size);
		frame = new JFrame();
	}

	// Game thread start method
	public synchronized void start() {
		runnig = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	// Game thread stop method
	public synchronized void stop() {
		runnig = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (runnig) {
	
	/* because of timing issue between computer performances 
	 * the game will be running at different speed on each 
	 * computer. So we need to split the game run function into 2 parts
	 * first one will be the game logic and the calculation of the player input 
	 * in which we will integrate a timer. and the second part will be the rendering 
	 * function which will be responsible of displaying the calculation result on screen to the user
	 * */		
			// game logic method conventional [tick()] | fix a specific speed
			update();
			// rendering method | rendering as fast as we can
			render();
		}

	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		// retrieving the bufferstrategy of canvas since our game class is a sub class of canvas 
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Rain");
		// add game which is a canvas sub class to the jframe
		game.frame.add(game);
		// this method is for making the jframe the same sise that the canvas
		game.frame.pack();
		// make the exit button of the jframe close the game process in the same time that it closes the frame
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// to position the jframe at the center of the screen 
		game.frame.setLocationRelativeTo(null);
		// to make the frame visible to user
		game.frame.setVisible(true);
		
		// start the game
		game.start();
	}
}
