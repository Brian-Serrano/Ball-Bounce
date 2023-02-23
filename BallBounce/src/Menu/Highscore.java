package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Button.LeftButton1;
import Database.FetchHighscore;
import Button.ButtonMouseListener;
import Button.HighscoreBoard;
import Util.AudioPlayer;
import Util.Image;
import Util.Variables;

public class Highscore extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public Image image;
	public HighscoreBoard highscoreBoard;
	public LeftButton1 backButton;
	public FetchHighscore fetchHighscore;
	
	public Highscore() {
		this.image = new Image();
		this.highscoreBoard = new HighscoreBoard();
		this.backButton = new LeftButton1();
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
	}
	
	public void render(FetchHighscore fetchHighscore) {
		this.fetchHighscore = fetchHighscore;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image.backgroundImage, 0, 0, Variables.WINDOWWIDTH, Variables.WINDOWHEIGHT, null);
        g2.drawImage(image.highscoreBoard, highscoreBoard.x, highscoreBoard.y, highscoreBoard.width, highscoreBoard.height, null);
        g2.drawImage(image.previousButton, backButton.x, backButton.y, backButton.width, backButton.height, null);
        
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Impact", Font.PLAIN, 15));
        
        for(int i = 0; i < 5; i++) {
        	g2.drawString(fetchHighscore.data[i][0].toString().toUpperCase(), 180, i * 55 + 147);
        	g2.drawString(fetchHighscore.data[i][1].toString().toUpperCase(), 450, i * 55 + 147);
        }
	}
	
	public void handleClick(int x, int y, AudioPlayer buttonClick) {
		if(x > backButton.x && 
		   x < backButton.x + backButton.width && 
		   y > backButton.y && 
		   y < backButton.y + backButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 2;
		}
	}
}
