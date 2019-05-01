package com.gr.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static final int WIDTH = 80; // width in pixel of tile
	public static final int HEIGHT = 80; // height of tile
	public static final int SLIDE_SPEED = 20;
	public static final int ARC_WIDTH = 15;
	public static final int ARC_HEIGHT = 15;
	
	private int value; // number in tile
	private BufferedImage tileImage; // actual tile image
	private Color background;
	private Color text;
	private Font font;
	private Point slideTo;
	private int x;
	private int y;
	
	private boolean canCombine = true; // keep track of thing is already combine or not
	
	public Tile (int value, int x, int y) {
		this.value = value;
		this.x =x;
		this.y = y;
		slideTo = new Point(x, y);
		tileImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		drawImage(); // draw to tile number and background
	}
	
	private void drawImage () {
		Graphics2D g = (Graphics2D)tileImage.getGraphics();
		if (value == 2) {
			background = new Color(0xe9e9e9);
			text = new Color(0x000000);
		} else if (value == 4) {
			background = new Color(0xe6daab);
			text = new Color(0xffffff);
		} else if (value == 8) {
			background = new Color(0xf79d3d);
			text = new Color(0xffffff);
		} else if (value == 16) {
			background = new Color(0xf28007);
			text = new Color(0xffffff);
		} else if (value == 32) {
			background = new Color(0xf55e3b);
			text = new Color(0xffffff);
		} else if (value == 64) {
			background = new Color(0xff0000);
			text = new Color(0xffffff);
		} else if (value == 128) {
			background = new Color(0x9de84);
			text = new Color(0x000000);
		} else if (value == 256) {
			background = new Color(0xf6e873);
			text = new Color(0xffffff);
		} else if (value == 512) {
			background = new Color(0xf5e455);
			text = new Color(0xffffff);
		} else if (value == 1024) {
			background = new Color(0xf7e12c);
			text = new Color(0xffffff);
		} else if (value == 2048) {
			background = new Color(0xffe400);
			text = new Color(0xffffff);
		} else {
			background = Color.black;
			text = Color.white;
		}
		
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(background);
		g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		
		
		g.setColor(text);
		// set font
		if (value <= 64) { // 2 digits number
			font = Game.main.deriveFont(36f);
		} else {
			font = Game.main;
		}
		g.setFont(font);
		//draw number
		int drawX = WIDTH / 2 - DrawUtils.getMessageWidth("" + value, font, g)/2; // start at left
		int drawY = HEIGHT / 2 + DrawUtils.getMessageHeight("" + value, font, g)/2; // start at bottom
		g.drawString("" + value, drawX, drawY);
		g.dispose();
	}
	
	public void update() {
		
	}
	
	public void render (Graphics2D g) {
		g.drawImage(tileImage, x, y, null);
	}
	
	public int getValue () {
		return value;
	}
	
	public void setValue (int value) {
		this.value = value;
		drawImage();
	}

	public boolean CanCombine() {
		return canCombine;
	}

	public void setCanCombine(boolean canCombine) {
		this.canCombine = canCombine;
	}

	public Point getSildeTo() {
		return slideTo;
	}

	public void setSildeTo(Point slideTo) {
		this.slideTo = slideTo;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
