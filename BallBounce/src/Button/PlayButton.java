package Button;

import Util.Variables;

public class PlayButton {
	public int x, y, width, height;
	
	public PlayButton(){
		this.width = 200;
		this.height = 200;
		this.x = (Variables.WINDOWWIDTH - this.width) / 2;
		this.y = (Variables.WINDOWHEIGHT - this.height) / 2;
	}
}
