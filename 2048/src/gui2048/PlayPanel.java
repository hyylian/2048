package gui2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import game2048.DrawUtils;
import game2048.Game;
import game2048.GameBoard;
import game2048.Keyboard;
import game2048.ScoreManager;

public class PlayPanel extends GuiPanel {

	private GameBoard board;
	private BufferedImage info;
	private ScoreManager scores;
	private Font scoreFont;
	private String timeF;
	private String bestTimeF;
	// Game Over
	private GuiButton tryAgain;
	private GuiButton mainMenu;
	private GuiButton screenShot;
	private int smallButtonWidth = 160;
	private int spacing = 20;
	private int largeButtonWidth = smallButtonWidth * 2 + spacing;
	private int buttonHeight = 50;
	private boolean added;
	private int alpha = 0;
	private Font gameOverFont;
	private boolean screenshot;
        boolean newGame;
	public PlayPanel() 
        {
		scoreFont = Game.main.deriveFont(24f);
		gameOverFont = Game.main.deriveFont(70f);
		board = new GameBoard(Game.WIDTH - GameBoard.BOARD_WIDTH - 10, Game.HEIGHT / 2 - GameBoard.BOARD_HEIGHT/2);
		scores = board.getScore();
		info = new BufferedImage(Game.WIDTH, 200, BufferedImage.TYPE_INT_RGB);
		mainMenu = new GuiButton(Game.WIDTH / 2 - largeButtonWidth / 2, 450, largeButtonWidth, buttonHeight);
		screenShot = new GuiButton(Game.WIDTH / 2 - largeButtonWidth / 2, 375, largeButtonWidth, buttonHeight);
		screenShot.setText("Screenshot");
		mainMenu.setText("Back to Main Menu");
		screenShot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screenshot = true;
			}
		});
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                newGame=true;
                                GuiScreen.getInstance().setCurrentPanel("Menu");
			}
		});
	}

	private void drawGui(Graphics2D g) {
		// Format the times
		timeF = DrawUtils.formatTime(scores.getTime());
		bestTimeF = DrawUtils.formatTime(scores.getBestTime());
		
                // Draw it
		Graphics2D g2d = (Graphics2D) info.getGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, info.getWidth(), info.getHeight());
		g2d.setColor(Color.lightGray);
		g2d.setFont(scoreFont);
		g2d.drawString("Score: " + scores.getCurrentScore(), 30, 40);
		
		g2d.drawString("Time: " + timeF, 30, 80);
		
		g2d.setColor(Color.red);
		g2d.drawString("Best Score: " + scores.getCurrentTopScore(), 30, 120);
		g2d.drawString("Fastest: " + bestTimeF, 30, 160);
        
		g2d.dispose();
		g.drawImage(info, 0, 0, null);
	}

	public void drawGameOver(Graphics2D g) {
		g.setColor(new Color(222, 222, 222, alpha));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.red);
                g.setFont(gameOverFont);
		g.drawString("Game Over!", Game.WIDTH / 2 - DrawUtils.getMessageWidth("Game Over!", gameOverFont, g) / 2, 250);
                g.setColor(Color.black);
                g.setFont(scoreFont);
                g.drawString("Press ESC to Try Again", Game.WIDTH / 2 - DrawUtils.getMessageWidth("Press ESC to Try Again", scoreFont, g) / 2, 325);
	}

	@Override
	public void update() {
		board.update();
                if(true==MainMenuPanel.diff)
                {
                    newGame=true;
                    MainMenuPanel.diff=false;
                }
                newGame();
		if (board.isDead()) {
			alpha++;
			if (alpha > 170) alpha = 170;
		}
                
		
	}
	@Override
	public void render(Graphics2D g) {
		drawGui(g);
		board.render(g);
		if (screenshot) {
			BufferedImage bi = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = (Graphics2D) bi.getGraphics();
			g2d.setColor(Color.white);
			g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			drawGui(g2d);
			board.render(g2d);
			try {
				ImageIO.write(bi, "gif", new File(System.getProperty("user.home") + "\\Desktop", "screenshot" + System.nanoTime() + ".gif"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			screenshot = false;
		}
		if (board.isDead()) {
			if (!added) {
				added = true;
				add(mainMenu);
				add(screenShot);
			}
			drawGameOver(g);
            	}
		super.render(g);
	}
        public void newGame(){
            if(!Keyboard.pressed[KeyEvent.VK_ESCAPE]&&Keyboard.prev[KeyEvent.VK_ESCAPE]||newGame){
                    board.reset();
                    scores.reset();
                    if(added){
                        remove(mainMenu);
                        remove(screenShot);
                        alpha=0;
                        added=false;
                    }
                    newGame=false;
            }
        }
}