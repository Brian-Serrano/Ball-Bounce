package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Button.ButtonMouseListener;
import Button.RightButton1;
import Button.RightButton2;
import Button.RightButton3;
import Database.SyncData;
import Button.PlayButton;
import Button.RightBottomButton1;
import Util.AudioPlayer;
import Util.Image;
import Util.ToastMessage;
import Util.Variables;

public class Menu extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public PlayButton startButton;
	public RightButton1 settingsButton;
	public Image image;
	public RightButton2 highscoreButton;
	public RightButton3 signButton;
	public RightBottomButton1 syncButton;
	public SyncData syncData;
	
	public Menu() {
		this.image = new Image();
		this.startButton = new PlayButton();
		this.settingsButton = new RightButton1();
		this.highscoreButton = new RightButton2();
		this.signButton = new RightButton3();
		this.syncButton = new RightBottomButton1();
		this.syncData = new SyncData();
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image.backgroundImage, 0, 0, Variables.WINDOWWIDTH, Variables.WINDOWHEIGHT, null);
        g2.drawImage(image.playButton, startButton.x, startButton.y, startButton.width, startButton.height, null);
        g2.drawImage(image.settingsButton, settingsButton.x, settingsButton.y, settingsButton.width, settingsButton.height, null);
        g2.drawImage(image.highscoreButton, highscoreButton.x, highscoreButton.y, highscoreButton.width, highscoreButton.height, null);
        g2.drawImage(image.signButton, signButton.x, signButton.y, signButton.width, signButton.height, null);
        g2.drawImage(image.syncButton, syncButton.x, syncButton.y, syncButton.width, syncButton.height, null);
        
        g2.setFont(new Font("Impact", Font.BOLD, 50));
        String game = "BALL BOUNCE";
        FontMetrics fontMetrics = g2.getFontMetrics();
        int gameX = (Variables.WINDOWWIDTH - fontMetrics.stringWidth(game)) / 2;
    	int gameY = 80;
        g2.setColor(new Color(179, 89, 0));
    	g2.drawString(game, gameX, gameY);
    	g2.setColor(new Color(255, 128, 0));
    	g2.drawString(game, gameX - 3, gameY - 1);
    	
    	g2.setFont(new Font("Impact", Font.PLAIN, 25));
    	g2.setColor(new Color(128, 96, 0));
    	g2.drawString(Variables.username.toUpperCase(), 10, Variables.WINDOWHEIGHT - 15);
    	g2.drawString("HIGHSCORE: " + Variables.highscore, 10, Variables.WINDOWHEIGHT - 40);
    	g2.setColor(new Color(204, 153, 0));
    	g2.drawString(Variables.username.toUpperCase(), 10 - 3, Variables.WINDOWHEIGHT - 15 - 1);
    	g2.drawString("HIGHSCORE: " + Variables.highscore, 10 - 3, Variables.WINDOWHEIGHT - 40 - 1);
	}
	
	public void handleClick(int x, int y, AudioPlayer buttonClick) {
		if(x > startButton.x && 
           x < startButton.x + startButton.width && 
           y > startButton.y && 
           y < startButton.y + startButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 1;
		}
		if(x > settingsButton.x && 
		   x < settingsButton.x + settingsButton.width && 
		   y > settingsButton.y && 
		   y < settingsButton.y + settingsButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 3;
		}
		if(x > highscoreButton.x && 
		   x < highscoreButton.x + highscoreButton.width && 
		   y > highscoreButton.y && 
		   y < highscoreButton.y + highscoreButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 4;
		}
		if(x > signButton.x && 
		   x < signButton.x + signButton.width && 
		   y > signButton.y && 
		   y < signButton.y + signButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			if(!Variables.isLoggedIn) {
				Variables.selectedPanel = 6;
			} else {
				ToastMessage.showToastMessage("You already logged in", 2000);
			}
			
		}
		if(x > syncButton.x && 
		   x < syncButton.x + syncButton.width && 
		   y > syncButton.y && 
		   y < syncButton.y + syncButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			if(Variables.isLoggedIn) {
				syncData.syncData();
			} else {
				ToastMessage.showToastMessage("You did not logged in", 2000);
			}
		}
	}
}
