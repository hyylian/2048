package gui2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import game2048.DrawUtils;
import game2048.Game;
import game2048.ScoreManager;

public class MainMenuPanel extends GuiPanel {

	private Font titleFont = Game.main.deriveFont(100f);
	private Font creatorFont = Game.main.deriveFont(24f);
	private String title = "2048";
	private String creator = "By ";
	private int buttonWidth = 200;
        private ScoreManager score;
        private int height = 250;
        public static boolean diff=false;
        public GuiButton resumeButton;
        public GuiButton Resume;
	public GuiButton playButton;	
        public GuiButton scoreButton;
        public GuiButton quitButton ;
	public MainMenuPanel() {
		super(); 
		Resume = new GuiButton(Game.WIDTH / 8 - buttonWidth / 2, height, buttonWidth, 60);
		playButton = new GuiButton(3* Game.WIDTH / 8 - buttonWidth / 2, height, buttonWidth, 60);
		scoreButton = new GuiButton(5 * Game.WIDTH / 8 - buttonWidth / 2, height, buttonWidth, 60);
		quitButton = new GuiButton(7 * Game.WIDTH / 8 - buttonWidth / 2, height, buttonWidth, 60);
                
                Resume.addActionListener((ActionEvent e) -> {
                    GuiScreen.getInstance().setCurrentPanel("Play");
                });
                Resume.setText("Resume");
                add(Resume);
                
                playButton.addActionListener((ActionEvent e) -> {
                    diff=true;
                    GuiScreen.getInstance().setCurrentPanel("Difficulty");
                });
                playButton.setText("Play");
                add(playButton);
                
                scoreButton.addActionListener((ActionEvent e) -> {
                    GuiScreen.getInstance().setCurrentPanel("Leaderboards");
                });
		scoreButton.setText("Score");
		add(scoreButton);
		quitButton.addActionListener((ActionEvent e) -> {
                    System.exit(0);
                });
		quitButton.setText("Quit");
		add(quitButton);
	}
        @Override
        public void update(){    
        }
	@Override
	public void render(Graphics2D g){
		super.render(g);
		g.setFont(titleFont);
		g.setColor(Color.black);
		g.drawString(title, Game.WIDTH / 2 - DrawUtils.getMessageWidth(title, titleFont, g) / 2, 150);
		g.setFont(creatorFont);
		g.drawString(creator, 20, Game.HEIGHT - 10);
	}
}
