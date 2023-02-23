package Button;

import Util.Variables;

public class HighscoreBoard {
	public int x, y, width, height;
	
	public HighscoreBoard() {
		this.width = Variables.WINDOWHEIGHT;
		this.height = Variables.WINDOWHEIGHT;
		this.x = (Variables.WINDOWWIDTH - this.width) / 2;
		this.y = 0;
	}
}
