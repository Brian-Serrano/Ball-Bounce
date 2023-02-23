package Button;

import Util.Variables;

public class MiddleRightButton1 {
	public int x, y, width, height;
	
	public MiddleRightButton1(int move) {
		this.width = 50;
		this.height = 50;
		this.x = ((Variables.WINDOWWIDTH - this.width) / 2) + 100;
		this.y = ((Variables.WINDOWHEIGHT - this.height) / 2) + move;
	}
}
