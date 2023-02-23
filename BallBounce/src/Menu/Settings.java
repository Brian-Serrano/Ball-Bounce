package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Button.LeftButton1;
import Button.ButtonMouseListener;
import Button.MiddleRightButton1;
import Button.MiddleLeftButton1;
import Game.Ball;
import Util.AudioPlayer;
import Util.Image;
import Util.SaveResources;
import Util.Variables;

public class Settings extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public Image image;
	public LeftButton1 backButton;
	public MiddleLeftButton1 previous1, previous2;
	public MiddleRightButton1 next1, next2;
	public Ball ball;
	public SaveResources saveResources;
	public int ballX, ballY, paddleX, paddleY, ballWidth, ballHeight, paddleWidth, paddleHeight;
	
	public Settings() {
		this.backButton = new LeftButton1();
		this.previous1 = new MiddleLeftButton1(-50);
		this.previous2 = new MiddleLeftButton1(150);
		this.next1 = new MiddleRightButton1(-50);
		this.next2 = new MiddleRightButton1(150);
		this.saveResources = new SaveResources();
		this.image = new Image();
		this.ball = new Ball();
		this.ballWidth = 50;
		this.ballHeight = 50;
		this.paddleWidth = 50 * 2;
		this.paddleHeight = (50 * 2) / 3;
		this.ballX = (Variables.WINDOWWIDTH - this.ballWidth) / 2;
		this.ballY = (Variables.WINDOWHEIGHT - this.ballHeight) / 2;
		this.paddleX = (Variables.WINDOWWIDTH - this.paddleWidth) / 2;
		this.paddleY = (Variables.WINDOWHEIGHT - this.paddleHeight) / 2;
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image.backgroundImage, 0, 0, Variables.WINDOWWIDTH, Variables.WINDOWHEIGHT, null);
        g2.drawImage(image.previousButton, backButton.x, backButton.y, backButton.width, backButton.height, null);
        g2.drawImage(image.previousButton, previous1.x, previous1.y, previous1.width, previous1.height, null);
        g2.drawImage(image.previousButton, previous2.x, previous2.y, previous2.width, previous2.height, null);
        g2.drawImage(image.nextButton, next1.x, next1.y, next1.width, next1.height, null);
        g2.drawImage(image.nextButton, next2.x, next2.y, next2.width, next2.height, null);

        g2.setFont(new Font("Impact", Font.BOLD, 30));
        String settings = "SETTINGS";
        String text1 = "SELECT PADDLE";
        String text2 = "SELECT BALL COLOR";
    	FontMetrics fontMetrics = g2.getFontMetrics();
    	int settingsX = (Variables.WINDOWWIDTH - fontMetrics.stringWidth(settings)) / 2;
    	int settingsY = 50;
        int text1X = (Variables.WINDOWWIDTH - fontMetrics.stringWidth(text1)) / 2;
        int text1Y = (Variables.WINDOWHEIGHT - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        int text2X = (Variables.WINDOWWIDTH - fontMetrics.stringWidth(text2)) / 2;
        int text2Y = (Variables.WINDOWHEIGHT - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        g2.setColor(new Color(179, 89, 0));
        g2.drawString(settings, settingsX, settingsY);
    	g2.drawString(text1, text1X, text1Y - 120);
    	g2.drawString(text2, text2X, text2Y + 80);
    	
    	g2.setColor(new Color(255, 128, 0));
        g2.drawString(settings, settingsX - 3, settingsY - 1);
    	g2.drawString(text1, text1X - 3, text1Y - 120 - 1);
    	g2.drawString(text2, text2X - 3, text2Y + 80 - 1);
        
        g2.setColor(ball.colors[Variables.selectedColor]);
	    g2.fillOval(ballX, ballY + 150, ballWidth, ballHeight);
	    
	    g2.drawImage(image.paddleImage.get(Variables.selectedPaddle), paddleX, paddleY - 50, paddleWidth, paddleHeight, null);
	}
	
	public void handleClick(int x, int y, AudioPlayer buttonClick) {
		if(x > backButton.x && 
		   x < backButton.x + backButton.width && 
		   y > backButton.y && 
		   y < backButton.y + backButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			saveResources.saveResources();
			Variables.selectedPanel = 2;
		}
		if(x > previous1.x && 
		   x < previous1.x + previous1.width && 
		   y > previous1.y && 
		   y < previous1.y + previous1.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPaddle--;
            if (Variables.selectedPaddle < 0) {
            	Variables.selectedPaddle = image.paddleImage.size() - 1;
            }
		}
		if(x > previous2.x && 
		   x < previous2.x + previous2.width && 
		   y > previous2.y && 
		   y < previous2.y + previous2.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedColor--;
            if (Variables.selectedColor < 0) {
            	Variables.selectedColor = ball.colors.length - 1;
            }
		}
		if(x > next1.x && 
		   x < next1.x + next1.width && 
		   y > next1.y && 
		   y < next1.y + next1.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPaddle++;
            if (Variables.selectedPaddle >= image.paddleImage.size()) {
            	Variables.selectedPaddle = 0;
            }
		}
		if(x > next2.x && 
		   x < next2.x + next2.width && 
		   y > next2.y && 
		   y < next2.y + next2.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedColor++;
            if (Variables.selectedColor >= ball.colors.length) {
            	Variables.selectedColor = 0;
            }
		}
	}
}
