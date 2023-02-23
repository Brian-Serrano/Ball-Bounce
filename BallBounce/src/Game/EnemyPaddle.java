package Game;

import java.awt.Graphics2D;

import Util.Image;
import Util.Variables;

public class EnemyPaddle {
	public int x, y, width, height, random;
	
	public EnemyPaddle() {
		this.random = (int) (Math.random() * 10);
		this.width = 90;
		this.height = 30;
		this.x = (Variables.WINDOWWIDTH - width) / 2;
		this.y = 20;
	}
	public void update(Ball ball) {
		this.x = ball.x - ((this.width - ball.width) / 2);
		if (this.x < 0) {
            this.x = 0;
        }
		if (this.x + this.width > Variables.WINDOWWIDTH) {
            this.x = Variables.WINDOWWIDTH - this.width;
        }
	}
	public void draw(Graphics2D g2, Image image) {
	    g2.drawImage(image.paddleImage.get(random), x, y, width, height, null);
	}
}