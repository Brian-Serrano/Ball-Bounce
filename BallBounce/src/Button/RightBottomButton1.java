package Button;

import Util.Variables;

public class RightBottomButton1 {
	public int x, y, width, height;
	
	public RightBottomButton1(){
		this.width = 50;
		this.height = 50;
		this.x = Variables.WINDOWWIDTH - this.width - 10;
		this.y = Variables.WINDOWHEIGHT - this.height - 10;
	}
}
