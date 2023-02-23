package Game;

import java.awt.Graphics2D;

import Util.Image;
import Util.Variables;

public class Paddle {
	public int x, y, width, height;
	
	public Paddle() {
		this.width = 90;
		this.height = 30;
		this.x = (Variables.WINDOWWIDTH - width) / 2;
		this.y = Variables.WINDOWHEIGHT - 50;
	}

	public void update(int x) {
		this.x = x - (this.width / 2);
		if (this.x < 0) {
            this.x = 0;
        }
		if (this.x + this.width > Variables.WINDOWWIDTH) {
            this.x = Variables.WINDOWWIDTH - this.width;
        }
	}
	public void draw(Graphics2D g2, Image image) {
	    g2.drawImage(image.paddleImage.get(Variables.selectedPaddle), x, y, width, height, null);
	}
}
