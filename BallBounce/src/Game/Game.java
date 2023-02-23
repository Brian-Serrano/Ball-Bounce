package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Button.ButtonMouseListener;
import Button.RightButton1;
import Util.AudioPlayer;
import Util.Image;
import Util.SaveResources;
import Util.Variables;


public class Game extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public PaddleMouseAdapter paddleMouseAdapter;
	public Image image;
	public Ball ball;
	public Paddle paddle;
	public EnemyPaddle enemyPaddle;
	public RightButton1 pauseButton;
	public SaveResources saveResources;
	public AudioPlayer paddleImpact;
	
	public Game() {
		this.image = new Image();
		this.ball = new Ball();
		this.paddle = new Paddle();
		this.enemyPaddle = new EnemyPaddle();
		this.pauseButton = new RightButton1();
		this.saveResources = new SaveResources();
		this.paddleImpact = new AudioPlayer("assets/Audio/paddle_impact.wav");
		this.paddleMouseAdapter = new PaddleMouseAdapter(paddle.x);
		this.addMouseMotionListener(paddleMouseAdapter);
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
	}

	public void update() {
		ball.update();
		paddle.update(paddleMouseAdapter.x);
		enemyPaddle.update(ball);
		collision();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image.backgroundImage, 0, 0, Variables.WINDOWWIDTH, Variables.WINDOWHEIGHT, null);
		ball.draw(g2);
        paddle.draw(g2, image);
        enemyPaddle.draw(g2, image);
        g2.setFont(new Font("Impact", Font.BOLD, 30));
        g2.setColor(new Color(179, 89, 0));
    	g2.drawString("Score: " + Variables.score, 50, 50);
    	g2.setColor(new Color(255, 128, 0));
    	g2.drawString("Score: " + Variables.score, 50 - 3, 50 - 1);
    	g2.drawImage(image.pauseButton, pauseButton.x, pauseButton.y, pauseButton.width, pauseButton.height, null);
        g2.dispose();
	}

	public void collision() {
    	boolean ballXCollidesWithPaddle = (ball.x + ball.width > paddle.x &&
                ball.x < paddle.x + paddle.width);
    	boolean ballYCollidesWithPaddle = (ball.y + ball.height > paddle.y &&
                ball.y < paddle.y + paddle.height);
    	boolean ballXCollidesWithEnemyPaddle = (ball.x + ball.width > enemyPaddle.x &&
                ball.x < enemyPaddle.x + enemyPaddle.width);
    	boolean ballYCollidesWithEnemyPaddle = (ball.y + ball.height > enemyPaddle.y &&
                ball.y < enemyPaddle.y + enemyPaddle.height);
    	if (ballXCollidesWithPaddle && ballYCollidesWithPaddle) {
    		paddleImpact.pause();
    		paddleImpact.setTime(0);
    		paddleImpact.play();
    	    Variables.score++;
    	    ball.velocityY = -ball.velocityY;
    	}
    	if (!ballXCollidesWithPaddle && ballYCollidesWithPaddle) {
    		if(Variables.highscore < Variables.score) {
    			Variables.highscore = Variables.score;
    		}
    		saveResources.saveResources();
    		Variables.selectedPanel = 2;
    	}
    	if (ballXCollidesWithEnemyPaddle && ballYCollidesWithEnemyPaddle) {
    		paddleImpact.pause();
    		paddleImpact.setTime(0);
    		paddleImpact.play();
    		ball.velocityY = -ball.velocityY;
    	}
    }
    
    public void resetGame() {
    	Variables.score = 0;
    	paddle.x = (Variables.WINDOWWIDTH - paddle.width) / 2;
    	enemyPaddle.x = (Variables.WINDOWWIDTH - enemyPaddle.width) / 2;
    	enemyPaddle.random = (int) (Math.random() * 10);
    	ball.x = (Variables.WINDOWWIDTH - ball.width) / 2;
    	ball.y = 50;
    	ball.directionX = (int) Math.round(Math.random());
    	paddleMouseAdapter.x = paddle.x;
    }
    
    public void handleClick(int x, int y, AudioPlayer buttonClick) {
		if(x > pauseButton.x && 
           x < pauseButton.x + pauseButton.width && 
           y > pauseButton.y && 
           y < pauseButton.y + pauseButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 5;
		}
	}
}
