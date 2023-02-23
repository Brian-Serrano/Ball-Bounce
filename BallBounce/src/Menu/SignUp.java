package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Button.LeftButton1;
import Button.ButtonMouseListener;
import Button.BottomButton1;
import Button.Input;
import Button.RightButton1;
import Database.SignUpSave;
import Util.AudioPlayer;
import Util.Image;
import Util.Variables;

public class SignUp extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public Input username;
	public Input email;
	public Input password;
	public LeftButton1 backButton;
	public RightButton1 loginButton;
	public Image image;
	public BottomButton1 confirmButton;
	public SignUpSave signUpSave;
	
	public SignUp() {
		this.image = new Image();
		this.username = new Input("ENTER USERNAME", -100);
		this.email = new Input("ENTER EMAIL", 0);
		this.password = new Input("ENTER PASSWORD", 100);
		this.backButton = new LeftButton1();
		this.loginButton = new RightButton1();
		this.confirmButton = new BottomButton1();
		this.signUpSave = new SignUpSave();
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
		this.setLayout(null);
		this.add(username);
		this.add(email);
		this.add(password);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image.backgroundImage, 0, 0, Variables.WINDOWWIDTH, Variables.WINDOWHEIGHT, null);
		g2.drawImage(image.previousButton, backButton.x, backButton.y, backButton.width, backButton.height, null);
		g2.drawImage(image.nextButton, loginButton.x, loginButton.y, loginButton.width, loginButton.height, null);
		g2.drawImage(image.confirmButton, confirmButton.x, confirmButton.y, confirmButton.width, confirmButton.height, null);
		
		g2.setFont(new Font("Impact", Font.BOLD, 50));
        String signUp = "SIGN UP";
        FontMetrics fontMetrics = g2.getFontMetrics();
        int signX = (Variables.WINDOWWIDTH - fontMetrics.stringWidth(signUp)) / 2;
    	int signY = 80;
        g2.setColor(new Color(179, 89, 0));
    	g2.drawString(signUp, signX, signY);
    	g2.setColor(new Color(255, 128, 0));
    	g2.drawString(signUp, signX - 3, signY - 1);
	}
	
	public void handleClick(int x, int y, AudioPlayer buttonClick) {
		if(x > backButton.x && 
           x < backButton.x + backButton.width && 
           y > backButton.y && 
           y < backButton.y + backButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 2;
		}
		if(x > loginButton.x && 
		   x < loginButton.x + loginButton.width && 
		   y > loginButton.y && 
		   y < loginButton.y + loginButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 7;
		}
		if(x > confirmButton.x && 
		   x < confirmButton.x + confirmButton.width && 
		   y > confirmButton.y && 
		   y < confirmButton.y + confirmButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			signUpSave.signUpSave(username.getText(), email.getText(), password.getText());
		}
	}
}
