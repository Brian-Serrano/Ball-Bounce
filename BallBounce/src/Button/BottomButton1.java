package Button;

import Util.Variables;

public class BottomButton1 {
	public int x, y, width, height;
	
	public BottomButton1(){
		this.width = 50;
		this.height = 50;
		this.x = (Variables.WINDOWWIDTH - this.width) / 2;
		this.y = Variables.WINDOWHEIGHT - this.height - 30;
	}
}
