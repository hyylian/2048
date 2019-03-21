package com.group.game;

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
	public static final int WIDTH = 400;
	public static final int HEIGHT = 630;
	public static final Font main = new Font("Bebas Neue Regular", Font.PLAIN, 28);
	private Thread game;
	private boolean running;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	private long startTime;
	private long elapsed;
	private boolean set;
	
	public Game() {
		setFocusable(true); // allow keyboard input to be set
		setPreferredSize(new Dimension(WIDTH, HEIGHT)); // determine how big the frame is
		addKeyListener(this);
	}
	
	private void update() { // be call 60 time/second
		
	}
	
	private void render() {
		Graphics2D g = (Graphics2D)image.getGraphics(); // draw image and in memory
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.dispose();
		//render board
		
		// get graphic of JPanel and draw final image to JPanel
		Graphics2D g2d = (Graphics2D)getGraphics(); 
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
	}

	@Override
	public void run() {
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	
	
}