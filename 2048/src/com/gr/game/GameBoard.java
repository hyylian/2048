package com.gr.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameBoard {
	
	public static final int ROWS = 4; // number of tile in row
	public static final int COLS = 4; // number of tile in col
	
	private final int startingTile = 2; // tile at beginning
	private Tile[][] board; // store tiles are on the board in 2d array
	private boolean dead; // lose
	private boolean won; // win the game
	private BufferedImage gameBoard; // background of game board
	private BufferedImage finalBoard; // game board and tile
	private int x; // where to render on the screen
	private int y; //
	
	private static int SPACING = 10; // Space between tile
	public static int BOARD_WIDTH = (COLS + 1)*SPACING + COLS * Tile.WIDTH;
	public static int BOARD_HEIGHT = (ROWS + 1)*SPACING + ROWS * Tile.HEIGHT;
	
	private boolean hasStarted;
	
	public GameBoard(int x, int y) {
		this.x = x;
		this.y = y;
		board = new Tile[ROWS][COLS];
		gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		createBoardImage();
	}
	
	private void createBoardImage() { // draw background of the board
		// draw rectangle (game board)
		Graphics2D g = (Graphics2D) gameBoard.getGraphics();
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		g.setColor(Color.lightGray);
		
		// draw number to game board
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; row < COLS; col++) {
				int x = SPACING + SPACING * col + Tile.WIDTH + col; // set x position
				int y = SPACING + SPACING * row + Tile.HEIGHT + row; // set y position
				g.fillRoundRect(x, y, Tile.WIDTH, Tile.HEIGHT, Tile.ARC_WIDTH, Tile.ARC_HEIGHT);
			}
		}
	}
	
	public void render (Graphics2D g) {
		Graphics2D g2d = (Graphics2D)finalBoard.getGraphics();
		g2d.drawImage(gameBoard, 0, 0, null);
		
		// draw Tile
		
		g.drawImage(finalBoard, x, y, null);
		g2d.dispose();
	}
	
	public void update () {
		checkKeys();
	}
	
	public void checkKeys() {
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
