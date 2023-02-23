package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import Util.Variables;

public class Ball {
	public int x, y, width, height, directionX, velocityY;
	public int[] velocityX = {-8, 8};
	public Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PINK, Color.MAGENTA, Color.ORANGE, Color.CYAN};

	public Ball() {
		this.width = 25;
		this.height = 25;
		this.x = (Variables.WINDOWWIDTH - this.width) / 2;
		this.y = 50;
		this.directionX = (int) Math.round(Math.random());
		this.velocityY = 8;
	}

	public void update() {
		x += velocityX[directionX];
		y += velocityY;
		
		if (x + width > Variables.WINDOWWIDTH || x < 0) {
			velocityX[directionX] = -velocityX[directionX];
		}
		
		if (y + height > Variables.WINDOWHEIGHT || y < 0) {
			velocityY = -velocityY;
		}
	}

	public void draw(Graphics2D g2) {
		g2.setColor(colors[Variables.selectedColor]);
	    g2.fillOval(x, y, width, height);
	}
}
