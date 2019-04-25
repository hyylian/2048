package com.gr.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, Runnable{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400; // width of the screen
	public static final int HEIGHT = 630; // height of the screen
	public static final Font main = new Font("Times New Roman", Font.PLAIN, 28); // font.plain just refers to is it italicized playing or bold
	private Thread game; 
	private boolean running; // keep track on thread: starting, stoping 
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	private long startTime; // keep track of if update
	private long elapsed;
	private boolean set;
	
	public Game() {
		setFocusable(true); // allow keyboard input to be set
		setPreferredSize(new Dimension(WIDTH, HEIGHT)); // determine how big the frame is
		addKeyListener(this);
	}
	
	private void update() {
		
	}
	
	private void render() {
		Graphics2D g = (Graphics2D) image.getGraphics(); // actual image
		g.setColor(Color.white); // clean the screen
		g.fillRect(0, 0, WIDTH, HEIGHT); // draw rectangle around the entire screen
		// render board
		
		g.dispose();
		
		Graphics2D g2d = (Graphics2D)getGraphics(); //actual graphics that we draw image
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
	}
	
	@Override
	public void run() {
		int fps = 0, updates = 0;
		long fpsTimer = System.currentTimeMillis();
		double nsPerUpdate = 1000000000.0/60; // how many nanoseconds between updates
		
		// last update time in nanoseconds
		double then = System.nanoTime();
		double unprocessed = 0; // how many update we need to do
		
		while(running) {

			boolean shouldRender = false;
			double now = System.nanoTime(); // get new time
			unprocessed += (now - then) / nsPerUpdate; // count how many update we need to do based on how much time has passed
			then = now;
			
			//update queue
			while (unprocessed >= 1) {
				updates++;
				update();
				unprocessed--;
				shouldRender = true;
			}
			
			//render
			if(shouldRender) { //rendering
				fps++;
				render();
				shouldRender = false;
			} else { // not rendering
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// FPS Timer
		if (System.currentTimeMillis() - fpsTimer > 1000) {
			System.out.printf("%d fps %d updates", fps, updates);
			System.out.println();
			// reset
			fps = 0;
			updates = 0;
			fpsTimer += 1000;
		}
	}
	
	public synchronized void start() { // synchronized because want entire method gets called
		if (running) return;
		running = true;
		game = new Thread(this, "game");
		game.start();
	}
	
	public synchronized void stop() {
		if (!running) return;
		running = false;
		System.exit(0);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
