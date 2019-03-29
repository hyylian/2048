package com.group.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameBoard {
	
	public static final int ROWS = 4;
	public static final int COLS = 4;
	
	private final int staringTiles = 2; // less than 16
	
	private Tile[][] board; //*******************keep track of where all the tile are on the board by storing it in this 2d array 
	private boolean dead;
	private boolean won;
	private BufferedImage gameBoard;
	private BufferedImage finalBoard; // have the game board and all the tiles on it
	private int x; // where to render on the screen
	private int y;
	
	private static int SPACING = 10; // the spacing in between where the the tiles are on the board (pixel)
	public static int BOARD_WIDTH = (COLS + 1)* SPACING + COLS * Tile.WIDTH; // get the actual width in pixels of the board
	public static int BOARD_HEIGHT = (ROWS + 1)* SPACING + ROWS * Tile.HEIGHT;
	
	private boolean hasStarted;
	
	public GameBoard(int x, int y) {
		this.x = x; // where we draw this in the screen
		this.y = y;
		board = new Tile[ROWS][COLS];
		gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		createBoardImage();
	}
	
	private void createBoardImage() {
		Graphics2D g = (Graphics2D) gameBoard.getGraphics();
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		g.setColor(Color.lightGray);
		
		// create background of the game board
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				int x = SPACING + SPACING *col + Tile.WIDTH * col;
				int y = SPACING + SPACING *row + Tile.HEIGHT *row;
				g.fillRoundRect(x, y, Tile.WIDTH, Tile.HEIGHT, Tile.ARC_WIDTH, Tile.ARC_HEIGHT);
				
			}
		}
	}
	
	public void render (Graphics2D g) {
		Graphics2D g2d = (Graphics2D) finalBoard.getGraphics(); // draw to final board
		g2d.drawImage(gameBoard, 0, 0, null);
		
		// draw tiles
		
		g.drawImage(finalBoard, x, y, null);
		g2d.dispose();
	}
	
	public void update() {
		checkKeys();
	}
	
	private void checkKeys() { // moving tiles
		if (Keyboard.typed(KeyEvent.VK_LEFT)) {
			// move tiles left
			if (!hasStarted) hasStarted = true;
		}
		if (Keyboard.typed(KeyEvent.VK_RIGHT)) {
			// move tiles right
			if (!hasStarted) hasStarted = true;
		}
		if (Keyboard.typed(KeyEvent.VK_UP)) {
			// move tiles up
			if (!hasStarted) hasStarted = true;
		}
		if (Keyboard.typed(KeyEvent.VK_DOWN)) {
			// move tiles down
			if (!hasStarted) hasStarted = true;
		}
	}
	
}
